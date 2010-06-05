package data_objects;

import java.io.*;
import java.lang.*;
import data_structures.*;

public class Student
{
	/* public data */
	public Sentence response;
	public static final int MAX_MAPS = 100;
	/* private data */
	private String name;
	private long studentID;
	private PhraseBank phraseBank;
	/* constructors */
	public Student(String name, long studentID)
	{
		/* initialize private data members */
		this.name = name;
		this.studentID = studentID;
	}
	/* public methods */
	public String getName()
	{
		return name;
	}
	public long getID()
	{
		return studentID;
	}
	public void setResponse(Sentence response)
	{
		this.response = response;
	}
	public Sentence getResponse()
	{
		return response;
	}
	public PhraseBank getPhraseBank()
	{
		return phraseBank;
	}
	/* private methods */


}
