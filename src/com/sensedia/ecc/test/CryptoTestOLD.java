package com.sensedia.ecc.test;

import java.io.UnsupportedEncodingException;

import com.sensedia.ecc.util.ECCUtil;

public class CryptoTestOLD {

	public static void main(String[] args) {
		pubKey();
		priKey();
		sign();
		aesTest();
	}

	private static void pubKey(){
		String secretPhrase = "mysecretphrase";
		byte[] pubKey = Crypto.getPublicKey(secretPhrase);
		String pubKeyStr = ECCUtil.encodeHexString(pubKey);
		String strPubKey;
		try {
			strPubKey = new String(pubKey, "UTF-8");
			System.out.println("strPubKey: "+strPubKey+" pubKeyStr: "+pubKeyStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void priKey(){
		String secretPhrase = "mysecretphrase";
		byte[] priKey = Crypto.getPrivateKey(secretPhrase);
		String priKeyStr = ECCUtil.encodeHexString(priKey);
		String strPriKey = priKey.toString();
		System.out.println("strPriKey: "+strPriKey+" priKeyStr: "+priKeyStr);
	}
	
	private static void sign(){
		String secretPhrase = "mysecretphrase";
		String message = "see my message";
		byte[] resp = Crypto.sign(message.getBytes(), secretPhrase);
		String strResp = resp.toString();
		System.out.println("sign - strResp: "+strResp+" strRespHex: "+ECCUtil.encodeHexString(resp));
	}
	
	private static void aesTest(){
		String secretPhrase = "mysecretphrase";
		byte[] pubKey = Crypto.getPublicKey(secretPhrase);
		byte[] priKey = Crypto.getPrivateKey(secretPhrase);
		String message = "original message";
		byte[] messEncrypt = Crypto.aesEncrypt(message.getBytes(), priKey, pubKey);
		System.out.println("messEncrypt: "+messEncrypt);
		byte[] messDecrypt = Crypto.aesDecrypt(messEncrypt, priKey, pubKey);
		try {
			System.out.println("messDecrypt: "+new String(messDecrypt,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
