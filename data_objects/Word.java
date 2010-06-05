package data_objects;

import java.io.*;
import java.lang.*;

public class Word
{
	/* public data */
	
	/* private data */
	private String text;
	private String partOfSpeech;
	private boolean isPossessive;
	/* constructors */
	public Word(String text)
	{
		this.text = text;
		partOfSpeech = "NULL";
		isPossessive = false;
	}
	/* public methods */
	public String toString()
	{
		return text;
	}
	public int length()
	{
		return text.length();
	}
	public void setPartOfSpeech(int index)
	{

		switch (index)
		{
			case 1: partOfSpeech = "AFX"; break; //affixes anti- pro-
			case 2: partOfSpeech = "CC"; break; //coordinating conjunction
			case 3: partOfSpeech = "CD"; break; //cardinal number
			case 4: partOfSpeech = "DT"; break; //determiner (a, an, the, either, neither, some, each...)
			case 5: partOfSpeech = "EX"; break; //existential there
			case 6: partOfSpeech = "FW"; break; //this is for foreign words (non-english)
			case 7: partOfSpeech = "IN"; break; //preposition or subordinating conjunction
			case 8: partOfSpeech = "JJ"; break; //adjective
			case 9: partOfSpeech = "JJR"; break; //adjective - comparative
			case 10: partOfSpeech = "JJS"; break; //adjective - superlative
			case 11: partOfSpeech = "LS"; break; //list item ( (A) (b) (i) (ii) 
			case 12: partOfSpeech = "MD"; break; //modal verb
			case 13: partOfSpeech = "NN"; break; //noun / singular / common
			case 14: partOfSpeech = "NNP"; break; //noun singular proper
			case 15: partOfSpeech = "NNPS"; break; //noun plural proper
			case 16: partOfSpeech = "NNS"; break; // noun plural common
			case 17: partOfSpeech = "PDT"; break; //predeterminer ( All, half rather)
			case 18: partOfSpeech = "POS"; break; // possessive
			case 19: partOfSpeech = "PRP"; break; // personal pronoun
			case 20: partOfSpeech = "PRP$"; break; // personal possessive pronoun
			case 21: partOfSpeech = "RB"; break; // adverb
			case 22: partOfSpeech = "RBR"; break; // comparative adverb
			case 23: partOfSpeech = "RBS"; break; // superlative adverb
			case 24: partOfSpeech = "RP"; break; // particle ???
			case 25: partOfSpeech = "SYM"; break; // a symbol like + or *
			case 26: partOfSpeech = "TO"; break; // the word "to"
			case 27: partOfSpeech = "UH"; break; //interjection
			case 28: partOfSpeech = "VB"; break; // verb, base form (This tag subsumes imperatives, infinitives, and subjunctives)
			case 29: partOfSpeech = "VBD"; break; //verb past tense
			case 30: partOfSpeech = "VBG"; break; // verb, present participle (infinitive, gerund, regular verb, adjective)
			case 31: partOfSpeech = "VBN"; break; //verb, past participle ending in -ed or other irregular verbs
			case 32: partOfSpeech = "VBP"; break; //Verb, present tense, not 3rd person singular
			case 33: partOfSpeech = "VBZ"; break; //Verb, present tense, 3rd person singular
			case 34: partOfSpeech = "WDT"; break; //w-determiner (which and that)
			case 35: partOfSpeech = "WP"; break; //wh-pronoun (what, who, and whom)
			case 36: partOfSpeech = "WP$"; break; //whose
			case 37: partOfSpeech = "WRB"; break; //wh-adverb (where, how, why)  
			default: System.out.println("Invalid Part of Speech Choice!"); break;
		}
		
	}
	public void setPartOfSpeech(String str)
	{
		partOfSpeech = str;
	}
	public String getPartOfSpeech()
	{
		return partOfSpeech;
	}
	public void setPossessive()
	{
		isPossessive = true;
	}
	public boolean getPossessive()
	{
		return isPossessive;
	}
	
	
	/* private methods */

}
