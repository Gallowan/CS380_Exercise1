/**
 * CS 380.01 - Computer Networks
 * Professor: NDavarpanah
 *
 * Exercise #1
 *
 * Justin Galloway
 *
 * ~EchoClient~
 */

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			
            BufferedReader br = new BufferedReader(isr);
			OutputStream os = socket.getOutputStream();
			PrintStream out = new PrintStream(os);
			
			Scanner sc = new Scanner(System.in);
			System.out.print("Client: ");
			String msg;
			
			while(!(msg = sc.nextLine()).equals("exit")){
				out.println(msg);
				System.out.println("Server: " + br.readLine());
				System.out.print("Client: ");
			}
        }
    }
}















