package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = new byte[payload.length + 1];
		
		rpcmsg[0] = rpcid;
		
		for (int i = 0; i < payload.length; i++) {
			rpcmsg[i+1] = payload[i];
		}
		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = new byte[rpcmsg.length-1];

		for (int i = 0; i < payload.length; i++) {
			payload[i] = rpcmsg[i+1];
		}
		
		return payload;
	}
	
	public static byte[] marshallString(String str) {
		
		
		byte[] encoded = str.getBytes();
			
		return encoded;
	}
	
	public static String unmarshallString(byte[] data) {
		
		String decoded = new String(data);
		
		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = new byte[0];
		
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {

		if (data.length != 0) {
			throw new IllegalArgumentException("Expected array of 0, recieved " + data.length);
		}
	
	}
	
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}
	
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}
	
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = new byte[4];
		
		encoded[0] = (byte)(x >> 24);
		encoded[1] = (byte)(x >> 16);
		encoded[2] = (byte)(x >> 8);
		encoded[3] = (byte)x;
		
		return encoded;
	}
	
	
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;
		
		decoded |= Byte.toUnsignedInt(data[0]) << 24;
		decoded |= Byte.toUnsignedInt(data[1]) << 16;
		decoded |= Byte.toUnsignedInt(data[2]) << 8;
		decoded |= Byte.toUnsignedInt(data[3]);
		return decoded;
	}
}
