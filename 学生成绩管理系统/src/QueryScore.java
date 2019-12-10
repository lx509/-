import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.TextArea;

public class QueryScore {

	

	private static TextField textField;
	private static JTable table;
	private static Statement statement;
	private static JFrame query_score_frame;
	private static TextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		query_score_initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void query_score_initialize() {
		query_score_frame = new JFrame();
		query_score_frame.setPreferredSize(new Dimension( 852, 531));
		query_score_frame.setBounds(100, 100, 450, 300);
		query_score_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		query_score_frame.pack();
		query_score_frame.getContentPane().setLayout(null);
		query_score_frame.setVisible(true);
		
		Label label = new Label("请输入学号");
		label.setBounds(100, 26, 132, 42);
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		query_score_frame.getContentPane().add(label);
		
		textField = new TextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 15));
		textField.setBounds(238, 34, 342, 23);
		query_score_frame.getContentPane().add(textField);
		
		Button button = new Button("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(query());
				else {
					JOptionPane.showMessageDialog(null, "输入学号不存在！" , null , JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(617, 34, 75, 29);
		button.setBackground(Color.LIGHT_GRAY);
		query_score_frame.getContentPane().add(button);
		
		textArea = new TextArea();
		textArea.setFont(new Font("Dialog", Font.BOLD, 16));
		textArea.setBounds(25, 101, 786, 344);
		query_score_frame.getContentPane().add(textArea);
		
		initializeDB();

		Button button_1 = new Button("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query_score_frame.dispose();//销毁当前页面
				new ChooseFrame().choose_frame_initialize();
			}
		});
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(736, 461, 75, 23);
		query_score_frame.getContentPane().add(button_1);
	}
	
	private static void initializeDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wangjiexi");
			System.out.println("Database connected");
			
			statement=connection.createStatement();
			
			ResultSet rset=statement.executeQuery("select *from student_score");
			
			String string="学号            姓名        课程        成绩";
			while(rset.next()) {
				string=string+'\n'+rset.getString(1)+"       "+rset.getString(2)+"        "+rset.getString(3)+"       "+rset.getString(4);
				textArea.setText(string);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static boolean query() {	
		try {
			String student_id=textField.getText();
			ResultSet rset=statement.executeQuery("select *from student_score");
			String string="学号            姓名        课程        成绩";
			while(rset.next()) {
				if(rset.getString(1).equals(student_id)) {
					string=string+'\n'+rset.getString(1)+"       "+rset.getString(2)+"        "+rset.getString(3)+"       "+rset.getString(4);
					textArea.setText(string);
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
