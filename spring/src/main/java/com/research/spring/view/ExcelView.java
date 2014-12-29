package com.research.spring.view;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.research.spring.model.UserInfo;

/**
 * 下载Excel视图
 * 
 * @author wdmcygah
 *
 */  
public class ExcelView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<UserInfo> list = (List<UserInfo>) model.get("infoList");
		if (list != null && list.size() != 0) {
			int len = list.size();
			Sheet sheet = workbook.createSheet();
			// 第一行文字说明
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0, Cell.CELL_TYPE_STRING);
			cell.setCellValue("用户名");
			cell = row.createCell(1, Cell.CELL_TYPE_STRING);
			cell.setCellValue("密码");

			//下面是具体内容
			for (int i = 0; i < len; i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0, Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(i).getUserName());
				cell = row.createCell(1, Cell.CELL_TYPE_STRING);
				cell.setCellValue(list.get(i).getPassword());
			}
		}

		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("utf-8");
		//这里对文件名进行编码，保证下载时汉字显示正常
		String fileName = URLEncoder.encode("用户.xls", "utf-8");
		//Content-disposition属性设置成以附件方式进行下载
		response.setHeader("Content-disposition", "attachment;filename="
				+ fileName);
		OutputStream os = response.getOutputStream();
		workbook.write(os);
		os.flush();
		os.close();
	}

}
