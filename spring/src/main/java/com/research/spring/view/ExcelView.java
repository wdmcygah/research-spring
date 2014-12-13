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
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.research.spring.model.UserInfo;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

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

		String fileName = encodeFilename("用户.xls", request);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="
				+ fileName);
		OutputStream os = response.getOutputStream();
		workbook.write(os);
		os.flush();
		os.close();
	}

	/**
	 * 这里对文件名进行编码，保证下载时汉字显示正确
	 * @param filename
	 * @param request
	 * @return
	 */
	public static String encodeFilename(String filename,
			HttpServletRequest request) {
		/**
		 * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
		 * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
		 * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
		 * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
		 */
		String agent = request.getHeader("USER-AGENT");
		try {
			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				String newFileName = URLEncoder.encode(filename, "UTF-8");
				newFileName = StringUtils.replace(newFileName, "+", "%20");
				if (newFileName.length() > 150) {
					newFileName = new String(filename.getBytes("GB2312"),
							"ISO8859-1");
					newFileName = StringUtils.replace(newFileName, " ", "%20");
				}
				return newFileName;
			}
			if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
				return MimeUtility.encodeText(filename, "UTF-8", "B");

			return filename;
		} catch (Exception ex) {
			return filename;
		}
	}

}
