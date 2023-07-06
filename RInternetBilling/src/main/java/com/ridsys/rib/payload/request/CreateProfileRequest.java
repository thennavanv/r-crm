package com.ridsys.rib.payload.request;

import java.util.List;

public class CreateProfileRequest {
	private String profilename;
	private List<CreateGroupRequest> groups;

	public String getProfilename() {
		return profilename;
	}

	public void setProfilename(String profilename) {
		this.profilename = profilename;
	}

	public List<CreateGroupRequest> getGroups() {
		return groups;
	}

	public void setGroups(List<CreateGroupRequest> groups) {
		this.groups = groups;
	}

}
