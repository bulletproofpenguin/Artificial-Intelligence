package data_structures;


import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import data_objects.*;

public class PhraseBank
{
	/* public data */

	/* private data */
	private ArrayList<Phrase> phrases;
	private ArrayList<Phrase> nonPhrases;
	/* constructors */
	public PhraseBank(ArrayList<Phrase> phrases)
	{
		this.phrases = phrases;
	}
	/* public methods */
	public void add(Phrase phrase)
	{
		phrases.add(phrase);
	}
	public void remove(Phrase phrase)
	{
		phrases.remove(phrase);
		nonPhrases.add(phrase);
	}
	public int size()
	{
		return phrases.size();
	}
	public Phrase getPhrase( Phrase phrase)
	{
		return phrases.get( phrases.indexOf(phrase) );
	}
	public boolean isNonPhrase(Phrase phrase)
	{
		return nonPhrases.contains(phrase);
	}
	public Phrase search( Phrase phrase )
	{
		Word[] words = new Word[2];
		words[0] = new Word("Not");
		words[1] = new Word("Found");
		Phrase result = new Phrase(words);
		/* use lexicographic equality */
		for( int i = 0; i < phrases.size(); i++)
		{
			if( phrases.get(i).toString().equals( phrase.toString() ) )
			{
				result = phrases.get(i); 
			}
		}
		return result;
	}
	public String toString()
	{
		String result = "";
		result += "----------PHRASE BANK----------\n";
		for(int i = 0; i < phrases.size(); i++)
		{
			result += "Type of Phrase: " + phrases.get(i).getTypeOfPhrase() + "\n"; 
			result += phrases.get(i) + "\n";
		}
		return result;
	}
	/* private methods */

}
