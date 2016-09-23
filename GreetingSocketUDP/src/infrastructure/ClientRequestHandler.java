package infrastructure;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientRequestHandler {
	
	public String send(byte[] msg) {
		String retorno = "";
		try {
			//Definindo o ip da máquina 
			InetAddress ip = InetAddress.getLocalHost();
			DatagramPacket dpk = new DatagramPacket(msg, msg.length, ip, 1313);
			DatagramSocket socket = new DatagramSocket();
			socket.send(dpk);
			socket.close();			
		} catch (IOException e) {
			System.out.println("Erro: ClientRequestHandler: " + e.getMessage());
			retorno = "Não foi possível enviar o objeto!";
		}
		return retorno;
	}
}
