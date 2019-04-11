package com.jt.sort;

/**
 * ������
 * ԭ��ָ���ö��������ݽṹ����Ƶ�һ�������㷨���ѻ���һ��������ȫ�������Ľṹ����ͬʱ����ѻ������ʣ����ӽ��ļ�ֵ����������С�ڣ����ߴ��ڣ����ĸ��ڵ㡣
 * �ؼ���/˼·��
 * 1����ȫ������������/��С�ѣ���
 * 2������Ϊ����/��С�Ѻ󽫵����һ��Ҷ�ӽڵ�����ڵ���н�����
 * 3��ָ��������ȫ���������һ��Ҷ�ӽڵ��ָ��end��ǰŲһλ��
 * 4��ѭ������1,2,3����ֱ��end=0;
 * ������Ľ��ۣ��ȶ�
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
		// �����±꣺index
		// �����±�index=0������ȫ�������ĸ��ڵ㣬���ӽڵ��±꣺1  �����ӽڵ��±꣺2
		// �����±�index!=0,����ȫ�������ķǸ��ڵ㣬���ӽڵ��±꣺2*index+1  �����ӽڵ��±꣺2*index+2
		// ��������һ���������±꣺end
		System.out.print("����:");
		Integer[] arr = {45,34,3,4,23,44,7,9,0,23,34,34,4,4,55,6,7,8,2,1,1,32,4,3,56,6,778,8,98,9,06775,4,45,34,3,4,23,44,7,9,0,23,34,34,4,4,55,6,7,8,2,1,1,32,4,3,56,6,778,8,98,9,6775,4,45,34,3,4,23,44,7,9,0,23,34,34,4,4,55,6,7,8,2,1,1,32,4,3,56,6,778,8,98,9,06775,4,45,34,3,4,23,44,7,9,0,23,34,34,4,4,55,6,7,8,2,1,1,32,4,3,56,6,778,8,98,9,6775,4,45};
		show(arr);
		int end=arr.length-1;
		while(end>0){
			// 1����������
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
			// 2�����Ѹ��ڵ������һ��Ԫ�ؽ��н���
			IntegerSwape(arr,0,end);
			end--;
		}

		System.out.print("\n������:");
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
