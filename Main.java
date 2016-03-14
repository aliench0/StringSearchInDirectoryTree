import java.io.File;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		
		File file = new File(Constants.MAIN_DIR);

		Stack<File> fileStack = new Stack<>();
		try {
			TxtFiles txtFiles = new TxtFiles(file);
			fileStack = txtFiles.toStack();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Storage storage = new Storage(fileStack);
		
		long t = System.currentTimeMillis();

		Producer[] producers = new Producer[Constants.NUMBER_OF_PRODUCERS];
		for (int i = 0; i < Constants.NUMBER_OF_PRODUCERS; i++) {
			producers[i] = new Producer(storage, "Producer-" + i, fileStack);
		}

		Consumer[] consumers = new Consumer[Constants.NUMBER_OF_CONSUMBERS];
		for(int i=0; i<Constants.NUMBER_OF_CONSUMBERS; i++){
			consumers[i]=new Consumer(storage, "Consumer-" + i, Constants.SEARCH_FOR);
		}
		

		try {
			for (Producer producer : producers) {
				producer.join();
			}
			for (Consumer consumer : consumers) {
				consumer.join();
			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		t = System.currentTimeMillis() - t;
		System.out.println(t);
	}

}