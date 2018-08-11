# Small Program to Read a user's name and greet them with Abbreviated name.
# Use as: G:\Temp\Programming> bash basic_IO.sh

function abbreviate {
    local shortName=''
    for word in $@
    do
        shortName=$(echo "$shortName${word:0:1}. ")
    done
    echo -n $shortName
}

#### Console Input ####
echo
echo 'User Input Mode: '
echo -n 'Please Enter Your Name: '
read name
echo "Good Morning $(abbreviate $name)! How are you today?"

#### File Input ####
echo
echo 'File Input Mode: '
[[ -e input.txt ]] || $(echo "Sandeep Kumar Ganjir" > input.txt)
echo "Good Evening $(abbreviate $(cat input.txt))! How are you today?"
