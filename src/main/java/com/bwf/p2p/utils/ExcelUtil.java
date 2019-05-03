package com.bwf.p2p.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ExcelUtil {

	private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);
	
	/** 工作簿 */
	public static Workbook workbook = null;
	
	public static void main(String[] args) throws Exception {
//		long st = System.currentTimeMillis();
//		List<Map<String,Object>> list = readExcel("D://user.xlsx");
//		long ed = System.currentTimeMillis();
//		System.out.println("解析毫秒数：" + (ed - st) + " size：" + list.size());
		
//		long bf = System.currentTimeMillis();
//		List<User> users = BeanUtil.mapToBean(list, User.class);
//		long af = System.currentTimeMillis();
//		System.out.println("转化毫秒数：" +(af - bf) + " size：" + users.size());
//		System.out.println("时间：" + (af - st) / 1000.0 + "秒");
		
		/*long bf = System.currentTimeMillis();
		List<Map<String, Object>> list = Lists.newLinkedList();
		for (int i = 0; i < 100000; i++) {
			Map<String, Object> map = Maps.newHashMap();
			for (int j = 0; j < 10; j++) {
				map.put("col" + j, j);
			}
			list.add(map);
		}
		long af = System.currentTimeMillis();
		System.out.println("构造毫秒数：" +(af - bf) + " size：" + list.size());
		
		long st = System.currentTimeMillis();
		writeExcel("D://map.xlsx", "D://data.xlsx", list);
		long ed = System.currentTimeMillis();
		System.out.println("生成excel秒数：" + (ed - st) / 1000 + " size：" + list.size());*/
//		createExcel();
	}
	
	/**
	 * read Excel file, to get a collections of Map<String, Object>
	 * @param filePath Excel's full directory
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, Object>> readExcel(String filePath) 
			throws RuntimeException, IOException {
		
		FileInputStream fis = new FileInputStream(filePath);
		// 判断Excel的两种格式
		if (filePath.endsWith(".xlsx")) {
			workbook = new XSSFWorkbook(fis);
		} else if (filePath.endsWith(".xls")) {
			workbook = new HSSFWorkbook(fis);
		} else {
			fis.close();
			throw new RuntimeException("此文件不是有效的Excel");
		}
		
		List<Map<String, Object>> list = Lists.newLinkedList();
		
		// 处理每个工作簿
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			List<Map<String, Object>> child_List = parseSheet(workbook.getSheetAt(i));
			list.addAll(child_List);
		}
		
		return list;
	}
	
	/**
	 * parse data of Sheet
	 * @param sheet Sheet
	 * @return
	 */
	public static List<Map<String, Object>> parseSheet(Sheet sheet) {
		if (sheet == null) {
			return null;
		}
		
		List<Map<String, Object>> list = Lists.newLinkedList();
		
		Row title = sheet.getRow(0); // 标题行
		// 从第二行开始，处理每一行
		for (int j = 1; j <= sheet.getLastRowNum(); j++) {
			Map<String, Object> map = Maps.newHashMap(); //创建map封装数据
			Row row = sheet.getRow(j);
			
			// 处理每个单元格
			for (int k = 0; k < row.getLastCellNum(); k++) {
				String key = title.getCell(k).getStringCellValue(); // 字段名
				Object value = getCellValue(row.getCell(k));
				map.put(key, value);
			}
			
			list.add(map);
		}
		
		return list;
	}
	
	/**
	 * to get Cell-Value by Cell-Type
	 * @param cell Cell
	 * @return Object
	 */
	private static Object getCellValue(Cell cell) {
		Object value = null; 
		if (cell == null) {
			return value;
		}
		
        switch (cell.getCellType()) {
	        case Cell.CELL_TYPE_NUMERIC: // 数字
	            if(DateUtil.isCellDateFormatted(cell)){ // 日期类型  
	            	value = cell.getDateCellValue();  
	            }else{  
	                value = (int)cell.getNumericCellValue();  
	            }  
	            break;  
	        case Cell.CELL_TYPE_STRING: // 字符串
	            value = cell.getStringCellValue().trim();  
	            break;  
	        case Cell.CELL_TYPE_BOOLEAN: // Boolean
	            value = cell.getBooleanCellValue();   
	            break;  
	        case Cell.CELL_TYPE_ERROR: // 故障
	        	value = null;
	            break;  
	        case Cell.CELL_TYPE_FORMULA: // 公式
	            value = cell.getCellFormula(); 
	            break;            
	        case Cell.CELL_TYPE_BLANK: // 空值
	            value = "";  
	            break;            
	        default :  
	            value = "[未知类型]";  
	            break;  
        }
        
        return value;  
	}
	
	@SuppressWarnings("resource")
	public static void writeExcel(String templateFilePath, String destPath, List<Map<String, Object>> dataList) throws Exception {
		if (Strings.isNullOrEmpty(templateFilePath)) {
			throw new IllegalArgumentException("templateFilePath can not be null");
		}
		if (Strings.isNullOrEmpty(destPath)) {
			throw new IllegalArgumentException("destPah can not be null");
		}
		if (dataList == null) {
			throw new IllegalArgumentException("dataList can not be null");
		}
		
		XSSFWorkbook xssfworkbook = new XSSFWorkbook(new FileInputStream(new File(destPath)));
		SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(xssfworkbook);
		Sheet sheet = sxssfWorkbook.getSheetAt(0); // 只写入一个工作簿

		writeData(dataList, sheet);
		
		FileOutputStream out = new FileOutputStream(templateFilePath);
		sxssfWorkbook.write(out);
		out.close();
	}

	private static void writeData(List<Map<String, Object>> dataList, Sheet sheet) {
		boolean firstRow = true;
		for (int i = 0; i < dataList.size(); i++) {
			Row row = sheet.createRow(firstRow ? i : i + 1);
			Map<String, Object> data = dataList.get(i);
			int j = 0;
			for (Entry<String, Object> entry : data.entrySet()) {
				Cell cell = row.createCell(j++);
				// 首行
				if (firstRow) {
					cell.setCellValue(entry.getKey());
				} else {
					cell.setCellValue(entry.getValue().toString());
					firstRow = false;
				}
			}
			// 首行被写入，改变首行状态标识，并且将光标移到第一个数据
			if (firstRow) {
				firstRow = false;
				i--;
			}
		}
	}
	/**
	 * String[] colomnNames, String[] colomnFormats, List<Map<String, Object>> data
	 * @throws IOException
	 */
	public static void createExcel(OutputStream os) throws IOException {
		// 1.创建workbook，对应一个excel
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		// 2.单元格设置
		Font font = workbook.createFont(); // 创建字体样式
		font.setFontName("宋体"); // 使用宋体
		font.setFontHeightInPoints((short) 11); // 字体大小
//		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
		
		CellStyle cellStyle = workbook.createCellStyle(); // 单元格样式style
		cellStyle.setFont(font); // 注入字体
		cellStyle.setWrapText(true); // 自动换行
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 靠左对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 上下居中
		cellStyle.setFillBackgroundColor(IndexedColors.WHITE.index); // 背景颜色
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyle.setBorderTop((short) 1); // 边框大小
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderRight((short) 1);
		cellStyle.setBorderLeft((short) 1);
		
		// 生成一个sheet，对应excel的sheet，参数为excel中sheet显示的名字
		Sheet sheet = workbook.createSheet();
		sheet.setColumnWidth(0, 0);
		sheet.setColumnWidth(1, 20*256);
		sheet.setColumnWidth(2, 20*256);
		sheet.setColumnWidth(3, 20*256);
		
		Row row = sheet.createRow(0);
		row.setHeight((short) 800);
		Cell cell = row.createCell(0);
		cell = row.createCell(1);
		cell.setCellValue("x");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(2);
		cell.setCellValue("y");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(3);
		cell.setCellValue("z");
		cell.setCellStyle(cellStyle);
		
		for (int i = 1; i <= 1000000; i++) {
			row = sheet.createRow(i);
			row.setHeight((short) 800);
			cell = row.createCell(0);
			cell = row.createCell(1);
			cell.setCellValue("x");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(2);
			cell.setCellValue("y");
			cell.setCellStyle(cellStyle);
			cell = row.createCell(3);
			cell.setCellValue("z");
			cell.setCellStyle(cellStyle);
		}
		
//		FileOutputStream os = new FileOutputStream("d://test.xlsx");
		workbook.write(os);
		os.flush();
		os.close();
		workbook.close();
	}

}
