package display;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Window.Type;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import loader.ExcelHandler;

public class RegisterDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private final JDialog windowInstance = this;
	/**
	 * Create the dialog.
	 */
	public RegisterDialog(ExcelHandler handler, String name) {
		setType(Type.POPUP);
		setBounds(100, 100, 386, 148);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			String text = handler.getNicknameForName(name);
			if(text.equals("")){
				text = name;
			}
			JLabel lblConfirmExit = new JLabel("Welcome, "+text+"!");
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
							windowInstance.dispose();
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
