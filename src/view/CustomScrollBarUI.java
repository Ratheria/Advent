/**
 *	@author Ariana Fairbanks
 */

package view;

import java.awt.Color;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;


public class CustomScrollBarUI extends BasicScrollBarUI
{
	@Override
	public void configureScrollBarColors()
	{
		LookAndFeel.installColors(scrollbar, "ScrollBar.background", "ScrollBar.foreground");
		thumbHighlightColor = Color.GREEN;
		thumbLightShadowColor = Color.GREEN;
		thumbDarkShadowColor = Color.BLACK;
		thumbColor = Color.BLACK;
		trackColor = Color.BLACK;
		trackHighlightColor = Color.BLACK;
	}

	@Override
	public void installComponents()
	{
        if (scrollbar.getOrientation() == 1)
        {
            incrButton = createIncreaseButton(SwingConstants.SOUTH);
            decrButton = createDecreaseButton(SwingConstants.NORTH);
        }
        else if (scrollbar.getOrientation() == 0)
        {
            if (scrollbar.getComponentOrientation().isLeftToRight())
            {
                incrButton = createIncreaseButton(SwingConstants.EAST);
                decrButton = createDecreaseButton(SwingConstants.WEST);
            }
            else
            {
                incrButton = createIncreaseButton(SwingConstants.WEST);
                decrButton = createDecreaseButton(SwingConstants.EAST);
            }
        }

		incrButton.setBackground(Color.BLACK);
		incrButton.setForeground(Color.BLACK);
		incrButton.setOpaque(true);
		decrButton.setBackground(Color.BLACK);
		decrButton.setForeground(Color.BLACK);
		decrButton.setOpaque(true);
		scrollbar.add(incrButton);
		scrollbar.add(decrButton);
		scrollbar.setEnabled(scrollbar.isEnabled());
	}

}
