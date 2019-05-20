package com.sensedia.ecc.util;

import java.security.Key;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import com.sensedia.ecc.curve.Curve25519KeyPairGenerator;

public class KeyUtil {

	/*
	public static String genPubKey() {
		String ret = null;
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			KeyPair keyPair = Curve25519KeyPairGenerator.generateKeyPair(random);
			PublicKey pub = keyPair.getPublic();
			PrivateKey prv = keyPair.getPrivate();
	
			byte[] pubBytes = pub.getEncoded();
			byte[] prvBytes = prv.getEncoded();
			ret = ECCUtil.byteToStringHexa(pubBytes);
		}catch( Exception e ) {
			
		}
		return ret;
	}*/
	
	public static String toStringHexa( Key key ) {
		byte[] keyBytes = toByte(key);
		return ECCUtil.byteToStringHex(keyBytes);
	}
	
	public static byte[] toByte( Key key ) {
		return key.getEncoded();
	}
	
	public static PublicKey genPubKey() {
		PublicKey ret = null;
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			KeyPair keyPair = Curve25519KeyPairGenerator.generateKeyPair(random);
			ret = keyPair.getPublic();
		}catch( Exception e ) {
			
		}
		return ret;
	}
	
	public static PrivateKey genPriKey() {
		PrivateKey ret = null;
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			KeyPair keyPair = Curve25519KeyPairGenerator.generateKeyPair(random);
			ret = keyPair.getPrivate();
		}catch( Exception e ) {
			
		}
		return ret;
	}
	
	public static KeyPair genKeyPair(SecureRandom secureRandom) {
		KeyPair ret = null;
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ret = Curve25519KeyPairGenerator.generateKeyPair(random);
		}catch( Exception e ) {
			
		}
		return ret;
	}
	
	public static KeyPair genKeyPair() {
		KeyPair ret = null;
		try {
			ret = genKeyPair(SecureRandom.getInstance("SHA1PRNG"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static void main( String[] args ) {
		String pubKey = toStringHexa(genPubKey());
		System.out.println(pubKey);
		String priKey = toStringHexa(genPriKey());
		System.out.println(priKey);
		byte[] pubKeyByte = ECCUtil.stringHexToByte(pubKey);
		String s = new String(pubKeyByte);
		System.out.println(s);
	}
}
