// consumer thread class
class Consumer implements Runnable {
	private int x = 0;
	private BoundedBuffer buf; // pointer to shared buffer object

	public Consumer(BoundedBuffer b) {
		buf = b;
	}

	public void run() {
		while (true)
			System.out.println(buf.consume());
	}
} // consumer
