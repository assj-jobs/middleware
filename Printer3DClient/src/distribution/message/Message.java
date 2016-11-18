package distribution.message;

import java.io.Serializable;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 2743117559646953358L;
	
	private MessageHeader header;
	private MessageBody body;
	
	public MessageHeader getHeader() {
		return header;
	}
	public void setHeader(MessageHeader header) {
		this.header = header;
	}
	public MessageBody getBody() {
		return body;
	}
	public void setBody(MessageBody body) {
		this.body = body;
	}
	
	

}
