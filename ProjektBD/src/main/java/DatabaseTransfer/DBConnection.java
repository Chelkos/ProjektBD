package DatabaseTransfer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mysql.cj.jdbc.MysqlDataSource;



public class DBConnection {
	private String result;
    private MysqlDataSource dataSource;
    private JFrame window;
    private JdbcTemplate jdbcTemplateObject;
    public void setDataSource(MysqlDataSource dataSource){
    	  window = new JFrame();
          this.dataSource = dataSource;
          this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    public String displayCash(String whatCashierId, String whatDate)
    { 
    	 String SQL = "SELECT display_cash(?,?);";
    	
	    			int whatCashierIdInt;
	    			try {
	    			whatCashierIdInt = Integer.parseInt( whatCashierId);
	    			 result = jdbcTemplateObject.queryForObject(SQL,String.class,whatCashierIdInt, whatDate);
	    		}
	    		catch(Exception E)
	    		{
	    			return "No invoices for this data";
	    		}
       return result;
    }
    public String paySalary(String amountString,String position)
    {
    	String SQL = "CALL pay_salary(?,?);";
	    		try {
	    			Float amount;
	    			amount = Float.parseFloat(amountString);
	    			result = jdbcTemplateObject.queryForObject(SQL,String.class,amount, position);
	    		}
	    		catch(Exception E)
	    		{
	    			return "Wrong data";
	    		}
    	return result;
    }
    public String addWorker(String identifier,String name,String surname,String PESEL,String position,String wage)
    {
    	String SQL = "CALL add_worker(?,?,?,?,?,?);";
		try {
			int identifierInt = 0;
			long PESELLong = 0;
			float wageFloat = 0;
			PESELLong = Long.parseLong(PESEL);
			identifierInt = Integer.parseInt(identifier);
			wageFloat = Float.parseFloat(wage);
			result = jdbcTemplateObject.queryForObject(SQL,String.class,identifierInt,name,surname,PESELLong,position,wageFloat);
		}
		catch(Exception E)
		{
			return "Wrong data";
		}
return result;
    }
    public String updateWorker(String identifier, String name, String surname, String PESEL, String position, String wage)
    {
    	long PESELLong =0;
    	int identifierInt=0;
    	float wageFloat =0;
    	String SQL = "CALL update_worker(?,?,?,?,?,?);";
    	try
    	{
    		identifierInt = Integer.parseInt(identifier);
    		
    		if(PESEL.equals("")) {
    			PESELLong=0;
    		} else {
    			PESELLong = Long.parseLong(PESEL);
    		}
    		
    		if(wage.equals("")) {
    			wageFloat=0.0F;
    		} else {
    			wageFloat = Float.parseFloat(wage);
    		}
    		
			result = jdbcTemplateObject.queryForObject(SQL,String.class,identifierInt,name,surname,PESELLong,position,wageFloat);
    		
    	}
    	catch(Exception E)
    	{
    		return E.getMessage();
    	}
    	return result;
    }
    public String removeWorker(String identifier)
    {
    	int identifierInt=0;
    	String SQL = "CALL remove_worker(?);";
    	try
    	{
    		identifierInt = Integer.parseInt(identifier);
			result = jdbcTemplateObject.queryForObject(SQL,String.class,identifierInt);
    	}
    	catch(Exception E)
    	{
    		return "Wrong data";
    	}
    	return result;
    }
    public String addToInvoice(String invoice_id,String product_id, String product_amount)
    {
    	int invoiceIdInt =0;
    	int productIdInt =0;
    	int productAmountInt =0;
    	String SQL = "CALL add_to_invoice(?,?,?);";
    	try
    	{
    		invoiceIdInt = Integer.parseInt(invoice_id);
    		productIdInt = Integer.parseInt(product_id);
    		productAmountInt = Integer.parseInt(product_amount);
			result = jdbcTemplateObject.queryForObject(SQL,String.class,invoiceIdInt,productIdInt, productAmountInt);
    	}
    	catch(Exception E)
    	{
    		return "Wrong data";
    	}
    	return result;
    }
    public String addProduct(String name,String developer, String release_date, String genre,String PEGI,String price)
    {
     int PEGIInt = 0;
     float priceFloat =0;
     String SQL = "CALL add_product(?,?,?,?,?,?);";
     try
 	{
    	 if(release_date.equals(""))
    		 release_date=null;
 		 PEGIInt = Integer.parseInt(PEGI);
 		 priceFloat = Float.parseFloat(price);
		 result = jdbcTemplateObject.queryForObject(SQL,String.class,name,developer,release_date,genre,PEGIInt,priceFloat);
 	}
 	catch(Exception E)
 	{
 		return "Wrong data";
 	}
    	return result;
    }
    public List<Product> displayProducts(String name,String developer,String lowerBound, String upperBound)
    {
    	Float lowerBoundF;
    	Float upperBoundF;
    	List<Product> table = new ArrayList<Product>();
    	String SQL = "CALL display_products(?,?,?,?);";
    	try {
    		 if(lowerBound.equals("") || upperBound.equals("")) {
    			 lowerBoundF=null; upperBoundF=null;
    		 } else {
    			 lowerBoundF = Float.parseFloat(lowerBound);
        		 upperBoundF = Float.parseFloat(upperBound);
    		 }
    		 table = jdbcTemplateObject.query(SQL,new ProductsMapper(),name,developer,lowerBoundF,upperBoundF);
    	}
    	catch(Exception e)
    	{
    		table.clear();
    		return table;
    	}
    	return table;
    }
    public List<Invoice> displayInvoices(String dateOfIssue)
    {
    	List<Invoice> table = new ArrayList<Invoice>();
    	String SQL = "CALL display_invoices(?);";
    	 ResultSet rs;
    	try {
    		table = jdbcTemplateObject.query(SQL,new InvoicesMapper(),dateOfIssue);
    	}
    	catch(Exception e)
    	{
    		table.clear();
    		return table;
    		
    	}
    	
    	
    	return table;
    }
    public String createInvoice(String customerName,String customerSurname,String NIP,String workerId,String terminalId,String dateOfIssue)
    {
    	String SQL = "CALL create_invoice(?,?,?,?,?,?);";
    	long NIPL = 0;
    	int workerIdInt = 0;
    	int terminalIdInt = 0;
    	try {
    		NIPL = Long.parseLong(NIP);
    		workerIdInt = Integer.parseInt(workerId);
    		terminalIdInt = Integer.parseInt(terminalId);
    		result = jdbcTemplateObject.queryForObject(SQL,String.class,customerName,customerSurname,NIPL,workerIdInt,terminalIdInt,dateOfIssue);
    	}
    	catch(Exception e)
    	{
    		return "Wrong data";
    	}
    	return result;
    }
    public String restock(String gameName,String gameDeveloper,String amount)
    {
    	String SQL = "CALL restock(?,?,?)";
    	int amountInt =0;
    	try {
    		amountInt = Integer.parseInt(amount);
    		result = jdbcTemplateObject.queryForObject(SQL,String.class,gameName,gameDeveloper,amountInt);
    		
    	}
    	catch(Exception e)
    	{
    		
    		return "Wrong data";
    	}
    	return result;
    }
    public String addClient(String email,String login,String password)
    {
    	String SQL = "CALL add_client(?,?,?)";
    	try {
    		
    		result = jdbcTemplateObject.queryForObject(SQL,String.class,email,login,password);
    	}
    	catch(Exception e)
    	{
    		return "Wrong data!";
    		
    	}
    	return result;
    }
    public String databaseBackup(String savePath)
    {
    	String executeCmd = "mysqldump -u Admin -p Adminpass --database gameshop -r " + savePath;
    	 try {
    		 
    		 Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
    	        int processComplete = runtimeProcess.waitFor();
    	        if (processComplete == 0) {
    	            return "Backup Complete";
    	        } else {
    	           return "Backup Failure";
    	        }
    	 }
    	 catch(Exception e)
    	 {
    		 e.getMessage();
    	 }
    	return result;
    	
    }
    public String databaseRestore(String restorePath)
    {
    	 String[] executeCmd = new String[]{"mysql gameshop -u Admin -p Adminpass -e", " source " + restorePath};
    	try {
    		 Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
             int processComplete = runtimeProcess.waitFor();
             if (processComplete == 0) {
                return "Success";
             } else {
                return "Failure";
             }
    		
    	}
    	catch(Exception e)
    	{
    		e.getMessage();
    	}
    	return result;
    }
    public class Invoice
    {
    	int id;
    	String customerName;
    	String customerSurname;
    	long NIP;
    	int workerId;
    	int terminalId;
    	String dateOfIssue;
    	float netto;
    	float tax;
    }
    public class Product
    {
    	String name;
    	String developer;
    	Date releaseDate;
    	String genre;
    	int PEGI;
    	float price;
    }
    private class InvoicesMapper implements RowMapper<Invoice>{

    	 @Override
    		public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {
    			Invoice invoice = new Invoice();
    			invoice.id=rs.getInt("id");
    			invoice.customerName=rs.getString("customer_name");
    			invoice.customerSurname=rs.getString("customer_surname");
    			invoice.NIP=rs.getLong("NIP");
    			invoice.workerId=rs.getInt("worker_id");
    			invoice.terminalId=rs.getInt("terminal_id");
    			invoice.dateOfIssue=rs.getString("date_of_issue");
    			invoice.netto=rs.getFloat("netto");
    			invoice.tax=rs.getFloat("tax");
    			return invoice;
    		}
}
    private class ProductsMapper implements RowMapper<Product>{

   	 @Override
   		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
   			Product product = new Product();
   			product.name=rs.getString("game_name");
   			product.developer=rs.getString("developer");
   			product.releaseDate=rs.getDate("release_date");
   			product.genre=rs.getString("genre");
   			product.PEGI=rs.getInt("PEGI");
   			product.price=rs.getFloat("price");
   			return product;
   		}
}
    
}