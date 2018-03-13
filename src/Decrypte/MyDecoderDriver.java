package Decrypte;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyDecoderDriver
{
	public MyDecoderDriver() 
	{
		//frame
		JFrame frame = new JFrame("Decoder V1.2");
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    //panel
	    JPanel panel = new MyDecoderPanel();
	    panel.setSize(400,300);
	       
	    //unresizable
	    frame.setResizable(false);
	    
	    //add panel
	    frame.add(panel);
	    
	    //display
	    frame.setVisible(true);
	}

	public static void main(String[] args)
	{
		new MyDecoderDriver();
	}
}
