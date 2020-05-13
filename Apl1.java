import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Apl1 extends Apl{
	protected JButton load,save;
	Apl1(){
		super(20,20,600,250);
		load = new JButton("load");
		load.addActionListener(new Load());
		contr.add(load);
		save = new JButton("save");
		save.addActionListener(new Save());
		contr.add(save);
		setTitle("Student's directory - version 2");
		revalidate();
	}
	public static void main(String arg[]){
		new Apl1();
	}
	class Save implements ActionListener  {
		public void actionPerformed(ActionEvent e ){
			ObjectOutputStream oos = null;
			try{
				oos = new ObjectOutputStream (new FileOutputStream ("save.ser"));
				oos.writeObject(prs);
			}
			catch (IOException ex){
				System.out.println(ex);
			}
			try{
				if(oos!=null)oos.close();
			}
			catch (IOException ex){}
		}
	}
	class Load implements ActionListener  {
		public void actionPerformed(ActionEvent e ){
			ObjectInputStream ios = null;
			try{
				ios = new ObjectInputStream (new FileInputStream ("save.ser"));
				prs= (Student[])ios.readObject();
			}
			catch (Exception ex){
				tf[2].setText("No file to load");
			}
			try{
				if(ios!=null)ios.close();
			}
			catch (IOException ex){}
			lst. removeAll();
			for(int i=0;i<prs.length;i++){
				lst.add(""+prs[i]);
			}           
		}       
	}
}