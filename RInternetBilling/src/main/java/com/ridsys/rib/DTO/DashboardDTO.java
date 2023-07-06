package com.ridsys.rib.DTO;

import java.util.HashMap;
import java.util.Map;

public class DashboardDTO {

	private Map<String, Long> cardcountinfo;

	public Map<String, Long> getCardcountinfo() {
		return cardcountinfo;
	}

	public void setCardcountinfo(long total, long active, long deactive, long online, long offline, long block,long newcus,long delete) {
		Map<String, Long> cardcountinfo = new HashMap<>();
		cardcountinfo.put("total", total);
		cardcountinfo.put("active", active);
		cardcountinfo.put("deactive", deactive);
		cardcountinfo.put("online", online);
		cardcountinfo.put("offline", offline);
		cardcountinfo.put("block", block);
		cardcountinfo.put("newcus", newcus);
		cardcountinfo.put("delete", delete);
		this.cardcountinfo = cardcountinfo;

	}

	public DashboardDTO() {
		super();
	}

	public DashboardDTO(Map<String, Long> cardcountinfo) {
		super();
		this.cardcountinfo = cardcountinfo;
	}

}
