package com.sensedia.ecc.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.KeyPair;

import com.sensedia.ecc.util.ECCUtil;
import com.sensedia.ecc.util.KeyUtil;

public class KeyTest {

	public static void main(String[] args) throws Exception {
		test1();
		String keyHex = "9FBC7D5B53AF152BA5F0688F1740C36D939A5DB3ACB50DFE44768C31D914977C";
		byte[] key = ECCUtil.hexStringToByteArray(keyHex);
		keyHexToFile(new String(key),"public.key");
		keyHexToFile(keyHex,"public_hex.key");
	}

	private static void test1() throws Exception {
		KeyPair kp = KeyUtil.genKeyPair();
		KeyPair kp2 = KeyUtil.getKeyPair();
		System.out.println("Key1");
		System.out.println("publicKey: "+KeyUtil.toStringHexa(kp.getPublic()));
		System.out.println("privateKey: "+KeyUtil.toStringHexa(kp.getPrivate()));
		System.out.println("Key2");
		System.out.println("publicKey: "+KeyUtil.toStringHexa(kp2.getPublic()));
		System.out.println("privateKey: "+KeyUtil.toStringHexa(kp2.getPrivate()));
	}
	
	private static void keyHexToFile(String value, String fileName) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(new File(fileName));
			bw = new BufferedWriter(fw);
			bw.write(new String(value));
			bw.flush();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try{bw.close();}catch(Exception e) {}
			try{fw.close();}catch(Exception e) {}
		}
	}
}
