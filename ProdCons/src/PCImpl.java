import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PCImpl extends UnicastRemoteObject implements PC {
	public PCImpl() throws RemoteException {
		super();
	}

	public static int[] buffer = new int[5];

	public static int[] produced = new int[5];
	public static int mutex = 1;
	public static int last_prod = 4;
	public static int[] sem = new int[5];

	void set_buffer() {

		for (int i = 0; i < buffer.length; i++)
			buffer[i] = i + 1;
	}

	void set_sem() {

		for (int i = 0; i < 5; i++)
			sem[i] = 1;
	}

	public synchronized void PC(int cons) {
		System.out.println("Consuming " + cons + " ...");

		try {
			while (sem[(cons - 1)] == 0 || mutex == 0)
				wait();
		} catch (java.lang.InterruptedException i) {
		}
		mutex = 0;
		buffer[(cons - 1)] = '\0';
		sem[(cons - 1)] = 0;
		mutex = 1;
		notify();
		System.out.println("Consumer " + cons + "has consumed");
	}

	public synchronized void produce() {
		int next;
		System.out.println("last producd " + (last_prod + 1));
		if (last_prod < 4) {

			if (sem[last_prod + 1] == 0) {
				buffer[last_prod + 1] = last_prod + 2;
				sem[last_prod + 1] = 1;
				if (last_prod == 4)
					last_prod = 0;
				else
					last_prod = last_prod + 1;

				System.out.println("Producer has produced " + (last_prod + 1));
			}
		}
		if (last_prod == 4) {
			if (sem[0] == 0) {
				buffer[0] = 1;
				sem[0] = 1;

				last_prod = 0;

				System.out.println("Producer has produced " + (1));
			}
		}
		/*
		 * for(int i=0;i<buffer.length;i++) { if(buffer[i]!=(i+1)) {
		 * buffer[i]=i+1; sem[i]=1; System.out.println("Producer has produced "
		 * + (i+1)); } }
		 */
	}
}