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

	@RequestMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file){
		if( file.getOriginalFilename().contains("财务") ){
			try {
				Workbook wb = new HSSFWorkbook(file.getInputStream());
				Sheet sheet = wb.getSheetAt(0);
				List<UserInfo> list = new ArrayList<UserInfo>();
				for( int i = 1; i <= sheet.getLastRowNum(); i++ ){
					Row row = sheet.getRow(i);
					UserInfo info = new UserInfo();
					info.setUserName(row.getCell(0).getStringCellValue());
					info.setPassword(row.getCell(1).getStringCellValue());
					list.add(info);
				}
				System.out.println(list);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	@RequestMapping("/download")
	public ModelAndView downloanFile(){
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
