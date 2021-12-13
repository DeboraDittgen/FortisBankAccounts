package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import bus.Account;
import bus.AccountService;
import bus.DataCollection;
import bus.Transaction;
import data.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class CustomerFrame {

	private JFrame frame;
	private JTextField textFieldAmount;
	private JTextField textFieldAccountNum;
	private JTable table;
	
	private double amount;
	private int accountNumber;
	private Transaction trans;
	
	private Connection connection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame window = new CustomerFrame();
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
	public CustomerFrame() {
		initialize();
		connection = DBConnection.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.getContentPane().setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		frame.setBounds(100, 100, 998, 856);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblFortis = new JLabel("FORTIS BANK");
		lblFortis.setForeground(new Color(70, 130, 180));
		lblFortis.setFont(new Font("Source Sans Pro Light", Font.BOLD, 26));
		lblFortis.setBounds(397, 0, 159, 41);
		frame.getContentPane().add(lblFortis);
		
		JLabel lblServices = new JLabel("Services");
		lblServices.setForeground(new Color(70, 130, 180));
		lblServices.setFont(new Font("Source Sans Pro Light", Font.BOLD, 25));
		lblServices.setBounds(57, 76, 117, 41);
		frame.getContentPane().add(lblServices);
		
		JRadioButton rdbtnWithdraw = new JRadioButton("WITHDRAW");
		rdbtnWithdraw.setBackground(new Color(240, 255, 255));
		rdbtnWithdraw.setForeground(new Color(30, 144, 255));
		rdbtnWithdraw.setFont(new Font("Source Sans Pro Light", Font.BOLD, 18));
		rdbtnWithdraw.setBounds(73, 158, 143, 23);
		frame.getContentPane().add(rdbtnWithdraw);
		
		JRadioButton rdbtnDeposit = new JRadioButton("DEPOSIT");
		rdbtnDeposit.setBackground(new Color(240, 255, 255));
		rdbtnDeposit.setForeground(new Color(30, 144, 255));
		rdbtnDeposit.setFont(new Font("Source Sans Pro Light", Font.BOLD, 18));
		rdbtnDeposit.setBounds(73, 192, 111, 23);
		frame.getContentPane().add(rdbtnDeposit);
		
		JLabel lblNewLabel = new JLabel("Select the Service :");
		lblNewLabel.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblNewLabel.setBounds(57, 125, 159, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEnterTheAmount = new JLabel("Enter the Amount :");
		lblEnterTheAmount.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblEnterTheAmount.setBounds(290, 125, 159, 26);
		frame.getContentPane().add(lblEnterTheAmount);
		
		JLabel lblEnterAccountNumber = new JLabel("Enter Account Number :");
		lblEnterAccountNumber.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblEnterAccountNumber.setBounds(544, 125, 186, 26);
		frame.getContentPane().add(lblEnterAccountNumber);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(289, 157, 204, 33);
		frame.getContentPane().add(textFieldAmount);
		textFieldAmount.setColumns(10);
		
		textFieldAccountNum = new JTextField();
		textFieldAccountNum.setColumns(10);
		textFieldAccountNum.setBounds(544, 157, 204, 33);
		frame.getContentPane().add(textFieldAccountNum);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 286, 868, 402);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					amount = Double.parseDouble(textFieldAmount.getText());
					accountNumber = Integer.parseInt(textFieldAccountNum.getText());
					Account searchedAccount;
					searchedAccount = AccountService.search2(accountNumber);	
					
					if(amount <= 0) {
						JOptionPane.showInternalMessageDialog(null, "The amount must be greater than zero.");
					}
					
					if(rdbtnWithdraw.isSelected() && amount > 0) {
						if(amount <= searchedAccount.getAccountBalance()) {
							Transaction tr = new Transaction();
							tr.withdraw(accountNumber, amount);
							JOptionPane.showInternalMessageDialog(null, "Successful!");
						}		
					}
					if (rdbtnDeposit.isSelected() && amount > 0) {
						Transaction tr = new Transaction();
						tr.deposit(accountNumber, amount);
						JOptionPane.showInternalMessageDialog(null, "Successful!");
					}
				}catch (Exception e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnOK.setBackground(new Color(70, 130, 180));
		btnOK.setForeground(new Color(0, 0, 0));
		btnOK.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnOK.setBounds(798, 157, 127, 33);
		frame.getContentPane().add(btnOK);
		
		JButton btnReload = new JButton("Transactions");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from Transactions where Account_Number = " + textFieldAccountNum.getText();
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnReload.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReload.setBackground(new Color(70, 130, 180));
		btnReload.setBounds(57, 721, 127, 33);
		frame.getContentPane().add(btnReload);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					accountNumber = Integer.parseInt(textFieldAccountNum.getText());
					Account searchedAccount;
					searchedAccount = AccountService.search2(accountNumber);
					
					if(searchedAccount.getAccountBalance() == 0) {
						DataCollection del = new DataCollection();
						del.deleteAccount(accountNumber);
						JOptionPane.showInternalMessageDialog(null, "Successful! Account Deleted.");
					}else {
						JOptionPane.showInternalMessageDialog(null, "Your account has a balance of: " + searchedAccount.getAccountBalance() + 
								"$ Please make a WITHDRAWAL");
						
					}
				}catch(Exception e1) {
					
				}
			}
		});
		btnDeleteAccount.setForeground(new Color(255, 69, 0));
		btnDeleteAccount.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDeleteAccount.setBackground(new Color(70, 130, 180));
		btnDeleteAccount.setBounds(798, 225, 127, 33);
		frame.getContentPane().add(btnDeleteAccount);
		
		JButton btnHome = new JButton("Home Page");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeFrame.main(null);
			}
		});
		btnHome.setForeground(new Color(70, 130, 180));
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHome.setBounds(663, 718, 119, 36);
		frame.getContentPane().add(btnHome);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(new Color(255, 99, 71));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(836, 718, 89, 36);
		frame.getContentPane().add(btnExit);
	}
}
