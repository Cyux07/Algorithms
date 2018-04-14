#include<stdio.h>
#include<iostream>

using namespace std;

//TODO Wrong Answer at Test 1

int mainBaseFreTable() {

	//bool flag = true;
	int length = 0, index = 0, index1 = 0, x = 0;

	int *i = new int[5000];
	cin >> length;
	while (index < length)
		cin >> i[index++];
	cin >> x;
	index1 = index;
	while (index > 0 )
	{
		cout << i[--index] << ' ';
	}
	cout << endl;
	while (index1 > 0)
	{
		if (i[--index1] != x)
		{
			cout << i[index1] << ' ';
		}
		/*ÁôµÚÒ»¸ö
		if (i[--index1] != x || flag)
		{
			cout << i[index1] << ' ';
			flag = false;
		}
		*/
	}
	cout << endl;

	//char
	//char *c = new char[200];
	{
		char tempc = ' ';
		cin >> length;
		while (index < length) {
			cin >> tempc;
			i[index++] = tempc;
		}
		cin >> tempc;
		x = tempc;
	}
	index1 = index;
	while (index > 0)
	{
		cout << (char) i[--index] << ' ';
	}
	cout << endl;
	while (index1 > 0)
	{
		if (i[--index1] != x)
		{
			cout << (char) i[index1] << ' ';
		}
	}
	cout << endl;

	//float
	double *f = new double[5000];
	double xd = .0;
	cin >> length;
	while (index < length)
		cin >> f[index++]; 
	cin >> xd;
	index1 = index;
	while (index > 0)
	{
		cout << f[--index] << ' ';
	}
	cout << endl;
	while (index1 > 0)
	{
		if (f[--index1] - xd < 0.000001)
		{
			cout << f[index1] << ' ';
		}
	}
	cout << endl;

	return 0;
}

