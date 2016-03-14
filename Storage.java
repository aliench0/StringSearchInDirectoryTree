import java.util.Vector;
public class Storage {

	
	private static final int MAX_SIZE = 20;
	private static Vector<String> storage = new Vector<String>();

	Storage(Vector<String> _storage) {
		storage = (_storage != null) ? _storage : new Vector<String>();
	}

	private String found(String substring) {

		for (int i = 0; i < storage.size(); i++) {

			if (storage.elementAt(i).contains(substring)) {
				int j = 0;
				String fileName = "";
				while (storage.elementAt(i).charAt(j) != ' ') {
					fileName = fileName + storage.elementAt(i).charAt(j);
					j++;
				}

				substring = fileName + " " + i + storage.elementAt(i).substring(fileName.length());

				storage.removeElementAt(i);

				return substring;
			}
		}
		if (storage.size() > 0)
			storage.removeElement(storage.firstElement());
		return null;
	}

	synchronized String get(String substring) {

		String temp = null;

		while ((temp = found(substring)) == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		}

		notify();
		return temp;
	}

	synchronized void put(String nextLine) {
		while (storage.size() == MAX_SIZE) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("InterruptedException caught");
			}
		}

		storage.addElement(nextLine);

		notify();
	}

	public boolean isFull() {
		return storage.size() == MAX_SIZE;
	}

	public void printStorage() {

		if (storage.isEmpty()) {
			System.out.println("Storage is empty!");
		} else {

			for (int i = 0; i < storage.size(); i++) {
				System.out.println(storage.elementAt(i) + " ");
			}
		}
	}

}