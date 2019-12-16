package com.orgabor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {
	private Socket socket;
	
	public Echoer(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			BufferedReader input = new BufferedReader(
								   new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			
			while(true) {
				String echoString = input.readLine();
				if(echoString.contentEquals("exit")) {
					break;
				}
				
				System.out.println(echoString);
			}
		} catch(IOException e) {
			System.out.println("Thread error: " + e.getMessage());
		} finally {
			try {
				socket.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
