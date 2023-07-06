package com.ridsys.rib.service;

import java.util.List;

import com.ridsys.rib.models.Radacct;

public interface RadacctService {

	List<Radacct> getSubscriberSessionHistoryByusername(String username,String role,String fdate,String tdate);

}
