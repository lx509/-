import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DeleteScore {

	private JFrame frame;
	private static JFrame delete_score_frame;
	private static TextField textField;
	private static TextArea textArea;
	private static Statement statement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		delete_score_initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public static void delete_score_initialize() {
		// TODO Auto-generated method stub
		delete_score_frame = new JFrame();
		delete_score_frame.setPreferredSize(new Dimension( 852, 531));
		delete_score_frame.setBounds(100, 100, 450, 300);
		delete_score_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		delete_score_frame.pack();
		delete_score_frame.getContentPane().setLayout(null);
		delete_score_frame.setVisible(true);
		
		Label label = new Label("请输入学号");
		label.setBounds(100, 26, 132, 42);
		label.setFont(new Font("Dialog", Font.PLAIN, 15));
		delete_score_frame.getContentPane().add(label);
		
		textField = new TextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 15));
		textField.setBounds(238, 34, 342, 23);
		delete_score_frame.getContentPane().add(textField);
		
		Button button = new Button("删除");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(delete());
				else {
					JOptionPane.showMessageDialog(null, "学号输入错误！" , null , JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBounds(617, 34, 75, 29);
		button.setBackground(Color.LIGHT_GRAY);
		delete_score_frame.getContentPane().add(button);
		
		textArea = new TextArea();
		textArea.setFont(new Font("Dialog", Font.BOLD, 16));
		textArea.setBounds(25, 101, 786, 344);
		delete_score_frame.getContentPane().add(textArea);
		
		Button button_1 = new Button("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete_score_frame.dispose();//销毁当前页面
				new ChooseFrame().choose_frame_initialize();
			}
		});
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(735, 447, 75, 23);
		delete_score_frame.getContentPane().add(button_1);
		
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

	private static boolean delete() {	
		String student_id=textField.getText();
		try {
			ResultSet rset=statement.executeQuery("select student_id from student_score");
			while(rset.next()) {
				//System.out.println(rset.getString("teacher_id")+" "+rset.getString("password"));
				//要用equals,不能用==
				if(rset.getString("student_id").equals(student_id)) {
					//System.out.println("loading");
					int rset1=statement.executeUpdate("delete from student_score where student_id = '"+student_id+"'");
					view();
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
