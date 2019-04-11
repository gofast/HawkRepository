package com.jt.sort.test;

public class Sample4 {

	public static void main(String[] args) {
		String[] strs={"dog","racecar","car"};
		String result = longestCommonPrefix(strs);
		System.out.println("reuslt:"+result);
	}
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1==null||nums1.length==0){
       	return nums2.length%2==1?(double)nums2[(nums2.length)/2]:(double)(nums2[nums2.length/2]+nums2[nums2.length/2-1])/2.0;
       }else if(nums2==null||nums2.length==0){
       	return nums1.length%2==1?(double)nums1[(nums1.length)/2]:(double)(nums1[nums1.length/2]+nums1[nums1.length/2-1])/2.0;
       }
       final int sum = nums1.length+nums2.length;
       boolean status = sum%2==0?true:false;
       int isAsc1 = nums1[0]<=nums1[nums1.length-1]?1:2;
       int isAsc2 = nums2[0]<=nums2[nums2.length-1]?3:6;
       // Êý×Ö·­×ª
       switch(isAsc1+isAsc2){
   		case 4: // 4 true,true 
   			break;
   		case 7: // 7 true,false
   			if(nums1.length>=nums2.length){
				Flip(nums2);
			}else{
				Flip(nums1);
				isAsc1=2;
			}
   		break;
   	case 5:// 5  false,true
   		if(nums1.length>=nums2.length){
				Flip(nums2);
			}else{
				Flip(nums1);
				isAsc1=1;
			}
   		break;
   		default:// 8 false,false
   			
   	}
        int[] nums = new int[nums1.length+nums2.length];
	   	System.arraycopy(nums1, 0, nums, 0,nums1.length);
	   	System.arraycopy(nums2, 0, nums, nums1.length,nums2.length);
	   	int kk = status ? sum/2+1:(sum+1)/2;
	   	int key=0;
	   	for (int j = 0; j < nums2.length; j++) { 
	   		int i = nums1.length+j;
	   		key = nums[i];
	   		for (; i >= 0; i--) {
	   			if(i==0){ 
	   				nums[i]=key;
	   				break;
	   			}if(isAsc1==1&&key>=nums[i-1]){
	   				nums[i]=key;
	   				break;
	   			}else if(isAsc1==2&&key<=nums[i-1]){
	   				nums[i]=key;
	   				break;
	   			}else{
	   				nums[i]=nums[i-1];
	   			}
	   		} 
	   		if(i==kk){
	   			if(status){
	   				return (double)(nums[sum/2+1]+nums[sum/2])/2.0;
	   			}
	   			return (double)nums[(sum+1)/2];
	   		}
		} 
		return 0.0;
   }
	
	public static void Flip(int[] nums2){
		int i=0,j=8;
       while(i<j){
       	nums2[i]=nums2[i]^nums2[j];
       	nums2[j]=nums2[i]^nums2[j];
       	nums2[i]=nums2[i]^nums2[j];
       }
	}
	
	
	public String countAndSay(int n) {
        if(n>30||n<1){
        	return "";
        }
        int[] arr=new int[30];
        int p = 0;
        arr[p]=1;
        int start;
        int index =1;
		while(index<=n){
			start = index;
			/*while(arr[index]){
				
			}*/
			if(p==n-1){
				return arr.toString();
			}
			index++;
		}
		
		return null;
    }
	
	public static String longestCommonPrefix(String[] strs) {
		if(strs==null || strs.length<1){
			return "";
		}
		if(strs.length==1){
			return strs[0];
		} 
		boolean status;
		int index = 0;
		while(index<strs[0].length()){
			status = false;
			char ch = strs[0].charAt(index);
			for (int i = 1; i < strs.length; i++) {
				if(index>=strs[i].length()||ch!=strs[i].charAt(index)){
					status=true;
					break;
				}
			}
			if(status){
				break;
			}
			index ++;
		}
		if(index==0){
			return "";
		}
		return strs[0].substring(0, index);
    }
	
	public ListNode reverseList(ListNode head) {
		ListNode p = head;
		ListNode tmp=null;
		int len = 0;
		while(p!=null){
        	len++;
        	p=p.next;
        }
		p = head;
		int start = 0;
		int step = len-1;
		while(step>0){
			tmp=p;
			while(step>0){
				tmp=tmp.next;
				step--;
			}
			int val= tmp.val;
			tmp.val=p.val;
			p.val=val;
			p=p.next;
			start++;
			step = len-2*start-1;
		}
		return head;
    }
	
	public class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { 
			 val = x; 
	     }
	}
}
