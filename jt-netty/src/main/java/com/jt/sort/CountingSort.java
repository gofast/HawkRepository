package com.jt.sort;

/**
 * 非比较类排序：计数排序
 * 算法原理：
 * 对于给定的输入序列中的每一个元素x，确定该序列中值小于x的元素的个数。一旦有了这个信息，就可以将x直接存放到最终的输出序列的正确位置上。
 * 当然，如果有多个元素具有相同的值时，我们不能将这些元素放在输出序列的同一个位置上，在代码中作适当的修改即可。
 * 算法复杂度：Ο(n+k)
 * 空间复杂度：Ο(k)
 * 要求：待排序数中最大数值不能太大
 * 注意：计数排序是典型的以空间换时间的排序算法，对待排序的数据有严格的要求，比如待排序的数值中包含负数，最大值都有限制，请谨慎使用。
 * @author maji48
 *
 */
public class CountingSort {

	public static void main(String[] args) {
		Integer[] arr = {34,2,3,12,1,23,3,4,5,67,5,8,9,2,3,4,52,4,5,6,8,9};
		System.out.print("\nsize:"+arr.length); 
		Integer[] result = testCountingSort(arr);
		System.out.print("\n输入：");
		show(arr);
		System.out.print("\n排序：");
		show(result);
	}
	
	
	public static Integer[] testCountingSort(Integer[] arr){
		int[] indexArr = new int[arr.length];
		Integer[] result = new Integer[arr.length];
		for(int i=0;i<arr.length;i++){
			for(int j=i+1;j<arr.length;j++){
				if(i==j||arr[i]==arr[j]){
					continue;
				}else if(arr[i]>arr[j]){
					indexArr[i]++;
				}else if(arr[i]<arr[j]){
					indexArr[j]++;
				}
			}
			int tmpIndex = indexArr[i];
			while(result[tmpIndex]!=null){
				tmpIndex++;
			}
			if(tmpIndex<result.length){
				result[tmpIndex]=arr[i];
			}
		}
		System.out.print("\n计数：");
		show(indexArr); 
		return result;
	}
	

	public static void show(Integer[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",\t");
		}
	}
	
	public static void show(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",\t");
		}
	}
}
