import java.awt.*;
import javax.swing.*;

public class FenetreBourse extends JFrame{
	protected JTextField textField[]= new JTextField[1];
	protected List lst1;
	protected JPanel plst1;
	protected Student prs[]=new Student[0];
	FenetreBourse(int x, int y, int ln, int ht){
		this.setLayout(new BorderLayout());
		this.setBounds(x, y, ln, ht);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Kaloyan Stoyanov - amelioration. Fenetre 2");
		plst1 = new JPanel(new FlowLayout());
		lst1 = new List(10);
		textField[0]= new JTextField("Excel File was generated!",10);
		textField[0].setEditable(false);
		plst1.add(lst1);
		add("South", textField[0]);
		add("Center",plst1);     
		revalidate();
	}
}