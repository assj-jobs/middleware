package infrastructure;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientRequestHandler {

	public byte[] send(byte[] send_msg, byte[] receive_msg) {
		byte[] retorno = null;
		try {
			InetAddress ip = InetAddress.getLocalHost();

			DatagramPacket send_pkg = new DatagramPacket(send_msg, send_msg.length, ip, 1313);
			DatagramSocket socketUDP = new DatagramSocket();
			socketUDP.send(send_pkg);

			DatagramPacket receive_pkg = new DatagramPacket(receive_msg, receive_msg.length);	
			socketUDP.receive(receive_pkg);
			retorno = receive_pkg.getData();
			socketUDP.close();
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		return retorno;
	}
}

