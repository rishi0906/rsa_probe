package com.rsa.rsasecurid.controller;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;


public class HttpUtils {
	
	@SuppressWarnings("deprecation")
	
	public static String executeHttpGetReq(String url){
		Integer timeout =5;
		HttpGet request = new HttpGet(url);
		ResponseHandler<String> handler = new BasicResponseHandler();
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams httpParams = httpClient.getParams();
		httpParams.setParameter(
		  CoreConnectionPNames.CONNECTION_TIMEOUT, timeout * 1000);
		httpParams.setParameter(
		  CoreConnectionPNames.SO_TIMEOUT, timeout * 1000);
		httpParams.setParameter(
		  ClientPNames.CONN_MANAGER_TIMEOUT, new Long(timeout * 1000));
		HttpResponse response;
		try {
			response = httpClient.execute(request);
			if(response.getStatusLine().getStatusCode()==200){
				return handler.handleResponse(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}