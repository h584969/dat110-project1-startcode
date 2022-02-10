package no.hvl.dat110.messaging;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] data;

	public Message(byte[] data) {
		if (data == null) {
			throw new NullPointerException("Data can't be null");
		}
		if (data.length > 127) {
			throw new IllegalArgumentException("To many bytes, expected array up to 127 bytes, recieved " + data.length + " bytes!");
		}
		
		this.data = data;
	}

	public byte[] getData() {
		return this.data; 
	}

}
