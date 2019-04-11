package com.jt.sort.test;

import java.util.ArrayList;
import java.util.List;

public class Sample3 {

	public static void main(String[] args) {
		int index = strStr("mississippi","pi");
		System.out.println("result:"+index);
	}
	
	
	public static int strStr(String haystack, String needle) {
        if(needle==null||needle.length()==0){
        	return 0;
        }
        int index = 0;
		char[] arr = needle.toCharArray();
		char[] hay = haystack.toCharArray();
		int start = 0;
		for (int i = 0; i < hay.length; i++) {
			if(index==arr.length){
				break;
			}else if(hay[i]==arr[index]){
				if(index==0){
					start = i;
				}
				index++;
			}else{
				if(index!=0){
					i=start;
				}
				index=0;
			}
		}
		if(index==0||index<arr.length){
			return -1;
		}
		return start;
    }
	
	public static boolean isPalindrome(String s) {
		if(s==null||s.length()==0){
        	return true;
        }
		s=s.toLowerCase();
		char[] arr = s.toCharArray();
		int j=arr.length-1;
		for (int i = 0; i <= j; i++) {
			if((arr[i]>='a'&&arr[i]<='z')||(arr[i]>='0'&&arr[i]<='9')){
				while(!(arr[j]>='a'&&arr[j]<='z')&&!(arr[j]>='0'&&arr[j]<='9')){ 
					j--;
				}
				if(arr[i]!=arr[j]){
					return false;
				}
				j--;
			}else{
				continue;
			}
		}
		return true;
    }
	
	public static int firstUniqChar(String s) {
        if(s==null){
        	return -1;
        }else if(s.length()<2){
        	return 0;
        }
        char[]arr = s.toCharArray();
        List<Integer> list = new ArrayList<Integer>();
        boolean status;
        for (int i = 0; i < arr.length; i++) {        	
        	Integer value = arr[i]-'a';
        	if(list.contains(value)){
        		continue;
        	}
        	status=true;
        	for (int j = i+1; j < arr.length; j++) {
				if(arr[i]==arr[j]){
					list.add(value);
					status = false;
					break;
				}
			}
        	if(status){
        		return i;
        	}
		}
		return -1;
    }
}
