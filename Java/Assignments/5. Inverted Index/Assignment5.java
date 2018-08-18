import java.io.*;
import java.util.*;

public class Assignment5
   {	public static void main(String args[]) throws IOException
	   {	Trie Root = new Trie();
		//arg[0] should contain the filename of the file which stores address of all input files
		Scanner inp = new Scanner(new FileReader(args[0]));
		String St,s1;	int i=1;
		
		//Loop to insert each file in the Trie and write the Inverted Index in a file.
		while(inp.hasNext())
		  {  s1 = inp.nextLine();
		     St = Root.Insert(s1);
		     St = s1 +"\n* for \'Anchor\' words\n\n"+ St;//Appending file name
		     String[] part = s1.split("\\.");
		     
		     //Writing the output in a file
		     PrintWriter fout = new PrintWriter(new FileWriter(part[0]+"_ii.txt"), true);
		     fout.write(St);	fout.close();
		  }

		Scanner in = new Scanner(System.in);
		//loop to provide user results of queries
		//This loop does not terminates unless user inputs' an integer
		while(i!=0)
		  { System.out.print("\nEnter the word you want to search. Enter 0 (integer) to Exit: ");
		    try {in.nextInt(); i=0;}
		    catch(InputMismatchException e) { System.out.println(Root.Search(in.next()));}
		  }
	   }
   }		 
