import java.io.*;
import java.rmi.*;
import java.net.*;

public class Client5 {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String args[]) throws IOException {
		try {

			int i, y, a = 1;

			PC obj[] = new PC[1];

			int x = 0, m;
			obj[0] = (PC) Naming.lookup("rmi://localhost:" + args[0]
					+ "/PCServer");

			MyThread th5 = new MyThread(5, obj[0]);
			th5.t.start();

			try {

				th5.t.join();

			} catch (InterruptedException e) {
				System.out.println("Exception caught");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}