/* Compile using
    G:\Temp\Programming> gcc HelloWorld.c
   OR
    G:\Temp\Programming> gcc -S HelloWorld.c
    G:\Temp\Programming> gcc -c HelloWorld.s -o HelloWorld.o
    G:\Temp\Programming> gcc HelloWorld.o -o HelloWorld
*/

#include <stdio.h>
int main()
{
    // printf() displays the string inside quotation
    printf("Hello, World!");
    getchar();
    return 0;
}