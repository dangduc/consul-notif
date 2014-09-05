package com.whoshere.consul.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

public class HttpService {
	String encodedAuth = null;
	
	/**
	 * 
	 * @param user		basic auth username
	 * @param pass		basic auth pass
	 */
	public HttpService(String user, String pass) {
		if (user != null && pass != null) {
			encodedAuth = new String(Base64.encodeBase64((user + ":" + pass).getBytes()));
		}
	}
	
	public String makeRestCall(String resourceUrl) throws Exception {
		HttpGet getMethod = new HttpGet(resourceUrl);
		
		if (encodedAuth != null) {
			getMethod.setHeader("Authorization", "Basic " + encodedAuth);
		}
		
		getMethod.setHeader("Content-Type", "text/xml; charset=utf-8");
		
		try {
			HttpClient client = getHttpClient();
			
			HttpResponse response = client.execute(getMethod);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200 && statusCode != 201) {
				throw new Exception("server error " + statusCode);
			}
			
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				throw new Exception("cannot read from request body");
			}
			
			InputStream contentStream = entity.getContent();
			if (contentStream == null) {
				throw new Exception("cannot read from request body");
			}

			StringWriter stringWriter = new  StringWriter();

			IOUtils.copy(contentStream, stringWriter);
			
			return stringWriter.toString();
			
		} catch (ClientProtocolException e) {
			throw new Exception("misconfigured http client", e);
		} catch (IOException e) {
			throw new Exception("cannot complete http request", e);
		}
	}
	
	private synchronized HttpClient getHttpClient() {	
		HttpClient client = new DefaultHttpClient();	
		client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000);
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 50000);
		
		return client;
	}
}
