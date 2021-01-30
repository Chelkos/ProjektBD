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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.PrintWriter;
import java.net.Socket;
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
public class WindowFactory {
	private JFrame window;
	private Map<String,JButton> buttons;
	WindowFactory()
	{

	}
	public JFrame create_window(String user,DataSource dataSource)
	{
	
		int x=150,y=75;
		window = new JFrame();
		window.setSize(1000,500);
		window.setLayout(new GridLayout(3,4));
		buttons = new HashMap<String,JButton>();
		buttons.put( "display_cash",new JButton("Display cash"));
		buttons.put( "pay_salary",new JButton("Pay salary"));
		buttons.put( "add_worker",new JButton("Add worker"));
		buttons.put( "update_worker",new JButton("Update worker"));
		buttons.put( "remove_worker",new JButton("Remove worker"));
		buttons.put( "add_to_invoice",new JButton("Add to invoice"));
		buttons.put( "add_product",new JButton("Add product"));
		buttons.put( "display_products",new JButton("Display products"));
		buttons.put( "display_invoices",new JButton("Display invoices"));
		buttons.put( "create_invoice",new JButton("Create invoice"));
		buttons.put( "restock",new JButton("Restock"));
		buttons.put( "add_client",new JButton("Add client"));
		
		

		buttons.get("display_cash").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		buttons.get("pay_salary").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		buttons.get("add_worker").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		buttons.get("update_worker").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		buttons.get("remove_worker").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		buttons.get("add_to_invoice").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		buttons.get("display_products").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		buttons.get("create_invoice").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		buttons.get("restock").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		buttons.get("add_client").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		buttons.get("add_product").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		
	    		
	    	}
	    });
		
		
		if(user.equals("Admin"))
		{
			window.add(buttons.get("display_cash"));
			window.add(buttons.get("pay_salary"));
			window.add(buttons.get("add_worker"));
			window.add(buttons.get("update_worker"));
			window.add(buttons.get("remove_worker"));
			window.add(buttons.get("add_to_invoice"));
			window.add(buttons.get("add_product"));
			window.add(buttons.get("display_products"));
			window.add(buttons.get("display_invoices"));
			window.add(buttons.get("create_invoice"));
			window.add(buttons.get("restock"));
			window.add(buttons.get("add_client"));

		}
		if(user=="Boss")
		{
			
			
		}
		if(user=="Manager")
		{
			
			
		}
		if(user=="Accountant")
		{
			
		}
		if(user=="ShopAssistant")
		{
			
		}
		if(user=="Client")
		{
			
		}
		return window;
	}
	
}
