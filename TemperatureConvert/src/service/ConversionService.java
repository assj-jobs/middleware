package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import distribution.Marshaller;
import infrastructure.ClientRequestHandler;
import infrastructure.ServerRequestHandler;

public class ConversionService {

	public static void main(String[] args) throws IOException {
			FileInputStream fis = null;
			Scanner type = new Scanner(System.in);

	        System.out.println("CONVERSÃO DE TEMPERATURA: ");
	        System.out.println("-------------------------------------\n");
	        System.out.println("(0) - Fahrenheit To Celsius");
	        System.out.println("(1) - Celsius To Fahrenheit");
	        System.out.println("(2) - Kelvin To Celsius");
	        System.out.println("(3) - Celsius To Kelvin\n");
	        System.out.println("Qual tipo de conversão você deseja realizar?");
	        int tipo = type.nextInt();

	        String nmType = "";
	        switch (tipo) {
			case 0:
				nmType = "Fahrenheit To Celsius";
				break;
			case 1:
				nmType = "Celsius To Fahrenheit";
				break;
			case 2:
				nmType = "Kelvin To Celsius";
				break;
			case 3:
				nmType = "Celsius To Kelvin";
				break;
			default :
				nmType = "None";
				break;
			}
	        
	        Scanner value = new Scanner(System.in);
	        value.useLocale(Locale.ENGLISH);
	        System.out.println("\nInforme o valor que deseja converter:");
	        double valor = value.nextDouble();
	        
	        ClientRequestHandler crh = new ClientRequestHandler();
			Marshaller m = new Marshaller();
			
			String msg = String.valueOf(tipo);
			msg += ", " + String.valueOf(valor);
			
			byte[] response = crh.send(m.convertToBytes(msg), new byte[1024]);
			double resultado = Double.parseDouble(m.convertFromBytes(response).toString());
			System.out.println("\nResultado da conversão: " + String.format(Locale.ENGLISH,"%.2f", resultado));
					
			type.close();
			value.close();
	}

}
