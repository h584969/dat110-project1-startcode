package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static final int MESSAGINGPORT = 8080;
	public static final String MESSAGINGHOST = "localhost";
	
	public static byte[] encapsulate(Message message) {
		
		byte[] segment = new byte[128];
		byte[] data = message.getData();
		
		if (data.length > 127) {
			throw new IllegalArgumentException("Invalid Message. Payload must be less than or equal to 127 bytes");
		}
		
		int padding = 127 - data.length;
		
		segment[0] = (byte)data.length;
		
		for (int i = 0; i < data.length; i++) {
			segment[i+1] = data[i];
		}
		
		return segment;
	}

	public static Message decapsulate(byte[] segment) {

		if (segment.length > 128) {
			throw new IllegalArgumentException("Expected segment with 128 bytes or less, recieved segment with " + segment.length + " bytes");
		}
		
		//cast to unsigned int to simplify range check
		int count = Byte.toUnsignedInt(segment[0]);
		
		if (count > segment.length - 1) {
			throw new IllegalArgumentException("Size parameter to large, data size: " + (segment.length - 1) + ", specified size: " + (count));
		}
		
		byte[] data = new byte[count];
		
		for (int x = 0; x < count; x++) {
			data[x] = segment[x+1];
		}
		
		Message message = new Message(data);
		
		return message;
		
	}
	
}
