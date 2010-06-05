package utility_objects;


import java.util.StringTokenizer;
import data_structures.*;
import data_objects.*;
import java.io.*;
import java.lang.*;

public class POSParser
{
	/* public data */

	/* private data */
	private DirectedGraph result;
	private File file;
	/* constructors */
	public POSParser( DirectedGraph graph, File file)
	{
		result = graph;
		this.file = file;
	}
	/* public methods */
	public DirectedGraph parse()
	{
		/* add the parts of speech */
		addPOS();
		/* update all node to include parts of speech */
		System.out.println("Parsing file for parts of speech analysis...");

		byte[] buffer = new byte[100000]; // 100 kb
		try
		{
			/* retrieve the data from the file */
			FileInputStream fin = new FileInputStream(file);
			fin.read(buffer);
			fin.close();
		} catch(Exception e) { System.out.println("IO Error: "+e); }
		
		/* transfer data to a string */
		String data = new String(buffer);

		/* create a tokenizer to parse the data */
		StringTokenizer st = new StringTokenizer(data, " :;\"\n\t\r_,.!?`\u2015\u2012\u2014\u2013\u2212"); //unicode for dash
		
		/* temporary variables */		
		String pos = "";
		String wordString = "";
		Word word = new Word("");
		Node node = new Node(word);
		while( st.hasMoreTokens() )
		{
			/* take care of extraneous hiphens */
			String test = st.nextToken();
			while( test.equals("-") )
			{ test = st.nextToken(); }
			while( test.equals("--") )
			{ test = st.nextToken(); }
			
			/* put the string to lowercase */
			wordString = test;				
			wordString = wordString.toLowerCase();

			/* get the POS */
			if( st.hasMoreTokens())
			{ pos = st.nextToken(); }
			/* if we have the possessive case */
			if(wordString.equals("'s"))
			{
				/* create a node object from the previous iteration */
				Word possessiveWord = new Word( word.toString() + "'s");
				node = new Node(possessiveWord);
				/* get the position of the node in the graph */
				int index = result.findIndex( node );
				/* add the possessive node to the graph */
				if( index >= 0 )
				{	
					/* make sure we get all the associations */
					node = result.nodeAt(index);
					/* transfer the part of speech */
					node.getWord().setPartOfSpeech( word.getPartOfSpeech());
					/* add the possessive quality */
					node.getWord().setPossessive();
					/* insert the node into our array at the right position */
					(result.getNodes())[index] = node;
				}

			}			
			else
			{			
				word = new Word(wordString);
				/* lots of if statements */
				if( pos.equals("AFX") )
				{ word.setPartOfSpeech(1);}
				else if( pos.equals("CC") )
				{ word.setPartOfSpeech(2);}
				else if( pos.equals("CD") )
				{ word.setPartOfSpeech(3);}
				else if( pos.equals("DT") )
				{ word.setPartOfSpeech(4);}
				else if( pos.equals("EX") )
				{ word.setPartOfSpeech(5);}
				else if( pos.equals("FW") )
				{ word.setPartOfSpeech(6);}
				else if( pos.equals("IN") )
				{ word.setPartOfSpeech(7);}
				else if( pos.equals("JJ") )
				{ word.setPartOfSpeech(8);}
				else if( pos.equals("JJR") )
				{ word.setPartOfSpeech(9);}
				else if( pos.equals("JJS") )
				{ word.setPartOfSpeech(10);}
				else if( pos.equals("LS") )
				{ word.setPartOfSpeech(11);}
				else if( pos.equals("MD") )
				{ word.setPartOfSpeech(12);}
				else if( pos.equals("NN") )
				{ word.setPartOfSpeech(13);}
				else if( pos.equals("NNP") )
				{ word.setPartOfSpeech(14);}
				else if( pos.equals("NNPS") )
				{ word.setPartOfSpeech(15);}
				else if( pos.equals("NNS") )
				{ word.setPartOfSpeech(16);}
				else if( pos.equals("PDT") )
				{ word.setPartOfSpeech(17);}
				else if( pos.equals("POS") )
				{ word.setPartOfSpeech(18);}
				else if( pos.equals("PRP") )
				{ word.setPartOfSpeech(19);}
				else if( pos.equals("PRP$") )
				{ word.setPartOfSpeech(20);}
				else if( pos.equals("RB") )
				{ word.setPartOfSpeech(21);}
				else if( pos.equals("RBR") )
				{ word.setPartOfSpeech(22);}
				else if( pos.equals("RBS") )
				{ word.setPartOfSpeech(23);}
				else if( pos.equals("RP") )
				{ word.setPartOfSpeech(24);}
				else if( pos.equals("SYM") )
				{ word.setPartOfSpeech(25);}
				else if( pos.equals("TO") )
				{ word.setPartOfSpeech(26);}
				else if( pos.equals("UH") )
				{ word.setPartOfSpeech(27);}
				else if( pos.equals("VB") )
				{ word.setPartOfSpeech(28);}
				else if( pos.equals("VBD") )
				{ word.setPartOfSpeech(29);}
				else if( pos.equals("VBG") )
				{ word.setPartOfSpeech(30);}
				else if( pos.equals("VBN") )
				{ word.setPartOfSpeech(31);}
				else if( pos.equals("VBP") )
				{ word.setPartOfSpeech(32);}
				else if( pos.equals("VBZ") )
				{ word.setPartOfSpeech(33);}
				else if( pos.equals("WDT") )
				{ word.setPartOfSpeech(34);}
				else if( pos.equals("WP") )
				{ word.setPartOfSpeech(35);}
				else if( pos.equals("WPS") )
				{ word.setPartOfSpeech(36);}
				else if( pos.equals("WRB") )
				{ word.setPartOfSpeech(37);}
			
				node = new Node(word);

				int index = result.findIndex( node );
				if( index >= 0 )
				{	
					/* make sure we get all the associations */
					node = result.nodeAt(index);
					/* transfer the part of speech */
					node.getWord().setPartOfSpeech( word.getPartOfSpeech());
					/* insert the node into our array at the right position */
						(result.getNodes())[index] = node;
				}
			}
		} // end while
		return result;

	}

	/* sets the parts of speech for each node in the graph */
	public void addPOS()
	{
		System.out.println("Running POS tagger...");
		/* parameters for exec command */
		File dir = new File("/home/josh/Desktop/Artificial_Intelligence/POS_Tagger");
		/* where we are placing the file to parse */
		File result = new File(file.toString()+".pos");
		int i = 1;
		String s = null, data = "";  
		try {
			/* command to run the POS tagger */
			String command = "java -mx300m -classpath stanford-postagger.jar edu.stanford.nlp.tagger.maxent.MaxentTagger -model models/"+
			"bidirectional-distsim-wsj-0-18.tagger -textFile " + file.toString(); 

			/* run the process */           
			Process p = Runtime.getRuntime().exec(command,null,dir);
             		i = p.waitFor();

            		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            		/* extract the data from the command */
            		
            		while ((s = stdInput.readLine()) != null) 
			{
               		 	/* copy the data into one string */
				data += s;
           		}
			/* place the data in a .pos text file */
			byte[] buffer = data.getBytes();
			FileOutputStream fout = new FileOutputStream(result, false); 
			fout.write(buffer);
			fout.close();
			
            
            		/* read any errors from the attempted command */
            		while ((s = stdError.readLine()) != null) 
			{
                		System.out.println(s);
            		}

		} catch(Exception e){
		 	System.out.println("Exception happened - here's what I know: ");
           	 	e.printStackTrace();
           		System.exit(-1);
		} 
		/* wait for the above process to complete */
		while( i == 1);
		/* update our file to include the POS tags */
		this.file = result;
	}

	/* private methods */

}
