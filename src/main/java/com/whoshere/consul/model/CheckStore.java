package com.whoshere.consul.model;

import java.util.HashMap;
import java.util.Map;

import com.whoshere.consul.input.Check;

public class CheckStore {
	Map<String, Check> checkMap;
	
	public CheckStore() {
		checkMap = new HashMap<String, Check>();
	}
	
	public String key(Check c) {
		return String.format("%s.%s", c.getNode(), c.getCheckId());
	}

	public boolean checkExists(Check c) {
		return checkMap.containsKey(key(c));
	}

	public Check getCheck(Check c) {
		return checkMap.get(key(c));
	}

	public void putCheck(Check c) {
		checkMap.put(key(c), c);
	}
	
}
