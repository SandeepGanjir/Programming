#!/usr/bin/env bash
chmod o+x shell_advanced.sh

chapter_number=5
echo $chapter_number
let chapter_number=$chapter_number+1
echo $chapter_number
the_empire_state="New York"
echo $the_empire_state
echo "I went to school in $the_empire_state."

math_lines=$(cat states.txt | wc -l)
echo $math_lines

echo "Script arguments: $@"
echo "First arg: $1. Second arg: $2."
echo "Number of arguments: $#"
fj
echo "Exit status of last command: $?"

echo "Type in a string and then press Enter:"
read response
echo "You entered: $response"

true && echo "Program 1 was executed."
false && echo "Program 2 was executed."

true || echo "Program 3 was executed."
false || echo "Program 4 was executed."

[[ 4 -gt 3 ]]
echo $?
[[ 3 -gt 4 ]]
echo $?
[[ 3 -gt 4 ]] && echo t || echo f
[[ -e states.txt ]] && echo t || echo f

number=7
[[ $number -gt 3 ]] && echo t || echo f
echo '-gt -ge -lt -lt -eq -ne -e -d -z -n'
[[ ! 6 -ne 3 ]] && echo t || echo f

my_name=sean
[[ $my_name =~ ^s.+n$ ]] && echo t || echo f

p1='Brian'
if [[ $p1 -gt 3 ]] && [[ $p1 -lt 7 ]]
then
  echo "$p1 is between 3 and 7"
elif [[ $p1 =~ "Jeff" ]] || [[ $p1 =~ "Roger" ]] || [[ $p1 =~ "Brian" ]]
then
  echo "$p1 works in the Data Science Lab"
else
  echo "You entered: $p1, not what I was looking for."
fi

arg=5
[[ $(expr $arg % 2) -eq 1 ]] && echo odd || echo even

plagues=(blood frogs lice flies sickness boils hail locusts darkness death)
echo ${plagues[0]}
echo ${plagues[*]}
echo ${plagues[*]:5:3}
echo ${#plagues[*]}
dwarfs=(grumpy sleepy sneezy doc)
echo ${dwarfs[*]}
dwarfs+=(bashful dopey happy)
echo ${dwarfs[*]}

echo {a..x}{1..2}
start=4
end=9
echo {$start..$end}
eval echo {$start..$end}
echo {Who,What,Why,When,How}?


echo "Explicit list:"
for picture in img001.jpg img002.jpg img451.jpg
do
    echo "picture is equal to $picture"
done

echo ""
echo "Array:"
stooges=(curly larry moe)
for stooge in ${stooges[*]}
do
    echo "Current stooge: $stooge"
done

echo ""
echo "Command substitution:"
for code in $(ls)
do
    echo "$code is a bash script"
done

count=3
while [[ $count -gt 0 ]]
do
  echo "count is equal to $count"
  let count=$count-1
done


function addseq {
  local sum=0
  for element in $@
  do
    let sum=sum+$element
  done
  echo $sum
}
#source script.sh
addseq 12 90 3
addseq 0 1 1 2 3 5 8 13
addseq
addseq 4 6 6 6 4
