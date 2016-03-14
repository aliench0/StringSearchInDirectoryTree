class Consumer extends Thread {
	private Storage storage;
	private String searchFor;

	public Consumer(Storage _storage, String _name, String _searchFor) {
		super(_name);
		storage = _storage;
		searchFor = _searchFor;
		start();
	}

	public void run() {

		try {
			while (!storage.isEmpty() || !storage.areAllFileRead()) {
				String temp = storage.get(searchFor);
				if (temp != null)
					System.out.println(temp);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}