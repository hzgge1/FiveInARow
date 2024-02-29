package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GUI {
	
	private static JFrame frame;
	private static int size = 10;
	private static boolean changePlayer = false;
	private static Function f;
	
	
	private static void creatFrame(int size) {
		frame = new JFrame("五子棋");
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(size*51,size*50));
		paint();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private static void paint() {
		
		JPanel menu = new JPanel();
		menu.setBackground(Color.GREEN);
		menu.setLayout(new FlowLayout());
		menu.add(new JLabel("Player 1: ● Player 2 :"));
		JLabel player2 = new JLabel("●");
		player2.setForeground(Color.WHITE);
		menu.add(player2);
		menu.setSize(new Dimension(size,size));
		frame.add(menu);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(size,size));
		panel.setSize(new Dimension(size*50,size*50));
		panel.setBackground(Color.decode("#FFDE78"));
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++)
				panel.add(creatLable(i,j));
		}
		frame.add(menu,BorderLayout.NORTH);
		frame.add(panel,BorderLayout.CENTER);
	}
	
	private static JLabel creatLable(final int x,final int y) {
		final JLabel label = new JLabel(" ");
		Font font = new Font("Arial", Font.BOLD, 70);
		label.setFont(font);
		
		Border border = LineBorder.createGrayLineBorder();
        label.setBorder(border);
        
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        
		label.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	f.playWith((changePlayer?1:2), x, y);
	                if(changePlayer) {
	                	label.setForeground(Color.WHITE);
	                }else {
	                	label.setForeground(Color.BLACK);
	                }
	                label.setText("●");
	                if(f.isGameOver()) JOptionPane.showMessageDialog(frame,"Player "+(changePlayer?"2(White)":"1(Black)")+" Win !" , "Game Over", JOptionPane.INFORMATION_MESSAGE);
	                changePlayer = !changePlayer;
	                label.removeMouseListener(this);
	            }
	        });
		return label;
	}
	
	
	public static void main(String[] args) {
		f = new Function(size,size);
		creatFrame(size);
	}

}
