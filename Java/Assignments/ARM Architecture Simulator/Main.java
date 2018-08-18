import java.io.*;
import java.util.*;
import java.text.*;

public class Main
   {	byte[] InA, RS, RT, RD;
	int pc, mLoc, BT;
   
	public static void main(String args[]) throws IOException
	   {	Main X = new Main();	Memory M = new Memory();
		BranchPredictor BP = new BranchPredictor();
		int pc, mLoc, BT, Binst=0, Bmiss=0, MInst=0, L1M=0, L2M=0;
		long NoIns=0,Delay=0;	byte rs, rt, rdP=-1, ITyp, I_T=3;
		X.getInstArray();	boolean Fb;
	     
		///Bufferedreader for execution file trace
		BufferedReader inF;	String SA;
		try {inF = new BufferedReader(new FileReader("exec_trace.txt"));} //4194304
		catch(Exception e)
		 { System.out.print(" Enter Execution Trace filename : ");
		   inF = new BufferedReader(new FileReader((new Scanner(System.in)).nextLine())); }
		   
		while(inF.ready())
		 { SA = inF.readLine();
		   try{X.ParseLine(SA);} catch(Exception e) {}
		   try
		    { pc=X.pc;	mLoc=X.mLoc;	BT=X.BT;
		      ITyp=X.InA[pc];	rs=X.RS[pc];	rt=X.RT[pc]; NoIns++;
		      
		      if(I_T==0)		//Pipeline Data Hazard
			{ if(rs==rdP) Delay++;
			  else if(rt==rdP) if(ITyp==1 || ITyp==2) Delay++;
			 }
			I_T=ITyp;	rdP=X.RD[pc];
		      
		      if(BT!=-1)		//Branch Prediction
			{ if(BT==0) Fb=false; else Fb=true;
			  if(!BP.branchPredictor(pc,Fb)) {Delay+=2; Bmiss++;}
			  Binst++;
			 }
		      
		      if(ITyp<2)		//Load-Store Inst
			{ MInst++;
			  if(ITyp==0) BT=M.MAccess(mLoc,false); else BT=M.MAccess(mLoc,true);
			  
			  if(BT>2) {L2M++; Delay += 199;}
			  else if(BT==2) {L1M++; Delay += 7;}
			}
		     } catch(Exception e) {}
		  }
		L1M += L2M;
		double IPC=NoIns*1.0/(NoIns+Delay), PredA=(Binst-Bmiss)*100.0/Binst;
		double L1mr=100.0*L1M/MInst, L2mr=100.0*L2M/L1M;
		
		DecimalFormat df = new DecimalFormat("##0.00#");
		System.out.println(df.format(IPC)+"     "+df.format(L1mr)+"%     "+df.format(L2mr)+"%     "+df.format(PredA)+"%");
		/*/
		System.out.println("\n  Instructions \t: "+NoIns+"\n  Delays \t: "+Delay+"\n  Branch Inst \t: "+Binst);
		System.out.println("  Branch Miss \t: "+Bmiss+"\n  Memory Inst \t: "+MInst);
		System.out.println("  L1 Miss \t: "+L1M+"\n  L2 Miss \t: "+L2M);
		/*/
	  }


	void ParseLine(String St)
	   {	StringTokenizer s1=new StringTokenizer(St.replaceAll("0x", ""));
		pc=Integer.valueOf(s1.nextToken(),16);
		mLoc=(Long.valueOf(s1.nextToken(),16)).intValue();
		BT=Integer.valueOf(s1.nextToken());
	   }
	
	void getInstArray() throws IOException
	   {	Scanner SA,inF,in;	String St;	int N=0,j;

		try {inF = new Scanner(new FileReader("inst_trace.txt"));
		      in = new Scanner(new FileReader("inst_trace.txt"));}
		catch(Exception e)
		 { System.out.print(" Enter Instruction Trace filename : ");
		   St = (new Scanner(System.in)).nextLine();
		   inF = new Scanner(new FileReader(St)); in = new Scanner(new FileReader(St));
		  }

		while(in.hasNextLine())
		  { SA = new Scanner(in.nextLine());
		    SA.skip("0x");  j=SA.nextInt(16);
		    if(j>N) N=j;
		  }

		InA = new byte[++N]; RS=new byte[N]; RT=new byte[N]; RD=new byte[N];
		for(j=0;j<N;++j) InA[j]=-1;

		while(inF.hasNextLine())
		 { SA = new Scanner(inF.nextLine());
		   SA.skip("0x");   j=SA.nextInt(16);	InA[j]=SA.nextByte();
		   RS[j]=SA.nextByte(); RT[j]=SA.nextByte(); RD[j]=SA.nextByte();
		 }
	   }
   }
   

 class BranchPredictor
   {	int bhr, bArr[]=new int[1024], gArr[]=new int[4096], Arr[]=new int[1024];
	
	BranchPredictor()
	  { for(int i=bhr=0;i<1024;i++) { bArr[i]=2; Arr[i]=2;}
	    for(int i=0;i<4096;i++) gArr[i]=2;	bhr=0;
	  }

	private boolean update(int[] Ar,int index,boolean flag)
	  { int countVal=Ar[index];
	  
	    if(flag)
	      if(countVal==3) return true;
	      else { Ar[index]++; if(countVal==2) return true; else return false;}
	    else
	      if(countVal==0) return true;
	      else { Ar[index]--; if(countVal==1) return true; else return false;}
	  }


	public boolean branchPredictor(int pc, boolean taken)
	  { boolean BiMod = update(bArr,pc%1024,taken);
	    boolean GShr = update(gArr,bhr^(pc%4096),taken);

	    pc%=1024;	int PChoice=Arr[pc];
	    if(taken) bhr=(bhr*2+1)%64; else bhr=(bhr*2)%64;
	    
	    if(BiMod && !GShr) {if(Arr[pc]!=0) Arr[pc]--;}
	    else if(!BiMod && GShr) {if(Arr[pc]!=3) Arr[pc]++;}
	    if(PChoice>1) if(GShr) return GShr;
	    return BiMod;
	  }
   }
