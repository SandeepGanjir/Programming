// Small Program to Read a user's name and greet them with Abbreviated name.

#include <iostream>
#include <cstring>
#include <fstream>
#include <conio.h>

using namespace std;

char * abbreviateName(char name[])
{
    char * abbrName;
    abbrName = new char[21];
    int j = 0;

    for (int i = 0; name[i] != 0; i++)
    {
        if (i == 0)
        {
            abbrName[j++] = name[i];
            abbrName[j++] = '.';
        }
        else if (name[i - 1] == 32)
        {
            abbrName[j++] = ' ';
            abbrName[j++] = name[i];
            abbrName[j++] = '.';
        }
    }
    abbrName[j] = 0;
    return abbrName;
}

void greetFromUserInput()
{
    char strArr[101];
    string name;

    printf("\nUser Input Mode: ");
    printf("\nPlease Enter Your Name: ");

    getline(cin, name); //Not scanf("%s", name);
    strcpy(strArr, name.c_str());

    cout << "Good Morning " << abbreviateName(strArr) << "! How are you today?";
}

void greetFromFileInput()
{
    char strArr[101];
    string name;

    ifstream input("input.txt");
    getline(input, name);
    strcpy(strArr, name.c_str());

    printf("\n\nFile Input Mode: ");
    cout << "\nGood Evening " << abbreviateName(strArr) << "! How are you today?";
}

main()
{
    greetFromUserInput();
    greetFromFileInput();
    getch();
    return 0;
}