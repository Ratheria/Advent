/**
 *	@author Ariana Fairbanks
 */

package view;

import javax.swing.JFrame;
import controller.AdventControl;

public class AdventureFrame extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private AdventPanel panel;

	public AdventureFrame()
	{
		panel = new AdventPanel();
		setName("Colossal Cave Adventure");
		this.setTitle("Adventure!");
		setContentPane(panel);
		setSize(750, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public void setUp()
	{
		panel.setUpGame();
	}
	
}
