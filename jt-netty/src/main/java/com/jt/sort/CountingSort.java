package com.jt.sort;

/**
 * �ǱȽ������򣺼�������
 * �㷨ԭ��
 * ���ڸ��������������е�ÿһ��Ԫ��x��ȷ����������ֵС��x��Ԫ�صĸ�����һ�����������Ϣ���Ϳ��Խ�xֱ�Ӵ�ŵ����յ�������е���ȷλ���ϡ�
 * ��Ȼ������ж��Ԫ�ؾ�����ͬ��ֵʱ�����ǲ��ܽ���ЩԪ�ط���������е�ͬһ��λ���ϣ��ڴ��������ʵ����޸ļ��ɡ�
 * �㷨���Ӷȣ���(n+k)
 * �ռ临�Ӷȣ���(k)
 * Ҫ�󣺴��������������ֵ����̫��
 * ע�⣺���������ǵ��͵��Կռ任ʱ��������㷨���Դ�������������ϸ��Ҫ�󣬱�����������ֵ�а������������ֵ�������ƣ������ʹ�á�
 * @author maji48
 *
 */
public class CountingSort {

	public static void main(String[] args) {
		Integer[] arr = {34,2,3,12,1,23,3,4,5,67,5,8,9,2,3,4,52,4,5,6,8,9};
		System.out.print("\nsize:"+arr.length); 
		Integer[] result = testCountingSort(arr);
		System.out.print("\n���룺");
		show(arr);
		System.out.print("\n����");
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
		System.out.print("\n������");
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
