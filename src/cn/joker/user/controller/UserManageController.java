package cn.joker.user.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.joker.entity.User;
import cn.joker.user.service.UserManageService;

@Controller
@RequestMapping("/user")
public class UserManageController {
	
	@Autowired
	private UserManageService userManageService;
	public void setUserManageService(UserManageService userManageService) {
		this.userManageService = userManageService;
	}
	@RequestMapping("/homepage.do")
	public String getHp(ServletRequest request,String userId) {
		request.setAttribute("userId", userId);
		return "user_homepage";
	}
	
	@RequestMapping("/goRegister.do")
	public String goRegister() {
		return "user_register";
	}
	@RequestMapping("/register.do")
	public String register(ServletRequest request,User user) {
		
		userManageService.register(user);
		request.setAttribute("message", "账户注册成功，您可以去<a href='/HpuFood/user/goLogin.do'>登录</a>");
		return "operationResult";
	}
	
	@RequestMapping("/goLogin.do")
	public String goLogin() {
		return "user_login";
	}
	@RequestMapping("/login.do")
	public String login(HttpSession session,User user) {
		User u = userManageService.login(user);
		
		if(u != null) {
			session.setAttribute("user", u);
		}
		else
			return "redirect:/user/goRegister.do";
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		
		session.removeAttribute("user");
		
		return "redirect:/index.jsp";
		
		
	}
	
	@RequestMapping("/info.do")
	@ResponseBody
	public User goUserInfo(String u_id) {
		
		if(u_id != null) {
			User user = userManageService.getUserInfo(u_id);
			return user;
		}
		return null;
	}
	@RequestMapping("/update.do")
	public String update(User user) {
		userManageService.update(user);
		return "redirect:homepage.do?userId="+user.getId();
	}


	
}
