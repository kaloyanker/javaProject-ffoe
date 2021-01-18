package reallifetaskmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
	
	protected JButton ad1, ad2;
	protected JTextField tf[]= new JTextField[5];
	protected List lstTask, lstGroceries;
	protected JPanel panelTask, panelGroceries;
	protected JScrollPane scrollableListGroceries, scrollableListTask;
	protected Tasks prs[]=new Tasks[0];
	protected Groceries prs1[]=new Groceries[0];
	protected AddTask addTask;
	protected AddGrocery addGrocery;

	Window(int x, int y, int ln, int ht){

		this.setLayout(new GridLayout());
		this.setBounds(x, y, ln, ht);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Task manager - real life");
		this.setResizable(false);
		panelGroceries = new JPanel(new FlowLayout());
		panelTask = new JPanel(new FlowLayout());
		ad1 = new JButton("Add");
		ad2 = new JButton("Add");
		tf[0]= new JTextField("",10);
		tf[1]= new JTextField("Things to buy", 10);
		tf[1].setHorizontalAlignment(JLabel.CENTER);
		tf[1].setEditable(false);
		tf[2]= new JTextField("",10);
		tf[3]= new JTextField("Things to do", 10);
		tf[3].setHorizontalAlignment(JLabel.CENTER);
		tf[3].setEditable(false);
		tf[4]= new JTextField("",10);
		lstGroceries = new List(15);
		scrollableListGroceries = new JScrollPane(lstGroceries);
		lstTask = new List(15);
		scrollableListTask = new JScrollPane(lstTask);
		
		//Groceries Panel
		
		panelGroceries.add(tf[1]);
		panelGroceries.add(tf[0]);
		panelGroceries.add(ad2);
		panelGroceries.add(lstGroceries);
		ad2.addActionListener(addGrocery=new AddGrocery());
		//ad.addActionListener(adSt=new AdSt());
		add("West", panelGroceries);
		
		//TO DO list Panel
		
		panelTask.add(tf[3]);
		panelTask.add(tf[2]);
		panelTask.add(tf[4]);
		panelTask.add(ad1);
		panelTask.add(lstTask);
		ad1.addActionListener(addTask=new AddTask());
		add("East", panelTask);
		revalidate();
	}

	
	class AddTask implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		Tasks t;
		int priority;
		String n=tf[2].getText();
		try {
			priority=Integer.parseInt(tf[4].getText());
			if((priority>10)||(priority<0)) {
				tf[3].setText("Priority must be between 0 and 10");
				 return; 
			}	
		 }
		
		catch(NumberFormatException ex){
			tf[3].setText("Priority between 0 and 10");
			return;
		}
		
		Tasks help[]= new Tasks[prs.length+1];
		System.arraycopy(prs, 0, help, 0, prs.length);
		help[help.length-1]= t =new Tasks(n,priority);
		prs=help;
		tf[3].setText("Task added");
		lstTask.add(""+t);
		revalidate();
		}
	}
	
	class AddGrocery implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		Groceries g;
		String s=tf[0].getText();		
		Groceries help1[]= new Groceries[prs.length+1];
		System.arraycopy(prs, 0, help1, 0, prs.length);
		help1[help1.length-1]= g =new Groceries(s);
		prs1=help1;
		tf[1].setText("Grocery added");
		lstGroceries.add(""+g);
		revalidate();
		}
	}
	
}
