import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellStyle;

public class Amelioration extends Apl2{
	protected JPanel southButtons;
	protected JButton bourseButton, noteBourseButton;
	public int noteBourse, noteBourseTemp;
	private static String[] columns = {"Nom", "Note", "Numero de téléphone"};

	Amelioration(){
		setTitle("Kaloyan Stoyanov - amelioration. Fenetre 1");
		setBounds(40,40,600,300);
		southButtons = new JPanel(new FlowLayout());
		bourseButton = new JButton("Qui gagne la bourse ?");
		noteBourseButton = new JButton("Note bourse");
		bourseButton.addActionListener(new Bourse());
		noteBourseButton.addActionListener(new NoteBourse());
		southButtons.add(noteBourseButton);
		southButtons.add(bourseButton);
		add("South", southButtons);
		revalidate();
	}

	class NoteBourse implements ActionListener{	
		public void actionPerformed(ActionEvent e){
			JFrame frameNoteBourse = new JFrame();
			try{
				String input = JOptionPane.showInputDialog(frameNoteBourse, "Note minimale pour obtenir la bourse: ");
				noteBourseTemp = Integer.parseInt(input);
				if((noteBourseTemp>20)||(noteBourseTemp<0)) { 
					tf[2].setText("Note must be between 0 and 20, error");
					return;
				}
				noteBourse=noteBourseTemp;
				tf[2].setText("Minimal note added!");
			}
			catch(NumberFormatException ex){
				tf[2].setText("Note must be integer, error");
				return;
			}
		}
	}

	class Bourse implements ActionListener {

		public void actionPerformed(ActionEvent e){

			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Students bourse");
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 17);
			headerFont.setColor(IndexedColors.RED.getIndex());
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			Row headerRow = sheet.createRow(0);

			for (int i=0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			FenetreBourse aml=new FenetreBourse(20,20,400,300);
			ObjectInputStream ios = null;
			try{
				ios = new ObjectInputStream (new FileInputStream ("save.ser"));
				prs=(Student[])ios.readObject();
			}
			catch (Exception ex){
				tf[2].setText("Error");
			}
			try{
				if(ios!=null)ios.close();
			}
			catch (IOException ex){}
			aml.lst1.removeAll();
			int rowNum = 1;
			for(int i=0;i<prs.length;i++){
				if(prs[i].note >= noteBourse) {
					aml.lst1.add(""+prs[i]);
					Row row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(prs[i].name);
					row.createCell(1).setCellValue(prs[i].note);
				}
			}
			try {
				FileOutputStream fileOut = new FileOutputStream("eligible_students.xlsx");
				workbook.write(fileOut);
				workbook.close();
			} catch (IOException e1) {
				return;
			}
		}
	}

	public static void main(String[] args) {
		new Amelioration();
	}
}