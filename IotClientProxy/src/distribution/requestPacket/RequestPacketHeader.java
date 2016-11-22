package distribution.requestPacket;

import java.io.Serializable;

public class RequestPacketHeader implements Serializable{

	private static final long serialVersionUID = 5035748997261968728L;
	
	private String queueNameDestiny;
	
	public RequestPacketHeader(String queueNameDestiny){
		this.queueNameDestiny = queueNameDestiny;
	}

	public String getQueueNameDestiny() {
		return queueNameDestiny;
	}

	public void setQueueNameDestiny(String queueNameDestiny) {
		this.queueNameDestiny = queueNameDestiny;
	}
	
	
	
	

}
