package koo.pro;

public class Product {
     int ProId;
     String ProName;
     int ProQnty=0;
     int ReorderThreshold;
     
	 public int getProId() {
		 return ProId;
	 }
	 public void setProId(int proId) {
		 ProId = proId;
	 }
	 public String getProName() {
		 return ProName;
	 }
	 public void setProName(String proName) {
		 ProName = proName;
	 }
	 public int getProQnty() {
		 return ProQnty;
	 }
	 public void setProQnty(int proQnty) {
		 ProQnty = proQnty;
	 }
	 public int getReorderThreshold() {
		 return ReorderThreshold;
	 }
	 public void setReorderThreshold(int reorderThreshold) {
		 ReorderThreshold = reorderThreshold;
	 }
     
}
