/* Precondition: sentence array MUST be full */

package data_structures;
/* directed graph using array -> basically you skip across a large array of words links are in the associations */

import java.io.*;
import java.lang.*;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;
import data_objects.*;
import utility_objects.*;

public class DirectedGraph
{
	/* public data */
	public static final int MAX_NODES = 20000;
	/* private data */
	private Node[] nodes;
	private Sentence[] initialData;
	private Node currentNode;
	private int nodeCount;
	/* constructors */
	public  DirectedGraph(Sentence[] sentences)
	{
		nodes = new Node[MAX_NODES];
		initialData = sentences;
		nodeCount = 0;
	}
	public  DirectedGraph(Node[] nodes)
	{
		this.nodes = nodes;
		nodeCount = nodes.length;
	}
	/* public methods */

	/* Purpose: goes through the Sentence array and creates node objects with associations */
	public void createGraph()
	{
		/* create node buffers */
		Node newNode, nextNode;
		/* step through sentence array */
		System.out.println("Creating nodes and associations...");
		for(int i = 0; i < initialData.length; i++)
		{
		
			/* step through each word */
			for( int j = 0; j < initialData[i].numWords(); j++)
			{
				
				/* Create a node for a word and the next one in line */
				newNode = new Node (initialData[i].getWord(j) );

				if ( (j+1) < initialData[i].numWords() )
				{				
					/* create an association object between the two nodes */
					nextNode = new Node( initialData[i].getWord(j+1));
					Association association = new Association( nextNode );
					
					/* check to see if the node already exists in our array */
					boolean foundNew = false, foundNext = false;
					int k, l;
					for(k = 0; k < nodeCount; k++)
					{
						if(newNode.getWord().toString().equals( nodes[k].getWord().toString() ) )
						{  foundNew = true;  break; }
					}
					for(l = 0; l < nodeCount && !foundNext; l++)
					{
						if(nextNode.getWord().toString().equals( nodes[l].getWord().toString() ) )
						{  foundNext = true; break; }
					}
					Association oldAssociation = new Association( nodes[l] );
					if( !foundNew)
					{	
						if(!foundNext)
						{
							/* add the association to our node */
							newNode.addAssociation(association);
						}
						else
						{
							/* add the existing node as an association to our new node */
							newNode.addAssociation(oldAssociation);
						}
						/* add the node to our array */
						nodes[nodeCount] = newNode;
						nodeCount++;
					}
					/* add this new association to the existing node */
					else
					{
						if( !foundNext)
						{
							nodes[k].addAssociation(association);
						}
						else
						{
							nodes[k].addAssociation(oldAssociation);
						}	
					}
				} // end if
				else
				{
					/* if the node is not in our array add it with no associations */
					boolean foundNew = false;
					for(int k = 0; k < nodeCount; k++)
					{
						if(newNode.getWord().toString().equals( nodes[k].getWord().toString() ) )
						{  foundNew = true;  break; }
					}
				        if(!foundNew)
					{
						nodes[nodeCount] = newNode;
						nodeCount++;
					}
				}
			} // end inner for loop
	
		} // end outer for loop
	}
	
	/* depth first search traversal visiting every edge */
	/* POST CONDITION: THE GRAPH NO LONGER EXISTS BECAUSE IT REMOVES ALL THE ASSOCIATIONS */
	public ArrayList<Node> traverseAll( Node currentNode, ArrayList<Node> result)
	{
		if( findIndex(currentNode)  > 0 )
		{
			/* find the current node in the graph*/
			currentNode = nodes [ findIndex( currentNode ) ];
		}
		result.add(currentNode);
		if( currentNode.getAssociationCount() > 0 )
		{
			do
			{
				currentNode = nextElement(currentNode);
				traverseAll(currentNode, result);	
			} while( currentNode.getAssociationCount() > 0); // this check must come after we change to the next node
		}

		return result;
	}
	public void insert( DirectedGraph newGraph)
	{
		
		for( int j = 0; j < newGraph.nodeCount; j++)
		{
			/* if the node is not already in our graph add it */
			if( findIndex( newGraph.nodeAt(j) ) == -1)
			{
				nodes[nodeCount] = newGraph.nodeAt(j);
				nodeCount++;
			}
			else
			{
				int index = findIndex( newGraph.nodeAt(j) );
				nodes[index] = merge( nodes[index], newGraph.nodeAt(j) );	
			}
				
		}
	}
	public Node nextElement()
	{
		Node nextElem = currentNode.getNext();
		currentNode.removeAssociation( nextElem );
		return nextElem;
	}
	public Node nextElement(Node node)
	{
		Node nextElem = node.getNext();
		node.removeAssociation( nextElem );
		return nextElem;
	}
	public String toString()
	{
		String result = "---------------------GRAPH-----------------------\n\n";

		for( int i = 0; i < nodeCount; i++)
		{
			result += (nodes[i]).toString();
			result += "\n";
		}
		result += "Nodes: "+nodeCount+"\n\n";
		return result;
	}

	public Node nodeAt( int index)
	{
		return nodes[index];
	}
	
	public int size()
	{
		return nodeCount;
	}

	public Node[] getNodes()
	{
		return nodes;
	}
	
	public int findIndex( Node node)
	{
		int i;
		if( nodeCount > 0)
		{
			for(i = 0; i < nodeCount; i++)
			{
				if( node.getWord().toString().equals( nodes[i].getWord().toString() ))
				{ return i; }
			}
		}	
		return -1;
	}
	/* private methods */

	/* merge the data of two nodes together */
	private Node merge( Node initialNode, Node newNode)
	{
		Node result = new Node(initialNode.getWord());
		/* in order to ensure no duplicate associations */
		TreeSet<Association> list = new TreeSet<Association>();
		if( initialNode.getAssociationCount() > 0)
		{
			for(int i  = 0; i < initialNode.getAssociationCount(); i++)
			{
				list.add( initialNode.getAssociation(i) );
			}
		}
		/* add the old associations */
		for ( Iterator<Association> iter = list.iterator(); iter.hasNext(); ) 
		{
      			result.addAssociation( iter.next() );
		}
		if( newNode.getAssociationCount() > 0)
		{
			for(int i  = 0; i < newNode.getAssociationCount(); i++)
			{
				if(! (list.add( newNode.getAssociation(i) ) ) )
				{
					/* increase the strength of the association */
					int newAssociationStrength = newNode.getAssociation(i).getStrength();
					int oldAssociationStrength = initialNode.getAssociation( newNode.getAssociation(i).getTarget() ).getStrength();
					newAssociationStrength += oldAssociationStrength;
					result.getAssociation( newNode.getAssociation(i).getTarget()).setStrength(newAssociationStrength);
				}
			}
		}
		for ( Iterator<Association> iter = list.iterator(); iter.hasNext(); ) 
		{
      			result.addAssociation( iter.next() );
		}	
		return result;		
	}

}
