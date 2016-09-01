package com.QuickStartFrame.management.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static final Logger logger = Logger.getLogger(ExcelUtil.class);

	/**
	 * 读取excel所有行列，当作字符串读取.
	 * 
	 * @param filePath
	 *            文件路径
	 * @return
	 */
	public static ArrayList<String[]> readExcel(String filePath) {

		ArrayList<String[]> rowList = new ArrayList<String[]>();

		// 打开文档
		Workbook workBook = null;
		try {
			workBook = new XSSFWorkbook(filePath);
		} catch (Exception ex) {
			try {
				workBook = new HSSFWorkbook(new FileInputStream(filePath));
			} catch (FileNotFoundException e) {
				logger.error("读取excel出错", e);
				return rowList;
			} catch (IOException e) {
				logger.error("读取excel出错", e);
				return rowList;
			}
		}

		// 循环sheet
		int sheetNum = workBook.getNumberOfSheets();
		for (int numSheet = 0; numSheet < sheetNum; numSheet++) {
			Sheet sheet = workBook.getSheetAt(numSheet);
			if (sheet == null) {
				continue;
			}

			// 循环行Row
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}

				// 循环列Cell
				String[] arrCell = new String[row.getLastCellNum() + 1];
				for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
					Cell cell = row.getCell(cellNum);
					if (cell == null) {
						continue;
					}

					// 强转字符串，读取
					cell.setCellType(Cell.CELL_TYPE_STRING);
					arrCell[cellNum] = cell.getStringCellValue();
				}
				rowList.add(arrCell);
			}
		}

		// 关闭excel
		try {
			workBook.close();
		} catch (IOException e) {
		}

		return rowList;
	}
}