package koo.war;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import koo.pro.Product;
public class Warehouse {
	private Map<Integer,Product> m = new HashMap<Integer,Product>();
	public synchronized List<Product> getProductList(){
		List<Product> l=new ArrayList<Product>();
		for(Map.Entry<Integer,Product> e:m.entrySet()){
			Product pro=new Product();
			pro.setProId(e.getValue().getProId());
			pro.setProName(e.getValue().getProName());
			pro.setProQnty(e.getValue().getProQnty());
			pro.setReorderThreshold(e.getValue().getReorderThreshold());
			l.add(pro);	
		}
		return l;
	}
	public  synchronized void addPro(Product pro) {
		
		if (pro != null){
			if(!m.containsKey(pro.getProId())) {
		    m.put(pro.getProId(), pro);
			System.out.println(pro.getProName()+" is successfully added");
			}
			else {
				System.out.println("Product ID " + m.get(pro.getProId()).getProId() + " already exists for product: " + m.get(pro.getProId()).getProName());
			}
		}
	}
	public synchronized void shipmentsArrive(Product pro, int qnty) {
		if(pro!=null&&qnty>=0) {
		if(m.containsKey(pro.getProId())) {
			 Product p=m.get(pro.getProId());
			 p.setProQnty(p.getProQnty()+qnty);
			 System.out.println("Stock updated successfully for product: " + p.getProName());
		}	
		else{
			System.out.println("Product not found in the warehouse.");
		}
		}
		else {
			System.out.println(Thread.currentThread().getName() + ": Invalid input. Please check the arguments.");
		}
	}
	public  synchronized void fullFillOrders(Product pro,int order) {
		if(pro!=null&&order>=0) {
		boolean flag=false;
	     for(Map.Entry<Integer,Product> e:m.entrySet()) {
	    	 if(e.getValue().getProName().equalsIgnoreCase(pro.getProName())) {
	    		 if(e.getValue().getProQnty()>=order) {
	    			 e.getValue().setProQnty(e.getValue().getProQnty()-order);
	    			 System.out.println("Order placed successfully for product: " + pro.getProName().toLowerCase());
	    		 }
	    		 else {
	    			 System.out.println("Currently out of stock. Sorry, the order cannot be fulfilled.");
	    		 }
	    		flag=true;
	    		break;
	    	 }
	     }
		if(!flag) {
			System.out.println("Product not found. Please check the product name.");
		}
		}
		else {
			System.out.println(Thread.currentThread().getName() + ": Invalid input. Please check the arguments.");
		}
	}
	public  synchronized void showPro() {
		System.out.println("Available Products in Warehouse:");
		System.out.println("----------------------------------------------------------------------");
		if(!m.isEmpty()) {
		for(Map.Entry<Integer,Product> e:m.entrySet()) {
			System.out.println("ProductId: "+e.getValue().getProId());
			System.out.println("ProductName: "+e.getValue().getProName());
			System.out.println("ProductQnty: "+e.getValue().getProQnty());
			System.out.println("ProductThreshold: "+e.getValue().getReorderThreshold());
			System.out.println("----------------------------------------------------------------------");
			
		}
		System.out.println("Total number of products: " + m.size());
		System.out.println("----------------------------------------------------------------------");
		}
		else {
			System.out.println("The warehouse is currently empty.");
		}
		
	}
}
