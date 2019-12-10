
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SELECT extends JFrame {

	/**
	 * SELECT
	 */
	private static final long serialVersionUID = 1L;
	private static Statement statement;
	private JFrame f;
	private Label L1,L2,L3;
	private Button B;
	private static TextField J1;
	private TextField J2;
	private TextField J3;
	private static TextField t1;
	private TextField t2;
	private TextField t3;
	private static Container c;
	private JButton btn,btn1;
	static Button b1;
	static Button b2;
	static Button b3;
	static Button b4;
	static Button b5;
	//private Button btn1;
	 private DefaultTableModel tableModel;   //表格模型对象
	private JTable table;
	//private String[] dataTitle = {"职工编号","总工资","加班天数","出勤","旷工","奖惩工资"};
	
	//private static void InitializeDB();
	/**
	 * Launch the application.
	 */
	 
	public static void main(String[] args) {
		new SELECT();
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public SELECT() {
		initializeDB();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 250, 400, 150);
		f = new JFrame("工资管理系统");
		f.setVisible(true);
		f.setBounds(600,250,350,400);
		c = f.getContentPane();
		//f.setBackground(Blue);
		c.setLayout(new FlowLayout());
		
		//btn1.bounds();
		//c.add(btn);
		L1 = new Label("职工编号：");
		c.add(L1);
		J1 = new TextField();
		J1.setColumns(10);
		J1.setBounds(100, 100, 100, 100);
		c.add(J1);
		btn = new JButton("确定");
		btn1 = new JButton("返回");
		
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click();
			}
		});
		c.add(btn);
		c.add(btn1);
	}
	
	private static void click() {
		initializeDB();
		ResultSet resultSet1;
		try {
			resultSet1 = statement.executeQuery("select *from student_score");
			//int i = 0;
			while(resultSet1.next()) {
				if(J1.getText().trim().equals(resultSet1.getString("student_id"))) {
					System.out.println(resultSet1.getString(2));//测试语句
					//总工资","加班天数","出勤天数","旷工天数","奖惩工资
					newButton();
				}	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private static void newButton() {
		ResultSet resultSet1 = null;
		b1 = new Button("总工资");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				try {
					while(resultSet1.next()) {
						System.out.println("a");//测试语句
						t1 = new TextField();
						t1.setColumns(20);
						t1.setText(resultSet1.getString(2));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				c.add(t1);
			}
		});
			
		
		b2 = new Button("出勤天数");
		b3 = new Button("加班天数");
		b4 = new Button("旷工天数");
		b5 = new Button("奖惩工资");
		c.add(b1);
		c.add(b2);
		c.add(b3);
		c.add(b4);
		c.add(b5);
	}
	
	private static void initializeDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded");
			
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/student?serverTimezone=UTC","root","wangjiexi");
			System.out.println("Database connected1");
			
			statement=connection.createStatement();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

 
	}
