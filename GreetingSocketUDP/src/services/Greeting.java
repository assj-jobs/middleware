package services;

import java.util.Scanner;

import distribution.Marshaller;
import infrastructure.ClientRequestHandler;

public class Greeting {

	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in);
		System.out.println("Digite seu nome: ");
		
		String name = ler.next();
		ler.close();
		
		ClientRequestHandler crh = new ClientRequestHandler();
		Marshaller m = new Marshaller();
		
		crh.send(m.convertToBytes(name));
		
	}

}
