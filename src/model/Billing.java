package model;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import lombok.*;
/*
 * author: sukru.okul
 * */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC) 
@ToString  
@Getter @Setter
public class Billing{
	private Long billingId;
	private Long productId;
	private double totalAmount;
	private String productName;
	private double productPrice;
	private int productQuantity;
	
	//Fatura oluþturma metodu sipariþler tedarikçi tarafýndan onaylandýktan sonra çaðýrýlýr
	public void generateBilling(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scmp", "root", "root");
			Statement st = con.createStatement();
			int i = st.executeUpdate("insert into billing(productId,productName,productPrice,productQuantity,totalAmount)values('" + this.getProductId() + "','"
					+ this.getProductName() + "','" + this.getProductPrice() + "','" + this.getProductQuantity() + "','" + this.getTotalAmount() + "')");
		} catch (Exception e) {
			//Veritabaný veya null pointer hatasý
			System.out.println(e.getMessage());
		}
	}
}
