package com.research.spring.controller;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.research.spring.model.UserInfo;

/**
 * 登录控制器
 * @author wdmcygah
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	//这里模拟数据库或其它地方里面存放的数据
	private static ConcurrentHashMap<String, String> userMap = new ConcurrentHashMap<String, String>();
	
	@RequestMapping("/show")
	public String login( HttpServletRequest request,Model model){
		String name = getRememberedName(request);
		if( null != name ){
			model.addAttribute("status", "2");
			model.addAttribute("userName", name);
		}
		return "/login/show";
	}
	
	@RequestMapping("/loginIn")
	public String loginIn( UserInfo info, boolean remember, HttpServletRequest request, HttpServletResponse response,Model model){
		if( "admin".equals(info.getUserName()) && "admin".equals(info.getPassword()) ){
			if( remember ){
				rememberUser(request, response, info);
			}
			model.addAttribute("status", "1");
			model.addAttribute("userName", info.getUserName());
			return "/login/show";
		}else{
			model.addAttribute("status", "-1");
			return "/login/show";
		}
	}
	
	@RequestMapping("/loginOut")
	public String loginOut( String userName, HttpServletResponse response,Model model){
		if( null != userName ){
			//删除缓存
			userMap.remove(userName);
			//删除Cookie数据
			removeCookie(userName, response);
		}
		return "/login/show";
	}

	/**
	 * 删除userName对应的Cookie值（实际是把有效期设置为0）
	 * @param userName
	 * @param response
	 */
	private void removeCookie(String userName, HttpServletResponse response) {
		Cookie cookie = new Cookie(userName,"remove");
		//有效期为0即为删除，同名cookie会进行覆盖
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/**
	 * 记住用户
	 * @param requset 
	 * @param response
	 * @param info
	 */
	private void rememberUser(HttpServletRequest request, HttpServletResponse response, UserInfo info) {
		//这里只是模拟，实际情况可能需要对值进行算法加密
		String cName = info.getUserName();
		String cValue = info.getUserName() + request.getServerName() + new Date().getTime();
		Cookie cookie = new Cookie(cName,cValue);
		//有效期30分钟
		cookie.setMaxAge(30*60);
		response.addCookie(cookie);
		//数据缓存到内存中
		userMap.put(cName, cValue);
	}
	
	/**
	 * 得到记住的用户名
	 * @param request 
	 * @param response
	 */
	private String getRememberedName(HttpServletRequest request) {
		Cookie [] cookies = request.getCookies();
		if( (null==cookies) || (0==cookies.length) ){
			return null;
		}
		String cName = null;
		String cValue = null;
		for( Cookie c : cookies ){
			cName = c.getName();
			cValue = c.getValue();
			//用户名及对应的值与缓存中的相同，则认为是记住的用户
			if( (cValue!=null) && ((cName!=null)) && (cValue.equals(userMap.get(cName)))) {
				return cName;
			}  
		}
		return null;
	}
}
