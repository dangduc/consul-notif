package com.whoshere.consul.input;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties("Services")
public class Node {
	String node;
	String address;
	List<Check> checks;
	
	@JsonProperty("Node")
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	
	@JsonProperty("Address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonProperty("Checks")
	public List<Check> getChecks() {
		return checks;
	}
	public void setChecks(List<Check> checks) {
		this.checks = checks;
	}
	
	public String toString() {
		String checksStr = "[";
		for (Check c : checks) {
			checksStr += c + "; ";
		}
		checksStr += "]";
		
		return String.format("Node: %s, Address: %s, Checks: %s", node, address, checksStr);
	}
}
