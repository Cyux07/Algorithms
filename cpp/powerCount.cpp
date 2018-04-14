/*
题目描述
一个整数总可以拆分为2的幂的和，例如： 7=1+2+4 7=1+2+2+2 7=1+1+1+4 7=1+1+1+2+2 7=1+1+1+1+1+2 7=1+1+1+1+1+1+1 总共有六种不同的拆分方式。 
再比如：4可以拆分成：4 = 4，4 = 1 + 1 + 1 + 1，4 = 2 + 2，4=1+1+2。
用f(n)表示n的不同拆分的种数，例如f(7)=6. 种数！！种数！！！
要求编写程序，读入n(不超过1000000)，输出f(n)%1000000000。
输入描述:
每组输入包括一个整数：N(1<=N<=1000000)。
输出描述:
对于每组数据，输出f(n)%1000000000。
示例1
输入

7
输出

6

分析：1+2+4，也可以把2也拆掉。但在1+2+的时候4不能拆得比2小。递归？
*/

#include<stdio.h>
#include<math.h>
//#include<cmath>//different?
#include<iostream>

using namespace std;

//find max 2power number of 'num'
long findMax(long num);
int compute(long num, long max);
int mainPowerCount() {
	long num, max, numtemp, maxtemp;//max:能被拆分成的最大二次幂数
	int count;
	while (cin >> num)
	{
		max = findMax(num);

		count = compute(num, max);
		/*while (max > 1)
		{
			
			
			//init
			numtemp = num;
			maxtemp = max;

			while(numtemp > 1)
			{
				if (numtemp > max)
				{
					//count++;
					count += compute(numtemp, max >> 2);
					numtemp -= max;
					//cout << i << endl
				}
				else
				{
					max >> 1;//left move = /2
				}
			}
			
		}*/
		cout << count << endl;
	}

	return 0;
}

long findMax(long num) {
	long max = 1;

	while (max < num)
	{
		max << 1;
	}

	return max >> 1;//500 得512， 再/2, 256为其最大可拆项
}

int compute(long num, long max) {
	int count = 0;

	while (max > 1)
	{
		while (num > 1)
		{
			if (num > max)
			{
				//count++;
				count += compute(num, max >> 1);//拆成更小项
				num -= max;//不拆，继续做下去
				//cout << i << endl
			}
			else
			{
				max >> 1;//left move = /2
			}
		}

	}
	return count;
}