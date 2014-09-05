package com.whoshere.consul.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Check {
	String node;
	String checkId;
	String name;
	String status;
	String notes;
	String output;
	String serviceId;
	String serviceName;
	
	@JsonProperty("Node")
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	
	@JsonProperty("CheckID")
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	
	@JsonProperty("Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("Status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonProperty("Notes")
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@JsonProperty("Output")
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	
	@JsonProperty("ServiceID")
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	@JsonProperty("ServiceName")
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String toString() {
		return String.format("Node: %s, CheckID: %s, Name %s, Status %s, Notes: %s, Output: %s, ServiceID: %s, ServiceName: %s", 
				node, checkId, name, status, notes, output, serviceId, serviceName);
	}
}
