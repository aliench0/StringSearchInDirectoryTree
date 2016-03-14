public class Main {

	public static void main(String[] args) {
		Storage myStorage = new Storage(null);

		Producer firstProducer = new Producer(myStorage, "text.txt");
		Producer secondProducer = new Producer(myStorage, "text1.txt");
		Producer thirdProducer = new Producer(myStorage, "text2.txt");
		//Consumer firstConsumer = new Consumer(myStorage, "bye1");
		Consumer secondConsumer = new Consumer(myStorage, "hello2");

		try {

			firstProducer.t.join();
			secondProducer.t.join();
			thirdProducer.t.join();
			//firstConsumer.t.join();
			secondConsumer.t.join();

		} catch (Exception e) {
			System.out.println("Exception");
		}

		System.out.println("myStorage:");
		myStorage.printStorage();

		System.out.println("end");

	}
}
