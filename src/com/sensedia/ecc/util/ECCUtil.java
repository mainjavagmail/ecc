package com.sensedia.ecc.util;

public class ECCUtil {

	static char[] hexaChars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String byteToStringHex(byte[] vector) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : vector) {
			int unsignedByte = b & 0xFF;
			stringBuilder.append(hexaChars[unsignedByte / 16]);
			stringBuilder.append(hexaChars[unsignedByte % 16]);
		}
		return stringBuilder.toString();
	}

	public static byte[] stringHexToByte(String hex) {
		byte[] val = new byte[hex.length() / 2];
		for (int i = 0; i < val.length; i++) {
			int index = i * 2;
			int j = Integer.parseInt(hex.substring(index, index + 2), 16);
			val[i] = (byte) j;
		}
		return val;
	}
	
	public static String byteToHex(byte num) {
		char[] hexDigits = new char[2];
		hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
		hexDigits[1] = Character.forDigit((num & 0xF), 16);
		return new String(hexDigits);
	}

	public static String encodeHexString(byte[] byteArray) {
		StringBuffer hexStringBuffer = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			hexStringBuffer.append(byteToHex(byteArray[i]));
		}
		return hexStringBuffer.toString();
	}

	public static byte[] hexStringToByteArray(String str) {
		byte[] val = new byte[str.length() / 2];
		for (int i = 0; i < val.length; i++) {
			int index = i * 2;
			int j = Integer.parseInt(str.substring(index, index + 2), 16);
			val[i] = (byte) j;
		}
		return val;
	}
}
