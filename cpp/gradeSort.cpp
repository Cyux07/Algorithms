/*#include<stdio.h>
#include<iostream>
#include<malloc.h>
#include<vector>
#include<algorithm>

using namespace std;

class student {
public:
	student(int g, string n) : grade(g), name(n){}
	string name;
	int grade;
	bool operator < (const student &o)const {
		return grade < o.grade;
	 }
};

void getAllValue(int** datas, int n);
student getValue();
void mainGRADESORT()
{
	int n = 0;
	int order = 0;
	//n = getchar();
	cin >> n;
	cin >> order;
	//string* names = (string*)malloc(sizeof(string) * n);
	//int** datas = (int**)malloc(sizeof(int) * n * 2);
	vector<student> stus;
	while (n--)
	{
		student stu = getValue();
		stus.push_back(stu);
	}
	//getValue(datas, n);

	stable_sort(stus.begin(), stus.end());
	if (order)
	{
		reverse(stus.begin(), stus.end());
	}

	for (int i = 0; i < stus.size(); i++) 
	{
		cout << "(" << stus[i].grade << "," << stus[i].name.c_str() << ")\n";
		//printf_s("%s)\n", stus[i].name.c_str());
	}
	getchar();

}
*/
/**deparated*//*
void getAllValue(int** datas, int n )
{
	int size = 0;
	while (size < n)
	{
		cin >> datas[size][0];
		cin >> datas[size][1];
		cin.get();// next line
	}

	//todo not finish
}

student getValue() 
{
	student stu(0, string(""));
	//char* temp = (char *)malloc(20*sizeof(char));
	char temp[20];
	//char* temp = new char[20];
	for (int i = 0; i < 20; i++)
	{
		temp[i] = 'a' + i;
	}

	int i = 0;
	do
	{
		scanf_s("%c", &temp[i]);
	} while (temp[i++] != ' ');
	temp[i] = '\0';
	scanf_s("%d", &stu.grade);
	//scanf_s("%s%d", temp, &stu.grade);
	//cin >> stu.grade;
	getchar();
	stu.name = temp;

	//free(temp);
	return stu;
}

*/