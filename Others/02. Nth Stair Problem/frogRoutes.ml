(* Special class of Program with O(exp(n)) naive solution but has O(n) soultion using 
    dynamic programming and O(log(n)) optimized solution using matrix multiplication.
   Required Property : T(n) = Σ±Ci*T(n-i), where Ci could be 0 and Range of i << n.
   Example : A frog can leap 2,3 or 5 step in one jump. Given a stairs of n steps, 
    determine the number of different possible routes the frog can take.
 
   Use as below:
    G:\Temp\Programming> ocaml
    # #use "frogRoutes.ml";;
   OR
    G:\Temp\Programming> ocaml frogRoutes.ml
*)

(*Gives Execution time of any function*)
let time f x =
  let t = Sys.time() in
  let fx = f x in
  Printf.printf "Execution time: %fs\n" (Sys.time() -. t);
  fx;;


(* O(exp(n)) naive solution.*)
  let rec frogSteps n = 
    if (n < 0) then 0 else
    if (n = 0) then 1 else
    (frogSteps (n-2)) + (frogSteps (n-3)) + (frogSteps (n-5));;

  let getFrogSteps n = Printf.printf "O(exp(n)) naive solution\n%d\n" (frogSteps n);;
  time getFrogSteps 50;;


(* O(n) dynamic programming solution.*)
  let frogSteps_n n =
    let rec frogStep_n (tn_1, tn_2, tn_3, tn_4, tn_5) i =
      if (i < 0) then 0 else
      if (i = 0) then tn_1 else
      frogStep_n (tn_2+tn_3+tn_5, tn_1, tn_2, tn_3, tn_4) (i-1)
    in frogStep_n (1,0,0,0,0) n;;

  let getFrogSteps_n n = Printf.printf "\nO(n) dynamic programming solution\n%d\n" (frogSteps_n n);;
  time getFrogSteps_n 50;;


(* O(log(n)) optimized solution using matrix multiplication.*)
  let frogSteps_logN n =
    let baseCase = [1;0;0;0;0] in
    let tn = [0;1;1;0;1] in
    let rec vector i j n =
      if (j >= n) then [] else
      if (j = i)
        then 1::vector i (j+1) n
        else 0::vector i (j+1) n in
    let idMatrix n =
      let rec matrix n i =
        if (i >= n) then [] else
        (vector i 0 n)::(matrix n (i+1)) in
      matrix n 0 in
    let inMatrix vec =
      let rec getmatrix n vec i = match vec with
        | [] -> []
        | h::rest -> (h::(vector (i+1) 1 n))::(getmatrix n rest (i+1)) in
      getmatrix (List.length vec) vec 0 in
    let rec mapn f lists = assert (lists <> []);
      if List.mem [] lists then []
      else f (List.map List.hd lists) :: mapn f (List.map List.tl lists) in
    let vector_multiply vec mat =
      mapn (fun column -> List.fold_left (+) 0 (List.map2 ( * ) vec column)) mat in
    let matrix_multiply m1 m2 =
      List.map (fun row -> mapn (fun column -> List.fold_left (+) 0 (List.map2 ( * ) row column)) m2) m1 in
    let rec matrixPow mtx n =
      if (n<=0) then idMatrix (List.length mtx) else
      if (n mod 2 = 0) then matrixPow (matrix_multiply mtx mtx) (n/2) else
      matrix_multiply mtx (matrixPow (matrix_multiply mtx mtx) (n/2)) in
    let finalVector = vector_multiply baseCase (matrixPow (inMatrix tn) n) in
    let result = match finalVector with [] -> 0 | head::rest -> head in
    result;;

  let getFrogSteps_logN n = Printf.printf "\nO(log(n)) optimized solution using matrix multiplication\n%d\n" (frogSteps_logN n);;
  time getFrogSteps_logN 50;;