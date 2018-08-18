import java.io.IOException;
import java.util.Scanner;

public class Assignment4
   {	Treap Trp;
	
	public static void main(String args[])
	   {	int ch = 0;	Assignment4 TP = new Assignment4();
		
		//This loop is to make sure program does not terminates unless user inputs' 6
		while(ch!=6)
		  {  //command line user interface
		     System.out.println("##########################################################\n Press any number (1-6) to denote your choice :\n1. Insert a node in the treap\n2. Delete a node in the treap\n3. Search a node in the treap\n4. Modify the priority of a node\n5. Output the treap data structure to a file\n6. Exit from this menu\n##########################################################\n");
		
		     try { ch = TP.choice();}
		     catch(Exception e) {System.out.println(" Provide a valid input\n");}
		  }
	   }

	//This function takes user's choice and calls respective function
	public int choice() throws IOException
	   {	Scanner inp = new Scanner(System.in);	int ch = inp.nextInt();
		switch(ch)
		    {	case 1 : insert();break;
			case 2 : delete();break;
			case 3 : search();break;
			case 4 : modify();break;
			case 5 : dump();break;
		    }
		return ch;
	   }
	
	/*/The functions insert(), delete(), search(), modify() & dump() reads inputs from console
	   and calls the respective function of Treap and updates the Trp.
	/*/
	public void insert()
	   {	Scanner in = new Scanner(System.in);	int k, v, p;
		System.out.println(" Enter the key, value and priority with spaces in between :");
		k = in.nextInt();	v = in.nextInt();	p = in.nextInt();
		try { if(Trp==null) Trp = new Treap(k,p,v);
		      else Trp = Trp.Insert(k,v,p); }
		catch(KeyAlreadyExistsInTreap e) {System.out.println(" Given key already present.\n");}
	   }

	public void delete()
	   {	Scanner in = new Scanner(System.in);	int k;
		System.out.print(" Enter the key to be deleted: ");	k = in.nextInt();
		try { if(Trp==null) throw new KeyNotFoundInTreap();
		      else Trp = Trp.Delete(k); }
		catch(KeyNotFoundInTreap e) {System.out.println(" Given key not found.\n");}
	   }
	
	public void search()
	   {	Scanner in = new Scanner(System.in);	int k;
		System.out.print(" Enter the key to search: ");		k = in.nextInt();
		try { if(Trp==null) throw new KeyNotFoundInTreap();
		      else System.out.println("\tValue : "+Trp.Search(k)+"\n"); }
		catch(KeyNotFoundInTreap e) {System.out.println("-1\n Given key not found.\n");}
	   }
	
	public void modify()
	   {	Scanner in = new Scanner(System.in);	int k, np;
		System.out.print(" Enter the key and new priority: ");
		k = in.nextInt();	np = in.nextInt();
		try { if(Trp==null) throw new KeyNotFoundInTreap();
		      else Trp = Trp.ModifyPriority(k,np); }
		catch(KeyNotFoundInTreap e) {System.out.println(" Given key not found.\n");}
	   }
	
	public void dump() throws IOException
	   {	if(Trp==null) System.out.println("NULL Treap\n Nothing to dump\n");
		else 
		  { System.out.print(" Enter the filename: ");
		    Trp.DumpTreap((new Scanner(System.in)).nextLine());
		  }
	   }
   }
