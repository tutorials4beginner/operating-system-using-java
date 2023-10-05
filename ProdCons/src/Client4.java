import java.io.*;
import java.rmi.*;
import java.net.*;

public class Client4 {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String args[]) throws IOException {
		try {

			int i, y, a = 1;

			PC obj[] = new PC[1];

			int x = 0, m;
			obj[0] = (PC) Naming.lookup("rmi://localhost:" + args[0]
					+ "/PCServer");
			// obj[0] = (PC) Naming.lookup("rmi://"+ args[0] + "/PCServer");

			MyThread th4 = new MyThread(4, obj[0]);
			th4.t.start();

			try {

				th4.t.join();

			} catch (InterruptedException e) {
				System.out.println("Exception caught");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}