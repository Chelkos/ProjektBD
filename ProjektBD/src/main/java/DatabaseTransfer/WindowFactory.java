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
import java.util.List;
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

import DatabaseTransfer.DBConnection.Invoice;
import DatabaseTransfer.DBConnection.Product;

class MultiLabelWindow extends JFrame {
	private JTextField[] textFields;
	private JLabel messageLabel;
	private JButton submitButton;
	
	public MultiLabelWindow(String title, String labels[]) {
		super(title);
		setSize(200, 100+50*labels.length);
		setResizable(true);
    	setLayout(new FlowLayout(FlowLayout.LEFT));
    	textFields=new JTextField[labels.length];
		messageLabel=new JLabel();
		submitButton=new JButton("Submit");
		for(int i=0; i<labels.length; i++) {
			add(new JLabel(labels[i]));
			textFields[i]=new JTextField(14);
			add(textFields[i]);
		}
    	add(submitButton);
    	add(messageLabel);
    	setLocation(750, 400-25*labels.length);
    	setVisible(true);
	}
	
	public String[] getParameters() {
		String[] parameters= new String[textFields.length];
		for(int i=0; i<textFields.length; i++) {
			parameters[i]=textFields[i].getText();
		}
		return parameters;
	}
	
	public void setMessage(String message) {
		messageLabel.setText(message);
		revalidate();
	}
	
	public void setMouseListener(MouseAdapter mouseAdapter) {
		this.submitButton.addMouseListener(mouseAdapter);
	}
	
}

public class WindowFactory {
	private JFrame window;
	private Map<String,JButton> buttons;
	WindowFactory(DBConnection connection)
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
	    		String[] labels= new String[]{"Terminal ID: ", "Date: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Display cash", labels);
	        	newWindow.setMouseListener(new MouseAdapter(){
	        		public void mousePressed(MouseEvent e) {
	        			String[] parameters=newWindow.getParameters();
	        			String result=connection.displayCash(parameters[0], parameters[1]);
	        			if(!result.equals("No invoices for this data")) {
	        				result="Total cash: " + result;
	        			}
	        			newWindow.setMessage(result);
	        		}
	        	});
	    	}
	    });
		
		buttons.get("pay_salary").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels= new String[]{"Amount: ", "Position: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Pay salary", labels);
	        	newWindow.setMouseListener(new MouseAdapter(){
	        		public void mousePressed(MouseEvent e) {
	        			String[] parameters=newWindow.getParameters();
	        			String result=connection.paySalary(parameters[0], parameters[1]);
	    	    		newWindow.setMessage(result);
	        		}
	        	});
	    	}
	    });
		
		buttons.get("add_worker").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels= new String[]{"Identifier: ", "Name: ", "Surname: ", "PESEL: ", "Position: ", "Wage: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Add worker", labels);
	        	newWindow.setMouseListener(new MouseAdapter(){
	        		public void mousePressed(MouseEvent e) {
	        			String[] parameters=newWindow.getParameters();
	        			String result=connection.addWorker(parameters[0], parameters[1], parameters[2]
	        					, parameters[3], parameters[4], parameters[5]);
	    	    		newWindow.setMessage(result);
	        		}
	        	});
	    	}
	    });
		
		buttons.get("update_worker").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels= new String[]{"Identifier: ", "Name: ", "Surname: ", "PESEL: ", "Position: ", "Wage: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Update worker", labels);
	        	newWindow.setMouseListener(new MouseAdapter(){
	        		public void mousePressed(MouseEvent e) {
	        			String[] parameters=newWindow.getParameters();
	        			String result=connection.updateWorker(parameters[0], parameters[1], parameters[2]
	        					, parameters[3], parameters[4], parameters[5]);
	    	    		newWindow.setMessage(result);
	        		}
	        	});
	    	}
	    });
		
		buttons.get("remove_worker").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels=new String[]{"Identifier: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Remove worker", labels);
	    		newWindow.setMouseListener(new MouseAdapter(){
	        		public void mousePressed(MouseEvent e) {
	        			String[] parameters=newWindow.getParameters();
	        			String result=connection.removeWorker(parameters[0]);
	    	    		newWindow.setMessage(result);
	        		}
	        	});
	    	}
	    });
		
		buttons.get("add_to_invoice").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels=new String[]{"Invoice ID: ", "Product ID: ", "Product amount: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Add to invoice", labels);
	    		newWindow.setMouseListener(new MouseAdapter(){
		        	public void mousePressed(MouseEvent e) {
		        		String[] parameters=newWindow.getParameters();
		        		String result=connection.addToInvoice(parameters[0], parameters[1], parameters[2]);
		    	    	newWindow.setMessage(result);
		        	}
		        });
	    	}
	    });
		
		buttons.get("add_product").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels= new String[]{"Name: ", "Developer: ", "Release date: ", "Genre: ", "PEGI: ", "Price: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Add product", labels);
	        	newWindow.setMouseListener(new MouseAdapter(){
	        		public void mousePressed(MouseEvent e) {
	        			String[] parameters=newWindow.getParameters();
	        			String result=connection.addProduct(parameters[0], parameters[1], parameters[2]
	        					, parameters[3], parameters[4], parameters[5]);
	    	    		newWindow.setMessage(result);
	        		}
	        	});
	    	}
	    });
		
		buttons.get("display_products").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels= new String[]{"Name: ", "Developer: ", "Lower bound: ", "Upper bound: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Display products", labels);
	        	newWindow.setMouseListener(new MouseAdapter(){
	        		public void mousePressed(MouseEvent e) {
	        			String[] parameters=newWindow.getParameters();
	        			List<Product> result=connection.displayProducts(parameters[0], parameters[1], parameters[2]
	        					, parameters[3]);
	        			if(result.isEmpty()) {
	        				newWindow.setMessage("Nothing found.");
	        			} else {
	        				newWindow.setMessage("");
	        				System.out.println("Title; Developer; Release date; Genre; PEGI; Price");
	        				for(Product p : result) {
		        				System.out.println(p.name + ", " + p.developer + ", " + p.releaseDate + ", " +
		        				p.genre + ", " + p.PEGI + ", " + p.price);
		        			}
	        			}
	        		}
	        	});
	    	}
	    });
		
		buttons.get("display_invoices").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels=new String[]{"Date: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Display invoices", labels);
	    		newWindow.setMouseListener(new MouseAdapter(){
	        		public void mousePressed(MouseEvent e) {
	        			String[] parameters=newWindow.getParameters();
	        			List<Invoice> result=connection.displayInvoices(parameters[0]);
	        			if(result.isEmpty()) {
	        				newWindow.setMessage("Nothing found.");
	        			} else {
	        				newWindow.setMessage("");
	        				System.out.println("Date of issue; Terminal ID; Worker ID; Customer's name & surname; NIP; Netto; Tax");
	        				for(Invoice i : result) {
		        				System.out.println(i.dateOfIssue + ", " + i.terminalId + ", " + i.workerId + ", " 
	        				+ i.customerName + i.customerSurname + ", " + i.NIP + ", " 
	        				+ i.netto + ", " + i.tax);
		        			}
	        			}
	        		}
	        	});
	    	}
	    });
		
		buttons.get("create_invoice").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels= new String[]{"Customer name: ", "Customer surname: ", "NIP: ", "Worker ID: ", "Terminal ID: ", "Date of issue: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Create invoice", labels);
	        	newWindow.setMouseListener(new MouseAdapter(){
	        		public void mousePressed(MouseEvent e) {
	        			String[] parameters=newWindow.getParameters();
	        			String result=connection.createInvoice(parameters[0], parameters[1], parameters[2]
	        					, parameters[3], parameters[4], parameters[5]);
	    	    		newWindow.setMessage(result);
	        		}
	        	});
	    	}
	    });
		
		buttons.get("restock").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels=new String[]{"Game title: ", "Developer: ", "Amount: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Restock", labels);
	    		newWindow.setMouseListener(new MouseAdapter(){
		        	public void mousePressed(MouseEvent e) {
		        		String[] parameters=newWindow.getParameters();
		        		String result=connection.restock(parameters[0], parameters[1], parameters[2]);
		    	    	newWindow.setMessage(result);
		        	}
		        });
	    	}
	    });
		
		buttons.get("add_client").addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e) {
	    		String[] labels=new String[]{"Email: ", "Login: ", "Password: "};
	    		MultiLabelWindow newWindow=new MultiLabelWindow("Add client", labels);
	    		newWindow.setMouseListener(new MouseAdapter(){
		        	public void mousePressed(MouseEvent e) {
		        		String[] parameters=newWindow.getParameters();
		        		String result=connection.addClient(parameters[0], parameters[1], parameters[2]);
		    	    	newWindow.setMessage(result);
		        	}
		        });
	    	}
	    });
		
	}
	public JFrame create_window(String user)
	{
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
