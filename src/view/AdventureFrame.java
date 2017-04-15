/**
 *	@author Ariana Fairbanks
 */

package view;

import javax.swing.JFrame;
import controller.AdventControl;

public class AdventureFrame extends JFrame 
{
	private AdventControl base;
	private AdventPanel panel;

	public AdventureFrame(AdventControl base)
	{
		this.base = base;
		panel = new AdventPanel(base);
		setName("Colossal Cave Adventure");
		this.setTitle("Adventure!");
		setContentPane(panel);
		setSize(750, 700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
