/* POST CONDITION: removes all associations from the graph given [need to save graph before parsing] */

package utility_objects;


import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;
import java.lang.*;
import data_structures.*;
import data_objects.*;
public class PhraseParser
{
	/* public data */
	public static int MAX_PHRASE_SIZE = 7; 
	/* private data */
	private DirectedGraph graph;
	private PhraseBank phraseBank;
	private ArrayList<Phrase> phrases;
	/* constructors */
	public PhraseParser(DirectedGraph graph)
	{
		this.graph = graph;
		phrases = new ArrayList<Phrase>();
	}
	/* public methods */
	public PhraseBank parse()
	{
		/* get the graph as an ArrayList of nodes in order */
		Node startNode = graph.nodeAt(1);	
		ArrayList<Node> nodeGraph = new ArrayList<Node>();
		nodeGraph = graph.traverseAll(startNode, nodeGraph);
		
		System.out.println("creating phrase bank....");

		for( int base = 0; base < nodeGraph.size() - MAX_PHRASE_SIZE; base++)
		{
			int increment = base;
			do	
			{
				Word[] words = new Word[MAX_PHRASE_SIZE - (increment - base)];
				int word_index = 0; 
				/* create the possible phrase */
				for(int j = increment; j < MAX_PHRASE_SIZE + base; j++ )
				{
					Word word = new Word( (nodeGraph.get(j)).getWord().toString() );
					String pos = (nodeGraph.get(j)).getWord().getPartOfSpeech();
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
					words[word_index] = word;
					word_index++;
				}
				/* create the phrase_object */
				
				Phrase phrase = new Phrase(words);
				
				/* check to see if is a grammatically correct phrase */
				if( isNounPhrase( phrase) )
				{ 
					phrase.setTypeOfPhrase(2);
					phrases.add( phrase);
				}
				if( isVerbPhrase( phrase) )
				{ 
					phrase.setTypeOfPhrase(7);
					phrases.add( phrase);
				}
				if( isPrepositionalPhrase( phrase) )
				{ 
					phrase.setTypeOfPhrase(4);
					phrases.add( phrase);
				}

				increment++;
			} while( increment < (base + MAX_PHRASE_SIZE) );
		}
		System.out.println("SIZE: "+ phrases.size());
		return (new PhraseBank(phrases));

	}
	public boolean isNounPhrase(Phrase phrase)
	{
		/* two dimensional array modeling all possibilities for a noun phrase */
		String[][] variations = new String[11][3];
		
		/* fill the 2-dimensional array with the appropriate POS tags */

		/* column 1 */
		variations[0][0] = "DT";
		variations[1][0] = "AFX";
		variations[2][0] = "PDT";
		variations[3][0] = "CD";

		/* column 3 */
		
		variations[0][1] = "JJ";
		variations[1][1] = "JJR";
		variations[2][1] = "JJS";
		variations[3][1] = "NN";
		variations[4][1] = "NNPS";
		variations[5][1] = "NNP";
		variations[6][1] = "NNS";
		variations[7][1] = "CD";
		variations[8][1] = "CC";
		variations[9][1] = "DT";
		variations[10][1] = "PRP$";
		
		/* column 3 */
		variations[0][2] = "NN";
		variations[1][2] = "NNPS";
		variations[2][2] = "NNP";
		variations[3][2] = "NNS";

		/* compare first word POS to first column */
		boolean found = false;
		for( int i = 0; i < 4; i++ )
		{
			if((phrase.getPhrase())[0].getPartOfSpeech().equals(variations[i][0]))
			{
				found = true;
			}
		}
		if(!found)
		{ return false; }

		/* check the middle words if they exist */
		if( phrase.getPhrase().length > 2)
		{
			boolean[] check = new boolean[ (phrase.getPhrase()).length -2];
			/* preset them to false */
			for(int i = 0; i < check.length; i++)		
			{ check[i] = false; }
				/* compare middle words to middle column */
			for(int i = 1; i < (phrase.getPhrase()).length - 1; i++)
			{
				for(int j = 0; j < 11; j++)
				{
					if( (phrase.getPhrase())[i].getPartOfSpeech().equals( variations[j][1] ))
					{	check[i-1] = true; }

				}
			}	
			/* check the boolean array for any falsities */
			for(int i = 0; i < check.length; i++)
			{
				if(! (check[i]) )
				{ return false; }
			}
		}

		found = false;	
		/* check the last word for a noun */
		for(int i = 0; i < 4; i++)
		{
			if( (phrase.getPhrase())[ (phrase.getPhrase()).length - 1].getPartOfSpeech().equals( variations[i][2] ) )
			{	found = true; }
		}	
		return found;
	}
	public boolean isVerbPhrase(Phrase phrase)
	{
		String[][] variations = new String[11][3];
		if( phrase.getPhrase().length != 3)
		{
			return false;
		}
		else
		{

			/* create the first column */
			variations[0][0] = "VB";
			variations[1][0] = "VBP";
			variations[2][0] = "VBZ";
			variations[3][0] = "VBD";
			variations[4][0] = "MD";

			/* create the second column */
			variations[0][1] = "VBD";
			variations[1][1] = "VBG";
			variations[2][1] = "VBN";
			variations[3][1] = "PRP$";
			variations[4][1] = "JJ";
			variations[5][1] = "NNS";
			variations[6][1] = "NN";
			variations[7][1] = "NNPS";
			variations[8][1] = "VB";
			variations[9][1] = "JJR";
			variations[10][1] = "JJS";

			/* create the third column */
			variations[0][2] = "NNS";
			variations[1][2] = "NN";
			variations[2][2] = "VBN";
			variations[3][2] = "NNPS";
			variations[4][2] = "NNS";
			variations[5][2] = "JJ";
			variations[6][2] = "PRP";

			/* compare first word POS to first column */
			boolean found = false;
			for( int i = 0; i < 5; i++ )
			{
				if((phrase.getPhrase())[0].getPartOfSpeech().equals(variations[i][0]))
				{
					found = true;
				}
			}
			if(!found)
			{ return false; }


			/* compare second word POS to second column */
			found = false;
			for( int i = 0; i < 11; i++ )
			{
				if((phrase.getPhrase())[1].getPartOfSpeech().equals(variations[i][1]))
				{
					found = true;
				}
			}
			if(!found)
			{ return false; }

			/* compare third word POS to second column */
			found = false;
			for( int i = 0; i < 7; i++ )
			{
				if((phrase.getPhrase())[2].getPartOfSpeech().equals(variations[i][2]))
				{
					found = true;
				}
			}
			if(!found)
			{ return false; }

			return found;
			}
		}

		public boolean isPrepositionalPhrase(Phrase phrase)
		{
			String[][] variations = new String[17][3];

			/* make sure it is atleast two words */
			if(phrase.getPhrase().length < 2 )
			{
				return false;
			}
			else
			{

				/* construct first column */
				variations[0][0] = "IN";

				/* construct second column */
				variations[0][1] = "AFX";
				variations[1][1] = "CC";
				variations[2][1] = "CD";
				variations[3][1] = "DT";
				variations[4][1] = "NN";
				variations[5][1] = "JJ";
				variations[6][1] = "JJR";
				variations[7][1] = "JJS";
				variations[8][1] = "NND";
				variations[9][1] = "NNPS";
				variations[10][1] = "NNS";
				variations[11][1] = "PDT";
				variations[12][1] = "PRP$";
				variations[13][1] = "PRP";
				variations[14][1] = "WP";
				variations[15][1] = "TO";
				variations[16][1] = "WDT";

				/* construct third column */
				variations[0][2] = "NN";		
				variations[1][2] = "NND";
				variations[2][2] = "NNPS";
				variations[3][2] = "NNS";

				/* compare the first word to the first column */
				if(! ((phrase.getPhrase())[0].getPartOfSpeech().equals(variations[0][0])))
				{ return false; }
	
				/* compare the middle words to the middle column */
				boolean[] check = new boolean[ (phrase.getPhrase()).length -2];
				/* preset them to false */
				for(int i = 0; i < check.length; i++)		
				{ check[i] = false; }

				for(int i = 1; i < (phrase.getPhrase()).length - 1; i++)
				{
					for(int j = 0; j < 17; j++)
					{
						if( (phrase.getPhrase())[i].getPartOfSpeech().equals( variations[j][1] ))
						{	check[i-1] = true; }

					}
				}	
				/* check the boolean array for any falsities */
				for(int i = 0; i < check.length; i++)
				{
					if(! (check[i]) )
					{ return false; }
				}


				/* check the last word in the phrase */
				boolean found = false;
				for( int i = 0; i < 4; i++ )
				{
					if((phrase.getPhrase())[phrase.getPhrase().length-1].getPartOfSpeech().equals(variations[i][2]))
					{
						found = true;
					}
				}
				return found;

		}

	}
	/* private methods */


}
