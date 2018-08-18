import java.io.*;
import java.util.*;

// Exception Class to show the Key not Present in Treap
class KeyNotFoundInTreap extends Exception
  { 	private static final long serialVersionUID = -555555555555L;
	public KeyNotFoundInTreap() { super();}
  }

// Exception Class to show the Key already Present in Treap
class KeyAlreadyExistsInTreap extends Exception
  { 	private static final long serialVersionUID = 555555555555L;
	public KeyAlreadyExistsInTreap() { super();}
  }


/*/ Treap is a binary tree data structure where each node contains 3 attributes.
 Priority: To maintain the heap status, Key: To maintain the BST status & Value: The actual value stored
/*/
class Treap
 {	int mKey ;
	int mValue ;
	int mPriority ;
	Treap mLeft = null ;
	Treap mRight = null ;
	
	//Constructor function
	Treap(int K,int P, int V) {mKey=K;mValue=V;mPriority=P;}

	//Binary Search like in BST
	public int Search(int key) throws KeyNotFoundInTreap
	  {	if(key==mKey) return mValue;
		if(key<mKey)
		  if(mLeft==null) throw new KeyNotFoundInTreap();
		  else return mLeft.Search(key);
		else
		  if(mRight==null) throw new KeyNotFoundInTreap();
		  else return mRight.Search(key);
	  }

	//Function to delete a node from the Treap.
	public Treap Delete(int key) throws KeyNotFoundInTreap
	  {	if(key==mKey)
		  if(mLeft==null) return mRight;
		  else if(mRight==null) return mLeft;
		     else if(mLeft.mPriority<mRight.mPriority)
			     { Treap tmp = mLeft; mLeft = tmp.mRight; tmp.mRight = Delete(key); return tmp; }
			     //Half Rotation to bubble down the node to be deleted.
			else { Treap tmp = mRight; mRight = tmp.mLeft; tmp.mLeft = Delete(key); return tmp; }
		else
		  if(key<mKey)
		    if(mLeft==null) throw new KeyNotFoundInTreap();
		    else mLeft=mLeft.Delete(key);
		  else
		    if(mRight==null) throw new KeyNotFoundInTreap();
		    else mRight=mRight.Delete(key);
		  return this;
	  }

	//Function to insert node in the Treap.
	public Treap Insert(int key, int value, int priority) throws KeyAlreadyExistsInTreap
	  {	if(key==mKey) throw new KeyAlreadyExistsInTreap();
		if(key<mKey)
		  {  if(mLeft==null) mLeft = new Treap(key,priority,value);	//Insert at leaf level
		     else mLeft = mLeft.Insert(key,value,priority);
		     if(mLeft.mPriority<mPriority)
		      { Treap tmp = mLeft; mLeft = tmp.mRight; tmp.mRight = this; return tmp; }
		      //Half Rotation to bubble up the node, to maintain the heap property.
		     else return this;
		  }
		else
		  {  if(mRight==null) mRight = new Treap(key,priority,value);	//Insert at leaf level
		     else mRight = mRight.Insert(key,value,priority);
		     if(mRight.mPriority<mPriority)
		      {	Treap tmp = mRight; mRight = tmp.mLeft; tmp.mLeft = this; return tmp; }
		      //Half Rotation to bubble up the node, to maintain the heap property.
		     else return this;
		  }
	  }
	
	//Function to modify the priority of a node.
	public Treap ModifyPriority(int key, int newpriority) throws KeyNotFoundInTreap
	  {	int Val = Search(key);
		try{ if(mLeft==null&&mRight==null&&mKey==key) {mPriority=newpriority; return this;}
		     else return Delete(key).Insert(key,Val,newpriority);}
		catch(KeyAlreadyExistsInTreap e){return this;}
		//Modifying by deleting and then inserting with new priority.
	  }

	//Function to write the tree in a file.
	public void DumpTreap(String filename) throws IOException
	  {	Queue<Treap> q1 = new LinkedList<Treap>();
		Queue<Integer> q2 = new LinkedList<Integer>();
		int i=-1,j=0;	Treap tmp;
		String st1=new String("digraph G {\n"), st2=new String("");
		q1.offer(this); q2.offer(0);

		//Implementatin using Queue to get nodes as in postorder traversal
		while(!q1.isEmpty())
		  { tmp = q1.poll();
		    st1+= ++i + " [ color = \"";
		    switch(q2.poll())
		      {	case 0 : st1+="Black";break;
			case 1 : st1+="Blue";break;
			case 2 : st1+="Red";break;
		      }
		    st1+= "\" , label = \" Key :"+tmp.mKey+" , Value :"+tmp.mValue+" , Prio :"+tmp.mPriority+" \" ];\n";
		    if(tmp.mLeft!=null)
		      {	st2 += i + " -> " + ++j + ";\n"; q1.offer(tmp.mLeft); q2.offer(1); }
		    if(tmp.mRight!=null)
		      {	st2 += i + " -> " + ++j + ";\n"; q1.offer(tmp.mRight); q2.offer(2); }
		  }
		//Writing the output in a file
		PrintWriter fout = new PrintWriter(new FileWriter(filename), true);
		fout.write(st1+st2+"}\n");  fout.close();
	  }
 }
