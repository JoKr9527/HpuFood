package cn.joker.store.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.joker.store.service.AccountManageService;
import cn.joker.entity.Restaurant;
import cn.joker.entity.Store;
import cn.joker.entity.StoreAdmin;
import cn.joker.entity.StoreInfo;

@Controller
@RequestMapping(value="/store")
public class AccountManageController {
	
	@Autowired()
	private AccountManageService accountManageService;
	
	public void setService(AccountManageService service) {
		this.accountManageService = service;
	}

	@RequestMapping(value="/goRegister.do")
	public String goRegister() {
		return "register";
	}
	@RequestMapping("/goLogin.do")
	public String goLogin() {
		return "login";
	}
	
	
	@RequestMapping(value="/register.do")
	public String register(ServletRequest request,String restaurantName,String storeName,String password,String themain,String address,String adminName) {
		
		Restaurant restaurant = new Restaurant(restaurantName);
		Store store = new Store(storeName, 0, restaurant);
		StoreInfo storeInfo = new StoreInfo(themain, address, 0, 0, store);
		StoreAdmin admin = new StoreAdmin();
		admin.setName(adminName);
		admin.setPassword(password);
		admin.setStore(store);
		accountManageService.register(restaurant,store,storeInfo,admin);
		request.setAttribute("message", "注册成功，您可以去<a href='/HpuFood/store/goLogin.do'>登录</a>");
		return "operationResult";
	}
	
	@RequestMapping(value="/goUpdatedInfo.do")
	public String goUpdatedInfo(HttpSession session,ServletRequest request) {
		
		String store_id = (String) session.getAttribute("store_id");
		accountManageService.getUpdatedInfo(store_id,request);
		
		return "store_updateInfo";
	}
	@RequestMapping("/update.do")
	public String update(HttpSession session,String restaurantName,String storeName,String themain,String address) {
		
		StoreAdmin admin = (StoreAdmin) session.getAttribute("admin");
		if(admin != null) {
			Restaurant restaurant = new Restaurant(restaurantName);
			Store store = new Store(storeName, 0, null);
			store.setId(admin.getStore().getId());
			StoreInfo storeInfo = new StoreInfo();
			storeInfo.setAddress(address);
			storeInfo.setStore(store);
			storeInfo.setThemain(themain);
			accountManageService.update(restaurant,store,storeInfo);
			return "redirect:homepage.do?storeId="+admin.getStore().getId();
		}
		
		return "redirect:goLogin.do";	
	}
	
	@RequestMapping("/login.do")
	public String login(HttpSession session,String name,String password) {
		
		StoreAdmin admin = accountManageService.login(name,password);
		if(admin == null)
			return "redirect:/store/goRegister.do";
		session.setAttribute("admin", admin);
		return "redirect:/index.jsp";	
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		
		session.removeAttribute("admin");
		
		return "redirect:/index.jsp";
		
		
	}
}
