/*implement a bloom flter and study the impact of parameters k (number of hash functions) and m (size of bit array) on the rate of false positives. For this you will be given two non-zero positive numbers L and n such that n < L. You have to generate a set S of n different numbers, chose randomly in the range of 1...L, and encode it in a bloom flter. Let us call the set of these numbers as S.
Now randomly pick any element from 1 to L and check for its set membership in S. Execute a large number of such random queries and find out the rate of false positives.
*/
import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Assignment3
   {	public static void main(String args[]) throws IOException
	   {	Assignment3 SP = new Assignment3();
		int L,n,X=8;
		//n is Size of the set represented by the bloom filter & L is the range of values.
		//X represents the max value of k (no. of hash function OR value of m such that m=k*5*n)
		Scanner inp  = new Scanner(System.in);
		System.out.println(" Enter value of L and n: ");
		L = inp.nextInt(); n = inp.nextInt();
		//Check to ensure n<L
		if(n>L) { System.out.println(" ERROR! n cannot be larger than L"); System.exit(0); }
		
		String Str = SP.BloomFilter(L,n,X);
		
		//Writing the output in a file
		PrintWriter fout = new PrintWriter(new FileWriter("test.dat"), true);
		fout.write(Str);  fout.close();
	   }
	

	//This function has time complexity O(X*n*X*X)
	public String BloomFilter(int L,int n,int X)
	   {	String Out=new String(""),St[] = new String[X];
		byte T[]=new byte[L];	Random Rnd = new Random();
		int m,c,p=n,q,A[]=new int[X],B[]=new int[X],tmp,i,R[]=new int[p];
		//A & B are the arrays to store a & b of hash functions. Here p(no. of quries) is taken to be n
		float q_p;						//rate of false positive
		NumberFormat df = new DecimalFormat("#0.000#");		//for rounding to fixed no. of digits
		
		//This loop find n (different) random items in range 0-(L-1) to form set R
		for(i=0;i<p;i++) { tmp=Rnd.nextInt(L); if(T[tmp]==1)i--; else{R[i]=tmp;T[tmp]=1;} }

		//This loop runs for different values of m {5n,10n,...35n}
		for(c=5;c<X*5;c+=5)
		  { m = c*n; Rnd = new Random(); long temp;
		    byte M[] = new byte[m],Rep[]=new byte[L]; int k,S[]=new int[n];
		    //M is representation of bit array of BloomFilter of size m
		    //Rep is dummy array that keeps actual record of items represented by bloom filter
		    
		    //This loop inserts n random items in range 0-(L-1) to form set S
		    for(i=0;i<n;i++) { tmp=Rnd.nextInt(L); if(Rep[tmp]==1)i--; else{S[i]=tmp;Rep[tmp]=1;} }
		    
		    if(St[0]==null) St[0]=new String("##k(hash functions)\tm="+c+"n");
		    else St[0]+= ",\t\tm="+c+"n";
		    
		    //This loop runs for different values of k(No. of hash functions)
		    for(k=1;k<X;k++)
		      {	temp=(long)(Rnd.nextDouble()*m*m); temp%=m; A[k]=(int)temp;
			temp=(long)(Rnd.nextDouble()*m*m); temp%=m; B[k]=(int)temp;
			//This loop inserts items from set S into bloom filter
			for(i=0;i<n;i++) M[(int)(((long)A[k]*S[i]+B[k])%m)]=1;

			//This loop fires p random set-membership queries on the bloom filter
			for(i=q=0;i<p;i++)
			  { byte j,F=1;	tmp=R[i];
			    //This loop checks r1,r2,..,rk (such that rj=fj(s) [fj is jth hash fn]) are set to 1
			    for(j=1;j<=k;j++) F*=M[(int)(((long)A[k]*tmp+B[k])%m)];
			    if(Rep[tmp]==0) q+=F;				//If false positive q+=1
			  }
			q_p = (float)q/p;					//computing rate of false positive
			if(St[k]==null) St[k]=new String("  "+k+"\t");
			St[k]+= "\t\t"+df.format(q_p);
		      }
		  }
		
		for(c=0;c<X;c++) Out+=St[c]+"\n";		//Merging Strings to single Output String
		return (Out+"\n for\tL = "+L+"\t\tn = "+n+"\n");
	   }
   }
