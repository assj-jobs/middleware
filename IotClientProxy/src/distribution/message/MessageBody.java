package distribution.message;

import java.io.Serializable;
import java.util.HashMap;

public class MessageBody implements Serializable{
	
	private static final long serialVersionUID = 8844771654818227902L;
	private String body;
	private HashMap<String, Object> parameters;

	
	public MessageBody(HashMap<String, Object> parameters, String body){
		this.parameters = parameters;
		this.body = body;
	}
	
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public HashMap<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}
	

}
