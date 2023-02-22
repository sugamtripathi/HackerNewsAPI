package pro.question.questionPro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pro.question.questionPro.dto.StoryDto;
import pro.question.questionPro.dto.commentDto;
import pro.question.questionPro.model.TopStoryModel;
import pro.question.questionPro.service.impl.HackerNewsServiceImpl;


//@RequestMapping("/api")
@RestController
public class Controller {

	
	
	 
	    @GetMapping("/test")
	    public String test() {
	    	return "health OK";
	    }
	    
   @Autowired	    
	    private HackerNewsServiceImpl hackerNewsService;

	    @GetMapping("/top-stories")
	    public List<StoryDto> getTopStories() {
	    	System.out.println("inside top stories");
	        return hackerNewsService.getTopStories();
	    }
	    
	    
	    @GetMapping("/past-stories")
	    public List<TopStoryModel> getPastStories(){
	    	return hackerNewsService.getPastStories();
	    } 
	    
	    @GetMapping("/{storyId}/sorted")
	    public List<commentDto> getCommentsSortedByChildCount(@PathVariable Long storyId) {
	        return hackerNewsService.getComments(storyId);
	    }
	    

}
