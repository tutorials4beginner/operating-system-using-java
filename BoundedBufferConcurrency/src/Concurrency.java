public class Concurrency // makes 1 producer and 2 consumers
{
	public static void main(String[] args) {
		BoundedBuffer buf = new BoundedBuffer(10); // size 10 buffer
		Thread t1, t2, t3;
		t1 = new Thread(new Producer(buf)); // create thread objects
		t2 = new Thread(new Consumer(buf));
		t3 = new Thread(new Consumer(buf));
		t1.start();
		t2.start();
		t3.start(); // start threads
	} // main
}
