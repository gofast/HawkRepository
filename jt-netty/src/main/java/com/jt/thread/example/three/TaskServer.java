package com.jt.thread.example.three;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TaskServer {
	
	private static final int NTHREADS=100; 

	public static void main(String[] args) throws IOException {
		 
	}
	
	/** ********************************************************************************************* */
	/** Executor example  base on  ‘execute’ and ‘Runnable’ */
	
	private static final Executor exc = Executors.newFixedThreadPool(NTHREADS);
	public static void testExecutor(){
		ServerSocket server;
		try {
			server = new ServerSocket(8080);
			while(true){
				final Socket  socket= server.accept(); 
				TaskServer.exc.execute(()->{
					// 处理请求；
					socket.isConnected();
					// ...
				});	 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** ********************************************************************************************* */
	/** ExecutorService example   base on ‘execute’ and ‘Runnable’
	 * @throws IOException */ 

	private static final ExecutorService excutorService = Executors.newFixedThreadPool(NTHREADS);
	public static void testExecutorService() throws IOException{
		ServerSocket  server = new ServerSocket(8080);
		while(!excutorService.isShutdown()){
			final Socket  socket= server.accept(); 
			TaskServer.excutorService.execute(()->{ 
					// 处理请求；
					socket.isConnected();
					// ...
		     });	 
		}
	} 

	/** ********************************************************************************************* */
	/** ExecutorService example   base on ‘submit’ and ‘Callable’
	 * @throws IOException */ 

	private static final ExecutorService excutorService2 = Executors.newFixedThreadPool(NTHREADS);
	public static void testExecutorService2() throws IOException{
		ServerSocket  server = new ServerSocket(8080);
		while(!excutorService2.isShutdown()){
			final Socket  socket= server.accept(); 
			Future<String> result = TaskServer.excutorService2.submit(()->{ 
					// 处理请求；
					socket.isConnected();
					// ...
					return "";
		     });	 
		}
	} 
	
	/** ********************************************************************************************* */
	/** ExecutorCompletionService example   base on ‘submit’ and ‘Callable’  band ‘ExecutorService’ and ‘taskQueue’
	 * @throws IOException */ 

	private static final ExecutorService excutorService3 = Executors.newFixedThreadPool(NTHREADS);
	public static void testExecutorService3() throws IOException{
		ServerSocket  server = new ServerSocket(8080);
		CompletionService<String> completionService = new ExecutorCompletionService<>(excutorService3);
		List<Future<String>> futureList = new ArrayList<Future<String>>();
		while(!excutorService3.isShutdown()){
			final Socket  socket= server.accept(); 
			Future<String> future = completionService.submit(new Callable<String>() {
				
				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					return null;
				}
			});	 
			futureList.add(future); 
		}
		Future<String> future=null;
		try {
			// result deal method two
			for(Future<String> future1 :futureList){
				String result= future1.get();// 若还未结束，则会阻塞等待，直到结果返回；
				// 处理结果
			}
			// result deal method one 
			for(int i=0;i<futureList.size();i++){
				future = completionService.take();
				String result= future.get(); // 若还未结束，则会阻塞等待，直到结果返回；
				String result2= future.get(10, TimeUnit.SECONDS); // 若还未结束，则会阻塞等待，直到结果返回；
				// 处理结果
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			future.cancel(true);  // 取消任务
		}
	} 
	
	
	/** ********************************************************************************************* */
	/** ExecutorService example   base on ‘submit’ and ‘Callable’  批量添加任务
	 * @throws IOException */ 

	private static final ExecutorService excutorService4 = Executors.newFixedThreadPool(NTHREADS);
	public static void testExecutorService4() throws IOException{
		ServerSocket  server = new ServerSocket(8080);
		CompletionService<String> completionService = new ExecutorCompletionService<>(excutorService3);
		List<Callable<String>> task = new ArrayList<Callable<String>>();
		for(int i=0;i<10;i++){
			task.add(()->{
				// 任务内容
				return "";
			});
		}
		List<Future<String>> futureList = null;
//		completionService.submit(task);
		try { 
			// TODO　批量添加任务
			futureList = excutorService4.invokeAll(task, 10, TimeUnit.MINUTES);
		} catch (InterruptedException e1) { 
			e1.printStackTrace();
		}
		
	} 
}
