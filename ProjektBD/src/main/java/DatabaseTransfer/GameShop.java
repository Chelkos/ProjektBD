package DatabaseTransfer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;

import com.mysql.cj.jdbc.MysqlDataSource;


public class GameShop {
	public static JFrame  window;
	private static void setupWindow()
	{
		 
		  window = new JFrame();
		  window.setSize(200, 180);
		  window.setLayout(new FlowLayout(FlowLayout.LEFT));
		  JTextField loginField=new JTextField(14);
		  JTextField passwordField=new JTextField(14);
		  Button accept = new Button("Login");
		  loginField.setSize(200, 70);
		  passwordField.setSize(200,50);
		  window.add(new JLabel("Login: "));
  		  window.add(loginField);
  		  window.add(new JLabel("Password: "));
		  window.add(passwordField);
		  accept.setSize(50,150);
		  accept.addMouseListener(new MouseAdapter(){
        	public void mousePressed(MouseEvent e) {
        		if(loginField.getText()!="Client")
        		setupDataSource(loginField.getText(),passwordField.getText());
        		else
        			setupDataSource(loginField.getText(),null);
        	}
        });
		  window.add(accept);
		  
  		  window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  		  window.setLocation(700, 400);
  		  window.setVisible(true);
		  window.setResizable(false);
	}
	private static void setupDataSource(String username,String password)
	{
		JFrame windowl;
		WindowFactory factory;
		MysqlDataSource dataSource;
		ApplicationContext context;
		DBConnection connection;
		
		context=new ClassPathXmlApplicationContext("file:src/main/java/beans.xml");
		connection=(DBConnection)context.getBean("DBConnection");
		dataSource=(MysqlDataSource)context.getBean("dataSource");
		dataSource.setUser(username);
		dataSource.setPassword(password);
		JdbcTemplate jdbcTemplateObject=new JdbcTemplate(dataSource);
		window.setVisible(false);
		factory = new WindowFactory();
		windowl = factory.create_window(username,connection);
		windowl.setVisible(true);
	}
	public static void main(String[] args) {
		setupWindow();

	}

}
