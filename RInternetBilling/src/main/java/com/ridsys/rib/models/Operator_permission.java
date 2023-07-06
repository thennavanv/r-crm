package com.ridsys.rib.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operator_permission")
public class Operator_permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int opid;

	private boolean walletrecharge;

	private boolean ott;
	private boolean iptv;
	private boolean sms;
	private boolean email;
	private boolean subwallet;
	private boolean planchange;
	private boolean passwordchange;
	private boolean billgenerate;
	private boolean oponlinerecharge;
	private boolean subonlinerecharge;

	public Operator_permission() {
		super();
	}

	public Operator_permission(int id, int opid, boolean walletrecharge, boolean ott, boolean iptv, boolean sms,
			boolean email, boolean subwallet, boolean planchange, boolean passwordchange, boolean billgenerate,
			boolean oponlinerecharge, boolean subonlinerecharge) {
		super();
		this.id = id;
		this.opid = opid;
		this.walletrecharge = walletrecharge;
		this.ott = ott;
		this.iptv = iptv;
		this.sms = sms;
		this.email = email;
		this.subwallet = subwallet;
		this.planchange = planchange;
		this.passwordchange = passwordchange;
		this.billgenerate = billgenerate;
		this.oponlinerecharge = oponlinerecharge;
		this.subonlinerecharge = subonlinerecharge;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOpid() {
		return opid;
	}

	public void setOpid(int opid) {
		this.opid = opid;
	}

	public boolean isWalletrecharge() {
		return walletrecharge;
	}

	public void setWalletrecharge(boolean walletrecharge) {
		this.walletrecharge = walletrecharge;
	}

	public boolean isOtt() {
		return ott;
	}

	public void setOtt(boolean ott) {
		this.ott = ott;
	}

	public boolean isIptv() {
		return iptv;
	}

	public void setIptv(boolean iptv) {
		this.iptv = iptv;
	}

	public boolean isSms() {
		return sms;
	}

	public void setSms(boolean sms) {
		this.sms = sms;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public boolean isSubwallet() {
		return subwallet;
	}

	public void setSubwallet(boolean subwallet) {
		this.subwallet = subwallet;
	}

	public boolean isPlanchange() {
		return planchange;
	}

	public void setPlanchange(boolean planchange) {
		this.planchange = planchange;
	}

	public boolean isPasswordchange() {
		return passwordchange;
	}

	public void setPasswordchange(boolean passwordchange) {
		this.passwordchange = passwordchange;
	}

	public boolean isBillgenerate() {
		return billgenerate;
	}

	public void setBillgenerate(boolean billgenerate) {
		this.billgenerate = billgenerate;
	}

	public boolean isOponlinerecharge() {
		return oponlinerecharge;
	}

	public void setOponlinerecharge(boolean oponlinerecharge) {
		this.oponlinerecharge = oponlinerecharge;
	}

	public boolean isSubonlinerecharge() {
		return subonlinerecharge;
	}

	public void setSubonlinerecharge(boolean subonlinerecharge) {
		this.subonlinerecharge = subonlinerecharge;
	}

}
