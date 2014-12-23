package com.research.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.research.spring.model.UserInfo;
import com.research.spring.view.ZipView;

/**
 * 压缩包下载控制器
 * @author wdmcygah
 *
 */
@Controller
@RequestMapping("/zip")
public class ZipController {

	/**
	 * 压缩文件下载处理
	 */
	@RequestMapping("/download")
	public ModelAndView downloanExcel(){
		List<UserInfo> list = new ArrayList<UserInfo>();
		UserInfo userInfo = new UserInfo();
		userInfo.setPassword("0000");
		userInfo.setUserName("sdfas");
		list.add(userInfo);
		list.add(userInfo);
		list.add(userInfo);
		list.add(userInfo);
		Map<String,List<UserInfo>> map = new HashMap<String, List<UserInfo>>();
		map.put("infoList", list);
		ZipView ve = new ZipView();
		return new ModelAndView(ve,map);
	}
	
}
