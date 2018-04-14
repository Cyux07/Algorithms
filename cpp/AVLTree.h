#pragma once
#include<stdint.h>
#include<stdlib.h>
#include<malloc.h>
#include"DudeElement.h"



class AVLTree
{
typedef AVLTree *Position;
typedef AVLTree *Tree;
private:
	DudeElement* Element;
	int height;

	Tree Left;
	Tree Right;


public:
	AVLTree(int value = 0);
	AVLTree(DudeElement* e);
	~AVLTree();

	Tree MakeEmpty(Tree tree);
	Position Find(DudeElement *X, Tree T);
	Position FindMin(Tree T);
	Position FindMax(Tree T);
	Tree Insert(DudeElement *X, Tree T);
	Tree Insert(DudeElement *X);

	static Position OddRotateWithLeft(Position K2);
	static Position OddRotateWithRight(Position K2);
	static Position EvenRotateWithLeft(Position K3);
	static Position EvenRotateWithRight(Position K3);
	static int Height(AVLTree *tree);
	void print();

};

