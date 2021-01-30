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
	private Map<String,Button> buttons;
	private String user;
	WindowFactory(String user,DataSource dataSource)
	{
	this.user=user;
	int x=50,y=50;
	window = new JFrame();
	window.setSize(500,500);
	buttons.put( "display_cash",new Button());
	buttons.put( "pay_salary",new Button());
	buttons.put( "add_worker",new Button());
	buttons.put( "update_worker",new Button());
	buttons.put( "remove_worker",new Button());
	buttons.put( "add_to_invoice",new Button());
	buttons.put( "add_product",new Button());
	buttons.put( "display_products",new Button());
	buttons.put( "display_invoices",new Button());
	buttons.put( "create_invoice",new Button());
	buttons.put( "restock",new Button());
	buttons.put( "add_client",new Button());
	
	
	buttons.get("display_cash").setSize(x, y);
	buttons.get("pay_salary").setSize(x, y);
	buttons.get("add_worker").setSize(x, y);
	buttons.get("update_worker").setSize(x, y);
	buttons.get("remove_worker").setSize(x, y);
	buttons.get("add_to_invoice").setSize(x, y);
	buttons.get("display_products").setSize(x, y);
	buttons.get("display_invoices").setSize(x, y);
	buttons.get("create_invoice").setSize(x, y);
	buttons.get("restock").setSize(x, y);
	buttons.get("add_client").setSize(x, y);
	buttons.get("add_product").setSize(x, y);
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
	
	
	if(user=="Admin")
	{
		
		
		
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
	}
	
	
}
