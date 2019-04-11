package com.jt.sort;

/**
 * ��������
 * �㷨������һ����˵���������򶼲���in-place��������ʵ�֡������㷨�������£�
 * 1���ӵ�һ��Ԫ�ؿ�ʼ����Ԫ�ؿ�����Ϊ�Ѿ�������
 * 2��ȡ����һ��Ԫ�أ����Ѿ������Ԫ�������дӺ���ǰɨ�裻
 * 3�������Ԫ�أ������򣩴�����Ԫ�أ�����Ԫ���Ƶ���һλ�ã�
 * 4���ظ�����3��ֱ���ҵ��������Ԫ��С�ڻ��ߵ�����Ԫ�ص�λ�ã�
 * 5������Ԫ�ز��뵽��λ�ú�
 * 6���ظ�����2~5��
 * �㷨���� �� ��������T(n) = O(n)   ������T(n) = O(n2)   ƽ�������T(n) = O(n2)
 * @author maji48
 *
 */
public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = {22,321,2,3,45,64,3,4,545,3,34,232,43,454,56,6,78,9,87};
		System.out.print("���룺");
		show(arr);
		testInsertionSort(arr); 
		System.out.print("\n����");
		show(arr);
	}
	
	/**
	 * ��������Insertion-Sort�����㷨������һ�ּ�ֱ�۵������㷨��
	 * ���Ĺ���ԭ����ͨ�������������У�����δ�������ݣ��������������дӺ���ǰɨ�裬�ҵ���Ӧλ�ò����롣
	 * ����������ʵ���ϣ�ͨ������in-place���򣨼�ֻ���õ�O(1)�Ķ���ռ�����򣩣�
	 * ����ڴӺ���ǰɨ������У���Ҫ������������Ԫ�������Ųλ��Ϊ����Ԫ���ṩ����ռ䡣
	 * @param arr
	 */
	public static void testInsertionSort(int[] arr){
		if(arr==null||arr.length<2){
			return;
		}
		// �ӵ�һ��Ԫ�ؿ�ʼ����Ԫ�ؿ�����Ϊ�Ѿ�������������ʱ��������arr[1]
		int tmpInt = 0;
		for(int i=1;i<arr.length;i++){
			tmpInt=arr[i];
			int j=i-1;
			// ��i-1��ʼ��ǰ���бȽϣ���tmpInt<arr[j]����arr[j+1]=arr[j]���������ƶ�һλ��
			for(;j>=0;j--){
				if(tmpInt<arr[j]){
					arr[j+1]=arr[j];
				}else{
					break;
				}
			}
			// ���Ǳ����������߷���tmpInt>=arr[j],��ֹͣ����ִ�в������
			arr[j+1]=tmpInt;
		}
	}
	
	public static void show(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",\t");
		}
	}
}
