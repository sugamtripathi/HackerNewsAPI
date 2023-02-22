package pro.question.questionPro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoryDto {

//	    public StoryDto(int id, String title, String url, Integer score, Long time, String by) {
//	    this.id=id;
//		this.title = title;
//		this.url = url;
//		this.score = score;
//		this.time = time;
//		this.by = by;
//	}
		private int id;
	    private String title;
	    private String url;
	    private Integer score;
	    private Long time;
	    private String by;
	    
	   
	
}
