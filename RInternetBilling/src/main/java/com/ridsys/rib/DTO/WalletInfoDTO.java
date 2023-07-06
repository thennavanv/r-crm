package com.ridsys.rib.DTO;

import java.util.List;

public class WalletInfoDTO {

	private double walletbalance;
	private double lastRecharge;
	private List<WalletupdatelogDTO> recenttransaction;

	public WalletInfoDTO(double walletbalance,double lastRecharge, List<WalletupdatelogDTO> recenttransaction) {
		super();
		this.walletbalance = walletbalance;
		this.lastRecharge=lastRecharge;
		this.recenttransaction = recenttransaction;
	}

	public double getWalletbalance() {
		return walletbalance;
	}

	public void setWalletbalance(double walletbalance) {
		this.walletbalance = walletbalance;
	}

	public List<WalletupdatelogDTO> getRecenttransaction() {
		return recenttransaction;
	}

	public void setRecenttransaction(List<WalletupdatelogDTO> recenttransaction) {
		this.recenttransaction = recenttransaction;
	}

	public double getLastRecharge() {
		return lastRecharge;
	}

	public void setLastRecharge(double lastRecharge) {
		this.lastRecharge = lastRecharge;
	}

	
	
}
