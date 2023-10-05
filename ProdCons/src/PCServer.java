import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

class MyThread1 implements Runnable {

	PCImpl obj;

	Thread t;

	MyThread1(PCImpl obj1) {

		obj = obj1;
		t = new Thread(this);
	}

	public void run() {
		try {
			obj.set_buffer();
			obj.set_sem();
			for (;;) {
				System.out.println("Producer waiting ...");

				obj.produce();

				t.sleep(5000);

			}
		} catch (java.lang.InterruptedException i) {
		}

	}
}

public class PCServer {
	public static void main(String args[]) {

		try {
			int test = 0;
			PCImpl obj = new PCImpl();
			String host = (args.length > 0) ? ":" + args[0] : "";
			Naming.rebind("rmi://" + host + "/PCServer", obj);
			// Naming.rebind("rmi://127.0.0.1:"+args[0]+"/PCServer", obj);
			System.out.println("\nPCServer bound successfully in registry ");
			MyThread1 th = new MyThread1(obj);
			th.t.start();

			try {

				th.t.join();

			} catch (InterruptedException e) {
				System.out.println("Exception caught");
			}

		} catch (RemoteException re) {
			System.out.println(re);
		} catch (MalformedURLException mfe) {
			System.out.println(mfe);
		}
	}
}