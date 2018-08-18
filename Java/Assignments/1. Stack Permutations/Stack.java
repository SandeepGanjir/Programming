//This is an Array Based Stack of type int.
//This Implementation is done by myself and the names of variables & methods is as discussed in the Lecture.
class Stack
   {	private int[] stoarr;
	private int top;
	
	//The Constructor takes size to implement Array-based stack. Also it initializes top.
	Stack(int cap)
	   {	if(cap<=0) throw new IllegalArgumentException("Stack's capacity must be greater than 0.");
		stoarr = new int[cap];
		top = -1;
	   }
	
	void push(int val)
	   {	if(top == stoarr.length) throw new RuntimeException("Stack's underlying storage is Full");
		stoarr[++top] = val;
	   }
	
	int pop()
	   {	if(top<0) throw new RuntimeException("Stack is empty");
		else return(stoarr[top--]);
	   }
	   
	int peek() { return stoarr[top]; }
	boolean isEmpty() { return(top<0); }
	
	/*This function is provided so that state of the stack can be printed when needed.
	  Since, no element of stack except the top can be seen without poping the prior ones.*/
	String stack2String()
	   {	String St = new String("[");
		for(int i=top;i>0;i--) St=St+stoarr[i]+",";
		if(top>-1) St=St+stoarr[0];
		return (St+"]");
	   }
   }
