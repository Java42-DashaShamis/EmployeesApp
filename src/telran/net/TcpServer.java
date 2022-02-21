package telran.net;

import java.io.IOException;
import java.net.*;

public class TcpServer implements Runnable {
	
	private int port;
	private ServerSocket serverSocket;
	private AppProtocol protocol;
	
	public TcpServer(int port, AppProtocol protocol) throws Exception{
		this.port = port;
		this.protocol = protocol;
		serverSocket = new ServerSocket(port);
	}

	@Override
	public void run() {
		System.out.println("Server is listening on port" + port);
		
			while(true) {
				try {
					Socket socket = serverSocket.accept();
					TcpClientServer client = new TcpClientServer(socket, protocol);
					client.run();
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
			}
		
	}

}
