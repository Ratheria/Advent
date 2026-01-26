/**
 *	@author Ariana Fairbanks
 */

package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import controller.AdventMain;
import state.*;
import version.Version;

import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JLabel;
import javax.swing.JButton;

public class AdventPanel extends JPanel 
{
	@Serial private static final long serialVersionUID = 1L;

	private SpringLayout springLayout = new SpringLayout();
	private JTextArea    displayLog   = new JTextArea();
	private DefaultCaret displayCaret = (DefaultCaret) displayLog.getCaret();
	private JScrollPane  scroll       = new JScrollPane(displayLog);
	private JScrollBar   scrollBar    = scroll.getVerticalScrollBar();
	private JTextField   inputField   = new JTextField();
	private JLabel       lblTurns     = new JLabel(),
			             lblScore     = new JLabel(),
			             lblTop       = new JLabel("Colossal Cave Adventure");
	private JButton      saveButton   = new JButton(" Save "),
			             loadButton   = new JButton(" Load ");

	// private Color outline      = new Color(22, 222, 22);
	// private Color consoleGreen = new Color(110, 245, 95);

	private Color outline      = new Color(30, 200, 30);
	private Color consoleGreen = new Color(100, 225, 90);

	// private Color outline      = new Color(22, 222, 22);
	// private Color consoleGreen = new Color(110, 245, 95);
	
	public AdventPanel()
	{
		setBorder(null);
		setUpPanel();
		setUpLayout();
		setUpListeners();
	}

	private void setUpPanel() 
	{
		setLayout(springLayout);
		add(inputField);
		add(scroll);
		add(lblTurns);
//		add(lblScore);
		add(lblTop);
		add(saveButton);
		add(loadButton);
		
		inputField.requestFocusInWindow();
		displayCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		UIManager.put("ScrollBarUI", "view.ScrollBarUI");
		scrollBar.setUI(new CustomScrollBarUI());
		inputField.requestFocusInWindow();
	}

	private void setUpLayout() 
	{
		setBackground(Color.black);
		displayLog.setEditable(false);
		displayLog.setWrapStyleWord(true);
		displayLog.setTabSize(4);
		displayLog.setLineWrap(true);
		inputField.setColumns(20);
		inputField.setText("");
		saveButton.setFocusPainted(false);
		saveButton.setContentAreaFilled(false);
		loadButton.setFocusPainted(false);
		loadButton.setContentAreaFilled(false);
		lblTop.setForeground(consoleGreen);
		lblTurns.setForeground(consoleGreen);
		lblScore.setForeground(consoleGreen);
		displayLog.setForeground(consoleGreen);
		displayLog.setBackground(Color.BLACK);
		saveButton.setBackground(Color.BLACK);
		saveButton.setForeground(consoleGreen);
		loadButton.setBackground(Color.BLACK);
		loadButton.setForeground(consoleGreen);
		inputField.setForeground(consoleGreen);
		inputField.setBackground(Color.BLACK);
		lblTop.setFont(new Font("Monospaced", Font.PLAIN, 20));
		lblTurns.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblScore.setFont(new Font("Monospaced", Font.PLAIN, 12));
		saveButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
		loadButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
		displayLog.setFont(new Font("DialogInput", Font.PLAIN, 15));
		inputField.setFont(new Font("DialogInput", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.WEST, scroll, 25, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scroll, -25, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblTop, 15, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTop, -10, SpringLayout.NORTH, scroll);
		springLayout.putConstraint(SpringLayout.WEST, lblTop, 25, SpringLayout.WEST, this);
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
		springLayout.putConstraint(SpringLayout.NORTH, saveButton, 15, SpringLayout.SOUTH, scroll);
		springLayout.putConstraint(SpringLayout.SOUTH, saveButton, -15, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, saveButton, 25, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, inputField, 10, SpringLayout.EAST, loadButton);
		springLayout.putConstraint(SpringLayout.NORTH, loadButton, 15, SpringLayout.SOUTH, scroll);
		springLayout.putConstraint(SpringLayout.SOUTH, loadButton, -15, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, loadButton, 10, SpringLayout.EAST, saveButton);
		displayLog.setBorder(new EmptyBorder(5, 15, 5, 15));
		scroll.setBorder(new LineBorder(consoleGreen));
		saveButton.setBorder(BorderFactory.createLineBorder(consoleGreen));
		loadButton.setBorder(BorderFactory.createLineBorder(consoleGreen));
		inputField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outline),
                BorderFactory.createEmptyBorder(0, 55, 0, 0)));
		inputField.requestFocusInWindow();
	}
	
	public void setUpGame()
	{
		setLabels();

		String displayString = "\n Welcome to ADVENTURE!\n"
				+ " Original development by William Crowther.\n"
				+ " Major features added by Don Woods.\n"
				+ " Java version by Ari.\n\n";

		displayString += Version.versionCheck();

		displayString += " Would you like instructions? (y/n)\n";

		displayLog.setText(displayString);
		inputField.requestFocusInWindow();
	}
	
	private void setLabels()
	{
		lblTurns.setText("Turns: " + AdventMain.advent.getTurns() );
		lblScore.setText("Score: " + AdventMain.advent.getScore() + "/350");
	}
	
	private void setUpListeners() 
	{
		saveButton.addActionListener
		(
			event ->
			{
				displayLog.append("\n\n" + GameStateHandler.writeData(displayLog.getText()) + "\n");
				displayLog.setCaretPosition(displayLog.getDocument().getLength());
				scroll.setViewportView(displayLog);
			}
		);
		
		loadButton.addActionListener
		(
			event ->
			{
				displayLog.setText(GameStateHandler.loadGame(displayLog.getText()));
				displayLog.setCaretPosition(displayLog.getDocument().getLength());
				setLabels();
				scroll.setViewportView(displayLog);
			}
		);
		
		inputField.addActionListener
		(
			event ->
			{
				if (AdventMain.advent.isOver())
				{
					AdventMain.newGame();
					setUpGame();
				}
				else
				{
					String inputText = inputField.getText().trim();
					if (!inputText.isEmpty())
					{
						//String log = displayLog.getText();
						String origin = "" + inputText;
						String input = "" + inputText.toLowerCase();
						if(input.contains("exception"))
						{ displayLog.append("\n\nI beg your pardon?\n"); }
						else if(input.contains(" "))
						{
							int space = input.indexOf(' ');
							String first = input.substring(0, space).trim();
							String second = input.substring(space + 1).trim();
							displayLog.append("\n\t> " + origin + "\n\n" + AdventMain.advent.determineAndExecuteCommand(first, second) + "\n");
						}
						else
						{ displayLog.append("\n\t> " + origin + "\n\n" + AdventMain.advent.determineAndExecuteCommand(input) + "\n"); }
						setLabels();
					}
				}
				displayCaret = (DefaultCaret)displayLog.getCaret();
				inputField.setText("");
				inputField.requestFocusInWindow();
				displayLog.setCaretPosition(displayLog.getDocument().getLength());
				scroll.setViewportView(displayLog);
			}
		);
	}
	
}
