package com.research.spring.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.apache.poi.util.IOUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.research.spring.model.UserInfo;
import com.research.spring.utils.ZipUtils;

/**
 * 下载Excel视图
 * 
 * @author wdmcygah
 *
 */
public class ZipView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HSSFWorkbook b1 = generateExcel(model);
		HSSFWorkbook b2 = generateExcel(model);
		File file = getFile();
		ZipUtils.zip(getFile(), b1,b2);
		response.setContentType("application/octet-stream");
		String name = URLEncoder.encode("压缩.zip", "UTF-8");
		response.setHeader("Content-disposition", "attachment;filename="
				+ name);
		OutputStream os = response.getOutputStream();
		IOUtils.copy(new FileInputStream(file), os);
		os.flush();
		os.close();
		file.delete();
	}
	
	private HSSFWorkbook generateExcel( Map<String,Object> model){
		HSSFWorkbook result = null;
		@SuppressWarnings("unchecked")
		List<UserInfo> list = (List<UserInfo>) model.get("infoList");
		if (list != null && list.size() != 0) {
			result = new HSSFWorkbook();
			int len = list.size();
			Sheet sheet = result.createSheet();
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
		return result;
	}

	private File getFile(){
		String targetPath = "/ziptarget/zip/";
		File file = new File(targetPath);
		if( !file.exists() ){
			file.mkdirs();
		}
		String fileName = "test.zip";
		File f = new File(targetPath+fileName);
		if( !f.exists() ){
			try {
				File.createTempFile("001", ".zip");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return f;
	}
	
}
