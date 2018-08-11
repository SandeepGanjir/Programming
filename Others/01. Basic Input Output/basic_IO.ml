(* Small Program to Read a user's name and greet them with Abbreviated name. 
   Use as below:
    G:\Temp\Programming> ocaml
    # #use "basic_IO.ml";;
  OR
    G:\Temp\Programming> ocaml basic_IO.ml

  For C Bytecode
    G:\Temp\Programming> ocamlc -output-obj -o basic_IO.c basic_IO.ml

  For port to Java  http://www.ocamljava.org/downloads/
*)

let abbreviateName p_Name = 
  let rec abbr i flag res = 
    if (i = String.length p_Name) then res else
      if p_Name.[i] = ' ' then abbr (i+1) true res else
      if flag = false then abbr (i+1) false res
      else abbr (i+1) false (res ^ (String.make 1 p_Name.[i]) ^ ". ")
  in abbr 0 true "";;

let read_file filename = 
  let lines = ref [] in
  let chan = open_in filename in
  try
    while true; do
      lines := input_line chan :: !lines
    done; !lines
  with End_of_file ->
    close_in chan;
    List.rev !lines ;;

let greetFromUserInput() =
  let () = 
    print_string "\nUser Input Mode: ";
    print_newline ();
    print_string "Please Enter Your Name: " in
  let name = read_line() in
  let () = 
    print_string "Good Morning ";
    print_string (abbreviateName name);
    print_endline "! How are you today?" in
  print_newline ();;

let greetFromFileInput() =
  let () = 
    print_string "\nFile Input Mode: ";
    print_newline () in
  let name = match (read_file "input.txt") with [] -> "" | h::rest -> h in
  let () = 
    print_string "Good Evening ";
    print_string (abbreviateName name);
    print_endline "! How are you today?" in
  print_newline ();;

let main() =
  greetFromUserInput();
  greetFromFileInput();;

main();;