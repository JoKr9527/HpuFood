package cn.joker.store.controller;


import java.io.File;
import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import cn.joker.entity.Store;
import cn.joker.entity.StoreAdmin;
import cn.joker.entity.StorePictures;
import cn.joker.store.service.CoverService;

@Controller
@RequestMapping("/store")
public class CoverController {
	@Autowired
	CoverService coverService;
	
	public void setCoverService(CoverService coverService) {
		this.coverService = coverService;
	}

	@RequestMapping("/images.do")
	public String imagesUpload(ServletRequest request,HttpSession session,@RequestPart MultipartFile file,UriComponentsBuilder uriBuilder) {
		
		StoreAdmin admin = (StoreAdmin) session.getAttribute("admin");
		if(admin != null) {
			if(!file.isEmpty()) {
				String fileName = System.currentTimeMillis()+".jpg";
				String uri = uriBuilder.path("/images/"+fileName).toUriString();
				@SuppressWarnings("deprecation")
				String location = request.getRealPath("/images");
				
				try {
					file.transferTo(new File(location+"\\"+fileName));
					// ´æ´¢uri
					coverService.uploadURI(admin.getStore(), uri);
					
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "redirect:homepage.do?storeId="+admin.getStore().getId();
		}
		return "redirect:goLogin.do";	
	}
	@SuppressWarnings("unused")
	@RequestMapping("/delPicture.do")
	public String delPic(HttpSession session,String sp_id) {
		StoreAdmin admin = (StoreAdmin) session.getAttribute("admin");
		Store store = admin.getStore();
		StorePictures sp = new StorePictures();
		sp.setId(sp_id);
		sp.setStore(store);
		if(admin != null) {
			coverService.delPicture(sp);
			return "redirect:homepage.do?storeId="+admin.getStore().getId();
		}
		return "redirect:goLogin.do";
	}
}
