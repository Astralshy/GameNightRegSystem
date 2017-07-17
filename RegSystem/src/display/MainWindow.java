package display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		
		JComboBox comboBox = new JComboBox();
		AutoCompleteSupport.install(comboBox, GlazedLists.eventListOf(handler.names.toArray()));
		comboBox.setEditable(true);
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
		
		contentPane.setFocusable(true);
		contentPane.addKeyListener(kl);
		this.addKeyListener(kl);
		
	}
}
