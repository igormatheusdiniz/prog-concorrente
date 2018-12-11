package Lista2.Lista2Questao4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Lista2Questao4JAVA {

	public final static int SIZE = 5;

	private static final long MEGABYTE = 1024L * 1024L;

	public static List<Integer> list = new ArrayList<Integer>();

	public static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}

	public static void main(String[] args) throws InterruptedException {
		teste();
	}


	public static void teste() throws InterruptedException {
		for (int i = 1; i < 3000; i++) {

			ExecutorService Server = Executors.newFixedThreadPool(i);
			for (int j = 0; j < i; j++) {
				Server.execute(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							
						}

					}
				});
			}
			Runtime runtime = Runtime.getRuntime();
			Server.shutdown();
			try {
			    if (!Server.awaitTermination(800, TimeUnit.MILLISECONDS)) {
			    	Server.shutdownNow();
			    } 
			} catch (InterruptedException e) {
				Server.shutdownNow();
			}

			

			long memory = runtime.totalMemory() - runtime.freeMemory();
			System.out.println("====================================================");
			System.out.println("Qte Threads:" + i);
			System.out.println("Used memory is bytes: " + memory);
			System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));
			runtime.gc();
		}
		System.out.println("====================================================");
	}
}