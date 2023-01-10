package model;
import java.util.Date;
import lombok.*;
/*
 * author: sukru.okul
 * */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC) 
@ToString  
@Getter @Setter
public class OrderProduct{
	private  Long orderId;
	private Long productId;
	private int quantity;
	private double totalAmount;
	private boolean isAprove;
}
