import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Shop {

	private JFrame frame;
	private JTable table;
	private JTextField textbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop window = new Shop();
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
	public Shop() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	 PreparedStatement pst;
	 ResultSet rs;
	 
	 public void Connect()
	     {
	         try {
	             Class.forName("com.mysql.jdbc.Driver");
	             con = DriverManager.getConnection("jdbc:mysql://localhost/shop", "root","");
	         }
	         catch (ClassNotFoundException ex) 
	         {
	           ex.printStackTrace();
	         }
	         catch (SQLException ex) 
	         {
	         	   ex.printStackTrace();
	         }
	 
	     }

	/**
	 * Initialize the contents of the frame.
	 */
	 public void table_load()
     {
      try 
      {
     pst = con.prepareStatement("select * from book");
     rs = pst.executeQuery();
     table.setModel(DbUtils.resultSetToTableModel(rs));
 } 
      catch (SQLException e) 
      {
      e.printStackTrace();
   } 
     }
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 539, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setBounds(174, 11, 108, 37);
		lblNewLabel.setFont(new Font("Palatino Linotype", Font.BOLD, 20));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 47, 235, 127);
		panel.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setToolTipText("");
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(10, 33, 71, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(10, 58, 71, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Edition");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1_1.setBounds(10, 89, 53, 14);
		panel.add(lblNewLabel_1_1_1_1);
		
		JTextPane textPrice = new JTextPane();
		textPrice.setBounds(111, 58, 86, 20);
		panel.add(textPrice);
		
		JTextPane textEdition = new JTextPane();
		textEdition.setBounds(111, 89, 86, 20);
		panel.add(textEdition);
		
		JTextPane textName = new JTextPane();
		textName.setBounds(111, 33, 86, 20);
		panel.add(textName);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price;
				bname = textName.getText();
				edition = textEdition.getText();
				price = textPrice.getText();
				
				try {
					pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Record Added!!!");
					table_load();
					textName.setText("");
					textEdition.setText("");
					textPrice.setText("");
					textName.requestFocus();	 
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 185, 65, 37);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(89, 185, 71, 37);
		frame.getContentPane().add(btnExit);
		
		JButton btnClean = new JButton("Clean");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textEdition.setText("");
				textPrice.setText("");
				textName.requestFocus();	
			}
		});
		btnClean.setBounds(174, 185, 71, 37);
		frame.getContentPane().add(btnClean);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 46, 226, 183);
		frame.getContentPane().add(scrollPane);
		
		JScrollPane scrollpane = new JScrollPane();
		scrollPane.setViewportView(scrollpane);
		
		table = new JTable();
		scrollpane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 233, 235, 54);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("book ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 29, 46, 14);
		panel_1.add(lblNewLabel_1);
		
		textbid = new JTextField();
		textbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				 try {
			           
		             String id = textbid.getText();
		 
		                 pst = con.prepareStatement("select name,edition,price from book where id = ?");
		                 pst.setString(1, id);
		                 ResultSet rs = pst.executeQuery();
		 
		             if(rs.next()==true)
		             {
		               
		                 String name = rs.getString(1);
		                 String edition = rs.getString(2);
		                 String price = rs.getString(3);
		                 
		                 textName.setText(name);
		                 textEdition.setText(edition);
		                 textPrice.setText(price);
		                 
		                 
		             }   
		             else
		             {
		              textName.setText("");
		              textEdition.setText("");
		                 textPrice.setText("");
		                  
		             }
		             
		 
		 
		         } 
		 
		 catch (SQLException ex) {
		            
		         }
		 
				
			}
		});
		textbid.setBounds(102, 26, 86, 20);
		panel_1.add(textbid);
		textbid.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String bname,edition,price,bid;
				bname = textName.getText();
				edition = textEdition.getText();
				price = textPrice.getText();
				bid = textbid.getText();
				try {
					pst = con.prepareStatement("update book set name=?,edition=?,price=? where id =?");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, bid);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Record Updated!!!");
					table_load();
					textName.setText("");
					textEdition.setText("");
					textPrice.setText("");
					textName.requestFocus();	 
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(277, 240, 83, 41);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				
				bid = textbid.getText();
				
				try {
					pst = con.prepareStatement("delete from book where id =?");
					
					pst.setString(1, bid);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Record Deleted!!!");
					table_load();
					textName.setText("");
					textEdition.setText("");
					textPrice.setText("");
					textName.requestFocus();	 
				}
				catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(409, 240, 83, 41);
		frame.getContentPane().add(btnNewButton_2);
	}
}
