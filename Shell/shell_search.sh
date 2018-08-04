ls ~/D*
clear

#### US States
echo Alabama > states.txt
echo Alaska >> states.txt
echo Arizona >> states.txt
echo Arkansas >> states.txt
echo California >> states.txt
echo Colorado >> states.txt
echo Connecticut >> states.txt
echo Missouri >> states.txt
echo Massachusetts >> states.txt
echo Mississippi >> states.txt
echo New Jersey >> states.txt
echo New York >> states.txt
echo  >> states.txt
####

grep "New" states.txt
egrep "s+as" states.txt
egrep "s{2,3}" states.txt
egrep "(i.{2}){3}" states.txt
egrep "^[AEIOU]{1}.+[aeiou]{1}$" states.txt
grep "[aeiou]$" states.txt | wc -l

####
echo "abcdefghijklmnopqrstuvwxyz" > states.txt
echo "ABCDEFGHIJKLMNOPQRSTUVWXYZ" >> states.txt
echo "0123456789" >> states.txt
echo "aa bb cc" >> states.txt
echo "rhythms" >> states.txt
echo "xyz" >> states.txt
echo "abc" >> states.txt
echo "tragedy + time = humor" >> states.txt
echo "http://www.jhsph.edu/" >> states.txt
echo "#%&-=***=-&%#" >> states.txt
####

egrep -v "\w" states.txt
egrep "\W" states.txt

find . -name "states.txt"

head -n 5 ~/.bash_history
tail ~/.bashrc
# bash					# load .bashrc
# nano ~/.bash_profile
# source ~/.bash_profile			# load .bash_profile

shasum states.txt
md5sum states.txt
diff states.txt states.txt
