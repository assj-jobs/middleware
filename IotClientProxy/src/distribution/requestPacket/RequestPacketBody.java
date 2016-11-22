package distribution.requestPacket;

import java.io.Serializable;

public class RequestPacketBody implements Serializable{

	private static final long serialVersionUID = 4945722857267126563L;
	
	private String bodyPacket;
	
	public RequestPacketBody(String bodyPacket){
		this.bodyPacket = bodyPacket;
	}

	public String getBodyPacket() {
		return bodyPacket;
	}

	public void setBodyPacket(String bodyPacket) {
		this.bodyPacket = bodyPacket;
	}
	
	

}
