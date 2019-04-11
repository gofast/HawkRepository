package com.jt.sort;

/**
 * �ǱȽ������򣺻�������
 * �㷨˼��
 * �����������ڡ�����ʽ���򡱣�distribution sort�����ǷǱȽ�������ʱ�������һ�֣��ֳơ�Ͱ�ӷ�����bucket sort����
 * ����˼�壬����͸����ֵ�Ĳ�����Ϣ����Ҫ�����Ԫ�ط�����ĳЩ��Ͱ���У����Դﵽ��������á�
 * ƽ��ʱ�临�Ӷȣ�O(dn)(d����ʾ���ε����λ��)�� 
 * �ռ临�Ӷȣ�O(10n) ��10��ʾ0~9�����ڴ洢��ʱ�����У� 
 * �ȶ��ԣ��ȶ�
 * ע�������Ҫ���򼸴Σ���Ҫ��������������ֵ��λ����eg��max=56546,����Ҫ��˳�򣨻����򣩣�����λ��ʮλ����λ��ǧλ����λ��˳�򣬲���ִ�У���Ͱ�����ռ��Ĺ��̣�
 * ��������ķ�ʽ���Բ���LSD��Least significant digital����MSD��Most significant digital����LSD������ʽ�ɼ�ֵ�����ұ߿�ʼ����MSD���෴���ɼ�ֵ������߿�ʼ�� 
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
	 * �㷨��������
	 * ��������������Ϊ������������10���ư�ÿλ��֣�Ȼ��ӵ�λ����λ���αȽϸ���λ����Ҫ��Ϊ�������̣� 
	 * (1)���䣬�ȴӸ�λ��ʼ������λֵ(0-9)�ֱ�ŵ�0~9��Ͱ�У����磶4����λΪ��������룴��Ͱ�У��� 
	 * (2)�ռ����ٽ�������0~9��Ͱ�е����ݰ�˳��ŵ������У� 
	 * �ظ�(1)(2)���̣��Ӹ�λ�����λ������32λ�޷������������4294967296�����λΪ��10λ����
	 * 
	 * @param arr
	 */
	public static void radixSort(Integer[] arr){ 
		System.out.print("\n���룺");
		show(arr);
		// ����λ��Ͱ���ռ�
		Integer[] tmpArr = createBucket(arr,RadixSort.ONE);
		System.out.print("\n����1��");
		show(tmpArr);
		// ��ʮλ��Ͱ���ռ�
		tmpArr = createBucket(tmpArr,RadixSort.TEN);
		System.out.print("\n����2��");
		show(tmpArr);
		// ����λ��Ͱ���ռ�
		tmpArr = createBucket(tmpArr,RadixSort.HUNDRED);
		System.out.print("\n����3��");
		show(tmpArr);
		// ��ǧλ��Ͱ���ռ�
		tmpArr = createBucket(tmpArr,RadixSort.THOUSAND);
		System.out.print("\n����4��");
		show(tmpArr);
	}
	
	public static Integer[] createBucket(Integer[] arr,int type){
		Integer[][] bucketArr=new Integer[10][arr.length];
		// ����
		int tmpBucketIndex = 0;
		for(int i=0;i<arr.length;i++){
			switch(type){
			case ONE:// ����λ��Ͱ������
				tmpBucketIndex = arr[i]%10;
				bucketArr[tmpBucketIndex][i]=arr[i];
				break;
			case TEN:// ��ʮλ��Ͱ������
				tmpBucketIndex = (arr[i]%100-arr[i]%TEN)/TEN;
				bucketArr[tmpBucketIndex][i]=arr[i];
				break;
			case HUNDRED:// ����λ��Ͱ 
				tmpBucketIndex = (arr[i]%1000-arr[i]%HUNDRED)/HUNDRED;
				bucketArr[tmpBucketIndex][i]=arr[i];
				break;
			case THOUSAND:// ��ǧλ��Ͱ
				tmpBucketIndex = (arr[i]%10000-arr[i]%THOUSAND)/THOUSAND;
				bucketArr[tmpBucketIndex][i]=arr[i];
				break;
				default:
			}
		}
		// �ռ�
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
