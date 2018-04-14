/*
题目描述
按照手机键盘输入字母的方式，计算所花费的时间 如：a,b,c都在“1”键上，输入a只需要按一次，
输入c需要连续按三次。 如果连续两个字符不在同一个按键上，则可直接按，如：ad需要按两下，
kz需要按6下 如果连续两字符在同一个按键上，则两个按键之间需要等一段时间，如ac，在按了a之后，
需要等一会儿才能按c。 现在假设每按一次需要花费一个时间段，等待时间需要花费两个时间段。
现在给出一串字符，需要计算出它所需要花费的时间。
输入描述:
一个长度不大于100的字符串，其中只有手机按键上有的小写字母
输出描述:
输入可能包括多组数据，对于每组数据，输出按出Input所给字符串所需要的时间
示例1
输入

bob
www
输出

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

	int times[26] = { // int *times 初始值设定项太多？
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
		prior = 26;//重置步骤很重要哇！！！
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