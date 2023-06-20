package com.duan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.duan.dao.AccountDao;
import com.duan.entity.Account;
import com.duan.service.MailerService;



@Controller
public class AccountController {
@Autowired AccountDao dao;
@Autowired
MailerService mailer;

@RequestMapping("quenmk")
public String quenmk() {
	return "/account/quenmk";
}

@RequestMapping("quenmktv")
public String quenmktv(Model model, @RequestParam("username") String username) {
	if (!dao.existsById(username)) {
		model.addAttribute("message", "Tài khoản này không tồn tại!");
	} else {
		Account ac = dao.getOne(username); 
		String pass = ac.getPassword();
		String email = ac.getEmail();
		try {
			mailer.queue(email, "Lấy lại mật khẩu", pass);
			model.addAttribute("message", "Chúng tôi đã gửi mật khẩu vào email" + " " + email + " " + "của bạn");
			return "/account/quenmk";
		} catch (Exception e) {
			
			return e.getMessage();
		}
	}
	return "/account/quenmk";
}
}
