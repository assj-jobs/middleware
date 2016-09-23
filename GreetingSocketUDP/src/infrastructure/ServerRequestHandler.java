package infrastructure;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import distribution.Marshaller;

public class ServerRequestHandler {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket(1313);
			Marshaller m = new Marshaller();
			System.out.println("Servidor iniciado!");
			System.out.println("Ouvindo a porta: 1313...");
			byte[] msg = new byte[256];
			DatagramPacket dp = new DatagramPacket(msg, msg.length);
			ds.receive(dp);
			
			String nameClient = m.convertFromBytes(dp.getData()).toString();
			
			System.out.println("Mensagem recebida com sucesso!");
			System.out.println("Olá, " + nameClient +"!");
			ds.close();
		} catch (Exception e) {
			System.out.println("Erro: ServerRequestHandler: " + e.getMessage());
		}
	}
}
