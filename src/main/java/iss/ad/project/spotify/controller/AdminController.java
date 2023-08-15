package iss.ad.project.spotify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import iss.ad.project.spotify.service.AdminService;

@Controller
public class AdminController {
	private final AdminService adminSrv;
	
	@Autowired
	public AdminController(AdminService adminSrv) {
		this.adminSrv=adminSrv;
	}

}