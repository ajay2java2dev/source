package com.vzwcorp.pricinglab.rest;

import java.util.Map;

public class WorkerThread extends Thread{
	String url;
	Map<String,String> param;
	public boolean done= false;
	public int numReq=0;
	public WorkerThread(String url, Map<String, String> sParam) {
		this.url=url;
		this.param= sParam;
}

	@Override
	public void run() {
		long t0= System.currentTimeMillis();
		while ( !done){
			RestClient.postRequest(url, param);
//			RestClient.numRequests++;
			numReq++;
			
		}
	}

}
