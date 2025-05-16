package minesweeper;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
	public static void startscreen() {
		JFrame startscreen = new JFrame("minesweeper");
		JPanel startpanel = new JPanel();
		JLabel startlabel = new JLabel("minesweeper",JLabel.CENTER);
		JTextField gridsize = new JTextField("grid size");
		JTextField amountbombs = new JTextField("bombs");
		JButton finalize = new JButton("start");
		
		startscreen.setSize(300,200);
		startscreen.setLocationRelativeTo(null);
		startscreen.setResizable(false);
		startscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		startlabel.setFont(new Font("Arial", Font.CENTER_BASELINE,10));
		startlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		startlabel.setText("minesweeper");
		startlabel.setOpaque(true);
		
		startpanel.setLayout(new BoxLayout(startpanel, BoxLayout.Y_AXIS));
		gridsize.setMaximumSize(new Dimension(Integer.MAX_VALUE, gridsize.getPreferredSize().height));
	    amountbombs.setMaximumSize(new Dimension(Integer.MAX_VALUE, amountbombs.getPreferredSize().height));
	    finalize.setAlignmentX(Component.CENTER_ALIGNMENT);
	    finalize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				int size = Integer.parseInt(gridsize.getText());
				int bombs = Integer.parseInt(amountbombs.getText());
				startscreen.dispose();
				GameInterface minesweeper = new GameInterface(size,size,bombs);
				
			}
	    	
	    });

	    startpanel.add(startlabel);
        startpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        startpanel.add(gridsize);
        startpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        startpanel.add(amountbombs);
        startpanel.add(Box.createRigidArea(new Dimension(0, 10)));
        startpanel.add(finalize);
		
		startscreen.add(startpanel);
		startscreen.setVisible(true);
		
		
		
		
	}
	public static void main(String[] args) {
		
		startscreen();
		
	
	}

}