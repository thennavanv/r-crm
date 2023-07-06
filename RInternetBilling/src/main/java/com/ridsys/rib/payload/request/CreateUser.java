package com.ridsys.rib.payload.request;

import org.springframework.web.multipart.MultipartFile;

public class CreateUser {
	private CreateuserRequest createuserRequest;
	private MultipartFile file;

	public CreateUser(CreateuserRequest createuserRequest, MultipartFile file) {
		super();
		this.createuserRequest = createuserRequest;
		this.file = file;
	}

	public CreateuserRequest getCreateuserRequest() {
		return createuserRequest;
	}

	public void setCreateuserRequest(CreateuserRequest createuserRequest) {
		this.createuserRequest = createuserRequest;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
