package DatabaseTransfer;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.springframework.jdbc.core.JdbcTemplate;
public class DBConnection {
    private DataSource dataSource;
    private JFrame window;
    private JdbcTemplate jdbcTemplateObject;
    public void setDataSource(DataSource dataSource){
    	  window = new JFrame();
          this.dataSource = dataSource;
          this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    public void display_cash()
    { 
 
    	 window = new JFrame("Display cash");
    	 String SQL = "CALL display_cash(?,?);";
    	 window.setSize(300,300);
    	 window.setLayout(new FlowLayout(FlowLayout.LEFT));
    	 JTextField what_cashier_id = new JTextField(14);
    	 JTextField what_date = new JTextField(14);
    	 JButton submit = new JButton("Submit");
    	 window.add(what_cashier_id);
    	 window.add(what_date);
    	 window.add(submit);
    	 window.setVisible(true);
    	 System.out.println("In!");
    	 submit.addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		try {
	    			int what_cashier_id_int;
	    		 	Float result;
	    		 	System.out.println(what_cashier_id.getText());
	    			what_cashier_id_int = Integer.parseInt(what_cashier_id.getText());
	    			jdbcTemplateObject.update(SQL,what_cashier_id_int, what_date.getText());
	    			result = jdbcTemplateObject.queryForObject(SQL,float.class);
	    			JLabel result_label = new JLabel(result.toString());
	    			window.add(result_label);
	    			
	    			
	    		}
	    		catch(Exception E)
	    		{
	    			E.getMessage();
	    		}
	 
	    	}
	    });
    	
    }
    public JFrame pay_salary()
    {
    	return window;
    }
    public JFrame add_worker()
    {
    	return window;
    }
    public JFrame update_worker()
    {
    	return window;
    }
    public JFrame remove_worker()
    {
    	return window;
    }
    public JFrame add_to_invoice()
    {
    	return window;
    }
    public JFrame add_product()
    {
    	return window;
    }
    public JFrame display_products()
    {
    	return window;
    }
    public JFrame display_invoices()
    {
    	return window;
    }
    public JFrame create_invoice()
    {
    	return window;
    }
    public JFrame restock()
    {
    	return window;
    }
    public JFrame add_client()
    {
    	return window;
    }
    
}
