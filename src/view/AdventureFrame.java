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
		this.panel = new AdventPanel(base);
		this.setContentPane(panel);
		this.setSize(750, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
