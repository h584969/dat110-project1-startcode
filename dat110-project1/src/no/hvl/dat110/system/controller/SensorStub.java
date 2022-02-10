package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class SensorStub extends RPCLocalStub {

	private byte RPCIDREAD = 1;
	
	public SensorStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public int read() {
		
		int temp = 0;
		
		byte[] data = RPCUtils.marshallVoid();
		
		byte[] result = rpcclient.call(RPCIDREAD, data);
		
		temp = RPCUtils.unmarshallInteger(result);
		
		return temp;
	}
	
}
