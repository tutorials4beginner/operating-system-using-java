// producer thread - replaceable with RMI client
class Producer implements Runnable {
	private int x = 0; // value to be "produced"
	private BoundedBuffer buf; // pointer to shared buffer object

	public Producer(BoundedBuffer b) {
		buf = b;
	}

	public void run() {
		while (true)
			buf.produce(x++);
	}
} // producer
