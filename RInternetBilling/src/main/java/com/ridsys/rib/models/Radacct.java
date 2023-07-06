package com.ridsys.rib.models;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "radacct")

@NamedNativeQuery(name = "Radacct.SessionAcctHistory", query = "SELECT *,if(acctinterval is null or acctinterval='NULL',0,acctinterval) as acctintervalnew,ROUND((acctinputoctets)/1000/1000/1000,2) AS GB_in,ROUND((acctoutputoctets)/1000/1000/1000,2) AS GB_out FROM radacct ORDER BY radacctid DESC", resultSetMapping = "Mapping.SessionAcctHistoryRS")
@NamedNativeQuery(name = "Radacct.SessionAcctHistoryFtdate", query = "SELECT *,if(acctinterval is null or acctinterval='NULL',0,acctinterval) as acctintervalnew,ROUND((acctinputoctets)/1000/1000/1000,2) AS GB_in,ROUND((acctoutputoctets)/1000/1000/1000,2) AS GB_out  FROM radacct WHERE DATE(acctstarttime)>=:fdate AND DATE(acctstarttime)<=:tdate ORDER BY radacctid DESC", resultSetMapping = "Mapping.SessionAcctHistoryRS")
@NamedNativeQuery(name = "Radacct.OperatorSessionAcctHistory", query = "SELECT *,if(acctinterval is null or acctinterval='NULL',0,acctinterval) as acctintervalnew,ROUND((acctinputoctets)/1000/1000/1000,2) AS GB_in,ROUND((acctoutputoctets)/1000/1000/1000,2) AS GB_out FROM radacct where username IN(SELECT DISTINCT ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username)  ORDER BY radacctid DESC", resultSetMapping = "Mapping.SessionAcctHistoryRS")
@NamedNativeQuery(name = "Radacct.OperatorSessionAcctHistoryFtdate", query = "SELECT *,if(acctinterval is null or acctinterval='NULL',0,acctinterval) as acctintervalnew,ROUND((acctinputoctets)/1000/1000/1000,2) AS GB_in,ROUND((acctoutputoctets)/1000/1000/1000,2) AS GB_out FROM radacct where username IN(SELECT DISTINCT ui.username FROM userinfo ui RIGHT JOIN operators op ON op.id=ui.opid AND op.username=:username) AND DATE(acctstarttime)>=:fdate AND DATE(acctstarttime)<=:tdate ORDER BY radacctid DESC", resultSetMapping = "Mapping.SessionAcctHistoryRS")
@NamedNativeQuery(name = "Radacct.findByUsernameOrderByRadacctidDesc", query = "SELECT*,if(acctinterval is null or acctinterval='NULL',0,acctinterval) as acctintervalnew,ROUND((acctinputoctets)/1000/1000/1000,2) AS GB_in,ROUND((acctoutputoctets)/1000/1000/1000,2) AS GB_out FROM radacct WHERE username=:username", resultSetMapping = "Mapping.SessionAcctHistoryRS")
@NamedNativeQuery(name = "Radacct.findByUsernameOrderByRadacctidDescFtdate", query = "SELECT*,if(acctinterval is null or acctinterval='NULL',0,acctinterval) as acctintervalnew,ROUND((acctinputoctets)/1000/1000/1000,2) AS GB_in,ROUND((acctoutputoctets)/1000/1000/1000,2) AS GB_out FROM radacct WHERE username=:username and DATE(acctstarttime)>=:fdate AND DATE(acctstarttime)<=:tdate", resultSetMapping = "Mapping.SessionAcctHistoryRS")
@SqlResultSetMapping(name = "Mapping.SessionAcctHistoryRS", classes = {
		@ConstructorResult(targetClass = Radacct.class, columns = {
				@ColumnResult(name = "radacctid", type = Long.class),
				@ColumnResult(name = "acctsessionid", type = String.class),
				@ColumnResult(name = "acctuniqueid", type = String.class),
				@ColumnResult(name = "username", type = String.class),
				@ColumnResult(name = "realm", type = String.class),
				@ColumnResult(name = "nasipaddress", type = String.class),
				@ColumnResult(name = "nasportid", type = String.class),
				@ColumnResult(name = "nasporttype", type = String.class),
				@ColumnResult(name = "acctstarttime", type = String.class),
				@ColumnResult(name = "acctupdatetime", type = String.class),
				@ColumnResult(name = "acctstoptime", type = String.class),
				@ColumnResult(name = "acctintervalnew", type = int.class),
				@ColumnResult(name = "acctsessiontime", type = Long.class),
				@ColumnResult(name = "acctauthentic", type = String.class),
				@ColumnResult(name = "connectinfo_start", type = String.class),
				@ColumnResult(name = "connectinfo_stop", type = String.class),
				@ColumnResult(name = "GB_in", type = Double.class), @ColumnResult(name = "GB_out", type = Double.class),
				@ColumnResult(name = "calledstationid", type = String.class),
				@ColumnResult(name = "callingstationid", type = String.class),
				@ColumnResult(name = "acctterminatecause", type = String.class),
				@ColumnResult(name = "servicetype", type = String.class),
				@ColumnResult(name = "framedprotocol", type = String.class),
				@ColumnResult(name = "framedipaddress", type = String.class),
				@ColumnResult(name = "framedipv6address", type = String.class),
				@ColumnResult(name = "framedipv6prefix", type = String.class),
				@ColumnResult(name = "framedinterfaceid", type = String.class),
				@ColumnResult(name = "delegatedipv6prefix", type = String.class) }) })
public class Radacct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long radacctid;

	private String acctsessionid;
	private String acctuniqueid;
	private String username;
	private String realm;
	private String nasipaddress;
	private String nasportid;
	private String nasporttype;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String acctstarttime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String acctupdatetime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private String acctstoptime;

	private int acctinterval;
	private long acctsessiontime;
	private String acctauthentic;
	private String connectinfo_start;
	private String connectinfo_stop;
	private double acctinputoctets;
	private double acctoutputoctets;
	private String calledstationid;
	private String callingstationid;
	private String acctterminatecause;
	private String servicetype;
	private String framedprotocol;
	private String framedipaddress;
	private String framedipv6address;
	private String framedipv6prefix;
	private String framedinterfaceid;
	private String delegatedipv6prefix;

	public Radacct(long radacctid, String acctsessionid, String acctuniqueid, String username, String realm,
			String nasipaddress, String nasportid, String nasporttype, String acctstarttime, String acctupdatetime,
			String acctstoptime, int acctinterval, long acctsessiontime, String acctauthentic, String connectinfo_start,
			String connectinfo_stop, double acctinputoctets, double acctoutputoctets, String calledstationid,
			String callingstationid, String acctterminatecause, String servicetype, String framedprotocol,
			String framedipaddress, String framedipv6address, String framedipv6prefix, String framedinterfaceid,
			String delegatedipv6prefix) {
		super();
		
//		System.out.println(radacctid+" "+acctsessionid+" "+acctuniqueid+" "+username+" "+realm+" "+nasipaddress+" "+nasportid+" "+nasporttype+" "+acctstarttime+" "+acctupdatetime+" "+acctstoptime+" "+acctinterval
//				+" "+acctsessiontime+" "+acctauthentic+" "+connectinfo_start+" "+connectinfo_stop+" "+acctinputoctets+" "+acctoutputoctets+" "+calledstationid+" "+callingstationid+" "+acctterminatecause
//				+" "+servicetype+" "+framedprotocol+" "+framedipaddress+" "+framedipv6address+" "+framedipv6prefix+" "+framedinterfaceid+" "+delegatedipv6prefix);
		this.radacctid = radacctid;
		this.acctsessionid = acctsessionid;
		this.acctuniqueid = acctuniqueid;
		this.username = username;
		this.realm = realm;
		this.nasipaddress = nasipaddress;
		this.nasportid = nasportid;
		this.nasporttype = nasporttype;
		this.acctstarttime = acctstarttime;
		this.acctupdatetime = acctupdatetime;
		this.acctstoptime = acctstoptime;
		this.acctinterval = acctinterval;
		this.acctsessiontime = acctsessiontime;
		this.acctauthentic = acctauthentic;
		this.connectinfo_start = connectinfo_start;
		this.connectinfo_stop = connectinfo_stop;
		this.acctinputoctets = acctinputoctets;
		this.acctoutputoctets = acctoutputoctets;
		this.calledstationid = calledstationid;
		this.callingstationid = callingstationid;
		this.acctterminatecause = acctterminatecause;
		this.servicetype = servicetype;
		this.framedprotocol = framedprotocol;
		this.framedipaddress = framedipaddress;
		this.framedipv6address = framedipv6address;
		this.framedipv6prefix = framedipv6prefix;
		this.framedinterfaceid = framedinterfaceid;
		this.delegatedipv6prefix = delegatedipv6prefix;
	}

	public long getRadacctid() {
		return radacctid;
	}

	public void setRadacctid(long radacctid) {
		this.radacctid = radacctid;
	}

	public String getAcctsessionid() {
		return acctsessionid;
	}

	public void setAcctsessionid(String acctsessionid) {
		this.acctsessionid = acctsessionid;
	}

	public String getAcctuniqueid() {
		return acctuniqueid;
	}

	public void setAcctuniqueid(String acctuniqueid) {
		this.acctuniqueid = acctuniqueid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getNasipaddress() {
		return nasipaddress;
	}

	public void setNasipaddress(String nasipaddress) {
		this.nasipaddress = nasipaddress;
	}

	public String getNasportid() {
		return nasportid;
	}

	public void setNasportid(String nasportid) {
		this.nasportid = nasportid;
	}

	public String getNasporttype() {
		return nasporttype;
	}

	public void setNasporttype(String nasporttype) {
		this.nasporttype = nasporttype;
	}

	public String getAcctstarttime() {
		return acctstarttime;
	}

	public void setAcctstarttime(String acctstarttime) {
		this.acctstarttime = acctstarttime;
	}

	public String getAcctupdatetime() {
		return acctupdatetime;
	}

	public void setAcctupdatetime(String acctupdatetime) {
		this.acctupdatetime = acctupdatetime;
	}

	public String getAcctstoptime() {
		return acctstoptime;
	}

	public void setAcctstoptime(String acctstoptime) {
		this.acctstoptime = acctstoptime;
	}

	public int getAcctinterval() {
		return acctinterval;
	}

	public void setAcctinterval(int acctinterval) {
		this.acctinterval = acctinterval;
	}

	public long getAcctsessiontime() {
		return acctsessiontime;
	}

	public void setAcctsessiontime(int acctsessiontime) {
		this.acctsessiontime = acctsessiontime;
	}

	public String getAcctauthentic() {
		return acctauthentic;
	}

	public void setAcctauthentic(String acctauthentic) {
		this.acctauthentic = acctauthentic;
	}

	public String getConnectinfo_start() {
		return connectinfo_start;
	}

	public void setConnectinfo_start(String connectinfo_start) {
		this.connectinfo_start = connectinfo_start;
	}

	public String getConnectinfo_stop() {
		return connectinfo_stop;
	}

	public void setConnectinfo_stop(String connectinfo_stop) {
		this.connectinfo_stop = connectinfo_stop;
	}

	public double getAcctinputoctets() {
		return acctinputoctets;
	}

	public void setAcctinputoctets(double acctinputoctets) {
		this.acctinputoctets = acctinputoctets;
	}

	public double getAcctoutputoctets() {
		return acctoutputoctets;
	}

	public void setAcctoutputoctets(double acctoutputoctets) {
		this.acctoutputoctets = acctoutputoctets;
	}

	public void setAcctsessiontime(long acctsessiontime) {
		this.acctsessiontime = acctsessiontime;
	}

	public String getCalledstationid() {
		return calledstationid;
	}

	public void setCalledstationid(String calledstationid) {
		this.calledstationid = calledstationid;
	}

	public String getCallingstationid() {
		return callingstationid;
	}

	public void setCallingstationid(String callingstationid) {
		this.callingstationid = callingstationid;
	}

	public String getAcctterminatecause() {
		return acctterminatecause;
	}

	public void setAcctterminatecause(String acctterminatecause) {
		this.acctterminatecause = acctterminatecause;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getFramedprotocol() {
		return framedprotocol;
	}

	public void setFramedprotocol(String framedprotocol) {
		this.framedprotocol = framedprotocol;
	}

	public String getFramedipaddress() {
		return framedipaddress;
	}

	public void setFramedipaddress(String framedipaddress) {
		this.framedipaddress = framedipaddress;
	}

	public String getFramedipv6address() {
		return framedipv6address;
	}

	public void setFramedipv6address(String framedipv6address) {
		this.framedipv6address = framedipv6address;
	}

	public String getFramedipv6prefix() {
		return framedipv6prefix;
	}

	public void setFramedipv6prefix(String framedipv6prefix) {
		this.framedipv6prefix = framedipv6prefix;
	}

	public String getFramedinterfaceid() {
		return framedinterfaceid;
	}

	public void setFramedinterfaceid(String framedinterfaceid) {
		this.framedinterfaceid = framedinterfaceid;
	}

	public String getDelegatedipv6prefix() {
		return delegatedipv6prefix;
	}

	public void setDelegatedipv6prefix(String delegatedipv6prefix) {
		this.delegatedipv6prefix = delegatedipv6prefix;
	}

}
