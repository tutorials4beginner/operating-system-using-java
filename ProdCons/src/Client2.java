import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class Client2 {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String args[]) throws IOException {
		try {

			int i, y, a = 1;

			PC obj[] = new PC[1];

			int x = 0, m;

			obj[0] = (PC) Naming.lookup("rmi://localhost:" + args[0]
					+ "/PCServer");

			MyThread th2 = new MyThread(2, obj[0]);
			th2.t.start();

			try {

				th2.t.join();

			} catch (InterruptedException e) {
				System.out.println("Exception caught");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}