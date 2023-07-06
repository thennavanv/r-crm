package com.ridsys.rib.DTO;

import java.math.BigInteger;

import com.ridsys.rib.comm.Bandwidth;

import lombok.Data;

@Data
public class UserListDTO {

	private int id;
	private int planid;
	private String quotaexpirydate;
	private String fullname;
	private String username;
	private String password;
	private String email;
	private String mobile;
	private String mac;
	private String ip;
	private String nasip;
	private String ustatus;
	private String pstatus;
	private double down;
	private double up;
	private String download = "0";
	private String upload = "0";
	private String plan;
	private String group;
	private String expiry;
	private String operator;
	private int verificationstatus;
	private boolean expstatus;
	private String quotastartdate;
	private String lconame;
	private int lcoid;
	private int vlanid;
	private String planDays;
	private String selectIsOtt;
	private String totaldata = "0";
	private boolean ottplanActive;
	private String ottplanexpirydate;
	private String ottplanactivedate;
	private String poolType;
	private String lastseen;
	private String discountDays;
	private String selectIsIptv;
	private String gb_in;
	private String gb_out;
	private String gb_total;
	private String userdevice;
	private String deviceno;
	private String fromip;
	private String framedip;

	public UserListDTO() {
		super();
	}

	public UserListDTO(int id, String quotaexpirydate, String fullname, String username, String password, String email,
			String mobile, String plan, String lconame, int lcoid, String mac) {
		super();
		this.id = id;
		this.quotaexpirydate = quotaexpirydate;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.plan = plan;
		this.lconame = lconame;
		this.lcoid = lcoid;
		this.mac = mac;
	}

	public UserListDTO(int id, String quotaexpirydate, String fullname, String username, String password, String email,
			String mobile, String plan, String lconame, int lcoid, String mac, int planid) {
		super();
		this.id = id;
		this.quotaexpirydate = quotaexpirydate;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.plan = plan;
		this.lconame = lconame;
		this.lcoid = lcoid;
		this.mac = mac;
		this.planid = planid;
	}

	public UserListDTO(int id, int planid, String quotaexpirydate, String fullname, String username, String password,
			String email, String mobile, String mac, String ip, String nasip, BigInteger upload, BigInteger download,
			String plan, String ustatus, int verificationstatus, boolean expstatus, String quotastartdate,
			String lconame, int lcoid, int vlanid, String planDays, String selectIsOtt, boolean ottplanActive,
			String ottplanexpirydate, String ottplanactivedate, String poolType) {
		super();
		this.id = id;
		this.planid = planid;
		this.quotaexpirydate = quotaexpirydate;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.mac = mac;
		this.ip = ip;
		this.nasip = nasip;
		this.plan = plan;
		this.ustatus = ustatus;
		if (upload != null) {
			this.upload = Bandwidth.calBWDecimal(upload.doubleValue());
		}
		if (download != null) {
			this.download = Bandwidth.calBWDecimal(download.doubleValue());
		}
		this.verificationstatus = verificationstatus;
		this.expstatus = expstatus;
		this.quotastartdate = quotastartdate;
		this.lconame = lconame;
		this.lcoid = lcoid;
		this.vlanid = vlanid;
		this.planDays = planDays;
		this.selectIsOtt = selectIsOtt;
		this.ottplanActive = ottplanActive;
		if (totaldata != null) {
			BigInteger totaldata = download.add(upload);

			this.totaldata = Bandwidth.calBWDecimal(totaldata.doubleValue());
		}
		this.ottplanexpirydate = ottplanexpirydate;
		this.ottplanactivedate = ottplanactivedate;
		this.poolType = poolType;
	}

	public UserListDTO(int id, int planid, String quotaexpirydate, String fullname, String username, String password,
			String email, String mobile, String mac, String ip, String nasip, String plan, String ustatus,
			int verificationstatus, boolean expstatus, String quotastartdate, String lconame, int lcoid, int vlanid,
			String planDays, String selectIsOtt, boolean ottplanActive, String ottplanexpirydate,
			String ottplanactivedate, String poolType, String lastseen, String discountDays, String selectIsIptv,
			String gb_in, String gb_out, String gb_total, String userdevice, String deviceno, String fromip,
			String framedip) {
		super();
		this.id = id;
		this.planid = planid;
		this.quotaexpirydate = quotaexpirydate;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.mac = mac;
		this.ip = ip;
		this.nasip = nasip;
		this.plan = plan;
		this.ustatus = ustatus;
//		if (upload != null) {
//			this.upload = Bandwidth.calBWDecimal(upload.doubleValue());
//		}
//		if (download != null) {
//			this.download = Bandwidth.calBWDecimal(download.doubleValue());
//		}
		this.verificationstatus = verificationstatus;
		this.expstatus = expstatus;
		this.quotastartdate = quotastartdate;
		this.lconame = lconame;
		this.lcoid = lcoid;
		this.vlanid = vlanid;
		this.planDays = planDays;
		this.selectIsOtt = selectIsOtt;
		this.ottplanActive = ottplanActive;
//		if (totaldata != null) {
//			BigInteger totaldata = download.add(upload);
//
//			this.totaldata = Bandwidth.calBWDecimal(totaldata.doubleValue());
//		}
		this.ottplanexpirydate = ottplanexpirydate;
		this.ottplanactivedate = ottplanactivedate;
		this.poolType = poolType;
		this.lastseen = lastseen;
		this.discountDays = discountDays;
		this.selectIsIptv = selectIsIptv;
		this.gb_in = gb_in + " GB";
		this.gb_out = gb_out + " GB";
		this.gb_total = gb_total + " GB";
		this.userdevice = userdevice;
		this.deviceno = deviceno;
		this.fromip = fromip;
		this.framedip = framedip;
	}

	public UserListDTO(int id, int planid, String quotaexpirydate, String fullname, String username, String password,
			String email, String mobile, String mac, String ip, String nasip, BigInteger upload, BigInteger download,
			String plan, String ustatus, int verificationstatus, boolean expstatus, String quotastartdate,
			String lconame, int lcoid, int vlanid, String planDays, String selectIsOtt, boolean ottplanActive,
			String ottplanexpirydate, String ottplanactivedate, String poolType, String lastseen, String discountDays,
			String selectIsIptv) {
		super();
		this.id = id;
		this.planid = planid;
		this.quotaexpirydate = quotaexpirydate;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.mac = mac;
		this.ip = ip;
		this.nasip = nasip;
		this.plan = plan;
		this.ustatus = ustatus;
		if (upload != null) {
			this.upload = Bandwidth.calBWDecimal(upload.doubleValue());
		}
		if (download != null) {
			this.download = Bandwidth.calBWDecimal(download.doubleValue());
		}
		this.verificationstatus = verificationstatus;
		this.expstatus = expstatus;
		this.quotastartdate = quotastartdate;
		this.lconame = lconame;
		this.lcoid = lcoid;
		this.vlanid = vlanid;
		this.planDays = planDays;
		this.selectIsOtt = selectIsOtt;
		this.ottplanActive = ottplanActive;
		if (totaldata != null) {
			BigInteger totaldata = download.add(upload);

			this.totaldata = Bandwidth.calBWDecimal(totaldata.doubleValue());
		}
		this.ottplanexpirydate = ottplanexpirydate;
		this.ottplanactivedate = ottplanactivedate;
		this.poolType = poolType;
		this.lastseen = lastseen;
		this.discountDays = discountDays;
		this.selectIsIptv = selectIsIptv;
	}

}
