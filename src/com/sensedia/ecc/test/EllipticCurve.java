package com.sensedia.ecc.test;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import com.sensedia.ecc.util.ECCUtil;
import com.sensedia.ecc.util.KeyUtil;

public class EllipticCurve {

	public static void main(String[] args) {
		if( args != null && args.length > 0 ) {
			String operation = null;
			String pubKey = null;
			String priKey = null;
			String value = null;
			for( int i = 0; i < args.length; i++ ) {
				if( args[i].indexOf("operation::") > -1 ) {
					operation = args[i].substring(args[i].indexOf("::")+2);
				}
				if( args[i].indexOf("pubKey::") > -1 ) {
					pubKey = args[i].substring(args[i].indexOf("::")+2);
				}
				if( args[i].indexOf("priKey::") > -1 ) {
					priKey = args[i].substring(args[i].indexOf("::")+2);
				}
				if( args[i].indexOf("value::") > -1 ) {
					value = args[i].substring(args[i].indexOf("::")+2);
				}
			}
			if( operation.equals("GenKey") ) {
				KeyPair kp;
				try {
					kp = KeyUtil.genKeyPair();
					byte[] pubKeyByte = KeyUtil.toByte(kp.getPublic());
					System.out.println("pubKey: "+ECCUtil.byteToStringHex(pubKeyByte));
					byte[] priKeyByte = KeyUtil.toByte(kp.getPrivate());
					System.out.println("priKey: "+ECCUtil.byteToStringHex(priKeyByte));
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}
			}else if( operation.equals("Encrypt") ) {
				byte[] pubKeyByte = ECCUtil.stringHexToByte(pubKey);
				if( priKey == null )
					priKey = "E876D69ACB40D49E28C91758F3F27AA3950AA1DEE098FADC0F5B29DB368F0865";
				byte[] priKeyByte = ECCUtil.stringHexToByte(priKey);
				byte[] messByte = Crypto.aesEncrypt(value.getBytes(), priKeyByte, pubKeyByte);
				System.out.println("value: "+ECCUtil.encodeHexString(messByte).toUpperCase());
			}else if( operation.equals("Decrypt") ) {
				byte[] pubKeyByte = ECCUtil.stringHexToByte(pubKey);
				byte[] priKeyByte = ECCUtil.stringHexToByte(priKey);
				byte[] messByte = Crypto.aesDecrypt(ECCUtil.hexStringToByteArray(value), priKeyByte, pubKeyByte);
				System.out.println("value: "+new String(messByte));
			}
		}else {
			
		}
	}

}
