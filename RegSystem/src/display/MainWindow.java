package display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import loader.ExcelHandler;

import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private final JFrame windowInstance = this;
	JComboBox comboBox;
	ExcelHandler handler;
	
	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	private KeyListener kl = new KeyListener(){
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				ConfirmExit dialog = new ConfirmExit(windowInstance);
				int x = (int) ((dimension.getWidth() - dialog.getWidth()) / 2);
				int y = (int) ((dimension.getHeight() - dialog.getHeight()) / 2);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setResizable(false);
				dialog.setLocation(x, y);
				dialog.setVisible(true);
			}
		}
	};
	
	private KeyListener enter = new KeyListener(){

		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				ConfirmExit dialog = new ConfirmExit(windowInstance);
				int x = (int) ((dimension.getWidth() - dialog.getWidth()) / 2);
				int y = (int) ((dimension.getHeight() - dialog.getHeight()) / 2);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setResizable(false);
				dialog.setLocation(x, y);
				dialog.setVisible(true);
			}
			else if(e.getKeyCode() == KeyEvent.VK_ENTER){
				if(comboBox.getSelectedItem() != null){
					handler.register(comboBox.getSelectedItem().toString());
					comboBox.setSelectedIndex(0);
				}	
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		handler = new ExcelHandler();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - 440) / 2);
		int y = (int) ((dimension.getHeight() - 30) / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(x, y, 440, 30);
		contentPane.add(panel);
		panel.addKeyListener(kl);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		comboBox = new JComboBox();
		AutoCompleteSupport.install(comboBox, GlazedLists.eventListOf(handler.names.toArray()));
		comboBox.setEditable(true);
		comboBox.addKeyListener(enter);
		panel_1.add(comboBox, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.addKeyListener(kl);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.addKeyListener(kl);
		panel_2.add(panel_3);
		
		JButton btnEnter = new JButton("Enter");
		panel_2.add(btnEnter);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		btnEnter.addKeyListener(enter);
		btnEnter.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				if(comboBox.getSelectedItem() != null){
					handler.register(comboBox.getSelectedItem().toString());
					comboBox.setSelectedItem(null);
				}	
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		contentPane.setFocusable(true);
		contentPane.addKeyListener(kl);
		this.addKeyListener(kl);
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				handler.exportData();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
