package view;
import javax.swing.*;

@SuppressWarnings("serial")
public class DialogCria extends JFrame{
	
	public DialogCria(){
		super("Criar novo objeto");
		setSize(400,300);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String args[]){
		@SuppressWarnings("unused")
		DialogCria dialog = new DialogCria();
	}
}
