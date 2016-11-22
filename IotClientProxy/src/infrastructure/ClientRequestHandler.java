package infrastructure;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientRequestHandler implements Serializable{
	
	private static final long serialVersionUID = 5506527451620250429L;
	private String host;
	private int port;
	
	public ClientRequestHandler(String host, int port){
		this.host = host;
		this.port = port;
	}

	public byte[] send(byte[] send_msg, byte[] receive_msg) {
		byte[] retorno = null;
		try {
			InetAddress ip = InetAddress.getByName(this.host);
			DatagramPacket send_pkg = new DatagramPacket(send_msg, send_msg.length, ip, this.port);
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

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}

