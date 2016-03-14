import java.io.BufferedReader;
import java.io.FileReader;

class Producer implements Runnable {

	Thread t;
	private Storage storage;
	private String fileName;

	Producer(Storage _storage, String _fileName) {
		this.storage = _storage;
		fileName = _fileName;
		t = new Thread(this, "Producer");
		t.start();
	}

	public void run() {

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

			String line;

			while ((line = reader.readLine()) != null) {

				storage.put(fileName + " " + line);

			}

		} catch (Exception e) {
			System.out.println("Exception in Producer::run()");
		}

	}
}