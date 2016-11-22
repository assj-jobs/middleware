package distribution.message;

import java.io.Serializable;

public class MessageHeader implements Serializable {

	private static final long serialVersionUID = -502847313908535233L;
	
	public MessageHeader(String destination){
		this.destination = destination;
	}
	
	private String destination;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
