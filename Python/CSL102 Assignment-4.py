
#			NAME	  : Sandeep Ganjir

#########################################################################
'''
import random
import time

def print_timing(func):
	def wrapper(*arg):
		t1 = time.clock()
		res = func(*arg)
		t2 = time.clock()
		print ('%s took %0.3fms' % (func.func_name, (t2-t1)*1000.0))
		return res
	return wrapper
'''
#########################################################################
############################################################################################################################

#@print_timing
def PasT(n):
	i,j,t=0,0,0
	A=[]
	#INV: A contains the ith row of Pascal's triangle; 1<=i<=n.
	while(i<n):
		A.append(1);
		j,t=0,0
		while(j<i):
			A[j],t,j=A[j]+t,A[j],j+1
		i=i+1
		#Assert: A[0...i-1] contains the ith row of Pascal's triangle;
		print (A)

#--------------------------------------------------------------------------------------------------------------------------#

def past(n):
	i,j,t=0,0,0
	A=range(n)
	#INV: A[0...i-1] contains the i-1th row of Pascal's triangle; 1<=i<=n.
	while(i<n):
		j,t=0,0
		while(j<n):
			if(j<i):A[j],t=A[j]+t,A[j]
			else:A[j]=0
			j=j+1
		A[i],i=1,i+1
		#Assert: A[0...i-1] contains the ith row of Pascal's triangle;
		print (A)

############################################################################################################################

#@print_timing
def hamming(n):
	q=range(n)
	q[0] = 1
	i,x2,x3,x5,j2,j3,j5 = 1,2,3,5,1,1,1
        #INV: q[0..i-1] contains the first i values of the sequence; 1<=i<= n.
        #    x2 = 2*q[j2] is the minimum value > q[i-1] with the form 2*x for x in q[0..i-1]
        #    x3 = 3*q[j3] is the minimum value > q[i-1] with the form 3*x for x in q[0..i-1]
        #    x5 = 5*q[j5] is the minimum value > q[i-1] with the form 5*x for x in q[0..i-1]
	while (i < n):
		q[i] = min(x2,x3,x5)
		if(q[i]==x2): j2,x2 = j2+1,2*q[j2]
		if(q[i]==x3): j3,x3 = j3+1,3*q[j3]
		if(q[i]==x5): j5,x5 = j5+1,5*q[j5]
		i = i+1
	print (q)

############################################################################################################################

def lsearch(a,Ar):
	print ("The location found is: ")
	i,n=0,len(Ar)
	#INV: All elements of Ar[0...i-1] is checked against a; 1<=i<=n.
	while(i<n):
		if(Ar[i]==a):print (i)
		i=i+1

#--------------------------------------------------------------------------------------------------------------------------#

def bsearch(a,A,b,e):
	m=(b+e)/2
	#INV:Assuming A to be sorted.   If (A[m]<a), then no element from A[b...m] can have a:
	#				else no element from A[m+1...e] can have a
	if(A[m]==a):print (m)
	else:
		if(A[m]<a): bsearch(a,A,m+1,e)
		else: bsearch(a,A,b,m-1)

#--------------------------------------------------------------------------------------------------------------------------#

#@print_timing
def ssort(Ar):
	i,j,t,n=0,0,0,len(Ar)
	while(i<n):
		t=j=i
		while(j<n):
			if(Ar[j]<Ar[t]):
				t=j
			j=j+1
		Ar[i],Ar[t]=Ar[t],Ar[i]
		i=i+1
	print (Ar)

#--------------------------------------------------------------------------------------------------------------------------#

#@print_timing
def bsort(Ar):
	t,j,n=1,1,len(Ar)
	while(t>0):
		j=1
		t=0
		while(j<n):
			if(Ar[j]<Ar[j-1]):
				Ar[j],Ar[j-1]=Ar[j-1],Ar[j]
				t=1
			j=j+1
	print (Ar)

#--------------------------------------------------------------------------------------------------------------------------#

#@print_timing
def isort(Ar):
	i,j,n=1,0,len(Ar)
	while(i<n):
		j,t=i,Ar[i]
		while(Ar[j-1]>t and j>0):
			Ar[j]=Ar[j-1]
			j=j-1
		Ar[j]=t
		i=i+1
	print (Ar)

#--------------------------------------------------------------------------------------------------------------------------#

def quick(a,l,r):
	if(l<r):
		i,j=l+1,r
		#INV: a[l..i-1] <= x; a[j+1..r] > x; l <= i <= r+1; l-1 <= j <= r; i <= j+1
		while(i<=j):
			if(a[i]<= a[l]): i = i+1
			else: a[i],a[j],j = a[j],a[i],j-1
		#assert: a[l..j] <= x; a[j+1..ri] > x; l <= j <= r
		a[l],a[j] = a[j],a[l]
		quick(a,l,j-1)
		quick(a,j+1,r)

#@print_timing
def qsort(a):
	quick(a,0,len(a)-1)
	print (a)

#--------------------------------------------------------------------------------------------------------------------------#

def merge(a1,a2):
	a=[]
	i,j=0,0
	x,y=len(a1),len(a2)
	#invariant : a1[0..i-1] and a2[0..j-1] are merged ; 0<=i<=len(a1) ; 0<=j<]len(a2) ;	
	while (i<x) and (j<y):
		if (a1[i]<a2[j]):
			a.append(a1[i])
			i=i+1
		else:
			a.append(a2[j])
			j=j+1
	#assert : the array of smallest length is merged with the some elements of the other array ; 
	a = a+a1[i:]
	a = a+a2[j:]
	return list(a)

#@print_timing
def msort(a):
	mid=len(a)/2	
	if(len(a)>=2):
		return merge(msort(a[:mid]),msort(a[mid:]))
	else:
		return list(a)

############################################################################################################################

def maxsubseq(Ar):
	i,mxs,s,n=0,0,0,len(Ar)
	#invariant : 0<=i<=n; mxs will contain maximum subsequence sum upto i-1;
	#			s will contain sum of largest subseq with last element (i-1)th so that it is non -ve
	while(i<n):
		s=s+Ar[i]
		if(s<0): s=0
		if(mxs<s): mxs=s
		i=i+1
	print (mxs)		

############################################################################################################################
PasT(10)

maxsubseq([4, -3, 5, -2, -1, 2, 6, -2])