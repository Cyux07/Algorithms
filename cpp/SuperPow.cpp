#include<stdio.h>
#include<vector>

using namespace std;

const int DIV = 1337;
int superPow(int a, vector<int>& b);
int main() {

	return 0;
}

int superPow(int a, vector<int>& b) {
	if (b.empty()) return 1;
	int right_dig = b.back();
	b.pop_back();//return void
	return pow(superPow(a, b), 10) * pow(a, right_dig) % DIV;
}

int pow(int a, int k) {
	a %= DIV;
	long al = a, result = 1;
	int i = 0;
	while (i++<k)
		result = (result * al) % DIV;

	return (int)result;
}
