package utility_objects;

import java.io.*;
import java.lang.*;
import java.util.StringTokenizer;
import data_objects.*;

public class TextParser
{
	/* public data */
	public static final int MAX_WORDS = 300; //per sentence
	/* private data */
	private Sentence[] formattedData;
	private String data, punctuation;
	/* constructors */
	public TextParser()
	{
	}

	/* public methods */
	public Sentence[] parse(String data)
	{
		this.data = data;
		this.data.trim();
		String sentence, word;
		formattedData = new Sentence[ numSentences() ];
		
		int wordIndex = 0, sentenceIndex = 0;
		do
		{
			sentence = nextSentence();
			Word[] words = new Word[numWords(sentence)];
			String[] temp = parseWords(sentence);

			for(int i = 0; i < words.length; i++)
			{
				words[i] = new Word(temp[i]);
			}
			
			Punctuation punct = new Punctuation(punctuation);
			formattedData[sentenceIndex] = new Sentence(words, punct);
			sentenceIndex++;
		  
		} while ( sentenceIndex < formattedData.length );

		return formattedData;
	}
	/* private methods */
	private String nextSentence()
	{
		String result;
		int periodIndex, questionMarkIndex, exclamationIndex;
		periodIndex = data.indexOf('.');
		questionMarkIndex = data.indexOf('?');
		exclamationIndex = data.indexOf('!');
		if( periodIndex == -1)
		{ periodIndex = 10000; }	 
		if( questionMarkIndex == -1)
		{ questionMarkIndex = 10000; }
		if( exclamationIndex == -1)
		{ exclamationIndex = 10000; }	

		if( periodIndex < questionMarkIndex && periodIndex < exclamationIndex)
		{
			result = new String(data.substring(0, periodIndex) );
			data = data.substring( periodIndex+1, data.length() );
			punctuation = ".";
			return result;
		}
		else if( questionMarkIndex < periodIndex && questionMarkIndex < exclamationIndex)
		{
			result = new String(data.substring(0, questionMarkIndex) );
			data = data.substring( questionMarkIndex+1, data.length() );
			punctuation = "?";
			return result;
		}
		else if( exclamationIndex < questionMarkIndex && exclamationIndex < periodIndex)
		{
			result = new String(data.substring(0, exclamationIndex) );
			data = data.substring( exclamationIndex+1, data.length() );
			punctuation = "!";
			return result;
		}
		else
		{
			/* nothing to parse the string of words with [no punctuation] */
			return data;
		}
		 
	}
	private String[] parseWords(String sentence)
	{
		/* create buffers */
		String[] result = new String[MAX_WORDS];
		int index = 0;

		StringTokenizer st = new StringTokenizer(sentence, " ");
		while (st.hasMoreTokens()) {
		
     		result[index] = st.nextToken();
		
		/* remove extraneous punctuation */		
		result[index] = removeExtraPunctuation(result[index]);
		
		/* take away case sensitivity */
		result[index] = result[index].toLowerCase();		

		index++;
		}
		
		
		return result;
	}
	private int numWords(String sentence)
	{
		int result = 0;
		StringTokenizer st = new StringTokenizer(sentence, " ");
		while (st.hasMoreTokens()) {
     		st.nextToken();
		result++;
		}
		return result;
	}
	private int numSentences()
	{
		int result = 0;
		StringTokenizer st = new StringTokenizer(data, ".?!");
		while (st.hasMoreTokens()) {
     		st.nextToken();
		result++;
		}
		return result;
	}
	private String removeExtraPunctuation( String str)
	{
		String result = str;
		StringTokenizer st = new StringTokenizer(str, "/\";:][,()\n\t\r&`\u2015\u2012\u2014\u2013\u2212");
		while(st.hasMoreTokens())
		{
			result = st.nextToken();
		}
		return result;
	}


}
