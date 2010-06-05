/* Precondition: Word array must be the same size as number of elemnts */
package data_objects;


import java.lang.*;
import java.io.*;

public class Phrase 
{

	/* public data */

	/* private data */
	private Word[] phrase;
	private String typeOfPhrase;
	/* constructors */
	public Phrase(Word[] phrase)
	{
		this.phrase = phrase;
		typeOfPhrase = "NULL";
	}
	/* public methods */
	public Word[] getPhrase()
	{
		return phrase;
	}
	public void setTypeOfPhrase(int index)
	{
		switch(index)
		{
			case 1: typeOfPhrase = "predicate"; break;
			case 2: typeOfPhrase = "noun"; break;
			case 3: typeOfPhrase = "adverbial"; break;
			case 4: typeOfPhrase = "prepositional"; break;
			case 5: typeOfPhrase = "adjectival"; break;
			case 6: typeOfPhrase = "participial"; break;
			case 7: typeOfPhrase = "verb"; break;
			case 8: typeOfPhrase = "absolute"; break;
			default: System.out.println("Not a valid type of phrase.");
		}
	}
	public String getTypeOfPhrase()
	{
		return typeOfPhrase;
	}
	public String toString()
	{
		String result = "";
		for(int i = 0; i < phrase.length; i++)
		{
			result += phrase[i] + " ";
		}
		return result;
	}
	/* private methods */

}
