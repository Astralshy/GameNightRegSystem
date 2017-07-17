package display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;

public class ConfirmExit extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JFrame instance;
	private final JDialog windowInstance = this;
	/**
	 * Create the dialog.
	 */
	public ConfirmExit(JFrame instance) {
		
		this.instance = instance;
		setType(Type.POPUP);
		setBounds(100, 100, 386, 148);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblConfirmExit = new JLabel("Confirm Exit?");
			lblConfirmExit.setFont(new Font("Liberation Sans", Font.PLAIN, 40));
			lblConfirmExit.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblConfirmExit);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				buttonPane.add(okButton);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
				okButton.addMouseListener(new MouseListener(){
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						instance.dispose();
						windowInstance.dispose();
					}});
				okButton.addKeyListener(new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						if(e.getKeyCode() == KeyEvent.VK_ENTER){
							instance.dispose();
							windowInstance.dispose();
						}
						else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
							 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
							    manager.focusNextComponent();
						}
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.setActionCommand("Cancel"); 	
				cancelButton.addMouseListener(new MouseListener(){
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
					@Override
					public void mouseClicked(MouseEvent e) {
						windowInstance.dispose();
					}});
				cancelButton.addKeyListener(new KeyListener(){

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						if(e.getKeyCode() == KeyEvent.VK_ENTER){
							windowInstance.dispose();
						}
						else if (e.getKeyCode() == KeyEvent.VK_LEFT){
							 KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
							    manager.focusPreviousComponent();;
						}
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					
				});
			}
		}
	}

}
