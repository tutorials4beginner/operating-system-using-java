import java.io.*;
import java.rmi.*;
import java.net.*;

class MyThread implements Runnable {

	PC obj;
	int m;
	int cons;
	Thread t;

	MyThread(int i, PC obj1) {
		m = i;
		obj = obj1;
		t = new Thread(this);
	}

	public void run() {
		try {

			System.out.println("Welcome Consumer " + m);
			for (;;) {
				System.out
						.println("Press 1 if u want to consume and 0 if u want to quit:");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));

				String str = br.readLine();
				int j = Integer.parseInt(str);

				if (j == 1) {
					System.out.println("Consume called ...");
					System.out.println("Please Wait ...");
					obj.PC(m);
				} else if (j == 0)
					break;

			}
		} catch (java.io.IOException i) {
		}

	}
}

public class Client1 {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String args[]) throws IOException {
		try {

			int i, y, a = 1;

			PC obj[] = new PC[1];

			int x = 0, m;

			obj[0] = (PC) Naming.lookup("rmi://localhost:" + args[0]
					+ "/PCServer");

			MyThread th1 = new MyThread(1, obj[0]);
			th1.t.start();

			try {

				th1.t.join();

			} catch (InterruptedException e) {
				System.out.println("Exception caught");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}