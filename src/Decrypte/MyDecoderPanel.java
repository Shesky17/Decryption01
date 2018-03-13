package Decrypte;
	
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.Base64;

public class MyDecoderPanel extends JPanel
{
	/**
	 * @author Shesky
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> alphabet = new ArrayList<String>();
	private ArrayList<String> shiftedAlphabet = new ArrayList<String>();
	private String alphabetStr = new String("abcdefghijklmnopqrstuvwxyz ");
	private String shiftedAlphabetStr = new String("`~!@#$%^&*()_-+=|,<.>/?123 ");//("xyzabcdefghijklmnopqrstuvw*");
	private String emptySpace = new String(" ");
	
	private ArrayList<String> deShiftList = new ArrayList<String>();
	private ArrayList<String> deshiftedOutput = new ArrayList<String>();
	
	private ArrayList<String> nullRemovedList = new ArrayList<String>();
	
	private ArrayList<String> inOrder = new ArrayList<String>();
	//private ArrayList<String> reverseOrder = new ArrayList<String>();
	
	private ArrayList<String> encryptedCode = new ArrayList<String>();
	
	private ArrayList<String> finalOutput = new ArrayList<String>();
	
	private byte[] base64Decode;
	private ArrayList<String> temp2 = new ArrayList<String>();
	
	JButton decrypteBtn;
	JTextArea inputArea;
	JLabel inputLabel;
	JLabel outputLabel; 
	JLabel decrypteMessageLabel;
	
	public MyDecoderPanel()
	{
		inputLabel = new JLabel("Input: ");
	    inputLabel.setFont(new Font("Serif", Font.BOLD, 27));
	    this.add(inputLabel);
	    
	    inputArea = new JTextArea(1,10);
	    this.add(inputArea);
	    
	    decrypteBtn = new JButton("Decrypte");
	    this.add(decrypteBtn);
	    
	    //encryptedMessageLabel
	    decrypteMessageLabel = new JLabel("Decoded msg: ");
	    this.add(decrypteMessageLabel);
	    decrypteMessageLabel.setFont(new Font("Serif", Font.BOLD, 27));
	    
	    //outputLabel
	    outputLabel = new JLabel();
	    this.add(outputLabel);
	    outputLabel.setFont(new Font("Serif", Font.BOLD, 27));
	    
	    decrypteBtn.addActionListener(new decrypteListener());
	}
	  
	public String decrypte(String str)
	{
		//add the string to arraylist
		for(int i=0; i<str.length(); i++)
		{
			encryptedCode.add(str.substring(i, i+1));
		}
		
		/*
		//decode base64 
		String temp = "";
		for(String letter:encryptedCode)
		{
			temp+=letter;
		}
		base64Decode = Base64.getDecoder().decode(temp);
		*/
		//make the string in the arraylist into a string
		String temp = "";
		for(String letter:encryptedCode)
		{
			temp+=letter;
		}
		
		//convert decode to string 
		//add it to arraylist
		String tempStr = new String(temp);//(base64Decode);
		for(int i=0; i<tempStr.length();i++)
		{
			temp2.add(tempStr.substring(i, i+1));
		}
		
		//remove null letters in the arrayList(first 3 letters)
		//this method add the removedNull arr to nullRemovedList
		removeNull(temp2);

		//deshift the code
		//add them to deshiftedOutput arraylist
		for(int i=0; i<nullRemovedList.size(); i++)
		{
	  		deshiftedOutput.add(deshift(nullRemovedList).get(i));
	  	}
		
		//reverse the deshited array
		//add them to inOrder arrayList
		for(int i=0; i < deshiftedOutput.size(); i++)
		{
			inOrder.add(deshiftedOutput.get(i));
		}
		
		//return fianlOutput 
	  	for(int i= inOrder.size()-1; i>=0; i--)
	  	{
	  		finalOutput.add(inOrder.get(i));
	  	}
	  	
	  	//return in string
	  	String tempFinal = "";
	  	for(String letter : finalOutput)
	  	{
	  		tempFinal+=letter;
	  	}
	  	return tempFinal;
	}
	  
	//eliminate the null letters
	//1 at the front of the encrypted code
	//2 at the the back
	public void removeNull(ArrayList<String> list)
	{
		for(int i=1; i<list.size()-2; i++)
		{
			nullRemovedList.add(list.get(i));	
		}
	}
	  
	//unshift the encrypted code
	public ArrayList<String> deshift(ArrayList<String> list)
	{
		//add 26 letters to alphabet
		for(int i=0; i< 26; i++)
		{
			alphabet.add(alphabetStr.substring(i, i+1));
			shiftedAlphabet.add(shiftedAlphabetStr.substring(i, i+1));
		}
	    
		for(int i=0; i< list.size(); i++)
		{
			for(int j=0; j<alphabet.size(); j++)
			{
				if(list.get(i).compareTo(shiftedAlphabet.get(j))==0)
				{
					deShiftList.add(alphabet.get(j));
				}
				//try to find a way to solve space problem
				if(list.get(i).equals("*"))
				{
					deShiftList.add(emptySpace);
				}
			}
		}
		return deShiftList;
	}
	
	/*
	 * Actionlistener
	 */
	  private class decrypteListener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
		  {
			  outputLabel.setText(decrypte(inputArea.getText()));
			  outputLabel.setFont(new Font("Serif", Font.BOLD, 27));
		  }
	  }
}
