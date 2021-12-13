package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JTextField;

import bus.Customer;
import data.DBConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerLoginFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Connection connection;

	private JFrame frame;
	private JTextField textFieldCustID;
	private JTextField textFieldPin;
	private int custID;
	private int custPin;
	private Customer cust;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerLoginFrame window = new CustomerLoginFrame();
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
	public CustomerLoginFrame() {
		initialize();
		connection = DBConnection.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 456, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAuth = new JLabel("   Customer Auththentication");
		lblAuth.setFont(new Font("Source Sans Pro Light", Font.BOLD, 20));
		lblAuth.setForeground(new Color(70, 130, 180));
		lblAuth.setBounds(84, 34, 277, 31);
		frame.getContentPane().add(lblAuth);
		
		JLabel lblCustID = new JLabel("Customer ID :");
		lblCustID.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		lblCustID.setBounds(54, 135, 95, 24);
		frame.getContentPane().add(lblCustID);
		
		JLabel lblPin = new JLabel("Pin :");
		lblPin.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		lblPin.setBounds(54, 185, 69, 18);
		frame.getContentPane().add(lblPin);
		
		textFieldCustID = new JTextField();
		textFieldCustID.setBounds(159, 134, 173, 24);
		frame.getContentPane().add(textFieldCustID);
		textFieldCustID.setColumns(10);
		
		textFieldPin = new JTextField();
		textFieldPin.setColumns(10);
		textFieldPin.setBounds(159, 185, 173, 24);
		frame.getContentPane().add(textFieldPin);
		
		JButton btnAuthCust = new JButton("OK");
		btnAuthCust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					custID = Integer.parseInt(textFieldCustID.getText());
					custPin = Integer.parseInt(textFieldPin.getText());
					cust = Customer.search(custID);
					
					if(cust != null) {
						if(cust.getCustomerID() == custID && cust.getPin() == custPin) {
							JOptionPane.showInternalMessageDialog(null, "Successful!");
							CustomerFrame.main(null);
						}else {
							JOptionPane.showInternalMessageDialog(null, "Try again!");
						}
					}
					
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAuthCust.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAuthCust.setBackground(new Color(70, 130, 180));
		btnAuthCust.setBounds(243, 252, 89, 31);
		frame.getContentPane().add(btnAuthCust);
	}

}
