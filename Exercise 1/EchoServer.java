/**
 * CS 380.01 - Computer Networks
 * Professor: NDavarpanah
 *
 * Exercise #1
 *
 * Justin Galloway
 *
 * ~EchoServer~
 */

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
				Socket socket = serverSocket.accept();
				Runnable client = () -> {
					try{
						String address = socket.getInetAddress().getHostAddress();
						System.out.printf("Client connected: %s%n", address);
						
						OutputStream os = socket.getOutputStream();
						PrintStream out = new PrintStream(os);
						
						InputStream is = socket.getInputStream();
						InputStreamReader isr = new InputStreamReader(is, "UTF-8");
						Bufferedreader br = new BufferedReader(isr);
						String msg;
						
						while((msg = br.readLine()) != null){
							out.println(msg);
						}
						
						System.out.printf("Hi %s, thanks for connecting!%n", address);
						socket.close();
                }
				catch (Exception e){}
            };
			Thread clientThread = new Thread(client);
			clientThread.start();
        }
    }
}
