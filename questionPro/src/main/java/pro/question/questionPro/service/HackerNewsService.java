package pro.question.questionPro.service;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import pro.question.questionPro.dto.StoryDto;
import pro.question.questionPro.dto.commentDto;
import pro.question.questionPro.model.TopStoryModel;

@Service
public interface HackerNewsService {
	 
	public List<StoryDto> getTopStories();
	
	public List<TopStoryModel> getPastStories();
	public List<commentDto> getComments(Long StoryId);
    }
