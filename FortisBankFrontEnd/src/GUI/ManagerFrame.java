package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bus.Account;
import bus.AccountService;
import bus.Account_Exception;
import bus.Checking;
import bus.Credit;
import bus.DataCollection;
import bus.Saving;
import bus.ValidationException;
import data.DBConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ManagerFrame {

	private JFrame frame;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldID;
	private JTextField textFieldPIN;
	private JTextField textFieldAccNumber;
	private JTextField textFieldBalance;
	private JTextField textFieldDate;
	private JTextField textFieldManagerID;
	
	private String firstName;
	private String lastName;
	private int id;
	private int pin;
	private int accountNumber;
	private double balance;
	private String date;
	private int managerId;
	
	
	private Connection connection;
	private JTextField textFieldAccCust;
	private JTextField textFieldIDCust;
	private JTable tableMytable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerFrame window = new ManagerFrame();
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
	public ManagerFrame() {
		initialize();
		connection = DBConnection.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1106, 722);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Search = new JPanel();
		Search.setBackground(new Color(240, 255, 255));
		Search.setForeground(new Color(65, 105, 225));
		tabbedPane.addTab("Search", null, Search, null);
		Search.setLayout(null);
		
		JButton btnDeleteAccount = new JButton("Delete an Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					accountNumber = Integer.parseInt(textFieldAccCust.getText());
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
		btnDeleteAccount.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		btnDeleteAccount.setBackground(new Color(100, 149, 237));
		btnDeleteAccount.setBounds(24, 181, 152, 48);
		Search.add(btnDeleteAccount);
		
		JButton btnSearchByID = new JButton("Search by ID");
		btnSearchByID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from Accounts where Customer_ID = " + textFieldIDCust.getText();
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableMytable.setModel(DbUtils.resultSetToTableModel(rs));
								
				} catch (Exception e2) {
	
				}
			}
		});
		btnSearchByID.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		btnSearchByID.setBackground(new Color(100, 149, 237));
		btnSearchByID.setBounds(206, 181, 152, 48);
		Search.add(btnSearchByID);
		
		JButton btnShowAll = new JButton("Show all Accounts");
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query="select * from Accounts";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableMytable.setModel(DbUtils.resultSetToTableModel(rs));
								
				} catch (Exception e2) {
					
				}	
			}
		});
		btnShowAll.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		btnShowAll.setBackground(new Color(100, 149, 237));
		btnShowAll.setBounds(386, 181, 152, 48);
		Search.add(btnShowAll);
		
		JLabel lblFortis_1 = new JLabel("FORTIS BANK");
		lblFortis_1.setForeground(new Color(70, 130, 180));
		lblFortis_1.setFont(new Font("Source Sans Pro Light", Font.BOLD, 26));
		lblFortis_1.setBounds(417, 0, 159, 44);
		Search.add(lblFortis_1);
		
		JLabel lblEnterAccountNumber = new JLabel("Enter Account Number :");
		lblEnterAccountNumber.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblEnterAccountNumber.setBounds(24, 49, 186, 26);
		Search.add(lblEnterAccountNumber);
		
		textFieldAccCust = new JTextField();
		textFieldAccCust.setColumns(10);
		textFieldAccCust.setBounds(24, 73, 204, 33);
		Search.add(textFieldAccCust);
		
		JLabel lnlId = new JLabel("Enter the customer ID :");
		lnlId.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lnlId.setBounds(287, 49, 186, 26);
		Search.add(lnlId);
		
		textFieldIDCust = new JTextField();
		textFieldIDCust.setColumns(10);
		textFieldIDCust.setBounds(287, 73, 204, 33);
		Search.add(textFieldIDCust);
		
		JButton btnExit_1 = new JButton("EXIT");
		btnExit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			
			}
		});
		btnExit_1.setForeground(new Color(255, 99, 71));
		btnExit_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit_1.setBounds(969, 583, 89, 36);
		Search.add(btnExit_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 269, 1034, 303);
		Search.add(scrollPane);
		
		tableMytable = new JTable();
		scrollPane.setViewportView(tableMytable);
		
		JButton btnHomePage = new JButton("Home Page");
		btnHomePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeFrame.main(null);
			}
		});
		btnHomePage.setForeground(new Color(70, 130, 180));
		btnHomePage.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHomePage.setBounds(24, 583, 152, 36);
		Search.add(btnHomePage);
		
		JPanel panelManager_Service = new JPanel();
		panelManager_Service.setBackground(new Color(240, 255, 255));
		tabbedPane.addTab("Manager Service", null, panelManager_Service, null);
		panelManager_Service.setLayout(null);
		
		JLabel lblServices = new JLabel("Services");
		lblServices.setForeground(new Color(70, 130, 180));
		lblServices.setFont(new Font("Source Sans Pro Light", Font.BOLD, 24));
		lblServices.setBounds(37, 39, 119, 28);
		panelManager_Service.add(lblServices);
		
		JLabel lblFortis = new JLabel("FORTIS BANK");
		lblFortis.setForeground(new Color(70, 130, 180));
		lblFortis.setFont(new Font("Source Sans Pro Light", Font.BOLD, 26));
		lblFortis.setBounds(422, 0, 159, 55);
		panelManager_Service.add(lblFortis);
		
		JRadioButton rdbtnNewAccount = new JRadioButton("New Account(CHECKING)");
		rdbtnNewAccount.setBackground(new Color(240, 255, 255));
		rdbtnNewAccount.setForeground(new Color(30, 144, 255));
		rdbtnNewAccount.setFont(new Font("Source Sans Pro Light", Font.BOLD, 15));
		rdbtnNewAccount.setBounds(92, 101, 221, 23);
		panelManager_Service.add(rdbtnNewAccount);
		
		JRadioButton rdbtnCredit = new JRadioButton("Credit Account");
		rdbtnCredit.setBackground(new Color(240, 255, 255));
		rdbtnCredit.setForeground(new Color(30, 144, 255));
		rdbtnCredit.setFont(new Font("Source Sans Pro Light", Font.BOLD, 15));
		rdbtnCredit.setBounds(92, 124, 150, 23);
		panelManager_Service.add(rdbtnCredit);
		
		JRadioButton rdbtnSaving = new JRadioButton("Saving Account");
		rdbtnSaving.setBackground(new Color(240, 255, 255));
		rdbtnSaving.setForeground(new Color(30, 144, 255));
		rdbtnSaving.setFont(new Font("Source Sans Pro Light", Font.BOLD, 15));
		rdbtnSaving.setBounds(92, 150, 150, 23);
		panelManager_Service.add(rdbtnSaving);
		
		JLabel lblNewLabel = new JLabel("Select the Service :");
		lblNewLabel.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblNewLabel.setBounds(37, 68, 159, 26);
		panelManager_Service.add(lblNewLabel);
		
		JLabel lblEnterTheData = new JLabel("Enter the data in the available fields :");
		lblEnterTheData.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblEnterTheData.setBounds(37, 197, 276, 26);
		panelManager_Service.add(lblEnterTheData);
		
		JLabel lblFirstName = new JLabel("First Name :");
		lblFirstName.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblFirstName.setBounds(37, 234, 109, 26);
		panelManager_Service.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name :");
		lblLastName.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblLastName.setBounds(37, 270, 109, 26);
		panelManager_Service.add(lblLastName);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblId.setBounds(37, 306, 109, 26);
		panelManager_Service.add(lblId);
		
		JLabel lblPin = new JLabel("PIN :");
		lblPin.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblPin.setBounds(37, 342, 109, 26);
		panelManager_Service.add(lblPin);
		
		JLabel lblAccountNumber = new JLabel("Account Number :");
		lblAccountNumber.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblAccountNumber.setBounds(37, 378, 150, 26);
		panelManager_Service.add(lblAccountNumber);
		
		JLabel lblBalance = new JLabel("Balance :");
		lblBalance.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblBalance.setBounds(37, 415, 109, 26);
		panelManager_Service.add(lblBalance);
		
		JLabel lblDate = new JLabel("Date (YYYY-MM-DD) :");
		lblDate.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblDate.setBounds(37, 452, 172, 26);
		panelManager_Service.add(lblDate);
		
		JLabel lblManagerId = new JLabel("Manager ID");
		lblManagerId.setFont(new Font("Source Sans Pro Light", Font.BOLD, 17));
		lblManagerId.setBounds(37, 489, 109, 26);
		panelManager_Service.add(lblManagerId);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(236, 237, 276, 25);
		panelManager_Service.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(236, 273, 276, 25);
		panelManager_Service.add(textFieldLastName);
		
		textFieldID = new JTextField();
		textFieldID.setColumns(10);
		textFieldID.setBounds(236, 309, 276, 25);
		panelManager_Service.add(textFieldID);
		
		textFieldPIN = new JTextField();
		textFieldPIN.setColumns(10);
		textFieldPIN.setBounds(236, 345, 276, 25);
		panelManager_Service.add(textFieldPIN);
		
		textFieldAccNumber = new JTextField();
		textFieldAccNumber.setColumns(10);
		textFieldAccNumber.setBounds(236, 381, 276, 25);
		panelManager_Service.add(textFieldAccNumber);
		
		textFieldBalance = new JTextField();
		textFieldBalance.setColumns(10);
		textFieldBalance.setBounds(236, 418, 276, 25);
		panelManager_Service.add(textFieldBalance);
		
		textFieldDate = new JTextField();
		textFieldDate.setColumns(10);
		textFieldDate.setBounds(236, 455, 276, 25);
		panelManager_Service.add(textFieldDate);
		
		textFieldManagerID = new JTextField();
		textFieldManagerID.setColumns(10);
		textFieldManagerID.setBounds(236, 492, 276, 25);
		panelManager_Service.add(textFieldManagerID);
		
		JButton btnAdd = new JButton("ADD ACCOUNT");
		btnAdd.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(rdbtnNewAccount.isSelected()) {
						firstName = textFieldFirstName.getText();
						lastName = textFieldLastName.getText();
						id = Integer.parseInt(textFieldID.getText());
						pin = Integer.parseInt(textFieldPIN.getText());
						accountNumber = Integer.parseInt(textFieldAccNumber.getText());
						balance = Double.parseDouble(textFieldBalance.getText());
						date = textFieldDate.getText();
						managerId = Integer.parseInt(textFieldManagerID.getText());	
						
						try {
							Checking ch = new Checking();
							ch.addAccount(firstName, lastName, id, pin, accountNumber, balance, date, managerId);
						} catch (ValidationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Account_Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Account Added Successfully!");
					}	
					if(rdbtnCredit.isSelected()) {
						id = Integer.parseInt(textFieldID.getText());
						accountNumber = Integer.parseInt(textFieldAccNumber.getText());
						balance = Double.parseDouble(textFieldBalance.getText());
						date = textFieldDate.getText();
						
						try {
							JOptionPane.showMessageDialog(null, "Remember: You must open a checking account to open a Credit ACCOUNT.");
							Credit cr = new Credit();
							cr.addAccount(id, accountNumber, balance, date);
						} catch (ValidationException | Account_Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Account Added Successfully!");
					}
					if(rdbtnSaving.isSelected()) {
						
						id = Integer.parseInt(textFieldID.getText());
						accountNumber = Integer.parseInt(textFieldAccNumber.getText());
						balance = Double.parseDouble(textFieldBalance.getText());
						date = textFieldDate.getText();
						
						try {
							JOptionPane.showMessageDialog(null, "Remember: You must open a checking account to open a SAVING ACCOUNT.");
							Saving sv = new Saving();
							sv.addAccount(id, accountNumber, balance, date);
						} catch (ValidationException | Account_Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Account Added Successfully!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}	
			}
		});
		btnAdd.setBackground(new Color(100, 149, 237));
		btnAdd.setBounds(383, 528, 129, 36);
		panelManager_Service.add(btnAdd);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setForeground(new Color(255, 99, 71));
		btnExit.setBounds(962, 582, 89, 36);
		panelManager_Service.add(btnExit);
		
		JButton btnHome = new JButton("Home Page");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomeFrame.main(null);
			}
		});
		btnHome.setForeground(new Color(70, 130, 180));
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHome.setBounds(823, 582, 119, 36);
		panelManager_Service.add(btnHome);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "update Customer set First_Name = '" + textFieldFirstName.getText() +
							"', Last_Name = '" + textFieldLastName.getText() + "', pin ='" + textFieldPIN.getText()
							+ "' WHERE Customer_id ='" + textFieldID.getText() + "'" ;
		    		PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null, "Account UPDATED Successfully!");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnUpdate.setFont(new Font("Source Sans Pro Light", Font.BOLD, 14));
		btnUpdate.setBackground(new Color(100, 149, 237));
		btnUpdate.setBounds(383, 583, 129, 36);
		panelManager_Service.add(btnUpdate);
	}

}
