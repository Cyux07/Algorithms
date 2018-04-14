#include<stdio.h>
#include<string>
#include<malloc.h>
#include<iostream>
#include<queue>

using namespace std;

typedef  struct TreeNode *PtrToNode;
typedef  struct NormalTreeNode *Node;

struct TreeNode
{
	char* name; //char name[20] 类型是 const char*
	PtrToNode firstChild;
	PtrToNode nextSibling;
};

struct NormalTreeNode
{
	int value;
	NormalTreeNode *left = NULL;
	NormalTreeNode *right = NULL;
};

typedef struct SonBrotherTree
{
	int value;
	SonBrotherTree *son = NULL, *bro = NULL;
} *Sbtree;

template<typename Type>
void Build(SonBrotherTree &root, Type* sbs, int* deg, int n);
Node GetSampleTree();
bool CheckSorted(NormalTreeNode *root);

void mainTTT() 
{
	PtrToNode root = (PtrToNode)malloc(sizeof(struct TreeNode));
	TreeNode node = {
		"halo",
		NULL,NULL
	};

	PtrToNode toit = &node;

	/*float* f = (float*) malloc(sizeof(float));
	cout << "before give value" << &*f << endl;
	*f = 0.01f;
	cout << "after :" << &*f << endl;

	cout << *f << endl;

	cout << "name is " << node.name << endl;
	cout << "name is " << (int)(&*toit) << endl;
	cout << "name is " << (int)(&node) << endl;

	root->name = "root";
	cout << "root is " << root->name << endl;
	*/
	char c[] = {"fasdfas"};

	string s = c;
	printf("%s", s.c_str());

	////////////////////////////////////////////////////////////
	//孩子兄弟
	int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
	int degree[] = {2, 2, 3, 0, 0, 0, 0, 0};
	Sbtree sbtreeroot = new SonBrotherTree;
	sbtreeroot->value = arr[0];
	Build(*sbtreeroot, arr, degree, 1);

	//////////////////////////////////////////////////////////////
	Node bstroot = GetSampleTree();
	cout << "is bst? :" << CheckSorted(bstroot) << endl;
	printf("%s", CheckSorted(bstroot) ? "true" : "false");
	fputs(CheckSorted(bstroot) ? "true" : "false", stdout);
	getchar();
}

//计算孩子兄弟表示法下的叶子节点数P146
int CountLeaf(Sbtree tree) {
	if (!tree)//为空
	{
		return 0;
	}

	if (!tree->son)//无孩子
	{
		return CountLeaf(tree->bro) + 1;
	}
	else
	{
		return CountLeaf(tree->son) + CountLeaf(tree->bro);
	}
}

//递归求孩子兄弟表示法下的树深度
int Deep(Sbtree tree) {
	if (!tree)
	{
		return 0;
	}

	int sh = Deep(tree->son) + 1;
	int bh = Deep(tree->bro);

	return sh > bh ? sh : bh;
}

/**
*root当前节点
sbs整个节点的值序列
deg每个节点的度，下标和sbs一致
n数组长度                /X(孩子节点在数组中的开始位置)*/
template<typename Type>
void Build(SonBrotherTree &root, Type* sbs, int* deg, int n)
{
	//两个标，一个记录遍历位置，一个记录添加子/弟位置
}

/*
{
	Sbtree son = new SonBrotherTree;//(Sbtree)malloc(sizeof(SonBrotherTree));
	son->value = sbs[n];
	root.son = son;//度1为儿子

	Sbtree bro = NULL;
	int degree = 0;
	while (++degree <= deg[n])
	{
		//bro = (Sbtree)malloc(sizeof(SonBrotherTree));
		bro = new SonBrotherTree;
		bro->value = sbs[n + degree];
		son->bro = bro;
		son = bro;//指向下一个，迭代链接下一个兄弟的右兄弟
		Build(*bro, sbs, deg, SonLocation(deg, n + degree));//（递归）此bro建立自己的儿子
	}
	
}

int SonLocation(int *deg, int n)
{
	int sum = 1;
	for (int i = 0; i < n; i++)
	{
		sum += deg[i];
	}

	return sum + 1;
}
Sbtree cur = root;
	for (int i = 0; i < n; i++)
	{
		if (deg[i] == 0)//出度为0， 没有儿子
		{
			continue;
		}
		Sbtree son = (Sbtree)malloc(sizeof(SonBrotherTree));
		son->value = sbs[i];
		cur->son = son;

		if (deg[i] == 1) {//出度为1， 一个儿子，儿子没有右兄弟
			cur = son;//迭代到下一个找儿子
			continue;
		}

		Sbtree bro = (Sbtree)malloc(sizeof(SonBrotherTree));
		for (int j = i+1; j < deg[i]; j++)//度数-1 个右兄弟
		{
			bro->value = sbs[j];
			son->bro = bro;
			son = bro;//指向下一个，迭代链接下一个兄弟的右兄弟
		}
	}
	*/

//用例树，如p166图
Node GetSampleTree()
{
	Node root = new NormalTreeNode;
	root->value = 50;

	Node node = new NormalTreeNode;
	node->value = 38;
	root->left = node;
	Node node2l = new NormalTreeNode;
	node2l->value = 30;
	node->left = node2l;
	Node node2r = new NormalTreeNode;
	node2r->value = 45;
	node->right = node2r;
	Node node3l = new NormalTreeNode;
	node3l->value = 40;
	node2r->left = node3l;
	Node node3r = new NormalTreeNode;
	node3r->value = 48;
	node2r->right = node3r;

	node = new NormalTreeNode;
	node->value = 70;
	root->right = node;
	node2l = new NormalTreeNode;
	node2l->value = 60;
	node->left = node2l;
	node2r = new NormalTreeNode;
	node2r->value = 75;
	node->right = node2r;
	node3r = new NormalTreeNode;
	node3r->value = 80;
	node2r->right = node3r;

	return root;
}

bool CheckSorted(NormalTreeNode *root)
{
	bool sorted = true;
	if (root->left)
	{
		sorted = CheckSorted(root->left) 
			&& root->value > root->left->value;
	}

	if (root->right)
	{
		sorted = CheckSorted(root->right) 
			&& root->value < root->right->value;
	}

	return sorted;
}