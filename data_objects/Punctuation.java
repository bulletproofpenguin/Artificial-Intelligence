package data_objects;

import java.io.*;
import java.lang.*;



public class Punctuation
{
	/* symbol enumeration */
	public enum Symbol { 
		PERIOD ("."), 
		QUESTION_MARK ("?") , 
		EXCLAMATION_POINT ("!");

		private final String text;
		Symbol(String text)
		{	this.text = text; }
		public String text()
		{	return text; }
	}
	/* private data */
	private String text;
	/* constructors */
	public Punctuation(Symbol symbol)
	{
		text =  symbol.text();
		
	}
	public Punctuation(String text)
	{
		this.text = text;
		
	}
	/* public methods */
	public String toString()
	{
		return text;
	}

}
