package com.sensedia.ecc;

import com.sensedia.ecc.test.Crypto;
import com.sensedia.ecc.util.ECCUtil;
import com.sensedia.interceptor.externaljar.annotation.ApiSuiteInterceptor;
import com.sensedia.interceptor.externaljar.annotation.InterceptorMethod;
import com.sensedia.interceptor.externaljar.dto.ApiCallData;

@ApiSuiteInterceptor
public class CryptoInterceptor {

	@InterceptorMethod
	public void encrypt( ApiCallData call ) {
		String pubKey = (String)call.contextVariables.get("pubKey");
		call.tracer.trace("pubKey: "+pubKey);
		String priKey = (String)call.contextVariables.get("priKey");
		call.tracer.trace("priKey: "+priKey);
		String value = (String)call.contextVariables.get("value");
		call.tracer.trace("value: "+value);
		
		byte[] pubKeyByte = ECCUtil.hexStringToByteArray(pubKey);
		byte[] priKeyByte = ECCUtil.hexStringToByteArray(priKey);
		
		byte[] messEncrypt = Crypto.aesEncrypt(value.getBytes(), priKeyByte, pubKeyByte);
	}

	@InterceptorMethod
	public void decrypt( ApiCallData call ) {
		String pubKey = (String)call.contextVariables.get("pubKey");
		call.tracer.trace("pubKey: "+pubKey);
		String priKey = (String)call.contextVariables.get("priKey");
		call.tracer.trace("priKey: "+priKey);
		String value = (String)call.contextVariables.get("value");
		call.tracer.trace("value: "+value);
		
		byte[] pubKeyByte = ECCUtil.hexStringToByteArray(pubKey);
		byte[] priKeyByte = ECCUtil.hexStringToByteArray(priKey);
		
		byte[] messDecrypt = Crypto.aesDecrypt(value.getBytes(), priKeyByte, pubKeyByte);
	}

}
