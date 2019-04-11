package com.jt.sort;

/**
 * 非比较类排序：基数排序
 * 算法思想
 * 基数排序属于“分配式排序”（distribution sort），是非比较类线性时间排序的一种，又称“桶子法”（bucket sort），
 * 顾名思义，它是透过键值的部分信息，将要排序的元素分配至某些“桶”中，藉以达到排序的作用。
 * 平均时间复杂度：O(dn)(d即表示整形的最高位数)。 
 * 空间复杂度：O(10n) （10表示0~9，用于存储临时的序列） 
 * 稳定性：稳定
 * 注意事项：需要排序几次，需要根据序列中最大的值的位数，eg：max=56546,则需要按顺序（或逆序）：按个位，十位，百位，千位，万位按顺序，并都执行：分桶，并收集的过程；
 * 基数排序的方式可以采用LSD（Least significant digital）或MSD（Most significant digital），LSD的排序方式由键值的最右边开始，而MSD则相反，由键值的最左边开始。 
 * @author maji48
 *
 */
public class RadixSort {
	
	static final int ONE = 1;
	static final int TEN = 10;
	static final int HUNDRED = 100;
	static final int THOUSAND = 1000;
	
	public static void main(String[] args) {
		Integer[] arr = {3314,2546,35,12,1,233,332,7848,5454,607,50,888,9,222,3999,4,6444,4,5555,6444,4448,19};
		radixSort(arr);
	}
	
	
	/**
	 * 算法过程描述
	 * 基数排序（以整形为例），将整形10进制按每位拆分，然后从低位到高位依次比较各个位。主要分为两个过程： 
	 * (1)分配，先从个位开始，根据位值(0-9)分别放到0~9号桶中（比如６4，个位为４，则放入４号桶中）； 
	 * (2)收集，再将放置在0~9号桶中的数据按顺序放到数组中； 
	 * 重复(1)(2)过程，从个位到最高位（比如32位无符号整形最大数4294967296，最高位为第10位）。
	 * 
	 * @param arr
	 */
	public static void radixSort(Integer[] arr){ 
		System.out.print("\n输入：");
		show(arr);
		// 按个位分桶，收集
		Integer[] tmpArr = createBucket(arr,RadixSort.ONE);
		System.out.print("\n排序1：");
		show(tmpArr);
		// 按十位分桶，收集
		tmpArr = createBucket(tmpArr,RadixSort.TEN);
		System.out.print("\n排序2：");
		show(tmpArr);
		// 按百位分桶，收集
		tmpArr = createBucket(tmpArr,RadixSort.HUNDRED);
		System.out.print("\n排序3：");
		show(tmpArr);
		// 按千位分桶，收集
		tmpArr = createBucket(tmpArr,RadixSort.THOUSAND);
		System.out.print("\n排序4：");
		show(tmpArr);
	}
	
	public static Integer[] createBucket(Integer[] arr,int type){
		Integer[][] bucketArr=new Integer[10][arr.length];
		// 分配
		int tmpBucketIndex = 0;
		for(int i=0;i<arr.length;i++){
			switch(type){
			case ONE:// 按个位分桶，排序
				tmpBucketIndex = arr[i]%10;
				bucketArr[tmpBucketIndex][i]=arr[i];
				break;
			case TEN:// 按十位分桶，排序
				tmpBucketIndex = (arr[i]%100-arr[i]%TEN)/TEN;
				bucketArr[tmpBucketIndex][i]=arr[i];
				break;
			case HUNDRED:// 按百位分桶 
				tmpBucketIndex = (arr[i]%1000-arr[i]%HUNDRED)/HUNDRED;
				bucketArr[tmpBucketIndex][i]=arr[i];
				break;
			case THOUSAND:// 按千位分桶
				tmpBucketIndex = (arr[i]%10000-arr[i]%THOUSAND)/THOUSAND;
				bucketArr[tmpBucketIndex][i]=arr[i];
				break;
				default:
			}
		}
		// 收集
		Integer[] result = new Integer[arr.length];
		int index = 0;
		for(int bucket=0;bucket<10;bucket++){
			for(int i=0;i<result.length;i++){
				if(bucketArr[bucket][i]!=null){
					result[index]=bucketArr[bucket][i];
					index ++;
				}
			}	
		}
		return result;
	} 

	

	public static void show(Integer[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",\t");
		}
	}
}
