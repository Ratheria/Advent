/**
 *	@author Ariana Fairbanks
 */

package view;

import javax.swing.JFrame;
import controller.AdventGame;

public class AdventureFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	public AdventPanel panel;

	public AdventureFrame()
	{
		panel = new AdventPanel();
		setName("Colossal Cave Adventure");
		this.setTitle("Adventure!");
		setContentPane(panel);
		setSize(850, 750);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void setUp()
	{
		panel.setUpGame();
	}
}
