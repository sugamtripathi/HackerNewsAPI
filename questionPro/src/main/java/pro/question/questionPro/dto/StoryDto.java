package pro.question.questionPro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryDto {


		private int id;
	    private String title;
	    private String url;
	    private Integer score;
	    private Long time;
	    private String by;
	    
	   
	
}
