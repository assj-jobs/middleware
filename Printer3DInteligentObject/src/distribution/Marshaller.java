package distribution;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Marshaller implements IMarshaller{
	
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ObjectOutput out;

	@Override
	public byte[] convertToBytes(Object o){
		try {
			out = new ObjectOutputStream(baos);
			out.writeObject(o);
			out.flush();			
			return baos.toByteArray();
			
		} catch (IOException e) {
			System.out.println("Error: Marshaller: convertToBytes : " + e.getMessage());
			return null;
		} finally{ 
			try{
				baos.close();
			}
			catch (Exception e) {
				System.out.println("Error: Marshaller: convertToBytes : " + e.getMessage());
			}
		}
	}

	@Override
	public Object convertFromBytes(byte[] b){
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		ObjectInput input;
		try {
			
			input = new ObjectInputStream(bais);
			return input.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error: Marshaller: convertFromBytes : " + e.getMessage());
			return null;
		} finally {
			try {
				bais.close();
			} catch (Exception e) {
				System.out.println("Error: Marshaller: convertFromBytes : " + e.getMessage());
				return null;
			}
		}
	}
	
}
