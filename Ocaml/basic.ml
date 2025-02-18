(*
  To run from Ocaml Toplevel  IDE
  #> #use "basic.ml";;
*)

(* Q1. Calculate Factorial of given number n with good Time and Space Complexity *)

let rec fac(n) = if (n=0) then 1 else n*fac(n-1);;
(* Time Complexity: T(fac(n)) = 1 + T(fac(n-1)) = 1+1+T(fac(n-2))  = n multiplications = O(n)
   Space Complexity: S(fac(n)) = 1 + S(fac(n-1)) = 1+1+S(fac(n-2)) = n +S(fac(0)) = n spaces = O(n) *)

let fact n =
		let rec h1(n, p) = if (n=0) then p else h1(n-1, p*n);
	in h1(n, 1);;
(* Time Complexity: T(fact(n)) = 1 + T(fact(n-1)) = 1+1+T(fact(n-2)) = n multiplications = O(n)
   Space Complexity: S(fac(n)) = 2 spaces = O(1) *)


(* Q2. Calculate nth power of given number x with good Time and Space Complexity *)

let rec pow_n(x,n) = if(n=0) then 1 else x*pow_n(x,n-1);;
(* Time Complexity: T(pow_n(x,n)) = 1 + T(pow_n(x,n-1) = 1+1+T(pow_n(x,n-2) = n multiplications = O(n)
   Space Complexity: S(pow_n(x,n)) = 1 + S(pow_n(x,n)) = 1+1+S(pow_n(x,n)) = n spaces = O(n) *)

let power x n =
		let rec piter(p,xsq,n) = if (n = 0) then p
							else if ((n mod 2) = 0) then piter(p, xsq*xsq, n/2)
							else piter(p*xsq, xsq*xsq, n/2);
	in piter(1,x,n);;
(* Time Complexity: T(piter(p,xsq,n)) = 3 + T(piter(p,xsq,n div 2)) {Suppose n = 2^i) = 3i operations = O(log n)
   Space Complexity: S(fac(n)) = 3 spaces = O(1) *)


(* Q3. Calculate nth Fibonacci number with good Time and Space Complexity *)

let fib_n n =
		let rec h1(a,b,n) = if(n=0) then a else h1(b,a+b,n-1);
	in h1(0,1,n);;
(* Time Complexity: T(fib_n(n)) = 1 + T(fib_n(n-2)) = 1+1+T(fib_n(n-2)) = n operations = O(n)
   Space Complexity: S(fac(n)) = 3 spaces = O(1)
   Array Print Version : let fib_n n = let rec h1(a,b,n) = if(n=0) then [a] else a :: h1(b,a+b,n-1); in h1(0,1,n);;	 *)

let fib_logN n =
		let rec fib(a,b,p,q,m) = if m=0 then b
							else if (m mod 2 = 0) then fib(a, b, (p*p+q*q), (2*p*q + q*q), m/2)
							else fib((b*q + a*q + a*p), (b*p + a*q), p, q, m-1);
	in fib(1,0,0,1,n);;
(* Time Complexity: T(fib(a,b,p,q,n)) = 5 + T(fib(a,b,p,q,n div 2))		{Suppose n = 2^i) = 5i operations = O(log n)
	 Space Complexity: S(fac(n)) = 5 spaces = O(1) *)
fib_logN 12;;


(* Q4. Calculate nearest square root if given number with good Time and Space Complexity *)

let sqrt t =
	let sqr z = z*z; in
	let rec x(t) = if(t=0) then 0 else y(t/4)
		and y(t) = if(sqr(2*x(t)+1) > t) then 2*x(t) else 2*x(t)+1;
	in y(t);;
(* Time Complexity: T(sqrt(n)) = 3 + T(sqrt(n div 4)) {Suppose n = 4^i) = 3i operations = O(log n base 4)
	 Space Complexity: S(fac(n)) = 3 spaces = O(1) *)
sqrt 16478;;


(* Q5. A frog can leap over 1, 2 or 3 steps in one go. Given a stairs 100 steps. Return the number of 
		different possible routes that can be taken by the frog. Good Time and Space Complexity is required *)

let frog n =
		let rec count(a,b,c,n) = if (n=0) then c else count(b,c,a+b+c,n-1)
	in count(0,0,1,n);;
(* |A|= (a b c) and |P| = |0 0 1| then |A|*|B| = |b c a+b+c|
				above could be		|1 0 1|
				better approach		|0 1 1|			*)
frog 100;; 