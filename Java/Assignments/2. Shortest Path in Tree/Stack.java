//Stack class is used to make integer stack.
class Stack
   {	private int[] stoarr;
	private int top;
	//The Constructor takes size to implement Array-based stack. Also it initializes top.
	Stack(int cap) { stoarr = new int[cap]; top = -1; }
	void push(int val)
	   { if(top == stoarr.length) throw new RuntimeException("Stack's underlying storage is Full");
	     stoarr[++top] = val; }
	int pop() { if(top<0) throw new RuntimeException("Stack is empty"); else return(stoarr[top--]); }
	int peek() { return stoarr[top]; }
	boolean isEmpty() { return(top<0); }
   }
