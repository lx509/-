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
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import java.awt.TextArea;

public class AddScore {

	private JFrame frame;
	private static JFrame query_score_frame;
	private static Label label_1;
	private static TextField textField;
	private static TextField textField_1;
	private static TextField textField_2;
	private static TextField textField_3;
	private static TextArea textArea;
	private static Statement statement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		add_score_initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public static void add_score_initialize() {
		query_score_frame = new JFrame();
		query_score_frame.setPreferredSize(new Dimension( 852, 531));
		query_score_frame.setBounds(100, 100, 450, 300);
		query_score_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		query_score_frame.pack();
		query_score_frame.getContentPane().setLayout(null);
		query_score_frame.setVisible(true);
		
		Label label = new Label("请输入学生信息");
		label.setBounds(342, 10, 164, 42);
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		query_score_frame.getContentPane().add(label);
		
		label_1 = new Label("学号");
		label_1.setBounds(35, 73, 48, 32);
		query_score_frame.getContentPane().add(label_1);
		
		textField = new TextField();
		textField.setBounds(89, 73, 115, 23);
		query_score_frame.getContentPane().add(textField);
		
		Label label_2 = new Label("姓名");
		label_2.setBounds(210, 73, 48, 32);
		query_score_frame.getContentPane().add(label_2);
		
		textField_1 = new TextField();
		textField_1.setBounds(264, 73, 115, 23);
		query_score_frame.getContentPane().add(textField_1);
		
		Label label_3 = new Label("课程");
		label_3.setBounds(385, 73, 48, 32);
		query_score_frame.getContentPane().add(label_3);
		
		textField_2 = new TextField();
		textField_2.setBounds(439, 73, 115, 23);
		query_score_frame.getContentPane().add(textField_2);
		
		Label label_4 = new Label("成绩");
		label_4.setBounds(560, 73, 48, 32);
		query_score_frame.getContentPane().add(label_4);
		
		textField_3 = new TextField();
		textField_3.setBounds(614, 73, 115, 23);
		query_score_frame.getContentPane().add(textField_3);
		
		textArea = new TextArea();
		textArea.setFont(new Font("Dialog", Font.BOLD, 15));
		textArea.setBounds(35, 130, 769, 283);
		query_score_frame.getContentPane().add(textArea);
		
		Button button = new Button("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(735, 73, 75, 23);
		query_score_frame.getContentPane().add(button);
		
		Button button_1 = new Button("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query_score_frame.dispose();//销毁当前页面
				new ChooseFrame().choose_frame_initialize();
			}
		});
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(735, 447, 75, 23);
		query_score_frame.getContentPane().add(button_1);
		
		initializeDB();
	}
	
	private static void initializeDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wangjiexi");
			System.out.println("Database connected");
			
			statement=connection.createStatement();
			view();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void view() {
		ResultSet rset;
		try {
			rset = statement.executeQuery("select *from student_score");
			String string="学号            姓名        课程        成绩";
			while(rset.next()) {
				string=string+'\n'+rset.getString(1)+"       "+rset.getString(2)+"        "+rset.getString(3)+"       "+rset.getString(4);
				textArea.setText(string);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void add() {	
		String student_id=textField.getText();
		String student_name=textField_1.getText();
		String student_course=textField_2.getText();
		String student_score=textField_3.getText();
		try {
			int rset=statement.executeUpdate("insert into student_score values('"+student_id+"','"+student_name+"','"+student_course+"','"+student_score+"')");
			view();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
