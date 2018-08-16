// A program to Read a number and print it in Words.
/*  Use as below:
    G:\Temp\Programming> gcc NeumeralToWord.cpp -o NeumeralToWord
    G:\Temp\Programming> NeumeralToWord
*/
// Bug with input -12556890.6780

#include <iostream>
#include <conio.h>

class SpellNum
{
	char num[101];
	int i, m_DecimalPos, m_hasDecimal = 0, m_IsNegative = 0, tmpChar = 1;

	void _printPreDecimalTeens(int position)
	{
		switch (position)
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

	void _printPreDecimalTens(int position)
	{
		switch (position)
		{
		case '1':
			printf("Ten ");
			break;
		case '2':
			printf("Twenty ");
			break;
		case '3':
			printf("Thirty ");
			break;
		case '4':
			printf("Forty ");
			break;
		case '5':
			printf("Fifty ");
			break;
		case '6':
			printf("Sixty ");
			break;
		case '7':
			printf("Seventy ");
			break;
		case '8':
			printf("Eighty ");
			break;
		case '9':
			printf("Ninty ");
			break;
		}
	}

	void _printPreDecimalUnits(int position)
	{
		switch (position)
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
	}

	void _printPreDecimalThousandths(int position)
	{
		switch (position)
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

	void _printPreDecimal()
	{
		int o, m = m_IsNegative, e = 0;

		for (o = 1; m < m_DecimalPos; m++, o = 1)
		{
			e = m_DecimalPos - m;
			tmpChar = (num[m] - '0') * 10 + (num[m + 1] - '0');
			if (!((e - 2) % 3) && tmpChar > 10 && tmpChar < 20)
			{
				++m, o = 0;
				_printPreDecimalTeens(tmpChar);
			}
			_printPreDecimalTens((e % 3 == 2) * num[m] * o);
			_printPreDecimalUnits((e % 3 == 1 || e % 3 == 0) * num[m]);
			(e % 3 == 0 && num[m] > '0') ? printf("Hundred ") : printf("");
			_printPreDecimalThousandths((e * !((e - 1 - !o) % 3) / 3) * !!(num[m] + num[m - 1] + num[m - 2] - 144));
		}
	}

	void _printPostDecimal()
	{
		while (m_DecimalPos < i)
			switch (num[m_DecimalPos++] - '0')
			{
			case 0:
				printf("Zero ");
				break;
			case 1:
				printf("One ");
				break;
			case 2:
				printf("Two ");
				break;
			case 3:
				printf("Three ");
				break;
			case 4:
				printf("Four ");
				break;
			case 5:
				printf("Five ");
				break;
			case 6:
				printf("Six ");
				break;
			case 7:
				printf("Seven ");
				break;
			case 8:
				printf("Eight ");
				break;
			case 9:
				printf("Nine ");
				break;
			}
	}

  public:
	void takeInput()
	{
		printf("\nA program to Read a number{upto 100 digits} and print it in Words.");
		printf("\n{Input can be given e.g as -12345678901234567890.12345678901234567890}\n");
		printf("\nPlease enter a Number :  ");

		for (i = 0; i < 101; num[i++] = '0');

		for (i = 0; tmpChar != 13;)
		{
			tmpChar = getch();
			if (tmpChar >= '0' && tmpChar <= '9' || tmpChar == '.' && !m_hasDecimal || !i && tmpChar == '-')
			{
				num[i++] = tmpChar;
				putch(tmpChar);
				if (tmpChar == '.')
					m_DecimalPos = i - 1, m_hasDecimal = 1;
				if (tmpChar == '-')
					m_IsNegative = 1;
			}
			if (tmpChar == 8 && i > 0)
			{
				--i;
				printf("\b \b");
				m_hasDecimal = num[i] == '.' ? 0 : m_hasDecimal;
				m_IsNegative = (num[i] == '-') ? 0 : m_IsNegative;
			}
		}
		m_DecimalPos = m_hasDecimal ? m_DecimalPos : i;
	}

	void printWord()
	{
		if (m_DecimalPos > 66)
			printf("\n\n\tGiven Exponent is out of Range. Some junk is Being Printed.");

		m_IsNegative ? printf("\n\nMinus ") : printf("\n\n");
		_printPreDecimal();

		m_hasDecimal ? printf("Point(Decimal) ") : printf(". ");
		_printPostDecimal();
	}
};

main()
{
	SpellNum numToWord;
	numToWord.takeInput();
	numToWord.printWord();

	getch();
	return 0;
}