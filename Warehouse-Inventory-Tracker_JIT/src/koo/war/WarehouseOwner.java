package koo.war;

import java.util.InputMismatchException;
import java.util.Scanner;

import koo.obs.Observer;
import koo.pro.Product;

public class WarehouseOwner {
	static public int safeInt(Scanner sc) {
		int x = 0;
		while (true) {
			try {
				x = sc.nextInt();
				if (x < 0) {
					System.out.println("please enter the positive number");
					continue;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Please enter the valid Integer Number");
				sc.nextLine();
			}
		}
		return x;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice;
		Warehouse w = new Warehouse();
	    Observer o=new Observer(w);
	    Thread t=new Thread(o);
	    t.setDaemon(true);
	    t.start();
		while (true) {
			System.out.println("1 Add Product");
			System.out.println("2 Add New Stock");
			System.out.println("3 Order The Product");
			System.out.println("4 Show Product");
			System.out.println("5 Exit");
			System.out.println("Please enter your choice:");
			choice = safeInt(sc);
			sc.nextLine();
			switch (choice) {
			case 1: {
				Product pro = new Product();
				System.out.println("Enter Product ID:");
				pro.setProId(safeInt(sc));
				sc.nextLine();
				System.out.println("Enter Product Name:");
				pro.setProName(sc.nextLine());
				System.out.println("Enter Reorder Threshold:");
				pro.setReorderThreshold(safeInt(sc));
				sc.nextLine();
				w.addPro(pro);
				
				break;
			}
			case 2: {
				Product pro = new Product();
				System.out.println("Enter the Product id for Re-stock");
				pro.setProId(safeInt(sc));
				System.out.println("Enter Quantity:");
				int q=safeInt(sc);
				Thread t1=new Thread(()->w.shipmentsArrive(pro,q),"shipments-thread");
				t1.start();
				try {
				t1.join();
				}catch(InterruptedException e) {
					System.out.println("exception "+e);
				}
				break;
			}
			case 3: {
				Product pro = new Product();
				System.out.println("Enter the Product name");
				pro.setProName(sc.nextLine());
				System.out.println("Enter Quantity:");
				Thread t2=new Thread(()->w.fullFillOrders(pro,safeInt(sc)),"order-thread");
				t2.start();
				try {
					t2.join();
					}catch(InterruptedException e) {
						System.out.println("exception "+e);
					}
				break;

			}
			case 4: {
				w.showPro();
				break;
			}
			case 5: {
				o.stopObserver();
				System.out.println("Application exited successfully. Thank you for using the Warehouse Inventory Tracker.");
				System.exit(0);
			}

			default: {
				System.out.println("Invalid choice. Please try again.");
			}
			
			}
		}
	}
}
