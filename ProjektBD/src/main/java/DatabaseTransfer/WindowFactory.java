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
	public JFrame create_window(String user,DBConnection connection)
	{
		int x=150,y=75;
		window=new JFrame();
		window.setSize(1000,500);
		window.setLayout(new GridLayout(3,4));
		buttons=new HashMap<String,JButton>();
		buttons.put("display_cash",new JButton("Display cash"));
		buttons.put("pay_salary",new JButton("Pay salary"));
		buttons.put("add_worker",new JButton("Add worker"));
		buttons.put("update_worker",new JButton("Update worker"));
		buttons.put("remove_worker",new JButton("Remove worker"));
		buttons.put("add_to_invoice",new JButton("Add to invoice"));
		buttons.put("add_product",new JButton("Add product"));
		buttons.put("display_products",new JButton("Display products"));
		buttons.put("display_invoices",new JButton("Display invoices"));
		buttons.put("create_invoice",new JButton("Create invoice"));
		buttons.put("restock",new JButton("Restock"));
		buttons.put("add_client",new JButton("Add client"));
		
		buttons.get("display_cash").addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
	    		JFrame newWindow=new JFrame("Display cash");
	        	newWindow.setSize(300,300);
	        	newWindow.setLayout(new FlowLayout(FlowLayout.LEFT));
	        	JTextField terminalIDField=new JTextField(14);
	        	JTextField dateField=new JTextField(14);
	        	JButton submitButton=new JButton("Submit");
	        	newWindow.add(terminalIDField);
	        	newWindow.add(dateField);
	        	newWindow.add(submitButton);
	        	newWindow.setVisible(true);
	        	submitButton.addMouseListener(new MouseAdapter(){
	        		public void mousePressed(MouseEvent e) {
	        			String result=connection.displayCash(terminalIDField.getText(), dateField.getText());
	    	    		window.add(new JLabel(result));
	    	    		window.revalidate();
	        		}
	        	});
	    	}
	    });
		
		buttons.get("pay_salary").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		 //connection.pay_salary();
	    		
	    	}
	    });
		buttons.get("add_worker").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		 //connection.add_worker();
	    		
	    	}
	    });
		buttons.get("update_worker").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		//connection.update_worker();
	    		
	    	}
	    });
		buttons.get("remove_worker").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		// connection.remove_worker();
	    		
	    	}
	    });
		buttons.get("add_to_invoice").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		 //connection.add_to_invoice();
	    		
	    	}
	    });
		buttons.get("display_products").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		//connection.display_products();
	    		
	    	}
	    });
		buttons.get("create_invoice").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    	//connection.create_invoice();
	    		
	    	}
	    });
		buttons.get("restock").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		 connection.restock();
	    		
	    	}
	    });
		buttons.get("add_client").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    	//connection.add_client();
	    		
	    	}
	    });
		buttons.get("add_product").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		//connection.add_product();
	    		
	    	}
	    });
		
		
		if(user.equals("Admin")) {
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

		} else if(user.equals("Boss")) {
			window.add(buttons.get("display_cash"));
			window.add(buttons.get("add_worker"));
			window.add(buttons.get("update_worker"));
			window.add(buttons.get("remove_worker"));
			window.add(buttons.get("display_invoices"));
		} else if(user.equals("Manager")) {
			window.add(buttons.get("display_cash"));
			window.add(buttons.get("add_worker"));
			window.add(buttons.get("update_worker"));
			window.add(buttons.get("remove_worker"));
			window.add(buttons.get("display_invoices"));
			window.add(buttons.get("add_to_invoice"));
			window.add(buttons.get("add_product"));
			window.add(buttons.get("display_products"));
			window.add(buttons.get("display_invoices"));
			window.add(buttons.get("create_invoice"));
			window.add(buttons.get("restock"));
		} else if(user.equals("Accountant")) {
			window.add(buttons.get("pay_salary"));
			window.add(buttons.get("display_invoices"));
			window.add(buttons.get("display_cash"));
		} else if(user.equals("ShopAssistant")) {
			window.add(buttons.get("add_to_invoice"));
			window.add(buttons.get("add_product"));
			window.add(buttons.get("display_products"));
			window.add(buttons.get("display_invoices"));
			window.add(buttons.get("create_invoice"));
			window.add(buttons.get("restock"));
		} else if(user.equals("Client")) {
			window.add(buttons.get("add_client"));
			window.add(buttons.get("display_products"));
		} else {
			return null;
		}
		return window;
	}
	
}
