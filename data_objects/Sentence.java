package data_objects;
/* Precondition: words array must be full */


import java.io.*;
import java.lang.*;
public class Sentence
{
	/* public data */
	
	/* private data */
	private int numWords;
	private Word[] words;
	private Punctuation punctuation;
	/* constructors */
	public Sentence(Word[] words, Punctuation punctuation) //words array must be full
	{
		this.numWords = words.length;
		this.words = words;
		this.punctuation = punctuation;
	}
	public Sentence(int size)
	{
		this.numWords = 0;
		words = new Word[size];
	}
	/* public methods */
	public int numWords()
	{
		return numWords;
	}
	public Punctuation getPunctuation()
	{
		return punctuation;
	}
	public void addPunctuation(Punctuation punctuation)
	{
		this.punctuation = punctuation;
	}
	public int[] getIndex(Word word)
	{
		int[] result = new int[numWords+1];
		int index = 0;
		for(int i = 0; i < numWords; i++)
		{
			if( word.toString().equals( words[i].toString() ) )
			{
				result[index] = i;
				index++;
			}
		}
		/* add a special marker to signal end of indices */
		result[index] = -1;
		return result;
	}
	public Word getWord(Word word)
	{
		Word result = new Word("not found");
		for(int i = 0; i < numWords; i++)
		{
			if( word.toString().equals( words[i].toString() ) )
			{
				result =  words[i];
			}
		}
		return result;		
	}
	public Word getWord(int index)
	{
		Word result = new Word("error");
		if( index < numWords)
		{
			result = words[index];		
		}
		else
		{
			System.out.println("No words are in this sentence.");
		}	
		return result;
	}	
	public void addWord(Word word)
	{
		if(words.length == numWords)
		{
			System.out.println("Error: cannot addWord. words array is full!");
		}
		else
		{
			words[numWords] = word;
			numWords++;
		}
	}
	public void removeWord(int index)
	{
		if( numWords > 0)
		{
			System.arraycopy(words, index+1, words, index, numWords - (index+1) );
			numWords--;
		}
		else
		{
			System.out.println("removeWord failed: no words to remove");
		}
	}

	public String toString()
	{
		String result = "";
		for( int i = 0; i < numWords; i++)
		{
			result += words[i];
			if( i != (numWords-1) )
			{
				result += " ";
			}
		}
		result += punctuation.toString();
		return result;	
	}
	/* private methods */


}
