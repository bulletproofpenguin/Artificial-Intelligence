/* node to DirectedGraph encapsulating Word object */

package data_objects;

import java.io.*;
import java.lang.*;
import java.util.Random;

public class Node
{
	/* public data */	
	public static final int MAX_ASSOCIATIONS = 3000;
	/* private data */
	private Association[] associations;
	private int associationCount;
	private Word word;
	private Node nextNode;
		
	/* constructors */
	public Node(Word word)
	{
		associations = new Association[MAX_ASSOCIATIONS];
		associationCount = 0;
		this.word = word;
	}
	/* public methods */
	public Word getWord()
	{
		return word;
	}
	public int getAssociationCount()
	{
		return associationCount;
	}
	public Association getAssociation(Node targetNode)
	{
		Word target = new Word("error");
		Node temp = new Node(target);
		Association result = new Association(temp);
		if( associationCount > 0)
		{
			for(int i = 0; i < associationCount; i++)
			{
				if( associations[i].getTarget().getWord().toString().equals( targetNode.getWord().toString() ) )
				{
					result = associations[i];
				}
			}
		}
		else
		{
			System.out.println("Node has no known associations.");
		}
		return result;
	}
	public Association getAssociation(int index)
	{
		Word target = new Word("error");
		Node temp = new Node(target);
		Association result = new Association(temp);
		if( associationCount > 0)
		{
			return associations[index];
		}
		else
		{
			System.out.println("Node has no known associations.");
		}
		return result;
	}
	public void addAssociation(Association association)
	{
		boolean found = false;
		if( this.associationCount > 0 )
		{
			for(int i = 0; i < this.associationCount; i++)
			{
				if( association.getTarget().getWord().toString().equals( associations[i].getTarget().getWord().toString() ) )
				{   
					/* it already exists */
					found = true;    
					/* strengthen it */
					associations[i].strengthen();
					break;
				}
			}
		}
		if( !found && associationCount < MAX_ASSOCIATIONS )
		{
			associations[associationCount] = association;
			associationCount++;
		}
		
	}
	public void removeAssociation(Node node)
	{
		if( associationCount < 1 )
		{
			System.out.println("No associations to remove.");
		}
		else
		{
			for(int i = 0; i < associationCount; i++)
			{
				if( associations[i].getTarget().getWord().toString().equals( node.getWord().toString() ) )
				{
					System.arraycopy(associations, i+1,associations, i, associationCount - (i+1) );
					associationCount--;
					break;
				}
				
			}
		}
	}

	/* uses a random number generator to produce a random association based on strength */
	public Node getNext()
	{
		Word word = new Word("|end|");
		Node nextNode = new Node(word);
		if(associationCount > 0)
		{
			Association nextAssociation = chooseRandom();
			nextNode = nextAssociation.getTarget();
		}
		else
		{
			System.out.println(" There is no next. Thats it.");
		}
		return nextNode;
	}
	public String toString()
	{
		String result = "----------"+getWord().toString()+"----------\n";
		
		if(associationCount > 0)
		{
			for(int i = 0; i < associationCount; i++)
			{
				result += "Association: " + associations[i].getTarget().getWord().toString() + "    Strength: " + 
							    associations[i].getStrength() + "\n";
			}		
		}
		return result;
	}
	
	/* private methods */
	
	/* choose a random next node to go to based on the strengths */
	private Association chooseRandom()
	{
		/* create buffers */
		int sumStrengths = 0;
		Association result = new Association( associations[0].getTarget() );
		/* get the total of all the association strengths */
		for( int i = 0; i < associationCount; i++)
		{	
			sumStrengths += associations[i].getStrength();	
		}
		
		/* choose a random number from zero to this number ( not including this number ) */
		Random randGen = new Random();
		int random = randGen.nextInt(sumStrengths+1); // from 0 to sumStrengths - 1
		random++; // from 1 to sumStrengths

		int last_number = 0;
		for( int i = 0; i < associationCount; i++)
		{
			
			/* dealing with one association */
			if( random > last_number && random < last_number + associations[i].getStrength() )
			{
				result = associations[i];
				break;
			}
			last_number += associations[i].getStrength();
		}
		return result;
	} 



}

