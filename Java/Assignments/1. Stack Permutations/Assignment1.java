/*Using a stack and its functions (push, pop) one can generate the permutations of the given numbers. But not all the permutations can be found by this method. Now given a sequence, write a program which would say whether the given permutation is a stack permutation of the given sequence or not and if it is then what sequence of operations led to its generation.
*/
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;

public class Assignment1
   {	public int n;
	//The global variable n stores value of N as in 'Permutation of a sequence of N numbers' 

	public static void main(String args[]) throws IOException
	  {	Assignment1 SP = new Assignment1();
		
		//Input is obtained using takeinput() function.
		int[] Arr = SP.takeinput();
		
		//Input is Checked for Validity.
		if(SP.checkinput(SP.n,Arr))
		//If input is valid only then Sequence of operations is Calculated using seq_opr(int,int[]) function.
		  { String Str = SP.seq_opr(SP.n,Arr);
		    System.out.println(Str);
		  }
	  }
	
	
	//takeinput() gives user a choice to provide input from file or from console and returns an Array of int.
	//It will give Runtime Error message if input file is missing or if invalid input methods is selected.
	public int[] takeinput() throws IOException
	  {	Scanner inp  = new Scanner(System.in);
		int[] Ar = {};
		System.out.println("\nEnter:\n 1 for standard console input.\n 2 for input from a file.");
		this.n = inp.nextInt();
		
		//This section runs when console is selected for input.
		if(this.n==1)
		  { System.out.print("Enter the size of the array: ");
		    this.n=inp.nextInt();
		    Ar = new int[this.n];
		    System.out.print("Enter the array: ");
		    for(int i=0;i<n;i++) Ar[i]=inp.nextInt();
		  }
		
		//This section runs when input is given using a file.
		else if(this.n==2)
		  { System.out.println("Enter the path/name of the file: ");
		    //User has to provide a valid path and filename, otherwise FileNotFoundException is raised.
		    inp = new Scanner(new FileReader(inp.next()));		//"data.txt"
		    //input file must contain data in a Valid format.
		    n=inp.nextInt();
		    Ar = new int[n];
		    for(int i=0;i<n;i++) Ar[i]=inp.nextInt();
		  }
		  
		else throw new RuntimeException("\n None of Valid input methods is selected.\n Program will now Close.");
		inp.close();
		return Ar;
	  }
	
	
	//checkinput(int,int[]) validates the input against 3-kinds of invalidity as mentioned in the problem statement file.
	public boolean checkinput(int n,int[] A)
	  {	boolean flag = true;
		//for given 'n' permutation must contain n values.
		if(n!=A.length)
		 { System.out.println(" Permutation of 1.."+n+" must have exact "+n+" digits.");
		   flag = false; }
		//for given 'n' permutation array cannot have any value less than 1 or greater than n.
		for(int i=0;i<n;i++)
		 { if(A[i]>n)
		    {System.out.println((i+1)+"th element is greater than "+n); flag = false;}
		   if(A[i]<1)
		    {System.out.println((i+1)+"th element is less than 1."); flag = false;}
		 }
		//no item can come twice(or more) in a permutation.
		for(int j,f,tmp=0,i=1;i<=n;i++)
		   for(j=f=0;j<n;j++)
		     if(i==A[j])
		      { if(f==1) {f=2; flag = false; System.out.print("\n"+i+" repeats at "+tmp+", "+(j+1));}
			else if(f==2) System.out.print(", "+(j+1));
			else {f=1;tmp=j+1;}
		      }
	  	if(!flag) System.out.println("\nInvalid input Error");
	  	return flag;
	  }
	
	
	//seq_opr(int,int[]) calculates the sequence of Operations reqd to generate the stack permutation.
	public String seq_opr(int n,int[] A)
	  {	//tmpStack is the temporary stack.
		Stack tmpStack = new Stack(n);
		//stack is not used for storing 1..n and given sequence because it is redundant and the logic can implemented without it.
		/*String St will store sequence of operation as it is performed.
		  This can be printed if are able to get given array using Stack permutation.*/
		String St = new String(" Operation\t\tStack\n");
		int i=1,j=0;
		while(j<n)
		 { if(i==A[j]) {St=St+"  print()\t\t"+tmpStack.stack2String()+"\n";i++;j++;}
		   else if(!tmpStack.isEmpty()&&A[j]==tmpStack.peek())
			  { tmpStack.pop();
			    St=St+"  pop()\t\t\t"+tmpStack.stack2String()+"\n";
			    j++;
			  }
		   	else if(i>n) return (new String("No, given sequence is not a stack permutation."));
		   	else
		   	  { tmpStack.push(i);
		   	    St=St+"  push("+i+")\t\t"+tmpStack.stack2String()+"\n";
		   	    i++;
		   	  }
		 }
		System.out.println("YES!\nThe Sequence of Operations will be:");
		return St;
	  }
	/* The Algorithm followed is:
		1. we start with 3 stacks: inputstack = [1, 2,...n], tempstack = [] and reqdstack
		2. check 1st element of inputstack if it exist against 1st element of reqdstack
			if equal: show print and pop inputstack & reqdstack then step 5 else: step 3
		3. check 1st element of tempstack if it exist against 1st element of reqdstack
			if equal: show pop and pop tempstack & reqdstack then step 5 else: step 4
		4. if: inputstack is empty then no operation can lead us to get the provided permutation so print 'NO' and exit
			else: show push and push top of inputstack in tempstack and pop tempstack
		5. if: reqdstack is empty we have got stack permutation else: go to step 2
	*/
   }
