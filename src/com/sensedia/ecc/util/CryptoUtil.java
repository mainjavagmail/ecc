package com.sensedia.ecc.util;

import java.io.ByteArrayInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import com.sensedia.ecc.ecies.ECIES;
import com.sensedia.ecc.ecies.ECIESMessage;

public class CryptoUtil {

	public static String encrypt( PublicKey key, String data ) {
		String ret = null;
		try {
			ECIESMessage message;
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			message = ECIES.encryptData(key, (String) data, random);
			//ret = ECCUtil.byteToStringHex(message.getR());
			ret = new String(message.getR());
		}catch( Exception e ) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String decrypt( PrivateKey key, String data ) {
		String ret = null;
		try {
			//SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ByteArrayInputStream in = new ByteArrayInputStream(data.getBytes());
			ECIESMessage message = ECIESMessage.deserialize(in);
			//byte[] plaintext = ECIES.decryptMessage(key, message);
			ret = new String(message.getR());
		}catch( Exception e ) {
			e.printStackTrace();
		}
		return ret;
	}
	
	
	public static void main(String[] args) {
		PublicKey pubKey = KeyUtil.genPubKey();
		String data = "Esse texto deve ser criptografada";
		String dataEnc = encrypt(pubKey, data);
		System.out.println(dataEnc);
		PrivateKey priKey = KeyUtil.genPriKey();
		String dataDec = decrypt(priKey, dataEnc);
		System.out.println(dataDec);
	}
}
