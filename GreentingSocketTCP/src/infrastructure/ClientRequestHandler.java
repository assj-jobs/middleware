package infrastructure;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientRequestHandler {
	
	public String send(byte[] msg) {
		String retorno = "";
		try {
			//Host
			InetAddress ip = InetAddress.getLocalHost();
			//Implementa um serviço de entrega de pacotes sem conexão.
			DatagramPacket dpk = new DatagramPacket(msg, msg.length, ip, 1313);
			//Ponto de envio ou recebimento de um serviço de entrega de pacotes.
			DatagramSocket socket = new DatagramSocket();
			//Envio de pacote 
			socket.send(dpk);
			socket.close();			
		} catch (IOException e) {
			System.out.println("Erro: ClientRequestHandler: " + e.getMessage());
			retorno = "Não foi possível enviar o objeto!";
		}
		return retorno;
	}
}
