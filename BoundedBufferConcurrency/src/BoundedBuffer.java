class BoundedBuffer {
	private int[] buffer; // using a cicular queue for buffer
	private int front, tail, size, max;

	// consume from front, produce at tail. size indicates current
	// size, max is max size (of array).

	public BoundedBuffer(int m) {
		max = m;
		buffer = new int[max];
		front = tail = size = 0;
	}

	// produce must wait if buffer is full:
	public synchronized void produce(int x) {
		try {

			while (size == max)
				wait(); // why "while" and not just "if" ?
			buffer[tail] = x;
			tail = (tail + 1) % max; // move pointer forward in circular queue
			size++;
			if (size == 1)
				notifyAll(); // there could be consumers waiting!
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	// consume must wait if buffer empty
	public synchronized int consume() {
		int result = 0;
		try {

			while (size == 0)
				wait();
			result = buffer[front];
			front = (front + 1) % max;
			size--;
			if (size == max - 1)
				notifyAll(); // wake any waiting producers
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		return result;
	}

} // BoundedBuffer
