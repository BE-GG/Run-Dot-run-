package GameControl;
import java.awt.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;

import javax.swing.JPanel;

public class PausePanel extends JPanel{
	
	private JButton button;
	
	public PausePanel(){
		setPreferredSize(new Dimension(500,500));
		setLayout(new FlowLayout());
		button = new JButton("Pause Game");
		button.setBounds(300, 300, 100, 100);
		add(button);
	}
	
}
