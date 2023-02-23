package pro.question.questionPro.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class commentDto {
	 int id;
	 List<Long> Kids;
	 Long Parent;
	 String text ;							//Comment Text
	 Long Time;
	 String Type;
	 String by;								//user’s hacker news handle
	 
	 int KidSize=0;

	public commentDto(int id, List<Long> kids, Long parent, String text, Long time, String type, String by) {
		
		this.id = id;
		Kids = kids;
		Parent = parent;
		this.text = text;
		Time = time;
		Type = type;
		this.by = by;
		this.KidSize=kids!=null?kids.size():0;
	}
	 
	
}