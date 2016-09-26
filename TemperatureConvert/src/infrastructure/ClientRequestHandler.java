package infrastructure;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ClientRequestHandler {

	public byte[] send(byte[] send_msg, byte[] receive_msg) {
		byte[] retorno = null;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			String protocol = "";
			
			//GET PROTOCOL TYPE IN FILE
			InputStream is = new FileInputStream("./protocol.properties");
			int size = is.available();

			for(int i = 0; i < size; i++) {
				protocol += (char)is.read();
			}
			is.close();

			switch(protocol){
			case "UDP":
				//Send
				DatagramPacket send_pkg = new DatagramPacket(send_msg, send_msg.length, ip, 1313);
				DatagramSocket socketUDP = new DatagramSocket();
				socketUDP.send(send_pkg);
				
				//Receive
				DatagramPacket receive_pkg = new DatagramPacket(receive_msg, receive_msg.length);	
				socketUDP.receive(receive_pkg);
				retorno = receive_pkg.getData();
				socketUDP.close();
				break;
			case "TCP":
				Socket socketTCP = new Socket(ip, 1313);
				ObjectOutputStream out2server = new ObjectOutputStream(socketTCP.getOutputStream());
				out2server.writeObject(send_msg);
				out2server.flush();
				
				ObjectInputStream inFromServer = new ObjectInputStream(socketTCP.getInputStream());
				retorno = (byte[]) inFromServer.readObject();
				socketTCP.close();
				break;
			case "HTML":
				
				break;
			}	
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Erro: ClientRequestHandler: " + e.getMessage());
		} 
		return retorno;
	}
}
