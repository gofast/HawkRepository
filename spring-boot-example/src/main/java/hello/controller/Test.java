package hello.controller;

public class Test {

	/*public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println("sdfs");
		}
		System.gc();
		System.out.println("finish");
		test();
	}*/
	
	public static void test(){
		long _1MB = 1024*1024;
		long freeMemory = Runtime.getRuntime().freeMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		long maxMemory = Runtime.getRuntime().maxMemory();
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println("\nfree:\t"+freeMemory/_1MB);
		System.out.println("\ntotal:\t"+totalMemory/_1MB);
		System.out.println("\nmax:\t"+maxMemory/_1MB);
		System.out.println("\nmax:\t"+(maxMemory-totalMemory+freeMemory)/(_1MB*1024.0));
		System.out.println("\nProcessors:\t"+availableProcessors);
	}
	
}
