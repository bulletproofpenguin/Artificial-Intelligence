/*  word file format =  wordname partOfSpeech tense numberOfAssociations association1 strength1 assoication2 strength2 ..... */ 
package file_management;


import java.io.*;
import java.lang.*;
import data_structures.DirectedGraph;
import data_objects.*;
import java.util.StringTokenizer;


public class WordFileManager extends FileManager
{
	/* public data */
	public static final String DIRECTORY = "Vocabulary";
	public static final String SEPARATOR_CHARACTER = "&";
	/* private data */
	private Student student;
	private File[] files;
	private int mapCount;
	/* constructors */
	public WordFileManager( Student student )
	{
		/* point to this directory */
		super(DIRECTORY+"/"+student.getName());
		this.student = student;
		/* create a directory for the student [with Name] */
		String dir = MAIN_DIRECTORY+DIRECTORY+"/"+student.getName();
		(new File(dir)).mkdir();
		mapCount = 0;	
	}	
	/* public methods */
	public void save( DirectedGraph graph)
	{
		/* increment the mapCount */
		mapCount++;
		/* create the directory for the graph */
		String mapDir = MAIN_DIRECTORY+DIRECTORY+"/"+student.getName()+"/"+((Integer)mapCount).toString();
		(new File(mapDir)).mkdir();

		/* temporary buffers */

		try
		{
			for( int i = 0; i < graph.size(); i++)
			{
				String buffer = new String("");
				/* add the word name */
				buffer += graph.nodeAt(i).getWord().toString() + SEPARATOR_CHARACTER;
				
				/* add the partOfSpeech */
				buffer += graph.nodeAt(i).getWord().getPartOfSpeech() + SEPARATOR_CHARACTER;

				/* add boolean isPossessive */
				buffer += graph.nodeAt(i).getWord().getPossessive() + SEPARATOR_CHARACTER;
	
				
				/* add the number of associations */
				int associationCount = graph.nodeAt(i).getAssociationCount();
				buffer += ((Integer)associationCount).toString() + SEPARATOR_CHARACTER;


				/* add the associations */
				if( associationCount > 0)
				{
					for(int j = 0; j < associationCount; j++ )
					{
						buffer += graph.nodeAt(i).getAssociation(j).getTarget().getWord().toString() + 		
							SEPARATOR_CHARACTER;
						int intBuffer = graph.nodeAt(i).getAssociation(j).getStrength();
						buffer += ((Integer)intBuffer).toString() + SEPARATOR_CHARACTER;
					}
				}
				/* if the string contains a control character // do nothing */
				boolean isWord = true;
				for( int j = 0; j < graph.nodeAt(i).getWord().toString().length(); j++)
				{
					for(int k = 0; k < 26; k++)
					{
						if( graph.nodeAt(i).getWord().toString().codePointAt(j) == k)
						{ isWord = false; }
					}	
				}
				/* create a word file from the graph */
				if( isWord)
				{
					WordFile wordFile = new WordFile( student, mapCount , graph.nodeAt(i).getWord().toString(), buffer );
				}
					
				
			}

		}catch(Exception e) { System.out.println("IO Error: "+e); }
	}

	public DirectedGraph loadGraph(int directoryIndex)
	{
		/* create buffers */
		byte[] buffer = new byte[50000];
		FileManager temp = new FileManager(DIRECTORY+"/"+student.getName()+"/"+((Integer)directoryIndex).toString());
		 
		Node[] nodes = new Node[temp.getFileCount()];
		try
		{
			if( temp.getFileCount() > 0)
			{
				for( int i = 0; i < temp.getFileCount(); i++)
				{
					/* create a FileInputStream */
					FileInputStream fin = new FileInputStream(temp.getFile(i));
					/* read the data stream into a temporary buffer */
					fin.read(buffer);
				
					/* transfer to string */
					String fileData = new String(buffer);
					/* extract the elements from the data */

					/* skip the timestamp at the beginning of the file */
					int separatorIndex = fileData.indexOf(SEPARATOR_TOKEN);	 	
					fileData = fileData.substring( separatorIndex+1, fileData.length() );

					separatorIndex = fileData.indexOf(SEPARATOR_TOKEN);	 	
					String wordString = new String( fileData.substring(0, separatorIndex) );
					fileData = fileData.substring( separatorIndex+1, fileData.length() );
					
					separatorIndex = fileData.indexOf(SEPARATOR_TOKEN);	 	
					String partOfSpeech = new String( fileData.substring(0, separatorIndex) );
					fileData = fileData.substring( separatorIndex+1, fileData.length() );

					separatorIndex = fileData.indexOf(SEPARATOR_TOKEN);	 	
					boolean possessive = fileData.substring(0, separatorIndex).equals("true");
					fileData = fileData.substring( separatorIndex+1, fileData.length() );

					/* get rid of the number of associations part */
					separatorIndex = fileData.indexOf(SEPARATOR_TOKEN);	 	
					String associationCountString = new String( fileData.substring(0, separatorIndex) );
					int associationCount = Integer.parseInt(associationCountString);
					fileData = fileData.substring( separatorIndex+1, fileData.length() );
						
	
					Word word = new Word(wordString);
					word.setPartOfSpeech(partOfSpeech);
					if(possessive) { word.setPossessive(); }
					Node node = new Node(word);
					
					/* add the associations to the node */
					if( associationCount > 0)
					{
						for(int j = 0; j < associationCount; j++)
						{
				
							/* extract the association name */	
							separatorIndex = fileData.indexOf(SEPARATOR_TOKEN);	 	
							String associationName = new String( fileData.substring(0, separatorIndex) );
							fileData = fileData.substring( separatorIndex+1, fileData.length() );

							/* extract the associationStrength */
							separatorIndex = fileData.indexOf(SEPARATOR_TOKEN);	 	
							String associationStrengthString = new String( fileData.substring(0, separatorIndex) );
							int associationStrength = Integer.parseInt(associationStrengthString);
							fileData = fileData.substring( separatorIndex+1, fileData.length() );

     					 		Word targetWord = new Word(associationName);
					 		Node targetNode = new Node(targetWord);
							Association assoc = new Association(targetNode);
							assoc.setStrength(associationStrength);
							node.addAssociation(assoc);
						}
					}
					/* add the node to the nodes array */
					nodes[i] = node;
				}
			}
			else
			{
				System.out.println("No files to load");
			}

		} catch(Exception e) { System.out.println("IO Error: "+e); }

		/* create the graph from the node data */
		DirectedGraph result = new DirectedGraph(nodes);
		return result;
	}
	

	/* private methods */
}
