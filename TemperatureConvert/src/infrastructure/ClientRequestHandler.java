package infrastructure;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;

import distribution.Marshaller;

public class ClientRequestHandler {

	public byte[] send(byte[] send_msg, byte[] receive_msg) {
		byte[] retorno = null;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			String protocol = "";
			
			//GET PROTOCOL TYPE IN FILE
			InputStream is = new FileInputStream("C:/repositorio/TemperatureConvertSever/protocol.properties");
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
					String USER_AGENT = "Mozilla/5.0";
					/**
					 * NÃO CONSEGUI ENVIAR A MENSAGEM EM FORMA DE BYTE[].
					 * CONTINUAREI PESQUISANDO...
					 */
					//************************************************************
					Marshaller m = new Marshaller();							//
					String message = m.convertFromBytes(send_msg).toString();	//
					String[] params = message.split(",");						//
					//************************************************************
					String url = "http://localhost/convertion?type=" + params[0] + "&value=" + URLEncoder.encode(params[1], "UTF-8");
					 
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection(
							new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 1313))
					);
			
					con.setRequestMethod("GET");
					con.setRequestProperty("User-Agent", USER_AGENT);
			 
					int responseCode = con.getResponseCode();
			 
					BufferedReader in = new BufferedReader(
					        new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();
			 
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					retorno = m.convertToBytes(response.toString());
					in.close();
				break;
			}	
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Erro: ClientRequestHandler: " + e.getMessage());
		} 
		return retorno;
	}
}
