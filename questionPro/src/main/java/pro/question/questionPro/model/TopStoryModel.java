package pro.question.questionPro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TOPSTORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopStoryModel {
 
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="TITLE")
    private String title;
	
	@Column(name="URL")
    private String url;
	
	@Column(name="SCORE")
    private Integer score;
	
	@Column(name="TIME")
    private Long time;
	
	@Column(name="BY")
    private String by;
    
}
