import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Button;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QueryMassage {

	private static TextField textField_major;
	private static TextField textField_college;
	private static TextField textField_grade;
	private static TextField textField_tel;
	private static TextField textField_sex;
	private static TextField textField_age;
	private static TextField textField_name;
	private static TextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		query_massage_initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void query_massage_initialize() {
		// TODO Auto-generated method stub
		JFrame qurey_massage_frame = new JFrame();
		qurey_massage_frame.setPreferredSize(new Dimension( 852, 531));
		qurey_massage_frame.setBounds(100, 100, 852, 531);
		qurey_massage_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		qurey_massage_frame.pack();
		qurey_massage_frame.getContentPane().setLayout(null);
		qurey_massage_frame.setVisible(true);
		
		Label label = new Label("请输入学号");
		label.setBounds(100, 26, 132, 42);
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		qurey_massage_frame.getContentPane().add(label);
		
		textField = new TextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 15));
		textField.setBounds(238, 34, 342, 23);
		qurey_massage_frame.getContentPane().add(textField);
		
		Label label_1 = new Label("姓名");
		label_1.setBounds(38, 90, 67, 34);
		qurey_massage_frame.getContentPane().add(label_1);
		
		Label label_2 = new Label("年龄");
		label_2.setBounds(38, 130, 67, 41);
		qurey_massage_frame.getContentPane().add(label_2);
		
		Label label_3 = new Label("性别");
		label_3.setBounds(38, 178, 67, 41);
		qurey_massage_frame.getContentPane().add(label_3);
		
		Label label_4 = new Label("电话");
		label_4.setBounds(38, 225, 67, 41);
		qurey_massage_frame.getContentPane().add(label_4);
		
		Label label_5 = new Label("年级");
		label_5.setBounds(38, 272, 67, 41);
		qurey_massage_frame.getContentPane().add(label_5);
		
		Label label_6 = new Label("学院");
		label_6.setBounds(38, 319, 67, 41);
		qurey_massage_frame.getContentPane().add(label_6);
		
		Label label_7 = new Label("专业");
		label_7.setBounds(38, 364, 67, 41);
		qurey_massage_frame.getContentPane().add(label_7);
		
		textField_name = new TextField();
		//textField_name.setText("123");
		textField_name.setBounds(114, 101, 342, 23);
		qurey_massage_frame.getContentPane().add(textField_name);
		
		textField_age = new TextField();
		textField_age.setBounds(114, 148, 342, 23);
		qurey_massage_frame.getContentPane().add(textField_age);
		
		textField_sex = new TextField();
		textField_sex.setBounds(114, 196, 342, 23);
		qurey_massage_frame.getContentPane().add(textField_sex);
		
		textField_tel = new TextField();
		textField_tel.setBounds(114, 243, 342, 23);
		qurey_massage_frame.getContentPane().add(textField_tel);
		
		textField_grade = new TextField();
		textField_grade.setBounds(114, 290, 342, 23);
		qurey_massage_frame.getContentPane().add(textField_grade);
		
		textField_college = new TextField();
		textField_college.setBounds(114, 335, 342, 23);
		qurey_massage_frame.getContentPane().add(textField_college);
		
		textField_major = new TextField();
		textField_major.setBounds(114, 382, 342, 23);
		qurey_massage_frame.getContentPane().add(textField_major);
		
		Button button = new Button("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initializeDB();
				if(query());
				else {
					JOptionPane.showMessageDialog(null, "输入学号不存在！" , null , JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(617, 34, 75, 29);
		button.setBackground(Color.LIGHT_GRAY);
		qurey_massage_frame.getContentPane().add(button);
		
		Button button_1 = new Button("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				qurey_massage_frame.dispose();//销毁当前页面
				new ChooseFrame().choose_frame_initialize();
			}
		});
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(678, 426, 75, 23);
		qurey_massage_frame.getContentPane().add(button_1);
	}

	private static Statement statement;
	
	private static void initializeDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wangjiexi");
			System.out.println("Database connected");
			
			statement=connection.createStatement();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static boolean query() {
		String student_id=textField.getText();
		String querystring="select student_id,student_name,student_age,student_sex,student_tel,student_grade,student_college,student_major from student_massage";
		//textField_name.setText("123");
		try {
			ResultSet rset=statement.executeQuery(querystring);
			while(rset.next()) {
				if(rset.getString("student_id").equals(student_id)) {
					textField_name.setText(rset.getString("student_name"));
					textField_age.setText(rset.getString("student_age"));
					textField_tel.setText(rset.getString("student_tel"));
					textField_sex.setText(rset.getString("student_sex"));
					textField_grade.setText(rset.getString("student_grade"));
					textField_college.setText(rset.getString("student_college"));
					textField_major.setText(rset.getString("student_major"));
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
