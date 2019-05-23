package com.sensedia.ecc.test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.spec.ECGenParameterSpec;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class TestCipher {

	public static void main(String[] args) throws Exception {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
		ecKeyGen.initialize(new ECGenParameterSpec("brainpoolP384r1"));

		// doesn't work, which means we are dancing on the leading edge :)
		// KeyPairGenerator ecKeyGen = KeyPairGenerator.getInstance("EC");
		// ecKeyGen.initialize(new ECGenParameterSpec("secp384r1"));

		KeyPair ecKeyPair = ecKeyGen.generateKeyPair();
		System.out.println("What is slow?");

		Cipher iesCipher = Cipher.getInstance("AES/ECB/PKCS7Padding","BC");
		iesCipher.init(Cipher.ENCRYPT_MODE, ecKeyPair.getPublic());

		byte[] ciphertext = iesCipher.doFinal("mensagem a criptografar".getBytes());

		iesCipher.init(Cipher.DECRYPT_MODE, ecKeyPair.getPrivate());
		byte[] plaintext = iesCipher.doFinal(ciphertext);

		System.out.println(Hex.toHexString(ciphertext));
		System.out.println(new String(plaintext));
	}
	
}
