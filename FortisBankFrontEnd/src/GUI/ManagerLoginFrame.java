package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;

import bus.Manager;
import data.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerLoginFrame {
	
	private Connection connection;

	private JFrame frame;
	private JTextField textFieldManagerID;
	private int manID;
	private Manager manager;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerLoginFrame window = new ManagerLoginFrame();
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
	public ManagerLoginFrame() {
		initialize();
		connection = DBConnection.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 466, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAuthManager = new JLabel("Manager Authentication");
		lblAuthManager.setFont(new Font("Source Serif Pro Light", Font.BOLD, 20));
		lblAuthManager.setBackground(new Color(240, 240, 240));
		lblAuthManager.setForeground(new Color(70, 130, 180));
		lblAuthManager.setBounds(108, 31, 268, 28);
		frame.getContentPane().add(lblAuthManager);
		
		JLabel lblManID = new JLabel("Manager ID :");
		lblManID.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		lblManID.setBounds(48, 105, 94, 28);
		frame.getContentPane().add(lblManID);
		
		textFieldManagerID = new JTextField();
		textFieldManagerID.setBounds(152, 105, 146, 25);
		frame.getContentPane().add(textFieldManagerID);
		textFieldManagerID.setColumns(10);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					manID = Integer.parseInt(textFieldManagerID.getText());
					manager = Manager.search(manID);
					
					if(manager != null) {
						if(manager.getManagerId() == manID) {
							JOptionPane.showInternalMessageDialog(null, "Successful!");
							ManagerFrame.main(null);
						}else {
							JOptionPane.showInternalMessageDialog(null, "Try again!");
						}
					}
					
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBackground(new Color(70, 130, 180));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(219, 195, 79, 33);
		frame.getContentPane().add(btnNewButton);
	}
}
