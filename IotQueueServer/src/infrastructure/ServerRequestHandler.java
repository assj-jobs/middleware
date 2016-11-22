package infrastructure;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import distribution.marshaller.Marshaller;

public class ServerRequestHandler {


	public static void main(String[] args) {
		try {
			Marshaller m = new Marshaller();
			byte[] sendMsg = new byte[1024];
			byte[] receiveMsg = new byte[1024];
			int PORT = 1313;


			//Sending or receiving point for a packet delivery service.
			DatagramSocket ds = new DatagramSocket(PORT);

			//Package to receive.
			DatagramPacket dpr = new DatagramPacket(receiveMsg, receiveMsg.length);


			//Receives a datagram packet from this socket.
			ds.receive(dpr);
			//Get ip origin.
			InetAddress IPAddress = dpr.getAddress();

			//Get port origin.
			int port = dpr.getPort();

			//Unmarshaling message
			String[] inputUDP = m.convertFromBytes(dpr.getData()).toString().split(",");


			//Marshalling message
			sendMsg = m.convertToBytes("");

			//Package to response.
			DatagramPacket dps = new DatagramPacket(sendMsg, sendMsg.length, IPAddress, port);
			ds.send(dps);
			ds.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
