package com.jt.sort.test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Sample2 {

	public static void main(String[] args) {
		int[][] matrix={{1,2,3},{4,5,6},{7,8,9}};  
		showRotate(matrix);
		rotate(matrix); 
		System.out.println("\n顺时针旋转90°的结果：");
		showRotate(matrix);
	}
	

	public static int singleNumber(int[] nums) {
        Map<Integer,Integer> map= new HashMap<Integer,Integer>();
        int index=0;
        while(index<nums.length){
        	Integer a = map.get(nums[index]);
        	if(a==null){
        		map.put(nums[index],1);
        	}else{
        		map.put(nums[index],a+1);
        	}
        	index++;
        }
        for(Map.Entry<Integer, Integer> m:map.entrySet()){
        	if(m.getValue().intValue()==1){
        		return m.getKey(); 
        	}
        }
		return 0;
    }
	
	
	public static int[] plusOne(int[] digits) {
        int len = digits.length-1;
        int a = 1;
        do{
        	digits[len]+=a;
        	if(digits[len]>9){
        		digits[len]=0;
        		a = 1;
        	}else{
        		a = 0;
        		return digits;
        	}
        	len--;
        }while(len>=0); 
        int[] arr = new int[digits.length+1];
        arr[0]=a;
        System.arraycopy(digits, 0, arr, 1, digits.length); 
		return arr;
    }
	
	public boolean containsDuplicate(int[] nums) {
        for(int i=0;i<nums.length;i++){
        	for(int j=i+1;j<nums.length;j++){
            	if(nums[i]==nums[j]){
            		return true;
            	}
            }
        }
		return false;
    }
	
	public static void moveZeroes(int[] nums) {
		if(nums==null||nums.length<2){
			return;
		}
		int zero = -1;
        for(int i=0;i<nums.length;i++){
        	if(nums[i]==0){
        		zero =i;
        		i++;
        	}else{
        		continue;
        	}
        	while(zero<i&&i<nums.length){
        		while(i<nums.length){
        			if(nums[i]!=0){
        				break;
        			}
        			i++;
        		}
        		if(i>=nums.length){
        			return;
        		}
        		nums[zero]=nums[i];
        		nums[i]=0;
        		i++;
        		zero++;
        	}
        }
    }
	

	/**
	 * 矩阵顺时针旋转90度
	 * 第一步：上下对折/翻转
	 * 第二步：左上 ，右下角为轴线，对折/翻转
	 * 完成
	 * @param matrix
	 */
	public static void rotate(int[][] matrix) {
		final int len = matrix[0].length;
        int yCenter = len/2;
        boolean status = len%2==0?true:false;
        // 1 上下对折/翻转
        for(int x=0;x<len;x++){
        	for(int y=0;y<len;y++){
        		if(status&&x>yCenter-1){
        			break;
        		}
        		if(!status&&x>=yCenter){
        			break;
        		}
        		matrix[x][y]=matrix[x][y]^matrix[len-x-1][y];
        		matrix[len-x-1][y]=matrix[x][y]^matrix[len-x-1][y];
        		matrix[x][y]=matrix[x][y]^matrix[len-x-1][y];
            }
        }
     // 2 左上 ，右下角为轴线，对折/翻转
        for(int x=0;x<len;x++){
        	for(int y=0;y<len;y++){
        		if(x==y){
        			break;
        		}
        		matrix[x][y]=matrix[x][y]^matrix[y][x];
        		matrix[y][x]=matrix[x][y]^matrix[y][x];
        		matrix[x][y]=matrix[x][y]^matrix[y][x];
            }
        }
    }

	public static void show(int[] arr){
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+",\t");
		}
	}
	
	public static void showRotate(int[][] arr){
		int len = arr[0].length;
		for(int x=0;x<len;x++){
        	for(int y=0;y<len;y++){
				System.out.print(arr[x][y]+",\t");
			}
			System.out.print("\n");
		}
	}
}
