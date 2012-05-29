package am.monitor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.ow2.wildcat.hierarchy.attribute.POJOAttribute;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

public class WorkloadSensor extends POJOAttribute {

	private String host;
	private int port;
	private String service;

	public WorkloadSensor(String host, int port, String service) {
		super(null);

		this.host = host;
		this.port = port;
		this.service = service;

	}
	

	@Override
	public Object getValue() {
		Map<String, Object> m = new HashMap<String, Object>();

		String url = "http://" + this.host + ":" + this.port + "/"+ this.service;

		ClientResource requestResource = new ClientResource(
				url);

		Representation reply = requestResource.get();
		String replyText;
		try {
			replyText = reply.getText();

			if (reply.getMediaType().equals(new MediaType("text/html"))) {

				int i = replyText.indexOf("requests/sec");


				int j = replyText.substring(0, i-1).lastIndexOf("<dt>");


				String s = replyText.substring(j+4, i-1);


				m.put("workload",Double.parseDouble(s));



			}
			reply.release();
			requestResource.release();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		//System.out.println("executado");

		return m;
	}

	public static void main(String[] args) {
		WorkloadSensor ws = new WorkloadSensor("localhost",80,"server-status");
		System.out.println(ws.getValue());
	}
}

