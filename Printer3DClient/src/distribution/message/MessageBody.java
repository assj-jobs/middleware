package distribution.message;

import java.io.Serializable;

public class MessageBody implements Serializable{
	
	private static final long serialVersionUID = 8844771654818227902L;

	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	

}
