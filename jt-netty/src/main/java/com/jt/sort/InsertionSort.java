package com.jt.sort;

/**
 * 插入排序
 * 算法描述：一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
 * 1、从第一个元素开始，该元素可以认为已经被排序；
 * 2、取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 3、如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 4、重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 5、将新元素插入到该位置后；
 * 6、重复步骤2~5。
 * 算法分析 ： 最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
 * @author maji48
 *
 */
public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = {22,321,2,3,45,64,3,4,545,3,34,232,43,454,56,6,78,9,87};
		System.out.print("输入：");
		show(arr);
		testInsertionSort(arr); 
		System.out.print("\n排序：");
		show(arr);
	}
	
	/**
	 * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
	 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
	 * 插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外空间的排序），
	 * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
	 * @param arr
	 */
	public static void testInsertionSort(int[] arr){
		if(arr==null||arr.length<2){
			return;
		}
		// 从第一个元素开始，该元素可以认为已经被排序；所以临时变量保存arr[1]
		int tmpInt = 0;
		for(int i=1;i<arr.length;i++){
			tmpInt=arr[i];
			int j=i-1;
			// 从i-1开始往前进行比较，若tmpInt<arr[j]，则将arr[j+1]=arr[j]，即往后移动一位；
			for(;j>=0;j--){
				if(tmpInt<arr[j]){
					arr[j+1]=arr[j];
				}else{
					break;
				}
			}
			// 凡是遍历结束或者发现tmpInt>=arr[j],则停止，并执行插入操作
			arr[j+1]=tmpInt;
		}
	}
	
	public static void show(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",\t");
		}
	}
}
