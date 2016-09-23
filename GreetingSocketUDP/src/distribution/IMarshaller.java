package distribution;

public interface IMarshaller {

	byte[] convertToBytes(Object o);
	
	Object convertFromBytes(byte[] b);
	
}
