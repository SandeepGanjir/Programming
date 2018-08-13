// A program to Read a number and print it in Words.

#include <iostream>
//#include <cstdlib>
//#include <string>
#include <conio.h>

main()
{
    char n[101];
    int i, d, a = 1, f = 1, m = 0, e = 0, o = 1;
    printf("\nA program to Read a number{upto 100 digits} and print it in Words.");
    printf("\n{Input can be given e.g as -12345678901234567890.12345678901234567890}\n");
    printf("\nPlease enter a Number :  ");

    for (i = 0; i < 101; n[i++] = 48);

    for (i = 0; a != 13;)
    {
        a = getch();
        if (a > 47 && a < 58 || a == 46 && f || !i && a == 45)
        {
            n[i++] = a;
            putch(a);
            if (a == 46)
                d = i - 1, f = 0;
            if (a == 45)
                m = 1;
        }
        if (a == 8 && i > 0)
        {
            --i;
            printf("\b \b");
            n[i] == 46 ? f = 1 : f = f;
            n[i] == 45 ? m = 0 : m = m;
        }
    }
    d = f ? i : d;
    if (d > 66)
        printf("\n\n\tGiven Exponent is out of Range. Some junk is Being Printed.");
    
    m ? printf("\n\nMinus ") : printf("\n\n");

    for (o; m < d; m++, o = 1)
	{
		e = d - m;
		a = n[m] * 10 + n[m + 1] - 528;
		if (!((e - 2) % 3) && a > 10 && a < 20)
		{
			++m, o = 0;
			switch (a)
			{
			case 11:
				printf("Eleven ");
				break;
			case 12:
				printf("Twelve ");
				break;
			case 13:
				printf("Thirteen ");
				break;
			case 14:
				printf("Fourteen ");
				break;
			case 15:
				printf("Fifteen ");
				break;
			case 16:
				printf("Sixteen ");
				break;
			case 17:
				printf("Seventeen ");
				break;
			case 18:
				printf("Eighteen ");
				break;
			case 19:
				printf("Ninteen ");
				break;
			}
		}

		switch ((e % 3 == 2) * n[m] * o)
		{
		case 49:
			printf("Ten ");
			break;
		case 50:
			printf("Twenty ");
			break;
		case 51:
			printf("Thirty ");
			break;
		case 52:
			printf("Forty ");
			break;
		case 53:
			printf("Fifty ");
			break;
		case 54:
			printf("Sixty ");
			break;
		case 55:
			printf("Seventy ");
			break;
		case 56:
			printf("Eighty ");
			break;
		case 57:
			printf("Ninty ");
			break;
		}

		switch ((e % 3 == 1 || e % 3 == 0) * n[m])
		{
		case 49:
			printf("One ");
			break;
		case 50:
			printf("Two ");
			break;
		case 51:
			printf("Three ");
			break;
		case 52:
			printf("Four ");
			break;
		case 53:
			printf("Five ");
			break;
		case 54:
			printf("Six ");
			break;
		case 55:
			printf("Seven ");
			break;
		case 56:
			printf("Eight ");
			break;
		case 57:
			printf("Nine ");
			break;
		}
		(e % 3 == 0 && n[m] > 48) ? printf("Hundred ") : printf("");

		switch ((e * !((e - 1 - !o) % 3) / 3) * !!(n[m] + n[m - 1] + n[m - 2] - 144))
		{
		case 1:
			printf("Thousand, ");
			break;
		case 2:
			printf("Million, ");
			break;
		case 3:
			printf("Billion, ");
			break;
		case 4:
			printf("Trillion, ");
			break;
		case 5:
			printf("Quadrillion, ");
			break;
		case 6:
			printf("Quintillion, ");
			break;
		case 7:
			printf("Sextillion, ");
			break;
		case 8:
			printf("Septillion, ");
			break;
		case 9:
			printf("Octillion, ");
			break;
		case 10:
			printf("Nonillion, ");
			break;
		case 11:
			printf("Decillion, ");
			break;
		case 12:
			printf("10^36, ");
			break;
		case 13:
			printf("10^39, ");
			break;
		case 14:
			printf("10^42, ");
			break;
		case 15:
			printf("10^45, ");
			break;
		case 16:
			printf("10^48, ");
			break;
		case 17:
			printf("10^51, ");
			break;
		case 18:
			printf("10^54, ");
			break;
		case 19:
			printf("10^57, ");
			break;
		case 20:
			printf("10^60, ");
			break;
		case 21:
			printf("10^63, ");
			break;
		}
	}

	f ? printf(". ") : printf("Point(Decimal) ");
	
	while (d < i)
		switch (n[d++])
		{
		case 48:
			printf("Zero ");
			break;
		case 49:
			printf("One ");
			break;
		case 50:
			printf("Two ");
			break;
		case 51:
			printf("Three ");
			break;
		case 52:
			printf("Four ");
			break;
		case 53:
			printf("Five ");
			break;
		case 54:
			printf("Six ");
			break;
		case 55:
			printf("Seven ");
			break;
		case 56:
			printf("Eight ");
			break;
		case 57:
			printf("Nine ");
			break;
		}

    getch();
    return 0;
}