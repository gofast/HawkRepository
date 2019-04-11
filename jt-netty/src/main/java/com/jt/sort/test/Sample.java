package com.jt.sort.test;

import java.util.HashMap;
import java.util.Map;

public class Sample {
	
	public static void main(String[] args) {
		int[] nums= {7,2,3};
//		int result = removeDuplicates(nums);
		/*rotate(nums,2);
		System.out.print("\n结果：");
		show(nums);*/
		/*int target=6;
		int[] result = twoSum(nums,target);
		System.out.print("\n结果：");
		show(result);*/
		
		 nums[0] = nums[0] ^ nums[2];
		 System.out.println("result:nums[0]="+nums[0]+",nums[2]="+nums[2]); 
		 nums[2] = nums[0] ^ nums[2];
		 System.out.println("result:nums[0]="+nums[0]+",nums[2]="+nums[2]); 
		 nums[0] = nums[0] ^ nums[2];
		 System.out.println("result:nums[0]="+nums[0]+",nums[2]="+nums[2]); 
	}
	
	public static void swap(int[] nums,int a,int b){
		nums[a] = nums[a] ^ nums[b];
		 nums[b] = nums[a] ^ nums[b];
		 nums[a] = nums[a] ^ nums[b];
	}

	public static int removeDuplicates(int[] nums) {
        if(nums==null||nums.length<2){
            return nums==null?0:nums.length;
        }
        int p = nums.length-1;
        for(int i=0;i<nums.length;i++){
        	int start=0;
        	int end=0;
        	if(i+1<=p&&nums[i]==nums[i+1]){
        		start=end=i+1; 
        	}else{
        		continue;
        	}
        	int j=i+2;
        	while(j<=p&&nums[i]==nums[j]){
        		end=j;
        		j++;
        	}
        	int len= end-start+1;
        	while(j<=p){
        		nums[j-len]=nums[j];
        		j++;
        	}
        	p=p-len;
        	 
        }
        return p+1;
    }
	
	public static int[] twoSum(int[] nums, int target) {
        int[] ne= new int[2];
		Map<Integer,Integer> mapX = new HashMap<Integer,Integer>(nums.length); 
		int index = 0;
		while(index<nums.length){
			mapX.put(nums[index],target-nums[index]); 
			index++;
		}
		for(Map.Entry<Integer, Integer> ma:mapX.entrySet()){
			Integer tmp = mapX.get(ma.getValue());
			if(tmp!=null){
				index = 0;
				int status = 0;
				while(index<nums.length){
					if(nums[index]==ma.getValue().intValue()){
						ne[status]=index;
						status++;
					}else if(nums[index]==tmp.intValue()) {
						ne[status]=index;
						status++;
					}
					if(status>1){
						break;
					}
					index++;
				} 
				if(status<2){
					ne[0]=0;
					continue;
				}
				return ne;
			}
		}
        return ne;
    }
	
	public  static void rotate(int[] nums, int k) {
        k=k%nums.length;
		if(k==0){
        	return;
        }
		final int p = nums.length-k;
		int index = p;
		// 1 右分区交换
		int key=(2*nums.length-k-1)/2;
		int a = 0;
		// 偶数 0,奇数1，
		boolean status = (nums.length-index)%2==0?true:false;
		while(index<=key){
			if(status&&index+a>key){
				break;
			}
			if(!status&&index+a>=key){
				break;
			}
			int tmp = nums[index+a];
			nums[index+a]=nums[nums.length-1-a];
			nums[nums.length-1-a]=tmp;
			a++;
		}
		index = 0;
		// 2 左分区交换
		key=(p-1)/2;
		a = 0;
		// 偶数 0,奇数1，
		status = p%2==0?true:false;
		while(index<=key){
			if(status&&index+a>key){
				break;
			}
			if(!status&&index+a>=key){
				break;
			}
			int tmp = nums[index+a];
			nums[index+a]=nums[p-1-a];
			nums[p-1-a]=tmp;
			a++;
		}
		index = 0;
		// 3 全交换
		key=(nums.length-1)/2;
		a = 0;
		// 偶数 0,奇数1，
		status = nums.length%2==0?true:false;
		while(index<=key){
			if(status&&index+a>key){
				break;
			}
			if(!status&&index+a>=key){
				break;
			}
			int tmp = nums[index+a];
			nums[index+a]=nums[nums.length-1-a];
			nums[nums.length-1-a]=tmp;
			a++;
		}
		 
    }
	
	public static void show(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",\t");
		}
	}
}
