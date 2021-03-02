package com.rsa.rsasecurid.controller;

import java.sql.Timestamp;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsa.rsasecurid.dto.RSAResponseDto;

@RestController
public class MainController {
	

	@Value("${ami_url1}")
	private String ami_url1;

	@Value("${ami_url2}")
	private String ami_url2;

	@Value("${ami_url3}")
	private String ami_url3;

	@Value("${hdap_url1}")
	private String hdap_url1;

	@Value("${hdap_url2}")
	private String hdap_url2;

	@Value("${hdap_url3}")
	private String hdap_url3;

	@RequestMapping("/")
	@ResponseBody 
	public Response getSastaticData(){
		return Response.status(Status.OK).entity("Application Started").build();
	}
	
	@RequestMapping(value = "/getStatus", method = RequestMethod.GET)
	@ResponseBody 
	public String getStatus(){
		RSAResponseDto rSAResponseDto =new RSAResponseDto();
		rSAResponseDto.setTime(new Timestamp(System.currentTimeMillis()).toString());
		rSAResponseDto.setDataCenter("LTC");

		boolean getAMIStatus =getAmiStatus();
		boolean getHdapStatus = getHdapStatus();
		
		if(getAMIStatus && getHdapStatus) {
			rSAResponseDto.setStatus("UP");
		}
		rSAResponseDto.setStatus("down");
		
		return rSAResponseDto.toJson();

	}
	
	private boolean getAmiStatus(){
		System.out.println("Started getAmiStatus");
		boolean flag =false;
		String urlResponse  =null;
		System.out.println("Started getAmiStatus URL1");

		urlResponse = HttpUtils.executeHttpGetReq(ami_url1);

		if(urlResponse ==null) {
			System.out.println("Started getAmiStatus URL2");
			urlResponse = HttpUtils.executeHttpGetReq(ami_url2);

			if(urlResponse ==null) {
				System.out.println("Started getAmiStatus URL3");
				urlResponse = HttpUtils.executeHttpGetReq(ami_url3);
				if(urlResponse !=null) {
					flag = true;	
				}	
			}else {
				flag = true;	
			}
		}else {
			flag = true;	
		}
		System.out.println("Ended getAmiStatus");

		return flag;
	}

	
	private boolean getHdapStatus(){
		boolean flag =false;
		String urlResponse  =null;
		urlResponse = HttpUtils.executeHttpGetReq(hdap_url1);

		if(urlResponse ==null) {
			urlResponse = HttpUtils.executeHttpGetReq(hdap_url2);

			if(urlResponse ==null) {
				urlResponse = HttpUtils.executeHttpGetReq(hdap_url3);
				if(urlResponse !=null) {
					flag = true;	
				}	
			}else {
				flag = true;	
			}
		}else {
			flag = true;	
		}

		return flag;
	}

	
}
