package com.sensedia.ecc;

import java.security.KeyPair;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sensedia.ecc.util.ECCUtil;
import com.sensedia.ecc.util.KeyUtil;
import com.sensedia.interceptor.externaljar.annotation.ApiSuiteInterceptor;
import com.sensedia.interceptor.externaljar.annotation.InterceptorMethod;
import com.sensedia.interceptor.externaljar.dto.ApiCallData;

@ApiSuiteInterceptor
public class KeyInterceptor {

	@InterceptorMethod
	public void generatePublicKey( ApiCallData call ) {
		String contentType = (String)call.contextVariables.get("contentType");
		call.tracer.trace("contentType: "+contentType);
		KeyPair kp = KeyUtil.genKeyPair();
		byte[] pubKey = KeyUtil.toByte(kp.getPublic());
		call.tracer.trace("pubKey: "+ECCUtil.byteToStringHex(pubKey));
		byte[] priKey = KeyUtil.toByte(kp.getPrivate());
		call.tracer.trace("priKey: "+ECCUtil.byteToStringHex(priKey));
		
		// Retorno
		//call.request.setHeader("pubKey", ECCUtil.byteToStringHex(pubKey));
		//call.response.setHeader("pubKey", ECCUtil.byteToStringHex(pubKey));
		
		JsonObject jsonObject = new JsonParser().parse("{\"publicKey\":\"\",\"privateKey\":\"\"}").getAsJsonObject();
		
		jsonObject.addProperty("publicKey", ECCUtil.byteToStringHex(pubKey));
		jsonObject.addProperty("privateKey", ECCUtil.byteToStringHex(priKey));
		
		if( call.response == null ) {
			call.request.getBody().setBytes(jsonObject.toString().getBytes());
		}else {
			call.response.getBody().setBytes(jsonObject.toString().getBytes());
		}
	}
}