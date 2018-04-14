#include<stdio.h>
#include<iostream>
#include "AVLTree.h"

#ifndef max
#define max(a,b) ((a) > (b) ? (a) : (b))
#endif


typedef AVLTree *Position;
typedef AVLTree *Tree;

AVLTree::AVLTree(int v)
{
	DudeElement *e = new DudeElement(v);
	//e->value  = v;
	//AVLTree(e);
	Element = e;
}

AVLTree::AVLTree(DudeElement* e)
{
	Element = e;
}


AVLTree::~AVLTree()
{
	//free(X);
	delete Element;
}

Tree AVLTree::MakeEmpty(Tree tree)
{
	//TODO impl
	return Tree();
}

Position AVLTree::Find(DudeElement *X, Tree T)
{
	//TODO impl
	return Position();
}

Position AVLTree::FindMin(Tree T)
{
	return Position();
}

Position AVLTree::FindMax(Tree T)
{
	return Position();
}

Tree AVLTree::Insert(DudeElement *X, Tree T)
{
	if (T == NULL)
	{
		T = new AVLTree();//(AVLTree*)malloc(sizeof(AVLTree));
		if (T != NULL)
		{
			T->Element = X;
			T->height = 0;
			T->Left = T->Right = NULL;
		}
		else
		{
			std::cout << "error! no space for one leap" << std::endl;
		}
	}//empty tree put root
	else
	if (*X < *(T->Element))
	{//put left
		T->Left = Insert(X, T->Left);
		if (Height(T->Left) - Height(T->Right) >= 2)
		{
			if (*X < *(T->Left->Element))
			{
				T = OddRotateWithLeft(T);
			}
			else
			{
				T = EvenRotateWithLeft(T);
			}
		}
	}
	else
	if (*X > *(T->Element))
	{//put right
		T->Right = Insert(X, T->Right);
		if (Height(T->Right) - Height(T->Left) >= 2)
		{
			if (*X > *(T->Right->Element))
			{
				T = OddRotateWithRight(T);
			}
			else
			{
				T = EvenRotateWithRight(T);
			}
		}
	} 

	T->height = max(Height(T->Left), Height(T->Right)) + 1;

	return T;
}

Tree AVLTree::Insert(DudeElement *X)
{
	return this->Insert(X, this);
}

Position AVLTree::OddRotateWithLeft(Position K2)
{
	Tree K1 = K2->Left;
	K2->Left = K1->Right;
	K1->Right = K2;

	K2->height = max(Height(K2->Left), Height(K2->Right)) + 1;//+self
	K1->height = max(Height(K1->Left), Height(K1->Right)) + 1;
	
	return K1;
}

Position AVLTree::OddRotateWithRight(Position K2)
{
	Tree K1 = K2->Right;
	K2->Right = K1->Left;
	K1->Left = K2;

	//update height
	K2->height = max(Height(K2->Left), Height(K2->Right)) + 1;
	K1->height = max(Height(K1->Left), Height(K1->Right)) + 1;

	return K1;
}

Position AVLTree::EvenRotateWithLeft(Position K3)
{
	Tree K2 = OddRotateWithRight(K3->Left);

	return OddRotateWithLeft(K2);
}

Position AVLTree::EvenRotateWithRight(Position K3)
{
	Tree K2 = OddRotateWithLeft(K3->Right);

	return OddRotateWithRight(K2);
}

int AVLTree::Height(AVLTree *tree)
{
	if (tree == NULL)
	{
		return -1;
	}
	else
	{
		return tree->height;
	}
}

void AVLTree::print()
{
	std::cout << "self:" << this->Element->value << std::endl;
	std::cout << "left----------------------------------------:" << std::endl;
	if (this->Left != NULL)
	{
		this->Left->print();
	}
	std::cout << "left-over----------------------------------:" << std::endl; 
	std::cout << "right----------------------------------------:" << std::endl;
	if (this->Right != NULL)
	{
		this->Right->print();
	}
	
	std::cout << "right-over----------------------------------:" << std::endl;

}
