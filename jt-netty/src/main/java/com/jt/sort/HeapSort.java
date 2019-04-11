package com.jt.sort;

/**
 * 堆排序
 * 原理：指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
 * 关键词/思路：
 * 1、完全二叉树（最大堆/最小堆）：
 * 2、调整为最大堆/最小堆后将的最后一个叶子节点与根节点进行交换；
 * 3、指向无序完全二叉树最后一个叶子节点的指针end往前挪一位；
 * 4、循环以上1,2,3步，直至end=0;
 * 堆排序的结论：稳定
 * size:33, swaper-num:250
 * size:65, swaper-num:686
 * size:129, swaper-num:1767
 * @author maji48
 *
 */
public class HeapSort {

	public static void main(String[] args) {
		testHeapSort();
	}
	static int swaperNum = 0;
	
	public static void testHeapSort(){
		// 数组下标：index
		// 数组下标index=0：是完全二叉树的根节点，左子节点下标：1  ，右子节点下标：2
		// 数组下标index!=0,是完全二叉树的非根节点，左子节点下标：2*index+1  ，右子节点下标：2*index+2
		// 数组最后的一个待排序下标：end
		System.out.print("输入:");
		Integer[] arr = {45,34,3,4,23,44,7,9,0,23,34,34,4,4,55,6,7,8,2,1,1,32,4,3,56,6,778,8,98,9,06775,4,45,34,3,4,23,44,7,9,0,23,34,34,4,4,55,6,7,8,2,1,1,32,4,3,56,6,778,8,98,9,6775,4,45,34,3,4,23,44,7,9,0,23,34,34,4,4,55,6,7,8,2,1,1,32,4,3,56,6,778,8,98,9,06775,4,45,34,3,4,23,44,7,9,0,23,34,34,4,4,55,6,7,8,2,1,1,32,4,3,56,6,778,8,98,9,6775,4,45};
		show(arr);
		int end=arr.length-1;
		while(end>0){
			// 1、构造最大堆
			for(int index=0;index<=end;index++){
				if(index==0){
					if(end>=1&&arr[0]<arr[1]){
						IntegerSwape(arr,0,1);
					}
					if(end>=2&&arr[0]<arr[2]){
						IntegerSwape(arr,0,2);
					}
				}else {
					int left= 2*index+1;
					int right= 2*index+2;
					if(end>=left&&arr[index]<arr[left]){
						IntegerSwape(arr,index,left);
					}
					if(end>=right&&arr[index]<arr[right]){
						IntegerSwape(arr,index,right);
					}
				}
			}
			// 2、最大堆根节点与最后一个元素进行交换
			IntegerSwape(arr,0,end);
			end--;
		}

		System.out.print("\n排序结果:");
		show(arr); 
		System.out.println("\nsize:"+arr.length+", swaper-num:"+swaperNum);
	}
	
	public static void show(Integer[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",");
		}
	}
	
	public static void IntegerSwape(Integer[] arr,int a,int b){ 
		int tmpData = arr[a];   
		arr[a]=arr[b].intValue(); 
		arr[b]=tmpData;
		swaperNum ++;
	}
}
