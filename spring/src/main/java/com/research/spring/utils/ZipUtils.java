package com.research.spring.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 使用JDK自带的Zip处理类进行文件的压缩与解压
 *
 */
public class ZipUtils {

	/**
	 * 对Excel文件进行压缩
	 * 
	 * @param wb
	 */
	public static void zip(File targetFile, HSSFWorkbook... wbs) {
		if (wbs == null || wbs.length == 0) {
			return;
		}
		ZipOutputStream zos = null;
		try {
			zos = new ZipOutputStream(new FileOutputStream(targetFile));
			for (int i = 0; i < wbs.length; i++) {
				ZipEntry entry = new ZipEntry("文件" + i + ".xls");
				// 设置压缩包的入口
				zos.putNextEntry(entry);
				wbs[i].write(zos);
				zos.flush();
			}
			zos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
