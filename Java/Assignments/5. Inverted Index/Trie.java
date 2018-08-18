import java.io.*;
import java.util.*;

////********Linked list of type int********////
class llint
   {	int val;	llint next;
	llint(int j) { val = j; next = null;}
	//function to insert value at the end of linked list
	public void Insert(int v)
	  {  if(next!=null) next.Insert(v);
	     else next=new llint(v);
	  }
	//dump linked list in a String
	public String dump(boolean f)
	  { String s=new String(""); if(f) s+="*";
	    if(next!=null) return (val +s+", "+ next.dump(f));
	    else return (val +s+", ");
	  }
   }


////********Node for storing a Word********////
class Wnode
   {	int Doc_id, freq, Afreq;
	llint pos, Apos;
	Wnode next;
	//Doc_id stores the id of the file corresponding to which the word is stored
	//freq stores the frequency and pos stores the positions of occurences of word
	//Afreq stores the frequency and Apos stores the positions of occurences as Anchor word
	//next stores occurence information of the word in different file (stored in order of priority)
	
	Wnode(int id, int p, boolean flag)
	  {  Doc_id=id;	next=null;
	     if(flag)	{ freq=0; Afreq=1; pos=null; Apos=new llint(p);}
	     else	{ freq=1; Afreq=0; pos=new llint(p); Apos=null;}
	  }

	public Wnode Insert(int id, int p, boolean flag)
	  {  if(id!=Doc_id)
		if(next==null) {next=new Wnode(id,p,flag); return next;}
		else return next.Insert(id,p,flag);
	     else
	      	if(flag) {Afreq+=1; if(Apos==null) Apos=new llint(p); else Apos.Insert(p);}
		else {freq+=1; if(pos==null) pos=new llint(p); else pos.Insert(p);}
		return null;
	  }
	  
	public Wnode Reorder()
	  { if(next==null) return this;
	    else
	      {	Wnode tmp=next.Reorder();
		if(Afreq<tmp.Afreq||(Afreq==tmp.Afreq&&freq<tmp.freq))
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

	static int index = -1;
	static ArrayList<String> Ar = new ArrayList<String>();
	public static int AddFile(String st) { index++; Ar.add(st); return index;}
	//public static String[] File_Array() {return Ar.toArray();}
	
	//this maps a character to its corresponding array index
	int map(char ch)
	  {  if(ch>96&&ch<123) return (ch-97);
	     else if(ch>64&&ch<91) return (ch-65);
	     else return -1;
	  }
	  
	//this function is used to maintian order of relevance of documents corresponding to a word
	private void Reorder(String St)
	  {  try{ if(St.charAt(0)=='*') {if(loc!=null) loc=loc.Reorder();}
		  else {Trie tmp=down[map(St.charAt(0))]; if(tmp!=null) tmp.Reorder(St.substring(1));}
		}
	     catch(StringIndexOutOfBoundsException e) {if(loc!=null) loc=loc.Reorder();}
	     catch(ArrayIndexOutOfBoundsException e) {if(loc!=null) loc=loc.Reorder();}
	  }
	  
	//this inserts a word into the trie
	public Wnode Insert(int Id, String St, int index)
	  {  try{ if(St.charAt(0)=='*')
		    if(loc==null) {loc = new Wnode(Id,index,true); return loc;}
		    else return loc.Insert(Id,index,true);
		  else { Trie tmp=down[map(St.charAt(0))];
			 if(tmp==null) {tmp=new Trie();down[map(St.charAt(0))]=tmp;}
			 return tmp.Insert(Id,St.substring(1),index);
			}
		}
	     catch(StringIndexOutOfBoundsException e)
		{ if(loc==null) {loc = new Wnode(Id,index,false); return loc;}
		  else return loc.Insert(Id,index,false); }
	     catch(ArrayIndexOutOfBoundsException e)
		{ if(loc==null) {loc = new Wnode(Id,index,false); return loc;}
		  else return loc.Insert(Id,index,false); }
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

	//Function to get result of query (in order of priority)
	public String Search(String qry)
	  {  Wnode tmp = Find(qry);	String St=new String("");
	     if(tmp!=null) while(tmp!=null) {St+= tmp.Doc_id + ", "; tmp=tmp.next;}
	     else St+= "Word not found";
	     return St;
	  }
	
	//Function which inserts a file into the trie
	public String Insert(String File) throws FileNotFoundException
	  {  Scanner in = new Scanner(new FileReader(File));
	     Queue<Wnode> Q1 = new LinkedList<Wnode>(); Queue<String> Q2 = new LinkedList<String>();
	     //Implementatin using Queue so that inverted index is formed in order of occurence 
	     String s1,St=new String("");	int i=0,j,Fid=AddFile(File); 	Wnode tmp;
	     in.useDelimiter("[(,).?\\s]+"); //0123456789= useDelimiter("[()_-{}|+:;<>?,.`/~!@#$%^&\\\t\f\r\"\'\n\\s]+");
	     
	     while(in.hasNext())
		{   s1 = in.next();
		    if(map(s1.charAt(0))<0) s1=s1.substring(1);
		    tmp = Insert(Fid,s1,i++);
		    //Removing non alphabetical characters
		    try{for(j=1;map(s1.charAt(j))!=-1;j++); s1=s1.substring(0,j);}
		    catch(Exception e) {}
		    
		    if(tmp!=null) {Q1.offer(tmp); Q2.offer(s1);}
		}
	     while(!Q2.isEmpty())
		{   s1 = Q2.poll();	Reorder(s1);
		    St+= s1 +"\t-> ";	tmp = Q1.poll();
		    if(tmp.Afreq!=0) St+= tmp.Apos.dump(true);
		    if(tmp.freq!=0) St+= tmp.pos.dump(false);
		    St+= "\n";
		}
	     return St;
	  }
   }
