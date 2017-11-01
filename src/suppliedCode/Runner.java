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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.socket.client.IO;
import io.socket.emitter.Emitter;


public class Runner {
	
	public static void run(Critter c) throws InterruptedException {
		
		ObjectMapper objectMapper = new ObjectMapper();
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
			  	Response response = null;
			  	try {
						response = objectMapper.readValue(args[0].toString(), Response.class);
					} catch (JsonParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JsonMappingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  	
			  	
			  	for(Tile t : response.getGameBoard()) {
			  		System.out.println(t.getPlayer());
				  	System.out.println(t.getX());
			  	}
			  	

			  	socket.emit("move", c.chooseMove(response).getValue());
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
		
		

	}
	


}
