package com.jt.sort;

/**
 * 希尔排序，又叫缩小增量排序。该方法实质上是一种分组插入方法
 * 希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序，同时该算法是冲破O(n2）的第一批算法之一。
 * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
 * 希尔排序是把记录按下表的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 * 算法描述
 * 我们来看下希尔排序的基本步骤，在此我们选择增量gap=length/2，缩小增量继续以gap = gap/2的方式，这种增量选择我们可以用一个序列来表示，
 * {n/2,(n/2)/2...1}，称为增量序列。希尔排序的增量序列的选择与证明是个数学难题，我们选择的这个增量序列是比较常用的，也是希尔建议的增量，称为希尔增量，但其实这个增量序列不是最优的。
 * 核心逻辑：
 * 1、先将整个待排序的记录序列分割成为若干子序列
 * 2、然后分别进行直接插入排序
 * 算法稳定性：非稳定型
 * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
 * 1、插入排序在对几乎已经排好序的数据操作时，效率高，即可以达到线性排序的效率。
 * 2、但插入排序一般来说是低效的，因为插入排序每次只能将数据移动一位。
 * @author maji48
 *
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] arr = {3314,2546,35,12,1,233,332,7848,5454,607,50,888,9,222,3999,4,6444,4,5555,6444,4448,19};
		testShellSort(arr);
	}
	
	/** 希尔排序在效率上较直接插入排序有较大的改进
	 * 是插入排序的优化版
	 * 算法描述：
	 * 1、选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
	 * 2、按增量序列个数k，对序列进行k 趟排序；
	 * 3、每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
	 * @param arr
	 */
	public static void testShellSort(int[] arr){
	
		// 分组计算
		int gap = arr.length/2;
		while(gap>0){
			// 插入排序
			for(int i=gap;i<arr.length;i++){
				int current = arr[i];
				int preIndex = i - gap;
                while (preIndex >= 0 && arr[preIndex] > current) {
                	arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                arr[preIndex + gap] = current;
			}
			System.out.print("\n分组：gap="+gap+"\n结果：");
			show(arr);
			// 分组计算
			gap = gap/2;
		}
	}
	
	public static void show(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",\t");
		}
	}
	
}
