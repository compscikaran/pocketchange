package com.mvc.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mvc.dao.ExpenseDAO;
import com.mvc.entities.Expense;

@Service
@Transactional
public class ReportService {
	
	@Autowired
	private ExpenseDAO edao;
	
	public String generateReport() {
		Document doc = new Document();
		String filename = "report" + String.valueOf(randomNo()) + ".pdf";
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		doc.open();
		
		PdfPTable table = new PdfPTable(4);
		addTableHeader(table);
		List<Expense> eList = edao.allExpenses();
		for (Expense expense : eList) {
			addRow(table, expense);
		}
		try {
			doc.add(table);
		} catch (DocumentException e) {
			System.out.println("Failed to add Table");
		}
		doc.close();
		return filename;
	}
	
	private int randomNo() {
		Random ra = new Random();
		return ra.nextInt(100);
	}
	
	private void addTableHeader(PdfPTable table) {
		String[] columns = {"Expense", "Amount", "Category", "Date"};
		Stream.of(columns).forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.WHITE);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}
	
	private  void addRow(PdfPTable table, Expense exp) {
		table.addCell(exp.getTitle());
		table.addCell(String.valueOf(exp.getAmount()));
		table.addCell(exp.getCategory().name());
		table.addCell(exp.getStamp().toString());
	}
	
	
}
