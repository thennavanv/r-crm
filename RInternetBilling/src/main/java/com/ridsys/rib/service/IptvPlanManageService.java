package com.ridsys.rib.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.ridsys.rib.payload.request.IptvPlanActivation;

public interface IptvPlanManageService {

	String iptvplanActivation(@Valid IptvPlanActivation reqobj);

}
