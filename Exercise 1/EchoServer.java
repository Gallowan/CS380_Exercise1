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

        boolean initialRun = true;
        try (ServerSocket serverSocket = new ServerSocket(22222)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    String address = socket.getInetAddress().getHostAddress();
					
                    if(initialRun) {
                        System.out.printf("Client connected: %s%n", address);
                        initialRun = false;
                    }
					
                    InputStream inStr = socket.getInputStream();
                    InputStreamReader inStrRead = new InputStreamReader(inStr, "UTF-8");
					BufferedReader br = new BufferedReader(inStrRead);
                    String msg = br.readLine();
					
                    if(!msg.equals("exit")) {
                        OutputStream outStr = socket.getOutputStream();
                        PrintStream outPrint = new PrintStream(outStr, true, "UTF-8");
                        String serverSend = "Server> " + msg;
                        outPrint.println(serverSend);
					} else {
                        System.out.printf("Client disconnected: %s%n", address);
                        initialRun = true;
                    }
				}
					
			}
            
        }
    }
    
}
