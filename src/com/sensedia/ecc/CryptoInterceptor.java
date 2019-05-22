package com.sensedia.ecc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
		//String value = (String)call.contextVariables.get("value");
		//call.tracer.trace("value: "+value);
		
		byte[] pubKeyByte = ECCUtil.hexStringToByteArray(pubKey);
		byte[] priKeyByte = ECCUtil.hexStringToByteArray(priKey);
		
		String body = null;
		if ( call.response == null ) {
			body = call.request.getBody().getString("UTF-8");
			
		}else {
			body = call.response.getBody().getString("UTF-8");
		}
		JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
		JsonElement fullName = jsonObject.get("fullName");
		JsonElement birthDate = jsonObject.get("birthDate");
		JsonObject phone = jsonObject.getAsJsonObject("phone");
		JsonElement number = phone.get("number");
		JsonElement personalID = jsonObject.get("personalID");
		
		
		byte[] fullNameByte = Crypto.aesDecrypt(fullName.getAsString().getBytes(), priKeyByte, pubKeyByte);
		byte[] birthDateByte = Crypto.aesDecrypt(birthDate.getAsString().getBytes(), priKeyByte, pubKeyByte);
		byte[] phoneByte = Crypto.aesDecrypt(phone.getAsString().getBytes(), priKeyByte, pubKeyByte);
		byte[] personalIDByte = Crypto.aesDecrypt(personalID.getAsString().getBytes(), priKeyByte, pubKeyByte);
		
		String fullNameStr = new String(fullNameByte);
		String birthDateStr = new String(birthDateByte);
		String phoneStr = new String(phoneByte);
		String personalIDStr = new String(personalIDByte);
		
		jsonObject.addProperty("fullName", fullNameStr);
		jsonObject.addProperty("birthDate", birthDateStr);
		phone.addProperty("phone", phoneStr);
		jsonObject.addProperty("personalID", personalIDStr);
		
		if( call.response == null ) {
			call.request.getBody().setBytes(jsonObject.toString().getBytes());
		}else {
			call.response.getBody().setBytes(jsonObject.toString().getBytes());
		}
	}

}
