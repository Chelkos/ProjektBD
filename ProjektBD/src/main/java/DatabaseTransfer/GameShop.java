package DatabaseTransfer;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.cj.jdbc.MysqlDataSource;

class LoginWindow extends JFrame{
	JTextField loginField;
	JTextField passwordField;
	JButton acceptButton;
	JLabel errorMessage;
	
	public LoginWindow() {
		super("Login");
		setSize(200, 200);
		setResizable(true);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		loginField=new JTextField(14);
		passwordField=new JTextField(14);
		acceptButton=new JButton("Login");
		errorMessage=new JLabel();
		loginField.setSize(200, 70);
		passwordField.setSize(200,50);
		add(new JLabel("Login: "));
		add(loginField);
		add(new JLabel("Password: "));
		add(passwordField);
		acceptButton.setSize(50,150);
		acceptButton.addMouseListener(new MouseAdapter(){
      	public void mousePressed(MouseEvent e) {
      		if(!loginField.getText().equals("Client"))
      		setupDataSource(loginField.getText(),passwordField.getText());
      		else
      			setupDataSource(loginField.getText(),null);
      	}
      });
		add(acceptButton);
		add(errorMessage);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(750, 400);
		setVisible(true);
	}
	
	private void setupDataSource(String username, String password)
	{
		JFrame mainMenu;
		WindowFactory factory;
		MysqlDataSource dataSource;
		ApplicationContext context;
		DBConnection connection;
		
		context=new ClassPathXmlApplicationContext("file:src/main/java/beans.xml");
		connection=(DBConnection)context.getBean("DBConnection");
		dataSource=(MysqlDataSource)context.getBean("dataSource");
		dataSource.setUser(username);
		dataSource.setPassword(password);
		try {
			dataSource.getConnection();
			factory=new WindowFactory(connection);
			mainMenu=factory.create_window(username);
			mainMenu.setLocation(350, 250);
			mainMenu.setVisible(true);
			setVisible(false);
		} catch(SQLException e) {
			errorMessage.setText("Wrong username or password!");
		} catch(NullPointerException e) {
			errorMessage.setText("User not supported!");
		}
		/* TODO: in case of authentication error
		System.out.println(e.getMessage()); 
		errorMessage.setText("Wrong username!");
		revalidate();*/
	}
	
}

public class GameShop {

	public static void main(String[] args) {
		new LoginWindow();
	}

}
