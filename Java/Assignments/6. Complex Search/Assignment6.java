import java.io.*;
import java.util.*;

public class Assignment6
   {	public static void main(String args[]) throws IOException
	   {	Trie Root=new Trie();	Assignment6 X=new Assignment6();
		//arg[0] should contain the filename of the file which stores address of all input files
		Scanner inp = new Scanner(new FileReader(args[0]));
		String St;	int i=1;
		
		//Loop to insert each Inverted Index File in the Trie.
		while(inp.hasNext()) Root.Insert(inp.nextLine());
		
		Scanner in = new Scanner(System.in);
		//loop to provide user results of queries
		//This loop does not terminates unless user inputs' an integer
		while(i!=0)
		  { System.out.print("\nEnter the word you want to search. Enter 0 (integer) to Exit: ");
		    try {in.nextInt(); i=0;}
		    catch(InputMismatchException e)
		     { St = (in.nextLine()).toLowerCase().replaceAll("_", " ");
		       System.out.println(X.search(St));
		     }
		  }
	   }
	   
	public String search(String st)
	   {	
		return st;
	   }
   }		 
