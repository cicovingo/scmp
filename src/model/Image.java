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
public class Image{
	private Long imageId;
	private String imagePath;
}
