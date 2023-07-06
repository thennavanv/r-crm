package com.ridsys.rib.payload.response;

import java.util.List;
import java.util.Map;

public class MessageResponseForBulk {

	private Map map;
	private List list;
	
	public MessageResponseForBulk(List list) {
		this.list = list;
	}
	
	public MessageResponseForBulk(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
