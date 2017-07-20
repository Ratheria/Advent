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
import javax.swing.text.DefaultCaret;
import javax.swing.JLabel;

public class AdventPanel extends JPanel 
{
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
	
	
	public AdventPanel(AdventControl base)
	{
		setBorder(null);
		this.base = base;
		springLayout = new SpringLayout();
		displayLog = new JTextArea();
		displayCaret = (DefaultCaret)displayLog.getCaret();
		inputField = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, inputField, -15, SpringLayout.SOUTH, this);
		scroll = new JScrollPane(displayLog);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 45, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, inputField, 15, SpringLayout.SOUTH, scroll);
		springLayout.putConstraint(SpringLayout.EAST, inputField, 0, SpringLayout.EAST, scroll);
		springLayout.putConstraint(SpringLayout.SOUTH, scroll, -60, SpringLayout.SOUTH, this);
		outline = new Color(0, 255, 0);
		scrollBar = scroll.getVerticalScrollBar();
		lblTurns = new JLabel("Turns: 0");
		springLayout.putConstraint(SpringLayout.SOUTH, lblTurns, -10, SpringLayout.NORTH, scroll);
		springLayout.putConstraint(SpringLayout.EAST, lblTurns, -25, SpringLayout.EAST, this);
		lblScore = new JLabel("Score: 36/350");
		springLayout.putConstraint(SpringLayout.SOUTH, lblScore, -10, SpringLayout.NORTH, scroll);
		springLayout.putConstraint(SpringLayout.WEST, lblTurns, 15, SpringLayout.EAST, lblScore);
		
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
		
		JLabel lblWelcomeToAdventure = new JLabel("Colossal Cave Adventure");
		springLayout.putConstraint(SpringLayout.NORTH, lblWelcomeToAdventure, 15, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblWelcomeToAdventure, 0, SpringLayout.WEST, inputField);
		springLayout.putConstraint(SpringLayout.SOUTH, lblWelcomeToAdventure, -10, SpringLayout.NORTH, scroll);
		springLayout.putConstraint(SpringLayout.EAST, lblWelcomeToAdventure, -230, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, lblScore, 15, SpringLayout.EAST, lblWelcomeToAdventure);
		springLayout.putConstraint(SpringLayout.EAST, lblScore, 120, SpringLayout.EAST, lblWelcomeToAdventure);
		lblWelcomeToAdventure.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lblWelcomeToAdventure.setForeground(Color.GREEN);
		add(lblWelcomeToAdventure);
		inputField.requestFocusInWindow();
		displayCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		UIManager.put("ScrollBarUI", "view.ScrollBarUI");
		scrollBar.setUI(new NewScrollBarUI());
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
	}
	
	private void setUpGame()
	{
		displayLog.setText(" Welcome to ADVENTURE!\n"
				+ " Original development by William Crowther.\n"
				+ " Major features added by Don Woods.\n"
				+ " Conversion to Java by Ari.\n\n"
				+ " Would you like instructions? (y/n)\n");
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
						displayLog.append("\n\t> " + origin 
								+ "\n\n" + base.determineAction(first, second) + "\n");
						lblTurns.setText("Turns: " + base.getTurns());
						lblScore.setText("Score: " + base.getScore());
						
					}
					else if(input.equals("exception"))
					{
						displayLog.append("I beg your pardon?\n");
						lblTurns.setText("Turns: " + base.getTurns());
						lblScore.setText("Score: " + base.getScore());
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
						lblScore.setText("Score: " + base.getScore());
					}
					inputField.setText("");
					inputField.requestFocusInWindow();
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
