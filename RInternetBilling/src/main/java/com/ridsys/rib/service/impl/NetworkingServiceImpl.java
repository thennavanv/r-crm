package com.ridsys.rib.service.impl;

import org.apache.commons.net.util.SubnetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ridsys.rib.comm.Dateformat;
import com.ridsys.rib.models.Branchinfo;
import com.ridsys.rib.models.Nas;
import com.ridsys.rib.models.Providerinfo;
import com.ridsys.rib.models.Radippool;
import com.ridsys.rib.models.Radreply;
import com.ridsys.rib.models.Switch;
import com.ridsys.rib.models.Userinfo;
import com.ridsys.rib.payload.request.CreateSwitchRequest;
import com.ridsys.rib.payload.request.IppoolPayload;
import com.ridsys.rib.payload.response.MessageResponse;
import com.ridsys.rib.repository.BranchinfoRepository;
import com.ridsys.rib.repository.NasRepository;
import com.ridsys.rib.repository.Operator_switchportRepository;
import com.ridsys.rib.repository.ProviderinfoRepository;
import com.ridsys.rib.repository.RadippoolRepository;
import com.ridsys.rib.repository.RadreplyRepository;
import com.ridsys.rib.repository.SwitchRepository;
import com.ridsys.rib.repository.UserinfoRepository;
import com.ridsys.rib.service.NetworkingService;

import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressSection;
import inet.ipaddr.IPAddressString;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NetworkingServiceImpl implements NetworkingService {

	@Autowired
	NasRepository nasRepository;

	@Autowired
	RadippoolRepository radippoolRepository;

	@Autowired
	ProviderinfoRepository providerinfoRepository;

	@Autowired
	BranchinfoRepository branchinfoRepository;

	@Autowired
	SwitchRepository switchRepository;

	@Autowired
	Operator_switchportRepository operator_switchportRepository;

	@Autowired
	UserinfoRepository userinfoRepository;

	@Autowired
	RadreplyRepository radreplyRepository;

	@Override
	public ResponseEntity<?> nasModification(Nas nasobj, String action) {

//		Nas nasObj2 = new Nas();
//
//		nasObj2.setShortname("Micro");
//		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("shortname", startsWith());
//
//		Example<Nas> example = Example.of(nasObj2, matcher);
//
//		List<Nas> n = nasRepository.findAll(example);
//
//		System.out.println("ddd"+n.size());
//		for (Nas nn : n) {
//			System.out.println(nn.getNasname() + " " + nn.getPorts());
//		}

		if (action.equalsIgnoreCase("create")) {

			if (nasRepository.existsByNasname(nasobj.getNasname())) {
				return ResponseEntity.badRequest().body(new MessageResponse("The Nas name is already in use!"));

			} else if (nasRepository.existsByPorts(nasobj.getPorts())) {
				return ResponseEntity.badRequest().body(new MessageResponse("The Port is already in use!"));

			} else if (nasRepository.existsBySecret(nasobj.getSecret())) {
				return ResponseEntity.badRequest().body(new MessageResponse("The Secret Key is already in use!"));

			}

			nasRepository.save(nasobj);

			return ResponseEntity.ok(new MessageResponse("Successfully Created!"));
		} else if (action.equalsIgnoreCase("update")) {

			Nas obj = nasRepository.getById(nasobj.getId());

			if (nasRepository.existsByNotInNasName(obj.getNasname(), nasobj.getNasname()) > 0) {
				return ResponseEntity.badRequest().body(new MessageResponse("The Nas name is already in use!"));

			} else if (nasRepository.existsByNotInPorts(obj.getPorts(), nasobj.getPorts()) > 0) {
				return ResponseEntity.badRequest().body(new MessageResponse("The Port is already in use!"));

			} else if (nasRepository.existsByNotInSecret(obj.getSecret(), nasobj.getSecret()) > 0) {
				return ResponseEntity.badRequest().body(new MessageResponse("The Secret Key is already in use!"));

			}

			obj.setNasname(nasobj.getNasname());
			obj.setPorts(nasobj.getPorts());
			obj.setSecret(nasobj.getSecret());
			obj.setShortname(nasobj.getShortname());
			obj.setDescription(nasobj.getDescription());
			obj.setType(nasobj.getType());

			nasRepository.save(obj);

			return ResponseEntity.ok(new MessageResponse("Nas Details Successfully updated!"));
		}

		return null;
	}

	@Override
	public List<Nas> getNasList() {
		return nasRepository.getAllNaslist();
	}

	@Override
	public ResponseEntity<?> nasDelete(long nasid) {

		if (nasRepository.activeUserCountByNas(nasid) == 0) {
			nasRepository.deleteById(nasid);

			return ResponseEntity.ok(new MessageResponse("Success!"));

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Nas in use!"));
		}

	}

	@Override
	public Map<String, String> ipCalculation(String address, String netmask) {
		Map<String, String> map = new HashMap<>();
		try {
			SubnetUtils utils = new SubnetUtils(address + "/" + netmask);
			String[] allIps = utils.getInfo().getAllAddresses();
			int i = 0;
			String hostmin = "";
			String hostmax = "";

			for (String s : allIps) {
				if (i == 0) {
					hostmin = s;
				}
				if (i == allIps.length - 1) {
					hostmax = s;
				}

				i++;
			}
			IPAddressString addrString = new IPAddressString(address);
			IPAddress subnet = addrString.toAddress();

			Integer prefixLength = subnet.getNetworkPrefixLength();
			if (prefixLength == null) {
				prefixLength = subnet.getBitCount();
			}
			// net mask
			IPAddress mask = subnet.getNetwork().getNetworkMask(prefixLength, false);

			// wildecard
			IPAddressSection wildcard = subnet.getHostSection();

			getIp(hostmin, "Network");

			Nas nasobj = nasRepository.findAllByIsactivelimit();

			map.put("Address", address);
			map.put("Network", getIp(hostmin, "Network") + "/" + netmask);
			map.put("HostMin", hostmin);
			map.put("HostMax", hostmax);
			map.put("Broadcast", getIp(hostmax, "Broadcast"));
			map.put("Hosts", String.valueOf(i));
			map.put("Nasipaddress", nasobj.getNasname());

		} catch (Exception e) {
			System.out.println("Exception at ipcalculation: " + e);
		}
		return map;
	}

	public String getIp(String ip, String action) {
		ip = ip.replace(".", "/");

		String s[] = ip.split("/");

		int d = 0;
		String adrs = "";

		for (String ss : s) {

			if (d == s.length - 1) {
				if (action.equalsIgnoreCase("network")) {
					ss = String.valueOf(Integer.parseInt(ss) - 1);
				} else if (action.equalsIgnoreCase("broadcast")) {
					ss = String.valueOf(Integer.parseInt(ss) + 1);
				}

			} else {

			}
			if (d == 0) {
				adrs += ss;
			} else {
				adrs += "." + ss;
			}

			d++;
		}

		return adrs;
	}

	@Override
	public ResponseEntity<?> ippoolModification(IppoolPayload reqobj, String action) {

		if (action.equalsIgnoreCase("CREATE")) {
			if (reqobj != null) {

				if (radippoolRepository.existsByFromip(reqobj.getFromip())) {
					return ResponseEntity.badRequest()
							.body(new MessageResponse(reqobj.getFromip() + " already in use!"));

				} else if (radippoolRepository.existsByToip(reqobj.getToip())) {
					return ResponseEntity.badRequest().body(new MessageResponse(reqobj.getToip() + " already in use!"));

				} else if (radippoolRepository.existsByPoolname(reqobj.getPoolname())) {
					return ResponseEntity.badRequest()
							.body(new MessageResponse(reqobj.getPoolname() + " already in use!"));
				}
				SubnetUtils utils = new SubnetUtils(reqobj.getAddress() + "/" + reqobj.getNetmask());
				String[] allIps = utils.getInfo().getAllAddresses();

				for (String s : allIps) {
					Radippool obj = new Radippool();
					obj.setNasipaddress(reqobj.getNasipaddress());
					obj.setFromip(reqobj.getFromip());
					obj.setToip(reqobj.getToip());
					obj.setIdentity(reqobj.getFranchies());
					obj.setPoolname(reqobj.getPoolname());
					obj.setPooltype(reqobj.getPooltype());
					obj.setCreationdate(Dateformat.getCurrentDatetime());
					obj.setFramedipaddress(s);
					obj.setCalledstationid("");
					obj.setCallingstationid("");
					obj.setPoolkey("");
					obj.setIsactive(true);
					radippoolRepository.save(obj);

				}
			}

			return ResponseEntity.ok(new MessageResponse("Successfully created!"));
		} else if (action.equalsIgnoreCase("UPDATE")) {

			Radippool obj = radippoolRepository.findByFromToIp(reqobj.getFromip(), reqobj.getToip());

			if (radippoolRepository.existsByNotInPoolName(obj.getPoolname(), reqobj.getPoolname()) > 0) {

				return ResponseEntity.badRequest().body(new MessageResponse("Pool Name in use!"));
			}

			if (!reqobj.isStatus()) {
				if (radippoolRepository.getFramedipStatusByfromtoIp(reqobj.getFromip(), reqobj.getToip()) > 0) {

					return ResponseEntity.badRequest().body(new MessageResponse("IP allocated for user!"));
				}
			}
			List<Radippool> radlist = radippoolRepository.findByFromipAndToip(reqobj.getFromip(), reqobj.getToip());

			System.out.println(radlist.size());

			for (Radippool r : radlist) {

				r.setPoolname(reqobj.getPoolname());
				r.setPooltype(reqobj.getPooltype());
				r.setIdentity(reqobj.getFranchies());
				r.setIsactive(reqobj.isStatus());

				radippoolRepository.save(r);

			}

			return ResponseEntity.ok(new MessageResponse("Successfully Updated!"));
		}
		return null;
	}

	@Override
	public ResponseEntity<?> ippoolDelete(String fromip, String toip) {

		if (radippoolRepository.getFramedipStatusByfromtoIp(fromip, toip) == 0) {

			radippoolRepository.deleteByFromipAndToip(fromip, toip);

			return ResponseEntity.ok(new MessageResponse("Success!"));

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse(" IP allocated for user!"));
		}

	}

	@Override
	public List getNetworkDetails(String about) {

		List lst = new ArrayList();

		if (about.equalsIgnoreCase("nas")) {
			List<Nas> naslist = nasRepository.findByIsactive(true);

			for (Nas n : naslist) {
				Map map = new HashMap<>();

				map.put("nasname", n.getNasname());
				map.put("id", n.getId());
				lst.add(map);

			}

			return lst;

		} else if (about.equalsIgnoreCase("provider")) {

			List<Providerinfo> providerlist = providerinfoRepository.findByIsactive(true);

			for (Providerinfo p : providerlist) {
				Map map = new HashMap<>();

				map.put("providername", p.getProvidername());
				map.put("id", p.getId());
				lst.add(map);

			}
			return lst;
		} else if (about.equalsIgnoreCase("branch")) {
			List<Branchinfo> branchlist = branchinfoRepository.findByIsactive(true);
			for (Branchinfo b : branchlist) {
				Map map = new HashMap<>();

				map.put("branchname", b.getBranchname());
				map.put("id", b.getId());
				lst.add(map);
			}
			return lst;

		} else if (about.equalsIgnoreCase("switch")) {
			List<Switch> switchlist = switchRepository.findByIsactive(true);

			for (Switch s : switchlist) {
				Map map = new HashMap<>();

				map.put("switch", s.getSwitchname() + " (" + s.getSwitchip() + ")");
				map.put("id", s.getId());
				lst.add(map);

			}
			return lst;
		} else if (about.equalsIgnoreCase("ippool")) {
			List<Radippool> ippoollist = radippoolRepository.getIppoolByGroup();

			for (Radippool r : ippoollist) {
				Map map = new HashMap<>();

				map.put("poolname", r.getPoolname());
				map.put("fromip", r.getFromip());
				lst.add(map);

			}
			return lst;
		}
		return null;

	}

	@Override
	public ResponseEntity<?> switchModification(CreateSwitchRequest obj) {

//		if (action.equalsIgnoreCase("CREATE")) {

		if (switchRepository.existsBySwitchname(obj.getSwitchname())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Switch name in use!"));

		} else if (switchRepository.existsBySwitchip(obj.getSwitchip())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Switch IP in use!"));
		}

		Switch switchobj = new Switch();

		switchobj.setSwitchname(obj.getSwitchname());
		switchobj.setSwitchip(obj.getSwitchip());
		switchobj.setSwitchport(obj.getSwitchport());
		switchobj.setCreationdate(Dateformat.getCurrentDatetime());
		switchobj.setIsactive(true);
		switchobj.setBranchid(obj.getBranch());
		switchobj.setProviderid(obj.getProvider());
		switchobj.setNasid(obj.getNas());

		switchRepository.save(switchobj);

		return ResponseEntity.ok(new MessageResponse("Successfully Created!"));
//		}

	}

	@Override
	public ResponseEntity<?> editSwitch(Switch reqobj) {

		Switch obj = switchRepository.findById(reqobj.getId());

		if (switchRepository.existsByNotInSwitchIp(obj.getSwitchip(), reqobj.getSwitchip()) > 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Switch IP in use!"));

		} else if (switchRepository.existsByNotInSwitchName(obj.getSwitchname(), reqobj.getSwitchname()) > 0) {
			return ResponseEntity.badRequest().body(new MessageResponse("Switch Name in use!"));

		}

		if (!reqobj.isIsactive()) {
			if (operator_switchportRepository.activeSwitchPort(reqobj.getId()) > 0) {

				return ResponseEntity.badRequest().body(new MessageResponse("Switch Port Allocated for Operator!"));
			}
		}

		obj.setSwitchname(reqobj.getSwitchname());
		obj.setSwitchip(reqobj.getSwitchip());
		obj.setSwitchport(reqobj.getSwitchport());
		obj.setBranchid(reqobj.getBranchid());
		obj.setProviderid(reqobj.getProviderid());
		obj.setNasid(reqobj.getNasid());
		obj.setIsactive(reqobj.isIsactive());
		obj.setUpdatedate(Dateformat.getCurrentDatetime());

		switchRepository.save(obj);
		return ResponseEntity.ok(new MessageResponse("Successfully Updated!"));
	}

	@Override
	public ResponseEntity<?> switchDelete(int switchid) {

		if (switchid != 0) {

			if (switchRepository.existsById(switchid)) {

				if (!operator_switchportRepository.existsBySwitchid(switchid)) {
					switchRepository.deleteById(switchid);

					return ResponseEntity.ok(new MessageResponse("Success!"));

				} else {
					return ResponseEntity.badRequest()
							.body(new MessageResponse("Switch already allocated for Operator!"));
				}

			}
		}
		return null;
	}

	@Override
	public Map<String, List> getLanPort(int switchid) {

		Map map1 = new HashMap<>();
		List lst = new ArrayList();

		List<Integer> portlist = operator_switchportRepository.getLanPortno(switchid);

		List<Integer> portnumbers = new ArrayList<>();
		portnumbers.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16));

		for (Integer i : portnumbers) {
			if (!portlist.contains(i)) {
				Map map = new HashMap<>();
				map.put("portno", i);
				map.put("id", i);
				lst.add(map);
			}

			map1.put("lanportlist", lst);

		}

		return map1;
	}

	@Override
	public List getIpList(String fromip) {
		List lst = new ArrayList<>();
		List<Radippool> ippoollist = radippoolRepository.getIppoolIplistByFromip(fromip);

		for (Radippool r : ippoollist) {
			Map map = new HashMap<>();

			map.put("framedip", r.getFramedipaddress());
			map.put("id", r.getFramedipaddress());
			lst.add(map);

		}
		return lst;
	}

	@Override
	public ResponseEntity<?> setIppoolForUser(Map<String, String> obj) {

		if (obj.get("iptype") != null && obj.get("username") != null) {
			if (obj.get("iptype").equalsIgnoreCase("Static")) {
				if (radippoolRepository.getUsernameByFromAndFramedIp(obj.get("fromip"), obj.get("framedip")) != null) {
					return ResponseEntity.badRequest()
							.body(new MessageResponse("Error: IP already allocated for User!"));
				}
			}
			Radippool radipobj = radippoolRepository.findByFromipAndFramedipaddress(obj.get("fromip"),
					obj.get("framedip"));

			Userinfo userobj = userinfoRepository.findByUsername(obj.get("username"));

			if (userobj != null) {

				userobj.setIptype(obj.get("iptype"));
				userinfoRepository.save(userobj);

				if (radipobj != null) {

					if (obj.get("iptype").equalsIgnoreCase("Static")) {
						radipobj.setUsername(obj.get("username"));

						Radreply radreply = new Radreply();
						radreply.setUsername(obj.get("username"));
						radreply.setAttribute("Framed-IP-address");
						radreply.setOp(":=");
						radreply.setValue(radipobj.getFramedipaddress());
						// insert radreply
						radreplyRepository.save(radreply);

					} else {
						radipobj.setUsername(null);

						radreplyRepository.deleteByAttributeAndUsername("Framed-IP-address", obj.get("username"));

					}
					radipobj.setUpdateddate(Dateformat.getCurrentDatetime());
					radippoolRepository.save(radipobj);

				}

				return ResponseEntity.ok(new MessageResponse("Success!"));
			}

		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Error:Something went wrong!"));
		}
		return null;
	}

	@Override
	public ResponseEntity<?> insertTables(Map<String, String> obj, String tablename) {

		if (tablename.equalsIgnoreCase("branch")) {

			if (!branchinfoRepository.existsByBranchname(obj.get("branchname"))) {
				Branchinfo bobj = new Branchinfo();

				bobj.setBranchname(obj.get("branchname"));
				bobj.setCreationdate(Dateformat.getCurrentDatetime());
				bobj.setIsactive(true);

				branchinfoRepository.save(bobj);

				return ResponseEntity.ok(new MessageResponse("Success!"));
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("Branch Name already in use!"));
			}

		} else if (tablename.equalsIgnoreCase("provider")) {

			if (!providerinfoRepository.existsByProvidername(obj.get("providername"))) {
				Providerinfo pobj = new Providerinfo();

				pobj.setProvidername(obj.get("providername"));
				pobj.setCreationdate(Dateformat.getCurrentDatetime());
				pobj.setIsactive(true);

				providerinfoRepository.save(pobj);

				return ResponseEntity.ok(new MessageResponse("Success!"));
			} else {
				return ResponseEntity.badRequest().body(new MessageResponse("Provider Name already in use!"));
			}

		}

		return null;
	}

}