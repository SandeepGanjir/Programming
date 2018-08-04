echo Sandeep
pwd
ls -la
clear
cd ~/Documents
pwd

mkdir temp
cd temp
touch journal-{01..10}{a..c}.txt
echo 'mornings are great.' >> journal-01a.txt
wc -lwm journal-01a.txt
ls|wc

cat journal-01a.txt
less journal-01a.txt
nano journal-01a.txt
tail -n 4 journal-01a.txt		# -f for follow

cd ..
mv temp tmp
cp -r tmp temp1
rm -rf temp1
rm -rf tmp

man apropos
apropos delete
