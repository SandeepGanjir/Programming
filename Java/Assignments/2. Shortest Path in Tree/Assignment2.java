/*Program to find shortest path in a Binary Tree and its associated Weight.
    e.g. For a given tree output is: Path is 6-4-2-5 and weight is 17".
  Input should be given as: java Assignment2 input.txt
*/
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.IOException;

public class Assignment2
   {	int N;		//N stores the total number of nodes.

	public static void main(String args[]) throws IOException
	   {	Assignment2 SP = new Assignment2();
		int n,Src,Dst;
		Scanner inp  = new Scanner(new FileReader(args[0]));
		
		///////////      File input(and Validity check) Begins Here      ///////////
		 SP.N = Integer.parseInt(inp.nextLine());		//Inputs No. of nodes
		  n=SP.N+1;
		 StringTokenizer st2, st1 = new StringTokenizer(inp.nextLine(),",");
		  Src=(new Integer(st1.nextToken())).intValue();	//Inputs Source
		  Dst=(new Integer(st1.nextToken())).intValue();	//Inputs Destination
		  //Source, Destination  Validity Check:
		  if(Src<1||Src>n)
		    { System.out.println(" Invalid Input!!\tSource - out of Bound"); System.exit(0); }
		  if(Dst<1||Dst>n)
		    { System.out.println(" Invalid Input!!\tDestination - out of Bound"); System.exit(0); }
		    
		 int n0,n1,n2,f=1,P[]=new int[n],W[]=new int[n];
		 BNode[] BTree = new BNode[n];				//Stores Binary Tree
		  BTree[0]=new BNode(0,0,0);				//0th position is used as equivalent of null
		 
		 while(inp.hasNext())
		   { n0 = Integer.parseInt(inp.nextLine());		//Inputs node no.
		     st1 = new StringTokenizer(inp.nextLine().replaceAll("nil", "0").replaceAll("[()]", ""));
	
		     st2 = new StringTokenizer(st1.nextToken(),",");
		     //n1, n2 represents Left, Right Child respectively and W[n1], W[n2] their associated weight.
		     n1 = (new Integer(st2.nextToken())).intValue(); W[n1]= (new Integer(st2.nextToken())).intValue();
		      if(st2.hasMoreTokens()) { System.out.println(" Invalid Input Format"); System.exit(0); }	//Input Format Invalid
		     st2 = new StringTokenizer(st1.nextToken(),",");
		     n2 = (new Integer(st2.nextToken())).intValue(); W[n2]= (new Integer(st2.nextToken())).intValue();
		      if(st2.hasMoreTokens()) { System.out.println(" Invalid Input Format"); System.exit(0); }	//Input Format Invalid

		     //Input Validity Check:
		     if(st1.hasMoreTokens())				//more than 2 child
		       { System.out.println(" Invalid Input!!\n Not a Binary Tree."); System.exit(0); }
		     if(n0<1||n1<0||n2<0||n0>n||n1>n||n2>n)		//Node no. out of Bound
		       { System.out.println(" Invalid Input!!\tNode no. out of Bound"); System.exit(0); }
		     if(BTree[n0]!=null)				//Duplicate nodes
		       { System.out.println(" Invalid Input!!\tCannot have 2 nodes with no. "+n0); System.exit(0); }
		     if(P[n1]!=0&&n1!=0)				//If child already had a Parent
		       { System.out.println(" Invalid Input!!\tNode no. "+n1+" has 2 Parent"); System.exit(0); }
		     if(P[n2]!=0&&n2!=0)				//If child already had a Parent
		       { System.out.println(" Invalid Input!!\tNode no. "+n2+" has 2 Parent"); System.exit(0); }
		     if(W[n1]<0||W[n2]<0)				//Negative Weight check
		       { System.out.println(" Invalid Input!!\tWeight cannot be Negative."); System.exit(0); }

		     BTree[n0]=new BNode(n0,n1,n2); P[n1]=n0;P[n2]=n0;
		   }
		 
		 //Assigning Parent and Weight after nodes has been created.
		 for(n0=1;n0<n;n0++)
		  { BTree[n0].Parent=P[n0]; BTree[n0].weight=W[n0];
		    if(P[n0]==0)					//Forest Check
		      if(f==1) f=0;
		      else { System.out.println(" Invalid Input!!\tMore than 1 root."); System.exit(0); }
		    if(W[n0]==0&&P[n0]!=0)				//0 Weight check
		      { System.out.println(" Invalid Input!!\tWeight cannot be 0 for non-root node."); System.exit(0); }
		  }
		/// Creating a tree is order O(N) operation as there are single loop going from 1-N ///
		 
		 //Validating whether given input has any self loop. This check has O(log(N!))=O(NlogN) time complexity.
		 for(n1=1;n1<n;n1++)
		  { n0=n1;
		    for(n2=n;n2>0&&n0!=0;n2--) n0=BTree[n0].Parent;
		    if(n2==0)
		     { System.out.println(" Invalid Input!!\n Not a Tree. It contains a Self loop.");
		       //Trees belong in the category of Directed Acyclic Graphs i.e. tree has no self loops.
		       System.exit(0);
		     }
		  }
		///////////------------------------------------------------------///////////
		
		
		//Calculating and Printing the shortest path and its weight.
		System.out.println(SP.ShortPath(BTree,Src,Dst));
	   }
	

	//ShortPath() calculates the shortest path to be taken to go from Source to Destination.
	public String ShortPath(BNode[] BTree,int Sc,int Ds)
	   {	Stack tmpStk1 = new Stack(N); Stack tmpStk2 = new Stack(N);	//tmpStk are the temporary stack.
		String St = new String("");
		int LCA=1,Wt=0;							//LCA is Least Common Ancestor
		//Finding ancestors of Source and storing in tmpStk1. This step has O(h) complexity where h is height of tree.
		while(Sc!=1) { tmpStk1.push(Sc); Sc=BTree[Sc].Parent;}
		//Finding ancestors of Destination and storing in tmpStk2. O(h) complexity
		while(Ds!=1) { tmpStk2.push(Ds); Ds=BTree[Ds].Parent;}
		//Removing the Common Ancestors while maintaining LCA. O(h) complexity
		while(tmpStk1.peek()==tmpStk2.peek())
		 { LCA=tmpStk1.pop(); tmpStk2.pop();
		   if(tmpStk1.isEmpty()||tmpStk2.isEmpty()) break;
		 }
		 
		//Calculating the path and the weight. O(h) complexity
		while(!tmpStk1.isEmpty()) {Wt+=BTree[tmpStk1.peek()].weight; St=tmpStk1.pop()+"-"+St;}
		St = " Path is " + St + LCA;
		while(!tmpStk2.isEmpty()) {Wt+=BTree[tmpStk2.peek()].weight; St=St+"-"+tmpStk2.pop();}
		St += " and Weight is " + Wt;
		
		return St;
		/// ShortPath has order O(height of tree) [= O(logN) in general] complexity.
	   }
   }
