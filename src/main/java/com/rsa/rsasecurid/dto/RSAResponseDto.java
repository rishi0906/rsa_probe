package com.rsa.rsasecurid.dto;

import java.sql.Timestamp;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

public class RSAResponseDto {
	
	private String status;
	
	private String time;
	
	private String dataCenter;
	
	public String getStatus() {
		return status;
	}
	
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDataCenter() {
		return dataCenter;
	}
	public void setDataCenter(String dataCenter) {
		this.dataCenter = dataCenter;
	}

	
	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String toJson(){
    	String jsonObj = null;
    	try {
			ObjectMapper om = new ObjectMapper();
			om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			jsonObj = om.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return jsonObj;
    }

}
