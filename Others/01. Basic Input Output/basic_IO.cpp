// Small Program to Read a user's name and greet them with Abbreviated name.

#include <iostream>
#include <conio.h>

/*char * abbreviateName(char [] name){
    char abbrName[21];
    return abbrName;
}*/

void greetFromUserInput()
{
    char name[101];
    printf("\nUser Input Mode: ");
    printf("\nPlease Enter Your Name: ");
    //scanf("%s",&name.c_str());
    scanf("%s", name);
    printf("Good Morning "); 
    //printf(abbreviateName(name)); 
    printf("! How are you today?");
}

void greetFromFileInput()
{
    char name[101];
    //Scanner ins = new Scanner(new FileReader("input.txt"));
    printf("\n\nFile Input Mode: ");
    printf("\nGood Evening "); 
    //printf(abbreviateName(name)); 
    printf("! How are you today?");
}

main()
{
    greetFromUserInput();
    greetFromFileInput();
    getch();
    return 0;
}