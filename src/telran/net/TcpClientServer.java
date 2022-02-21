package telran.net;

import java.io.*;
import java.net.*;

import telran.net.dto.Request;
import telran.net.dto.Response;

public class TcpClientServer implements Runnable {
	
	Socket socket;
	ObjectInputStream reader;
	ObjectOutputStream writer;
	AppProtocol protocol;
	
	public TcpClientServer(Socket socket, AppProtocol protocol) throws Exception {
		this.socket = socket;
		this.protocol = protocol;
		reader = new ObjectInputStream(socket.getInputStream());
		writer = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		try {
			while(true) {
				Request request = (Request) reader.readObject();
				Response response = protocol.getResponse(request);
				writer.writeObject(response);
			}
		}catch(EOFException e){
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
