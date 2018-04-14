#include<stdio.h>
#include<iostream>

using namespace std;
int mainBaseMaxmin() {
	//int *num = new int[100];
	int max = -1, min = 101;
	int length = 0;
	int num = 0;

	while (cin >> length)
	{
		while (length-- && cin >> num)
		{
			if (num < min)
			{
				min = num;
			}
			if (num > max)
			{
				max = num;
			}
		}
		cout << max << ' ' << min << endl;
		max = -1; min = 101;
	}

	return 0;
}