package infrastructure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import distribution.Marshaller;

public class ServerRequestHandler {

	public static void main(String[] args) {

		try {
			Marshaller m = new Marshaller();
			byte[] sendMsg = new byte[1024];
			byte[] receiveMsg = new byte[1024];
			String message = "";
			int PORT = 1313;


			//SET PROTOCOL IN THE FILE PROPERTIES
			Scanner p = new Scanner(System.in);
			System.out.println("\n(0) - UDP");
			System.out.println("(1) - TCP");
			System.out.println("(2) - HTML\n");
			System.out.println("Informe o protocolo de comunicação desejado: ");
			int protocolo = p.nextInt();

			String nmProtocolo = "";
			switch(protocolo){
			case 0:
				nmProtocolo = "UDP";
				break;
			case 1:
				nmProtocolo = "TCP";
				break;
			case 2:
				nmProtocolo = "HTML";
				break;
			}

			File file = new File("./protocol.properties");
			if(!file.exists()){
				file.createNewFile();
			}

			OutputStream out = new FileOutputStream(file);
			out.write(nmProtocolo.getBytes());
			out.close();

			switch(nmProtocolo){
			case "UDP":
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

				//Get result by convertion.
				double resultUDP = ConvertTemperature(Integer.parseInt(inputUDP[0]), 
						Double.parseDouble(inputUDP[1]));

				//Marshalling message
				sendMsg = m.convertToBytes(resultUDP);

				//Package to response.
				DatagramPacket dps = new DatagramPacket(sendMsg, sendMsg.length, IPAddress, port);
				ds.send(dps);
				ds.close();
				break;
			case "TCP":
				ServerSocket s = new ServerSocket(PORT);
				Socket client = s.accept();
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				byte[] msg = (byte[]) ois.readObject();

				String[] inputTCP = m.convertFromBytes(msg).toString().split(",");

				double resultTCP = ConvertTemperature(Integer.parseInt(inputTCP[0]), 
						Double.parseDouble(inputTCP[1]));

				ObjectOutputStream otc = new ObjectOutputStream(client.getOutputStream());
				otc.writeObject(m.convertToBytes(resultTCP));
				otc.flush();
				break;
			case "HTML":
				HttpServer server = HttpServer.create(new InetSocketAddress(1313), 0);
				server.createContext("/", new InfoHandler());
				server.createContext("/convertion", new Convertion());
				server.setExecutor(null);
				server.start();
			}
			System.out.println(message);
		} catch (Exception e) {
			System.out.println("Erro: ServerRequestHandler: " + e.getMessage());
		}
	}


	static double ConvertTemperature(int type, double value){
		double result = 0;
		switch (type) {
		case 0:
			result = (value - 32) / 1.8;
			break;
		case 1:
			result = value * 1.8 + 32;
			break;
		case 2:
			result = value - 273.15;
			break;
		case 3:
			result = value + 273.15;
			break;
		}

		return result;
	}

	static class InfoHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			String response = "Use /convert to convert temperature";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
	
	static class Convertion implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
			String response = "Use /convert to convert temperature";
			t.sendResponseHeaders(200, response.length());
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}


}
