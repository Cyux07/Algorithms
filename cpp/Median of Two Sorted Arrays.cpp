/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

找两有序数组的中位数，要求时间复杂度O(log(m+n))
*/

#include<stdio.h>
#include<vector>
#include<iostream>

using namespace std;

double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2);


int main() {
	vector<int> nums1 = { 1, 2 },//{},//{1, 2},
		nums2 = { 1,2 };//{3,4,7};//{1, 2, 3};//The median is (2 + 3)/2 = 2.5
	cout << findMedianSortedArrays(nums1, nums2);

	getchar();
	return 0;
}

//deprecated
double old_findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
	int i = 0, j = 0, n = nums1.size(), m = nums2.size(), count = 0, median = 0;
	while (i < n && j < m)
	{
		if (count == (n + m) / 2 && ((n + m) / 2 * 2 == n + m)) //偶长度，遇偶前
		{
			return (0.0 + median + nums1[i] < nums2[j] ? nums1[i] : nums2[j]) / 2;
		}
		else if (count == (n + m) / 2 + 1 || count == (n + m)/2 + 2) //奇长度，遇奇正中 or 偶长度，遇偶后
		{
			return median;
		}

		if (nums1[i] < nums2[j]) {
			median = nums1[i++]; count++;
		}
		else if (nums1[i] > nums2[j]) {
			median = nums2[j++]; count++;
		}
		else
		{
			i++; j++; count += 2;
		}
	}

	if (count == (n + m) / 2 + 1)
		return median;
	
	while (i < n)
	{
		if (count == (n + m) / 2 && ((n + m) / 2 * 2 == n + m))
		{
			return (0.0 + median/*(j != m ? median : nums2[m - 1])*/ + nums1[i]) / 2;
		}
		else if(count == (n + m) / 2 + 1)
		{
			return nums1[i];
		}

		median = nums1[i++];  count++; //j++;
	}
	if (count == (n + m) / 2 + 1)
		return median;

	while (j < m)
	{
		if (count == (n + m) / 2 && ((n + m) / 2 * 2 == n + m))
		{
			return (0.0 + median/*(i != n ? median : nums1[n-1])*/ + nums2[j]) / 2;
		}
		else if(count == (n + m) / 2 + 1)
		{
			return nums2[j];
		}

		median = nums2[j++];  count++; //i++;
	}
	if (count == (n + m) / 2 + 1)
		return median;
}


/**25.76%*/
double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
	int i = 0, j = 0, n = nums1.size(), m = nums2.size(), count = 0, limit = (n + m)/2;
	if ((n + m) / 2 * 2 == n + m) {//偶长度
		int median1, median2;
		while (i < n && j < m)
		{
			if (nums1[i] < nums2[j]) {
				median1 = nums1[i++]; count++;
			}
			else if (nums1[i] > nums2[j]) {
				median1 = nums2[j++]; count++;
			}
			else
			{
				count += 2;
				median1 = nums1[i]; i++; j++;
			}

			if (count == limit)
			{
				if (i < n && j < m)
					return (0.0 + median1 + (nums1[i] < nums2[j] ? nums1[i] : nums2[j])) / 2;
				else if(i == n)
					return (median1 + nums2[j] + 0.0) / 2;
				else if (j == m)
					return (median1 + nums1[i] + 0.0) / 2;
			}
			else if (count > limit) {
				return median1;
			}
		}//while

		while (i < n)
		{
			
			i++; count++;
			if (count == limit)
				return (nums1[i] + nums1[i - 1] + 0.0) / 2;
			
		}

		while (j < m)
		{
			j++; count++;
			if (count == limit)
				return (nums2[j] + nums2[j - 1] + 0.0) / 2;
		}
	}
	else//奇长度
	{
		int median;
		while (i < n && j < m)
		{
			if (nums1[i] < nums2[j]) {
				median = nums1[i++]; count++;
			}
			else if (nums1[i] > nums2[j]) {
				median = nums2[j++]; count++;
			}
			else
			{
				count += 2;
				median = nums1[i]; i++; j++;
			}

			if (count >= limit + 1)
			{
				return median;/*
			}
			else if(count==limit + 2)
			{
				if (i == n)
					return nums2[j];
				else if (j == m)
					return nums1[i];
				else
					return nums1[i] < nums2[j] ? nums1[i] : nums2[j];*/
			}
		}//while

		while (i < n)
		{
			median = nums1[i++]; count++;
			if (count == limit + 1)
				return median;
		}

		while (j < m)
		{
			median = nums2[j++]; count++;
			if (count == limit + 1)
				return median;
		}
	}
}