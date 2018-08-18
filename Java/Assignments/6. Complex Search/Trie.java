import java.io.*;
import java.util.*;

////********Linked list of type int********////
class llint
   {	int val;	llint next;
	llint(int j) { val = j; next = null;}
	void dump(int[] Ar,int i) {Ar[i]=val; if(next!=null) next.dump(Ar,i+1);}
	
	//function to insert value at the end of linked list
	public void Insert(int v)
	  {  if(next!=null) next.Insert(v);
	     else next=new llint(v);
	  }
	
	public int[] toArray(int size)
	  { int Arr[] = new int[size];
	    dump(Arr,0);
	    return Arr;
	  }
   }


////********Node for storing a Word********////
class Wnode
   {	int Doc_id, freq;	llint pos;	Wnode next;
	//Doc_id stores the id of the file corresponding to which the word is stored
	//freq stores the frequency and pos stores the positions of occurences of word
	//next stores occurence information of the word in different file (stored in order of priority)
	
	Wnode(int id) {Doc_id=id; next=null; freq=0; pos=null;}

	public Wnode Insert(int id)
	  {  if(id!=Doc_id)
		if(next==null) return (new Wnode(id));
		else return next.Insert(id);
	     else return this;
	  }
	  
	public Wnode Reorder()
	  { if(next==null) return this;
	    else
	      {	Wnode tmp=next.Reorder();
		if(freq<tmp.freq)
		  { next=tmp.next; tmp.next=this; return tmp;}
		else { next=tmp; return this;}
	      }
	  }
   }


////--------Trie for storing Inverted Index--------////
class Trie
   {	Wnode loc;
	Trie[] down = new Trie[26];
	//loc stores the location of word which is formed by path of traversal from root
	Trie() { loc=null;}	//Constructor fn.

	int index = -1;
	ArrayList<String> Ar = new ArrayList<String>();
	public int AddFile(String st) { index++; Ar.add(st); return index;}
	
	//this maps a character to its corresponding array index
	int map(char ch)
	  {  if(ch>96&&ch<123) return (ch-97);
	     else if(ch>64&&ch<91) return (ch-65);
	     else return -1;
	  }
	  
	//this function is used to maintian order of relevance of documents corresponding to a word
	void Reorder(String St)
	  {  try{ if(St.charAt(0)=='*') {if(loc!=null) loc=loc.Reorder();}
		  else {Trie tmp=down[map(St.charAt(0))]; if(tmp!=null) tmp.Reorder(St.substring(1));}
		}
	     catch(StringIndexOutOfBoundsException e) {if(loc!=null) loc=loc.Reorder();}
	     catch(ArrayIndexOutOfBoundsException e) {if(loc!=null) loc=loc.Reorder();}
	  }
	  
	//Returns inserts location for word in the trie
	Wnode insert(int Id, String St)
	  {  try{ if(St.charAt(0)=='*')
		    if(loc==null) return (new Wnode(Id));
		    else return loc.Insert(Id);
		  else { Trie tmp=down[map(St.charAt(0))];
			 if(tmp==null) down[map(St.charAt(0))] =new Trie();
			 return tmp.insert(Id,St.substring(1));
			}
		}
	     catch(Exception e)
		{ if(loc==null) return (new Wnode(Id));
		    else return loc.Insert(Id); }
	  }
	  
	//Function to find a word from the trie
	public Wnode Find(String St)
	  {  try{ if(St.charAt(0)=='*') return loc;
		  else { Trie tmp=down[map(St.charAt(0))];
			 if(tmp==null) return null;
			 else return tmp.Find(St.substring(1));
			}
		}
	     catch(StringIndexOutOfBoundsException e) {return loc;}
	     catch(ArrayIndexOutOfBoundsException e) {return loc;}
	  }
	
	//Function which inserts a file into the trie
	public void Insert(String File) throws FileNotFoundException
	  {  Scanner in,inp = new Scanner(new FileReader(File));
	     int i=0, Fid=AddFile(File);
	     String S1;		Wnode tmp;
	     
	     while(inp.hasNext())
		{   in = new Scanner(inp.nextLine());
		    in.useDelimiter("[,\\s]+");
		    
		    try
		      {	S1 = in.next();
			tmp = insert(Fid,S1);	i=0;
			while(in.hasNext())
			 { if(tmp.pos==null) tmp.pos=new llint(in.nextInt());
			   else tmp.pos.Insert(in.nextInt());
			   i++;
			 }
			tmp.freq = i;	Reorder(S1);
		      }
		    catch(Exception e) {}
		}
	  }
   }
