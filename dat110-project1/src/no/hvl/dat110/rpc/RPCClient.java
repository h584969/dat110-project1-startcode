package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		
		connection = msgclient.connect();
	}
	
	public void disconnect() {
		
		connection.close();
	}
	
	public byte[] call(byte rpcid, byte[] params) {
		
		byte[] data = RPCUtils.encapsulate(rpcid, params);
		Message message = new Message(data);
		
		connection.send(message);
		
		Message resultMessage = connection.receive();
		
		byte[] resultData = resultMessage.getData();
		if (resultData[0] != rpcid) {
			throw new IllegalStateException("Called method " + rpcid + ". recieved response for method " + resultData[0]);
		}
		
		byte[] returnval = RPCUtils.decapsulate(resultData);	
		return returnval;
	}

}
