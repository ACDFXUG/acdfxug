#include <iostream>
#include <iomanip>
#include <cstring>
#include <stdio.h>
using namespace std;
int main()
{
	while (true)
	{
		char a[500] = {};
		double b[500] = { 0 };
		char c[500] = {};
		cin.getline(a,500);
		if (a[0] != '0')
		{
			int i = 0; int j = 0;
			while (i < strlen(a))
			{
				if (a[i] != '+' && a[i] != '-' && a[i] != '*' && a[i] != '/' && a[i] != 32)
				{
					b[j] *= 10;
					b[j] += a[i] - 48;
				}
				if (a[i] == '+' || a[i] == '-' || a[i] == '*' || a[i] == '/')
				{
					c[j] = a[i];
					j++;
				}
				i++;
				if (a[i] == '\n') {
                    break;
                }
			}
			double sum = b[0];
			for (int j = 0; j <= strlen(c); j++)
			{
				if (c[j] == '*')
				{
					b[j] = b[j] * b[j + 1];
					b[j + 1] = 0;
					c[j] = '+';
				}
				if (c[j] == '/')
				{
					b[j] = b[j] / b[j + 1];
					b[j + 1] = 0;
					c[j] = '+';
				}
			}
			for (int j = 0; j < strlen(c); j++)
			{
				if (c[j] == '+')
				{
					sum += b[j + 1];
				}
				if (c[j] == '-')
				{
					sum -= b[j + 1];
				}
			}
			printf("%.2f\n",sum);
		}
		else
		{
			break;
		}
	}
}