package com.research.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.research.spring.model.UserInfo;
import com.research.spring.view.ExcelView;

@Controller
@RequestMapping("/file")
public class FileController {

	/**
	 * Excel文件上传处理
	 * @param file
	 * @return
	 */
	@RequestMapping("/upload")
	public ModelAndView uploadExcel(@RequestParam("file") MultipartFile file){
		List<UserInfo> list = new ArrayList<UserInfo>();
		if( file.getOriginalFilename().contains("用户") ){
			try {
				Workbook wb = new HSSFWorkbook(file.getInputStream());
				Sheet sheet = wb.getSheetAt(0);
				for( int i = 1; i <= sheet.getLastRowNum(); i++ ){
					Row row = sheet.getRow(i);
					UserInfo info = new UserInfo();
					info.setUserName(row.getCell(0).getStringCellValue());
					info.setPassword(row.getCell(1).getStringCellValue());
					list.add(info);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ModelAndView mav = new ModelAndView("content");
		mav.addObject("content",list.toString());
		return mav;
	}
	
	/**
	 * Excel文件下载处理
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
		ExcelView ve = new ExcelView();
		return new ModelAndView(ve,map);
	}
	
}
