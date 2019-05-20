package com.sensedia.ecc;

import java.security.KeyPair;

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
		call.request.setHeader("pubKey", ECCUtil.byteToStringHex(pubKey));
		call.response.setHeader("pubKey", ECCUtil.byteToStringHex(pubKey));
		// TODO - Verificar se deve armazenar no HEADER ou responder no Body
	}
}