package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame window = new HomeFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HomeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Calibri", Font.BOLD, 25));
		frame.setBackground(new Color(128, 128, 128));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to FORTIS BANK");
		lblWelcome.setForeground(new Color(70, 130, 180));
		lblWelcome.setFont(new Font("Source Sans Pro Light", Font.BOLD, 25));
		lblWelcome.setBounds(105, 49, 301, 32);
		frame.getContentPane().add(lblWelcome);
		
		JButton btnCustomer = new JButton("I am a CUSTOMER");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerLoginFrame.main(null);
				
			}
		});
		btnCustomer.setForeground(new Color(65, 105, 225));
		btnCustomer.setBackground(new Color(255, 255, 0));
		btnCustomer.setBounds(155, 133, 164, 32);
		frame.getContentPane().add(btnCustomer);
		
		JButton btnManager = new JButton("I am a MANAGER");
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerLoginFrame.main(null);
			}
		});
		btnManager.setForeground(new Color(65, 105, 225));
		btnManager.setBackground(Color.YELLOW);
		btnManager.setBounds(155, 192, 164, 32);
		frame.getContentPane().add(btnManager);
		frame.setBounds(100, 100, 493, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
