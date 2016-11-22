package distribution.requestPacket;

import java.io.Serializable;

import distribution.message.Message;

public class RequestPacket implements Serializable {
	
	private static final long serialVersionUID = 4377956910446904220L;

	private RequestPacketHeader requestPacketHeader;
	private RequestPacketBody requestPacketBody;
	private Message message;
	
	public RequestPacket(RequestPacketHeader requestPacketHeader, 
						  RequestPacketBody requestPacketBody,
						  Message message){
		this.requestPacketHeader = requestPacketHeader;
		this.requestPacketBody = requestPacketBody;
		this.message = message;
	}
	
	public RequestPacketBody getRequestPacketBody() {
		return requestPacketBody;
	}
	public void setRequestPacketBody(RequestPacketBody requestPacketBody) {
		this.requestPacketBody = requestPacketBody;
	}

	public RequestPacketHeader getRequestPacketHeader() {
		return requestPacketHeader;
	}

	public void setRequestPacketHeader(RequestPacketHeader requestPacketHeader) {
		this.requestPacketHeader = requestPacketHeader;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
