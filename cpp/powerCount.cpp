/*
��Ŀ����
һ�������ܿ��Բ��Ϊ2���ݵĺͣ����磺 7=1+2+4 7=1+2+2+2 7=1+1+1+4 7=1+1+1+2+2 7=1+1+1+1+1+2 7=1+1+1+1+1+1+1 �ܹ������ֲ�ͬ�Ĳ�ַ�ʽ�� 
�ٱ��磺4���Բ�ֳɣ�4 = 4��4 = 1 + 1 + 1 + 1��4 = 2 + 2��4=1+1+2��
��f(n)��ʾn�Ĳ�ͬ��ֵ�����������f(7)=6. ������������������
Ҫ���д���򣬶���n(������1000000)�����f(n)%1000000000��
��������:
ÿ���������һ��������N(1<=N<=1000000)��
�������:
����ÿ�����ݣ����f(n)%1000000000��
ʾ��1
����

7
���

6

������1+2+4��Ҳ���԰�2Ҳ���������1+2+��ʱ��4���ܲ�ñ�2С���ݹ飿
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
	long num, max, numtemp, maxtemp;//max:�ܱ���ֳɵ�����������
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

	return max >> 1;//500 ��512�� ��/2, 256Ϊ�����ɲ���
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
				count += compute(num, max >> 1);//��ɸ�С��
				num -= max;//���𣬼�������ȥ
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