/*
��Ŀ����
��������N(N>1)���������ĸ����� ��ͬ����������Ҫ�ظ����㡣��120=2*2*2*3*5������5����������
��������:
�����ж���������ݣ�ÿ��������ݵ�������һ��������N��(1<N<10^9)��
�������:
����ÿ�����ݣ����N���������ĸ�����

ʾ��1
����

120
���

5

���
��Ϊ�κ�һ���������ܱ�һ������С������������
���Ե�������С����ȥ�ֽ������������ʱ�������Ѿ������ĺ������ӷֽ��ˡ� 
���ߴӷ���ȥ˵�����������һ������a�����������M��
����Ȼ��j =a֮ǰӦ����һ������p < a �ܰ�a��������֮ǰ��������Mȥ����pֱ��p����������M ���������ִ�У�
����ô������ֳ�����M��һ����������a�ܱ�p�����أ�����Ȼì���ˡ�
�Ӷ������Ƴ���������������M��������������
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
	int prime[50000] = {2, 3, 5, 7, 11, 13, 17, 19};//������ʡ���������ظ�����
	int len = 50000;//9;// of array 'prime'
	int num, count, answer;

	/*
	�����ʼ����ֵǰn�����Ĭ��0
	prime[0] = 2; prime[1] = 3; prime[2] = 5; prime[3] = 7; 
	prime[4] = 11; prime[5] = 13; prime[6] = 17; prime[7] = 19;
	*/
	while (cin >> num)
	{
		count = 0;
		for (int i = 0; i < len; i++)
		{
			if (prime[i] == 0)//�����δ�������������
			{
				prime[i] = getPrime(prime[i-1]);
				////len++;//����prime����
			}
			answer = num % prime[i];//�������Ƿ�������
			if (answer == num)//���������䱾���˳�
			{
				break;
			}

			if (answer == 0) //������
			{
				count++;
				num /= prime[i];
				cout << "prime:" << prime[i] << endl;
				i--;//�ٳ����Ƿ��Ǹ�������ͬ����
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
		if (num > 1) count++;//�ؼ���600154635 = 3 x 5 x 40010309
		cout<<count<<endl;
	}

	return 0;
}