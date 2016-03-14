
class Consumer implements Runnable {

	Thread t;
	private String substring;
	private Storage storage;

	Consumer(Storage _storage, String _substring) {

		this.storage = _storage;
		this.substring = _substring;

		t = new Thread(this, "Consumer");
		t.start();
	}

	public void run() {
		System.out.println(storage.get(substring));

	}
}
