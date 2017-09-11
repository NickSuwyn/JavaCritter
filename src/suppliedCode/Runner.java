package suppliedCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


import io.socket.client.IO;
import io.socket.emitter.Emitter;


public class Runner {
	
	public static void run(Critter c) throws InterruptedException {
		
		List<Tile> list = new ArrayList<Tile>();
		io.socket.client.Socket socket;
		try {
			socket = IO.socket("http://localhost:9090");
			socket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
			    socket.emit("move", Move.COUNTERCLOCKWISE.getValue());

			  }

			}).on("response", new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {
			  	for(Object o : args) {
			  		System.out.println(o);
			  	}
			  	c.chooseMove(list).getValue();
			  	System.out.println(c.chooseMove(list).getValue());
			  	socket.emit("move", c.chooseMove(list).getValue());
			  }

			}).on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {

			  @Override
			  public void call(Object... args) {}

			});
			socket.connect();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		int portNumber = 9000;
//		Configuration config = new Configuration();
//		config.setHostname("localhost");
//		config.setPort(portNumber);
//		
//		final SocketIOServer server = new SocketIOServer(config);
//		server.addEventListener("connection", String.class, new DataListener<String>() {
//
//			@Override
//			public void onData(SocketIOClient client, String data, AckRequest ackRequest) throws Exception {
//				System.out.println(client.toString());
//				System.out.println(data);
//				System.out.println(ackRequest);
//				
//			}
//			
//		});
//		server.addConnectListener(new ConnectListener() {
//
//			@Override
//			public void onConnect(SocketIOClient client) {
//				System.out.println(client.toString());
//			}
//			
//		});
		
//		server.start();
//		
//		Thread.sleep(Integer.MAX_VALUE);
//		
//		server.stop();
//		try {
//			ServerSocket serverSocket = new ServerSocket(portNumber);
//			Socket clientSocket = serverSocket.accept();
//			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
//			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//			
//			String inputLine, outputLine;
//			out.println("connected");
//			List<Tile> list = new ArrayList<Tile>();
//			String choice;
//			int count = 0;
//			while ((inputLine = in.readLine()) != "exit") {
//				choice = c.chooseMove(list).getValue();
//        //System.out.println("Client: " + choice);
//				System.out.println(inputLine);
//				if(inputLine == null)
//					out.println(1);
//        if(count++ > 30 ) {
//        	break;
//        }
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
		
//		try {
//			Socket socket = new Socket(hostName, portNumber);
//			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//			
//			String userInput, fromServer;
//			List<Tile> list = new ArrayList<Tile>();
//			String choice;
//			while ((fromServer = in.readLine()) != null) {
//				//TODO create tile mapper from JSON and use that in chooseMove below
//			    if (fromServer.equals("Bye."))
//			        break;
//
//			    	choice = c.chooseMove(list).getValue();
//		        System.out.println("Client: " + choice);
//		        out.println(choice);
//			    
//				
//			}
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	


}
