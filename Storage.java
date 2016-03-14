import java.io.File;
import java.util.Stack;
import java.util.Vector;

class Storage {
	private int NUMBER_OF_FILES;
	private int filesRead;
	private final int MAX_SIZE = 1000;
	private Vector<LineInfo> vector = new Vector<>();

	public Storage(Stack<File> _fileStack) {
		NUMBER_OF_FILES = _fileStack.size();
		filesRead = 0;
	}

	private String searchFor(String sub) {

		while (!vector.isEmpty() && !vector.elementAt(0).getLine().contains(sub)) {
			vector.remove(0);
		}
		if (vector.isEmpty()) {
			return null;
		} else {
			String filePath = vector.elementAt(0).getFile().getPath();
			long lineNumber = vector.elementAt(0).getLineNumber();
			String line = vector.elementAt(0).getLine();

			vector.remove(0);
			return filePath + " " + lineNumber + " " + line;
		}
	}

	public synchronized void put(File _file, Long _lineNumber, String _line) throws InterruptedException {
		while (vector.size() == MAX_SIZE) // ? if or while? Works with if same as with while 
			wait();
		vector.addElement(new LineInfo(_file, _lineNumber, _line));
		notify();
	}

	public void print() {
		for (int i = 0; i < vector.size(); i++) {
			System.out.println(vector.get(i).getLine());
		}
		if (vector.size() == 0) {
			System.out.println("Storage is empty!");
		}
	}

	public synchronized String get(String sub) throws InterruptedException {
		String line = "";
		while (vector.isEmpty() && !areAllFileRead()) {
			wait();
		}
		line = searchFor(sub);
		notify();
		return line;
	}

	public boolean isEmpty() {
		return vector.size() == 0;
	}

	public boolean areAllFileRead() {
		return NUMBER_OF_FILES == filesRead;
	}

	synchronized public void incrementFilesRead() {
		filesRead++;
		notify();
	}
	public Vector<LineInfo> getVector(){
		return vector;
	}
}
