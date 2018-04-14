#include"AVLTree.h"
#include<stdio.h>
#include<iostream>

typedef AVLTree *Position;
typedef AVLTree *Tree;

void mainNotNow()
{
	Position p = new AVLTree();

	
}

//its a AVL tree
void testTree() {
Tree test_tree = new AVLTree(1);
	DudeElement *ele = NULL;
	for (int i = 2; i <= 7; i++)
	{
		ele = new DudeElement(i);
		test_tree = test_tree->Insert(ele);
	}

	for (int i = 16; i >= 10; i--)
	{
		ele = new DudeElement(i);
		test_tree = test_tree->Insert(ele);
	}
	ele = new DudeElement(8);
	test_tree = test_tree->Insert(ele);
	ele = new DudeElement(9);
	test_tree = test_tree->Insert(ele);

	test_tree->print();
	getchar();
	delete test_tree;
}

void funnyCode() {
	using namespace std;
	int a = 5, b = 1;
	while (a--\
		\
		\
		\
		\
			   > b) {
		cout << a << endl;
	}
}