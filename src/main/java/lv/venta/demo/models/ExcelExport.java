package lv.venta.demo.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExport {
	private XSSFWorkbook book;
	private XSSFSheet sheet;
	private ArrayList<EmployeeCourse> allEmplCourse;
	
	public ExcelExport(ArrayList<EmployeeCourse> allEmplCourse) {
		this.allEmplCourse = allEmplCourse;
		book = new XSSFWorkbook();
	}
	
	private void headerExcel() {
		sheet = book.createSheet("EmplyeeCourses");
		Row row = sheet.createRow(0);
		CellStyle style = book.createCellStyle();
		XSSFFont font = book.createFont();
		font.setBold(true);
		font.setFamily(FontFamily.MODERN);
		font.setFontHeight(14);
		style.setFont(font);
		createCell(row, 0, "IdEmplCo", style);
		createCell(row, 1, "ValuePr", style);
		createCell(row, 2, "Employee", style);
		createCell(row, 3, "Course", style);
		//createCell(row, 4, "Date", style);
	}
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (value instanceof Float) {
	            cell.setCellValue((Float) value);
	        } else if (value instanceof Boolean) {
	            cell.setCellValue((Boolean) value);
	        } else if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        }else if (value instanceof String) {
	            cell.setCellValue((String) value);
	        }else {
	        	style = book.createCellStyle();
	        	CreationHelper createHelper = book.getCreationHelper();
	        	short dateFormat = createHelper.createDataFormat().getFormat("dd-MM-yyyy");
	        	style.setDataFormat(dateFormat);
	        	cell.setCellValue((Date)value);
	        }
	        cell.setCellStyle(style);
	 }
	private void writeDataExcel() {
		int rowsCount = 1;
		int columnCount = 0;
		CellStyle style = book.createCellStyle();
		XSSFFont font = book.createFont();
		font.setFamily(FontFamily.MODERN);
		font.setFontHeight(12);
		
		for(EmployeeCourse emplCourse : allEmplCourse) {
			Row row = sheet.createRow(rowsCount++);
			
			createCell(row, columnCount++, emplCourse.getIdEmCo(), style);
			createCell(row, columnCount++, emplCourse.getValuePr(), style);
			createCell(row, columnCount++, emplCourse.getEmployee().getIdEm(), style);
			createCell(row, columnCount++, emplCourse.getCourse().getTitle().toString(), style);
			columnCount = 0;
		}
	}
	
	public void export(HttpServletResponse response) throws IOException {
		headerExcel();
        writeDataExcel();
         
        ServletOutputStream outputStream = response.getOutputStream();
        book.write(outputStream);
        book.close();
         
        outputStream.close();
         
    }
	   
	   
}