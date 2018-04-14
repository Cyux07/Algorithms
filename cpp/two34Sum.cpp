/*数组中两数之和，三数之和，四数之和 共三题目*/
/**
题一，两数之和等于target
题二，三数之和等于0*/
/*
此处有：HashMap引用
*/
#include<stdio.h>
#include<vector>
#include<unordered_map>
#include<algorithm>
#include<iostream> //

using namespace std;
vector<int> twoSum(vector<int> &numbers, int target);
vector<vector<int>> threeSum(vector<int>& nums);
vector<vector<int>> fourSum1(vector<int>& nums, int target);

int main234Sum() {
	//two
	vector<int> nums = { 2, 7, 11, 15 };
	int target = 9;
	for each (int num in twoSum(nums, target))
	{
		cout << num << endl;
	}

	//three
	vector<int> S = {-1, 0,0, 0, 0, 1, 2, -1, -4};
	for each (vector<int> triplet in threeSum(S))
	{
		for each (int num in triplet)
			cout << num ;
		cout <<endl;
	}
	cout << endl;

	//four1
	vector<int> SF = { 1, 0, -1, 0, -2, 2, 0, 0, 0,1, -2, -5, -4, -3, 3, 3, 5 };
	for each (vector<int> quadruplet in fourSum1(SF, -11))
	{
		for each (int num in quadruplet)
			cout << num;
		cout << endl;
	}
	getchar();
	return 0;
}

vector<int> twoSum(vector<int> &numbers, int target)
{
	//Key is the number and value is its index in the vector.
	unordered_map<int, int> hash;
	vector<int> result;
	for (int i = 0; i < numbers.size(); i++) {
		int numberToFind = target - numbers[i];

		//if numberToFind is found in map, return them
		if (hash.find(numberToFind) != hash.end()) {//find返回迭代器,end标准空结尾，意指[begin,end)
			//+1 because indices are NOT zero based
			result.push_back(hash[numberToFind] /*+ 1*/);
			result.push_back(i/* + 1*/);
			return result;
		}

		//number was not found. Put it in the map.
		hash[numbers[i]] = i;
	}
	return result;
}

vector<vector<int>> threeSum(vector<int>& nums) 
{
	vector<vector<int>> ans;
	int i, l, r, n = nums.size();
	sort(nums.begin(), nums.end());

	for (i = 0; i < n - 2; i++)
	{
		if (nums[i] > 0)
			break;
		if (i > 0 && nums[i] == nums[i - 1])
			continue;

		l = i + 1; r = n - 1;
		while (l < r) {
			if (nums[i] + nums[l] + nums[r] == 0)
			{
				vector<int> temp;
				temp.push_back(nums[i]);
				temp.push_back(nums[l]);
				temp.push_back(nums[r]);
				ans.push_back(temp);

				//avoid duplicate
				while (l < r && nums[l] == nums[l + 1])l++;
				while (l < r && nums[r] == nums[r - 1])r--;
				l++; r--;
			}
			else if (nums[i] + nums[r] + nums[l] < 0)
			{
				l++;
			}
			else
			{
				r--;
			}
		}//while lr end
	}//for i end

	return ans;
}

/**篡改threeSum，复杂度On3*/
vector<vector<int>> fourSum1(vector<int>& nums, int target)
{
	vector<vector<int>> ans;
	int i, l, r, n = nums.size();
	sort(nums.begin(), nums.end());
	for (int four = 0; four < n - 3; four++) {
		//if (nums[four] > target)break;
		if (nums[four] * 4>target) break;// Too Big!!//optimize
		if (nums[four] + 3 * nums[n - 1]<target) continue;//Too Small//optimize
		if (four > 0 && nums[four] == nums[four - 1])
			continue;
		for (i = four + 1; i < n - 2; i++)
		{
			if (i > four + 1 && nums[i] == nums[i - 1])
				continue;

			l = i + 1; r = n - 1;
			while (l < r) {
				if (nums[i] + nums[l] + nums[r] == target - nums[four])
				{
					vector<int> temp;
					temp.push_back(nums[four]);//
					temp.push_back(nums[i]);
					temp.push_back(nums[l]);
					temp.push_back(nums[r]);
					ans.push_back(temp);

					//avoid duplicate
					while (l < r && nums[l] == nums[l + 1])l++;
					while (l < r && nums[r] == nums[r - 1])r--;
					l++; r--;
				}
				else if (nums[i] + nums[r] + nums[l] < target - nums[four])
				{
					l++;
				}
				else
				{
					r--;
				}
			}//while lr end
		}//for i end
	}

	return ans;
}