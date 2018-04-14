/*
题目描述
求正整数N(N>1)的质因数的个数。 相同的质因数需要重复计算。如120=2*2*2*3*5，共有5个质因数。
输入描述:
可能有多组测试数据，每组测试数据的输入是一个正整数N，(1<N<10^9)。
输出描述:
对于每组数据，输出N的质因数的个数。

示例1
输入

120
输出

5

解答：
因为任何一个合数都能被一个比它小的质数整除。
所以当我们用小质数去分解这个给定的数时，我们已经把他的合数因子分解了。 
或者从反面去说，如果出现了一个合数a能整除这个数M，
那显然在j =a之前应该有一个质数p < a 能把a整除，而之前反复地用M去除以p直到p不能再整除M 程序才往下执行，
那怎么会后来又出现了M中一个合数因子a能被p整除呢？这显然矛盾了。
从而可以推出，程序中能整除M的数都是质数。
*/


#include<stdio.h>
#include<math.h>
//#include<cmath>//different?

#include<iostream>

using namespace std;

int getPrime(int prior);
int stupidSolution()
{
	//stack over flow!!
	int prime[50000] = {2, 3, 5, 7, 11, 13, 17, 19};//用来节省质因数的重复计算
	int len = 50000;//9;// of array 'prime'
	int num, count, answer;

	/*
	数组初始化赋值前n项，后几项默认0
	prime[0] = 2; prime[1] = 3; prime[2] = 5; prime[3] = 7; 
	prime[4] = 11; prime[5] = 13; prime[6] = 17; prime[7] = 19;
	*/
	while (cin >> num)
	{
		count = 0;
		for (int i = 0; i < len; i++)
		{
			if (prime[i] == 0)//更大的未计算过的质因数
			{
				prime[i] = getPrime(prime[i-1]);
				////len++;//更新prime表长度
			}
			answer = num % prime[i];//该质数是否是因数
			if (answer == num)//质数大于其本身，退出
			{
				break;
			}

			if (answer == 0) //是因数
			{
				count++;
				num /= prime[i];
				cout << "prime:" << prime[i] << endl;
				i--;//再尝试是否是复数个相同质数
			}
		}
		cout << count << endl;
	}

	return 0;
}

int getPrime(int prior) {
	bool notSuccess = true;
	while (notSuccess)
	{
		notSuccess = false;
		prior += 2;
		for (int i = 3; i < sqrt(prior) + 1; i+=2)
		{
			if (prior % i == 0)
			{
				notSuccess = true;
				break;
			}
		}
	}

	return prior;
}

int mainPrimeCount() {
	long num, sq;
	int count;
	while (cin >> num)
	{
		count = 0;
		sq = sqrt(num);
		for (int i = 2; i <= num; i += 2)
		{
			if (num % i == 0)
			{
				count++;
				num /= i;
				cout << i << endl;
				i -= 2;
			}
			if (i == 2) i = 1;
		}
		if (num > 1) count++;//关键：600154635 = 3 x 5 x 40010309
		cout<<count<<endl;
	}

	return 0;
}