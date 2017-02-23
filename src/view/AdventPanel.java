/**
 *	@author Ariana Fairbanks
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import controller.AdventControl;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.Caret;

public class AdventPanel extends JPanel 
{
	private AdventControl base;
	private JTextField inputField;
	private JTextArea displayLog;
	private SpringLayout springLayout;
	private JScrollPane scroll;
	private Color outline;
	private JScrollBar scrollBar;
	
	
	public AdventPanel(AdventControl base)
	{
		setBorder(null);
		this.base = base;
		this.springLayout = new SpringLayout();
		this.displayLog = new JTextArea();
		displayLog.setWrapStyleWord(true);
		displayLog.setTabSize(4);
		displayLog.setLineWrap(true);
		this.inputField = new JTextField();
		this.scroll = new JScrollPane(displayLog);
		this.outline = new Color(0, 255, 0);
		this.scrollBar = scroll.getVerticalScrollBar();
		
		
		setUpPanel();
		setUpLayout();
		setUpListeners();
		setUpGame();
	}

	private void setUpPanel() 
	{
		this.setLayout(springLayout);
		this.add(inputField);
		this.inputField.setColumns(20);
		inputField.setText("");
		this.add(scroll);
		displayLog.setEditable(false);
		inputField.requestFocusInWindow();
		UIManager.put("ScrollBarUI", "view.ScrollBarUI");
		scrollBar.setUI(new NewScrollBarUI());
	}

	private void setUpLayout() 
	{
		setBackground(new Color(0, 0, 0));
		inputField.setForeground(Color.GREEN);
		inputField.setBackground(Color.BLACK);
		displayLog.setForeground(Color.GREEN);
		displayLog.setBackground(Color.BLACK);
		inputField.setFont(new Font("DialogInput", Font.PLAIN, 14));
		displayLog.setFont(new Font("DialogInput", Font.PLAIN, 15));
		springLayout.putConstraint(SpringLayout.NORTH, inputField, 20, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, inputField, 0, SpringLayout.WEST, scroll);
		springLayout.putConstraint(SpringLayout.EAST, inputField, 0, SpringLayout.EAST, scroll);
		springLayout.putConstraint(SpringLayout.SOUTH, inputField, -15, SpringLayout.NORTH, scroll);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 70, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scroll, -25, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scroll, 25, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scroll, -25, SpringLayout.EAST, this);
		displayLog.setBorder(new EmptyBorder(5, 15, 5, 15));
		inputField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outline),
                BorderFactory.createEmptyBorder(0, 55, 0, 0)));
		scroll.setBorder(new LineBorder(new Color(0, 255, 0)));
	}
	
	private void setUpGame()
	{
		displayLog.setText(" Welcome to ADVENTURE!\n"
				+ " Original development by William Crowther\n"
				+ " Major features added by Don Woods\n"
				+ " Conversion to Java by A. F.\n\n"
				+ " Would you like instructions? (y/n)");
	}
	
	private void setUpListeners() 
	{
		inputField.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(inputField.getText().length() > 0)
				{
					String log = displayLog.getText();
					String origin = inputField.getText();
					String input = inputField.getText().toLowerCase();
					
					input = removeWhitespace(input);
					
					if(input.contains(" "))
					{
						int space = input.indexOf(' ');
						String first = input.substring(0, space);
						String second = input.substring(space + 1);
						displayLog.setText("\n\t> " + origin 
								+ "\n\n" + base.determineAction(first, second));
						
					}
					else
					{
						if(input.length() > 5)
						{
							input = input.substring(0, 5);
						}
						displayLog.setText("\n\t> " + origin 
								+ "\n\n" + base.determineAction(input));
					}

					inputField.setText("");
					inputField.requestFocusInWindow();
					scroll.setViewportView(displayLog);
				}
				
			}

		});
	}
	
	private String removeWhitespace(String input)
	{
		while(input.charAt(0)== ' ' && input.length() > 0)
		{
			input = input.substring(1);
			System.out.println(input);
		}
		int lastIndex = input.length() - 1;
		while(input.charAt(lastIndex)== ' ' && input.length() > 0)
		{
			input = input.substring(0, lastIndex);
			lastIndex--;
			System.out.println(input);
		}
		return input;
	}
	
	
}
