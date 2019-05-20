package com.sensedia.ecc.test;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;

import com.sensedia.ecc.util.ECCUtil;
import com.sensedia.ecc.util.KeyUtil;

public class WhsCrypto {

	public static void main(String[] args) {
		aesTest();
		testDecrypt();
	}

	private static void aesTest(){
		/**
		String secretPhrase = "mysecretphrase";
		byte[] pubKey = Crypto.getPublicKey(secretPhrase);
		byte[] priKey = Crypto.getPrivateKey(secretPhrase);
		*/
		KeyPair kp = KeyUtil.genKeyPair();
		byte[] pubKey = KeyUtil.toByte(kp.getPublic());
		System.out.println("pubKey: "+ECCUtil.byteToStringHex(pubKey));
		byte[] priKey = KeyUtil.toByte(kp.getPrivate());
		System.out.println("priKey: "+ECCUtil.byteToStringHex(priKey));
		String message = "original message";
		byte[] messEncrypt = Crypto.aesEncrypt(message.getBytes(), priKey, pubKey);
		System.out.println("messEncrypt: "+ECCUtil.byteToStringHex(messEncrypt));
		byte[] messDecrypt = Crypto.aesDecrypt(messEncrypt, priKey, pubKey);
		try {
			System.out.println("messDecrypt: "+new String(messDecrypt,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void testDecrypt(){
		String pubKeyStrHex = "DB31CFF9566CF0E2C6D048F43C18D4410758A6F26735EBA7EDB204028894E34A";
		String priKeyStrHex = "E8A96900C0C7EE6AD3871496E178092D7D1B66DDF925AC908EDA661EC2F76E70";
		String msgStrHex = "39D0A3E2FD56FB6C0FB11B3089C1054B56895983120ECC521B6E49776745C4C19D9415EBBBF61C0239326BC503EAEFD2";
		byte[] pubKeyByte = ECCUtil.stringHexToByte(pubKeyStrHex);
		byte[] priKeyByte = ECCUtil.stringHexToByte(priKeyStrHex);
		byte[] msgByte = ECCUtil.stringHexToByte(msgStrHex);
		byte[] messDecrypt = Crypto.aesDecrypt(msgByte, priKeyByte, pubKeyByte);
		try{
			System.out.println("messDecrypt: "+new String(messDecrypt,"UTF-8"));
		}catch( Exception e ){
			e.printStackTrace();
		}
	}
	
}
