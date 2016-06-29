/*
 * Copyright (c) 2013 SK planet.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of SK planet.
 * You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with SK planet.
 */
package com.libedi.test.common.model;

public class ApiTestRequest {
	private String name;
	private String telNo;
	private String deptNo;
	
	public ApiTestRequest() {
	}
	public ApiTestRequest(String name, String telNo, String deptNo) {
		super();
		this.name = name;
		this.telNo = telNo;
		this.deptNo = deptNo;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	@Override
	public String toString() {
		return new StringBuilder().append("ApiTestModel{name=").append(name).append(", telNo=").append(telNo).append(", deptNo=")
				.append(deptNo).append("}").toString();
	}
	
}
