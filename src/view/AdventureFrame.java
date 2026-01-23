/**
 *	@author Ariana Fairbanks
 */

package view;

import javax.swing.*;
import java.awt.*;

public class AdventureFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;

	private final AdventPanel panel;

	public AdventureFrame()
	{
		panel = new AdventPanel();
		setName("Colossal Cave Adventure");
		setTitle("Adventure!");
		setContentPane(panel);
		setSize(850, 750);
		setMinimumSize(new Dimension(550, 400));
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void setUp()
	{
		panel.setUpGame();
	}
}
