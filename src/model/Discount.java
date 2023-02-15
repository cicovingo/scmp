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
public class Discount{
	private Long id;
	private String retailerId;
	private Long userId;
	private Date expireDate;
	private double discountRate;
}
