import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ChooseFrame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		choose_frame_initialize();
	}


	/**
	 * Initialize the contents of the choose_frame.
	 */

	public static void choose_frame_initialize() {
			JFrame choose_frame;
			choose_frame = new JFrame();
			choose_frame.setPreferredSize(new Dimension( 852, 531));
			choose_frame.setBounds(100, 100, 852, 531);
			choose_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			choose_frame.pack();
			choose_frame.getContentPane().setLayout(null);
			choose_frame.setVisible(true);
			
			Button button = new Button("查询学生信息");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choose_frame.dispose();//销毁当前页面
					new QueryMassage().query_massage_initialize();
				}
			});
			button.setBackground(Color.LIGHT_GRAY);
			button.setFont(new Font("Adobe 宋体 Std L", Font.BOLD, 18));
			button.setBounds(205, 79, 427, 40);
			choose_frame.getContentPane().add(button);
			
			Button button_1 = new Button("查询学生成绩");
			button_1.setBackground(Color.LIGHT_GRAY);
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choose_frame.dispose();//销毁当前页面
					new QueryScore().query_score_initialize();
				}
			});
			button_1.setFont(new Font("Adobe 宋体 Std L", Font.BOLD, 18));
			button_1.setBounds(205, 155, 427, 40);
			choose_frame.getContentPane().add(button_1);
			
			Button button_2 = new Button("添加学生成绩");
			button_2.setBackground(Color.LIGHT_GRAY);
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choose_frame.dispose();//销毁当前页面
					new AddScore().add_score_initialize();
				}
			});
			button_2.setFont(new Font("Adobe 宋体 Std L", Font.BOLD, 18));
			button_2.setBounds(205, 237, 427, 40);
			choose_frame.getContentPane().add(button_2);
			
			Button button_3 = new Button("删除学生成绩");
			button_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choose_frame.dispose();//销毁当前页面
					new DeleteScore().delete_score_initialize();
				}
			});
			button_3.setBackground(Color.LIGHT_GRAY);
			button_3.setFont(new Font("Adobe 宋体 Std L", Font.BOLD, 18));
			button_3.setBounds(205, 316, 427, 40);
			choose_frame.getContentPane().add(button_3);
			
			Button button_4 = new Button("修改学生成绩");
			button_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					choose_frame.dispose();//销毁当前页面
					new UpdateScore().update_score_initialize();
				}
			});
			button_4.setFont(new Font("Adobe 宋体 Std L", Font.BOLD, 18));
			button_4.setBackground(Color.LIGHT_GRAY);
			button_4.setBounds(205, 389, 427, 40);
			choose_frame.getContentPane().add(button_4);
			
	}
}
