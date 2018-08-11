# Small Program to Read a user's name and greet them with Abbreviated name.
''' Use as below:
      G:\Temp\Programming> pyhon -u basic_IO.py
'''

def abbreviateName(p_Name):
    result = ""
    flag = True
    for c in p_Name:
        if (c == ' '):
            flag = True
        else:
            if flag:
                result = result + c + ". "
                flag = False
    result = result[:-1]
    return result

def greetFromUserInput():
    print("\nUser Input Mode: ")
    name = input("Please Enter Your Name: ")
    print("Good Morning ", abbreviateName(name), "! How are you today?", sep='', end='\n')

def greetFromFileInput():
    print("\nFile Input Mode: ")
    name = ""
    try:
        fp = open('input.txt')
        name = fp.readline()
    finally:
        fp.close()
    print("Good Evening", abbreviateName(name), "! How are you today?", sep=' ', end='\n')

def main():
    greetFromUserInput()
    greetFromFileInput()

main()