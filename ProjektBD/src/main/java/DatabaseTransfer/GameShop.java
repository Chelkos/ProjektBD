package DatabaseTransfer;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

class LoginWindow extends JFrame{
	JTextField loginField;
	JPasswordField passwordField;
	JButton acceptButton;
	JLabel errorMessage;
	
	public LoginWindow() {
		super("Login");
		setSize(200, 200);
		setResizable(true);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		loginField=new JTextField(14);
		passwordField=new JPasswordField(14);
		acceptButton=new JButton("Login");
		errorMessage=new JLabel();
		loginField.setSize(200, 70);
		passwordField.setSize(200,50);
		passwordField.setEchoChar('*');
		add(new JLabel("Login: "));
		add(loginField);
		add(new JLabel("Password: "));
		add(passwordField);
		acceptButton.setSize(50,150);
		acceptButton.addMouseListener(new MouseAdapter(){
      	public void mousePressed(MouseEvent e) {
      		if(!loginField.getText().equals("Client"))
      		setupDataSource(loginField.getText(), new String(passwordField.getPassword()));
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
		dataSource=(MysqlDataSource)context.getBean("dataSource2");
		try {
			dataSource.setUser("kacper");
			dataSource.setPassword("12345");
			JdbcTemplate jdbcTemplateObject=new JdbcTemplate(dataSource);
			String SQL="SHOW DATABASES;";
			List<String> databases=jdbcTemplateObject.queryForList(SQL, String.class);
			if(databases.contains("gameshop")) {
				dataSource=(MysqlDataSource)context.getBean("dataSource2");
				dataSource.setUser(username);
				dataSource.setPassword(password);
				dataSource.getConnection();
				factory=new WindowFactory(connection);
				mainMenu=factory.create_window(username);
				mainMenu.setLocation(350, 250);
				mainMenu.setVisible(true);
			} else {
				throw new Exception("Database doesn't exist!");
			}
			setVisible(false);
		} catch(SQLException e) {
			errorMessage.setText("Wrong username or password!");
		} catch(NullPointerException e) {
			errorMessage.setText("User not supported!");
		} catch(Exception e) {
			errorMessage.setText(e.getMessage());
		}
	}
	
}

public class GameShop {

	public static void main(String[] args) {
		new LoginWindow();
	}

}
