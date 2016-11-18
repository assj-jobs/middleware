package distribution.marshaller;

public interface IMarshaller {

	byte[] convertToBytes(Object o);
	
	Object convertFromBytes(byte[] b);
	
}
