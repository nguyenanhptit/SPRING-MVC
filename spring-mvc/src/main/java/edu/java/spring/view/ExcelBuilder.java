package edu.java.spring.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import edu.java.spring.model.JavaClazz;
import edu.java.spring.model.Student;

public class ExcelBuilder extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook worbook, HttpServletRequest rq,
			HttpServletResponse rsp) throws Exception {
		// TODO Auto-generated method stub
		Sheet sheet = worbook.createSheet("Java Clazz");
		 Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Name");
		header.createCell(2).setCellValue("Age");
				
			JavaClazz clazz = (JavaClazz) model.get("clazzObj");
						for(int i=0; i<clazz.getStudents().size();i++) {
				Student student = clazz.getStudents().get(i);
				Row row =sheet.createRow(i+1);
				row.createCell(0).setCellValue(student.getId());
				row.createCell(1).setCellValue(student.getName());
				row.createCell(2).setCellValue(student.getAge());
			
			}
	}

	
}

