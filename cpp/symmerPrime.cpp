/*No.1026
��������
�ж�һ�����Ƿ�Ϊ�Գ��Ҳ�������λ����������
����
�������ݺ��в�����50����������(0<n<2 ^ 32)��	
	���

	����ÿ��n����������ǲ�������λ���ĶԳ��������������Yes�������������No����ÿ���жϽ��������һ�С�
*/

/*TODO Wrong answer*/

#include<stdio.h>
#include<iostream>
#include<math.h>

using namespace std;
int deg(int num);
int notPrime(int num);
int mainSymmerPrime() {
	int num = 0, degree = 0, opp;
	while (cin >> num)
	{
		if (num <= 0 || num >= 100000 || notPrime(num))
		{
			cout << "No" << endl;
			continue;
		}

		degree = deg(num);
		opp = 1;
		while (degree != 0 && num / degree % 10 == num / opp % 10) {
			degree /= 10;
			opp *= 10;
		}
		if (degree)
		{
			cout << "No" << endl;
		}
		else
		{
			cout << "Yes" << endl;
		}
	}
	
	return 0;
}

int deg(int num) {
	int d = 0, result = 1;
	while (num /= 10)
	{
		d++;
	}

	while (d--)
	{
		result *= 10;
	}
	return result;
}

int notPrime(int num) {
	int gen = sqrt(num) + 1;

	if (num == 0)
		return 1;
	if (num == 1)
		return 0;
	if (num % 2 == 0)
		return 1;
	for (int i = 2; i < gen; i+=2)
		if (num % i == 0)
			return 1;

	return 0;
}