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
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.research.spring.model.UserInfo;
import com.research.spring.utils.ZipUtils;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

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
		String name = encodeFilename("压缩.zip",request);
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
