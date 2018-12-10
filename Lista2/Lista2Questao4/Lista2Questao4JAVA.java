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

		list = new ArrayList<Integer>();
		teste(list);

	}

	public static void teste(final List<Integer> Threads) throws InterruptedException {
		for (int i = 0; i < 4; i++) {

			ExecutorService Server = Executors.newFixedThreadPool(4);
			Server.execute(new Runnable() {
				@Override
				public void run() {

					for (int i = 0; i < 500000; i++) {
						// Integer number = (int) Math.ceil(Math.random() * 550000);
						Threads.add(i);
					}
				}
			});

			Server.shutdown();
			Server.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

			Runtime runtime = Runtime.getRuntime();

			runtime.gc();

			long memory = runtime.totalMemory() - runtime.freeMemory();
			System.out.println("====================================================");
			System.out.println("Used memory is bytes: " + memory);
			System.out.println("Used memory is megabytes: " + bytesToMegabytes(memory));
		}
		System.out.println("====================================================");
	}
}
