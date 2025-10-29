package koo.obs;

import koo.pro.Product;
import koo.war.Warehouse;

public class Observer implements StockAlert,Runnable {
     private Warehouse w;
     private boolean run=true;
     public Observer(){
   	 
   	  }
       public Observer(Warehouse w){
    	  this.w=w;
    	  }
      public void  run(){
    	  while(run) {
    	               try {
    	            	  Thread.sleep(3000);
    	            	  checkThreshold();
    	               }catch(InterruptedException e) {
    	            	   System.out.println("Threads are interrupt");
    	               }
    	  }
       }
      private void checkThreshold() {
    	  
    	   for(Product p:w.getProductList()) {
    		   if(p.getProQnty()<p.getReorderThreshold()) {
    			   alertService(p);
    		   }
    	   }
      }
      public void alertService(Product p) {
    	  if(p!=null) {
    		  System.out.println();
    		  System.out.println("     ------ Restock Alert: " + p.getProName() + " is below the reorder threshold!------");
    	  }
      }
      public void stopObserver() {
    	  System.out.println("Inventory monitor has been stopped.");
    	  run=false;
      }
}
