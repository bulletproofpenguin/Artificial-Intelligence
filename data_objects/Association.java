package data_objects;

import java.lang.*;
import java.io.*;
import java.util.Collections;
public class Association implements Comparable
{
	/* public data */

	/* private data */
	private Node target;
	private int strength;
	/* constructors */
	public Association(Node target)
	{
		this.target = target;
		strength = 1;
	}
	/* public methods */
	public Node getTarget()
	{
		return target;
	}
	public void strengthen()
	{
		strength++;
	}
	public void setStrength(int strength)
	{
		this.strength = strength;
	}
	public int getStrength()
	{
		return strength;
	}
	public void weaken()
	{
		if( strength > 0)
		{
			strength--;
		}
		else
		{
			System.out.println("Could not weaken association. Strength = 0.");
		}
	}
	public int compareTo( Object otherAssociation) throws ClassCastException
	{
		if( ! (otherAssociation instanceof Association))
			{ throw new ClassCastException("Expect Association object."); }
		
		if( this.getStrength() > ((Association)otherAssociation).getStrength() )
		{
			return 1;
		}
		else if( ((Association)otherAssociation).getStrength() > this.getStrength() )
		{
			return -1;
		}
		else
		{
			return 0;
		}
		
	}
	/* private methods */




}
