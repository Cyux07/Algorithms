/*
��Ŀ����
�����ֻ�����������ĸ�ķ�ʽ�����������ѵ�ʱ�� �磺a,b,c���ڡ�1�����ϣ�����aֻ��Ҫ��һ�Σ�
����c��Ҫ���������Ρ� ������������ַ�����ͬһ�������ϣ����ֱ�Ӱ����磺ad��Ҫ�����£�
kz��Ҫ��6�� ����������ַ���ͬһ�������ϣ�����������֮����Ҫ��һ��ʱ�䣬��ac���ڰ���a֮��
��Ҫ��һ������ܰ�c�� ���ڼ���ÿ��һ����Ҫ����һ��ʱ��Σ��ȴ�ʱ����Ҫ��������ʱ��Ρ�
���ڸ���һ���ַ�����Ҫ�����������Ҫ���ѵ�ʱ�䡣
��������:
һ�����Ȳ�����100���ַ���������ֻ���ֻ��������е�Сд��ĸ
�������:
������ܰ����������ݣ�����ÿ�����ݣ��������Input�����ַ�������Ҫ��ʱ��
ʾ��1
����

bob
www
���

7
7
*/

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<iostream>

using namespace std;

int mainPhoneTable() {
	int nums[26] = {
		1, 1, 1, //abc
		2, 2, 2, //def
		3, 3, 3, //ghi
		4, 4, 4, //jkl
		5, 5, 5, //mno
		6, 6, 6, 6, //pqrs
		7, 7, 7, //tuv
		8, 8, 8, 8 //wxyz
	};

	int times[26] = { // int *times ��ʼֵ�趨��̫�ࣿ
		1, 2, 3, //abc
		1, 2, 3, //def
		1, 2, 3, //ghi
		1, 2, 3, //jkl
		1, 2, 3, //mno
		1, 2, 3, 4, //pqrs
		1, 2, 3,//tuv
		1, 2, 3, 4//wxyz
	};

	char str[100];

	int sum = 0, alpha, prior;
		
	while (cin >> str)// cin?//scanf_s("%s", str) != EOF
	{
		sum = 0; 
		prior = 26;//���ò������Ҫ�ۣ�����
		for(int i = 0; i  < strlen(str); i++)
		{
			alpha = str[i] - 97;
			sum += times[alpha];
			if (prior < 26 && nums[prior] == nums[alpha])
			{
				cout << "prior" << prior << ",alpha" << alpha << endl;
				sum += 2;
			}

			prior = alpha;
		}
		cout << sum << endl;
	}

	return 0;
}