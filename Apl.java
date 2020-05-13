import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Apl extends JFrame{
	protected JButton ad;
	protected JTextField tf[]= new JTextField[3];
	protected List lst;
	protected JPanel contr, plst;
	protected AdSt adSt;
	protected Student prs[]=new Student[0];

	Apl(int x, int y, int ln, int ht){
		this.setLayout(new BorderLayout());
		this.setBounds(x, y, ln, ht);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Student's directory - version 1");
		contr = new JPanel(new FlowLayout());
		plst = new JPanel(new FlowLayout());
		ad = new JButton("add");
		contr.add(ad);
		tf[0]= new JTextField("name?",10);
		tf[1]= new JTextField("note?",3);
		tf[2]= new JTextField("Student's list",20);
		contr.add(tf[2]);
		tf[2].setEditable(false);
		contr.add(tf[0]);
		contr.add(tf[1]);
		ad.addActionListener(adSt=new AdSt());
		add("North",contr);
		lst = new List(10);
		plst.add(lst);
		add("Center",plst);
		revalidate();
	}
	class AdSt implements ActionListener{
		public void actionPerformed(ActionEvent e ){
			Student s;
			int nt;
			String n=tf[0].getText();
			try{
				nt=Integer.parseInt(tf[1].getText());
				 if((nt>20)||(nt<0)) { 
					 tf[2].setText("Note must be between 0 and 20");
					 return; 
				 }
			}
			catch(NumberFormatException ex){
				tf[2].setText("Note must be integer");
				return;
			}
			Student help[]= new Student[prs.length+1];
			System.arraycopy(prs, 0, help, 0, prs.length);
			help[help.length-1]= s=new Student(n,nt);
			prs=help; 
			tf[0].setText("name?");
			tf[1].setText("note?");
			tf[2].setText("Student added");
			lst.add(""+s);
			revalidate();
		}
	}
	public static void main(String [] arg){
		Apl apl=new Apl(20,20,600,300);
	}
}