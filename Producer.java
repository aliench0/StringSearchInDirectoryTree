import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Producer extends Thread {
	private Storage storage;
	private Stack<File> fileStack;
	File file;

	public Producer(Storage _storage, String _name, Stack<File> _fileStack) {
		super(_name);
		storage = _storage;
		fileStack = _fileStack;
		start();
	}

	public void run() {
		while ((file = fileStack.empty() ? null : fileStack.pop()) != null) {

			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				String line;
				long countOfLines = 1;
				while ((line = reader.readLine()) != null) {
					storage.put(file, countOfLines++, line);
				}
				storage.incrementFilesRead();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}