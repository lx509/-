import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.*;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private TextField textField;
	private TextField textField_1;
	private Statement statement;
	static LoginFrame login_frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login_frame = new LoginFrame();
					login_frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 852, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("学生成绩管理系统");
		label.setFont(new Font("楷体", Font.BOLD, 39));
		label.setBounds(248, 33, 420, 48);
		contentPane.add(label);
		
		Label label_1 = new Label("管理员账号");
		label_1.setFont(new Font("Adobe 黑体 Std R", Font.BOLD, 18));
		label_1.setBounds(125, 117, 152, 48);
		contentPane.add(label_1);
		
		Label label_2 = new Label("管理员密码");
		label_2.setFont(new Font("Adobe 黑体 Std R", Font.BOLD, 18));
		label_2.setBounds(125, 218, 152, 48);
		contentPane.add(label_2);
		
		textField = new TextField();
		textField.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 15));
		textField.setBounds(332, 136, 395, 33);
		contentPane.add(textField);
		
		textField_1 = new TextField();
		textField_1.setEchoChar('*');
		textField_1.setFont(new Font("Adobe 黑体 Std R", Font.PLAIN, 15));
		textField_1.setBounds(332, 233, 395, 33);
		contentPane.add(textField_1);
		
		initializeDB();
		
		Button button = new Button("登录");
		button.setBackground(Color.LIGHT_GRAY);
		button.setForeground(Color.BLACK);
		button.setFont(new Font("Adobe 宋体 Std L", Font.BOLD, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checked()){
					//System.out.println("win");
					login_frame.dispose();//销毁当前页面
					new ChooseFrame().choose_frame_initialize();
				}
			}
		});
		button.setBounds(159, 370, 102, 42);
		contentPane.add(button);
		
		Button button_1 = new Button("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Adobe 宋体 Std L", Font.BOLD, 15));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(590, 370, 102, 42);
		contentPane.add(button_1);
		
		addListener();
	}
	
	private void addListener() {
		addWindowListener(new WindowAdapter() {// 添加窗体事件监听
			public void windowClosing(WindowEvent e) {// 窗体关闭时
				System.exit(0);// 关闭程序
			}
		});
	}
	
	private void initializeDB() {
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
	
	private boolean checked() {
		String account=textField.getText();
		//System.out.println(account);
		String password=textField_1.getText();
		String checkstring="select teacher_id,password from login_massage";
		//System.out.println("1");
		try {
			ResultSet rset=statement.executeQuery(checkstring);
			while(rset.next()) {
				//System.out.println(rset.getString("teacher_id")+" "+rset.getString("password"));
				//要用equals,不能用==
				if(rset.getString("teacher_id").equals(account) && rset.getString("password").equals(password)) {
					//System.out.println("loading");
					return true;
				}
				else {
					JOptionPane.showMessageDialog(null, "用户名或密码错误！" , null , JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
