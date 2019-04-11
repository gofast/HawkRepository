package com.jt.thread.example.two;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/** 
 * fork/join框架，是jdk1.7新加入的特性，主要采用的是分治策略
 * @author maji48
 *
 */
public class ForkJoinTask extends RecursiveTask<Integer>{


	private static final int THRESHOLD = 2;
	
	private int start;
	
	private int end;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForkJoinTask(int a,int b){
		start=a;
		end=b;
	}
	
	@Override
	protected Integer compute() {
		int sum = 0;
		boolean canCompute = (this.end-this.start) <= ForkJoinTask.THRESHOLD;
		if(canCompute){
			for(int i=this.start;i<=this.end;i++){
				sum +=i;
			}
			return sum;
		}
		int middle = (this.end+this.start)/2;
		ForkJoinTask leftTask = new ForkJoinTask(this.start,middle);
		ForkJoinTask rightTask = new ForkJoinTask(middle+1,this.end);
		
		leftTask.fork();
		rightTask.fork();
		
		Integer leftResult = leftTask.join();
		Integer rightResult = rightTask.join();
		sum = leftResult + rightResult;
		return sum;
	}

	
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		
        //生成一个计算资格，负责计算1+2+3+4+ ... + n   
		ForkJoinTask task = new ForkJoinTask(1, 10); 
        Future<Integer> result = forkJoinPool.submit(task);
        try {
        	System.out.println(result.get());
		} catch (Exception e) {
		}
	}
 
}
