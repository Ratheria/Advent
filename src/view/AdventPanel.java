/**
 *	@author Ariana Fairbanks
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import controller.AdventControl;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JLabel;

public class AdventPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	private AdventControl base;
	private JTextField inputField;
	private JTextArea displayLog;
	private DefaultCaret displayCaret;
	private SpringLayout springLayout;
	private JScrollPane scroll;
	private Color outline;
	private JScrollBar scrollBar;
	private JLabel lblTurns;
	private JLabel lblScore;
	private JLabel lblTop;
	
	
	public AdventPanel(AdventControl base)
	{
		setBorder(null);
		this.base = base;
		springLayout = new SpringLayout();
		displayLog = new JTextArea();
		displayCaret = (DefaultCaret)displayLog.getCaret();
		inputField = new JTextField();
		scroll = new JScrollPane(displayLog);
		outline = new Color(0, 255, 0);
		scrollBar = scroll.getVerticalScrollBar();
		lblTurns = new JLabel("Turns: 1");
		lblScore = new JLabel("Score: 36/350");
		lblTop = new JLabel("Colossal Cave Adventure");
		
		setUpPanel();
		setUpLayout();
		setUpListeners();
		setUpGame();
	}

	private void setUpPanel() 
	{
		setLayout(springLayout);
		add(inputField);
		add(scroll);
		add(lblTurns);
		add(lblScore);
		add(lblTop);
		
		inputField.requestFocusInWindow();
		displayCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		UIManager.put("ScrollBarUI", "view.ScrollBarUI");
		scrollBar.setUI(new NewScrollBarUI());
		inputField.requestFocusInWindow();
	}

	private void setUpLayout() 
	{
		setBackground(new Color(0, 0, 0));
		displayLog.setEditable(false);
		displayLog.setWrapStyleWord(true);
		displayLog.setTabSize(4);
		displayLog.setLineWrap(true);
		displayLog.setForeground(Color.GREEN);
		displayLog.setBackground(Color.BLACK);
		displayLog.setFont(new Font("DialogInput", Font.PLAIN, 15));
		displayLog.setBorder(new EmptyBorder(5, 15, 5, 15));
		springLayout.putConstraint(SpringLayout.WEST, inputField, 25, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, scroll, 25, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scroll, -25, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblTop, 15, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblTop, 0, SpringLayout.WEST, inputField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTop, -10, SpringLayout.NORTH, scroll);
		springLayout.putConstraint(SpringLayout.EAST, lblTop, -230, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, inputField, -15, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblScore, 15, SpringLayout.EAST, lblTop);
		springLayout.putConstraint(SpringLayout.EAST, lblScore, 120, SpringLayout.EAST, lblTop);
		springLayout.putConstraint(SpringLayout.SOUTH, lblScore, -10, SpringLayout.NORTH, scroll);
		springLayout.putConstraint(SpringLayout.WEST, lblTurns, 15, SpringLayout.EAST, lblScore);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTurns, -10, SpringLayout.NORTH, scroll);
		springLayout.putConstraint(SpringLayout.EAST, lblTurns, -25, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 45, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, inputField, 15, SpringLayout.SOUTH, scroll);
		springLayout.putConstraint(SpringLayout.EAST, inputField, 0, SpringLayout.EAST, scroll);
		springLayout.putConstraint(SpringLayout.SOUTH, scroll, -60, SpringLayout.SOUTH, this);
		lblTop.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lblTop.setForeground(Color.GREEN);
		inputField.setColumns(20);
		inputField.setText("");
		inputField.setForeground(Color.GREEN);
		inputField.setBackground(Color.BLACK);
		inputField.setFont(new Font("DialogInput", Font.PLAIN, 14));
		inputField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outline),
                BorderFactory.createEmptyBorder(0, 55, 0, 0)));
		scroll.setBorder(new LineBorder(new Color(0, 255, 0)));
		lblTurns.setForeground(Color.GREEN);
		lblTurns.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblScore.setForeground(Color.GREEN);
		lblScore.setFont(new Font("Monospaced", Font.PLAIN, 12));
		inputField.setText("");
		inputField.requestFocusInWindow();
	}
	
	private void setUpGame()
	{
		displayLog.setText(" Welcome to ADVENTURE!\n"
				+ " Original development by William Crowther.\n"
				+ " Major features added by Don Woods.\n"
				+ " Conversion to Java by Ari.\n\n"
				+ " Would you like instructions? (y/n)\n");
		inputField.requestFocusInWindow();
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
					//String log = displayLog.getText();
					String origin = inputField.getText();
					String input = inputField.getText().toLowerCase();
					
					input = removeWhitespace(input);
					
					if(input.contains(" "))
					{
						int space = input.indexOf(' ');
						String first = input.substring(0, space);
						String second = input.substring(space + 1);
						displayLog.append("\n\t> " + origin 
								+ "\n\n" + base.determineAction(first, second) + "\n");
						lblTurns.setText("Turns: " + base.getTurns());
						lblScore.setText("Score: " + base.getScore() + "/350");
						
					}
					else if(input.equals("exception"))
					{
						displayLog.append("I beg your pardon?\n");
						lblTurns.setText("Turns: " + base.getTurns());
						lblScore.setText("Score: " + base.getScore() + "/350");
					}
					else
					{
						if(input.length() > 5)
						{
							input = input.substring(0, 5);
						}
						displayLog.append("\n\t> " + origin 
								+ "\n\n" + base.determineAction(input) + "\n");
						lblTurns.setText("Turns: " + base.getTurns());
						lblScore.setText("Score: " + base.getScore() + "/350");
					}
					displayCaret = (DefaultCaret)displayLog.getCaret();
					inputField.setText("");
					inputField.requestFocusInWindow();
					//scroll.setViewportView(displayLog);
				}
			}
		});
	}
	
	private String removeWhitespace(String input)
	{
		try
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
		}
		catch(IndexOutOfBoundsException e)
		{
			input = new String("exception");
		}
		return input;
	}
}
