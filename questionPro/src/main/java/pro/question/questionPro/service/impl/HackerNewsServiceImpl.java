package pro.question.questionPro.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pro.question.questionPro.client.HackerNewsApiClient;
import pro.question.questionPro.dto.ItemDto;
import pro.question.questionPro.dto.StoryDto;
import pro.question.questionPro.dto.commentDto;
import pro.question.questionPro.model.TopStoryModel;
import pro.question.questionPro.repository.TopStoryRepository;
import pro.question.questionPro.service.HackerNewsService;

@Service
public class HackerNewsServiceImpl implements HackerNewsService {
  
	@Autowired
	private TopStoryRepository topStoryRepo;
	
	@Autowired
	private HackerNewsApiClient hackerNewsApiClient;

	@Autowired
	public HackerNewsServiceImpl(HackerNewsApiClient hackerNewsApiClient) {
		this.hackerNewsApiClient = hackerNewsApiClient;
	}

	@Override
	@Cacheable(cacheNames = "topStory")
	public List<StoryDto> getTopStories() {

		List<Long> storyIds = hackerNewsApiClient.getTopStories();
		List<StoryDto> stories = storyIds.stream().map(id -> hackerNewsApiClient.getItem(id))
				.filter(item -> item.getType().equals("story"))
				.map(item -> new StoryDto(item.getId(), item.getTitle(), item.getUrl(), item.getScore(), item.getTime(),
						item.getBy()))
				.sorted(Comparator.comparing(StoryDto::getScore).reversed()).limit(10).collect(Collectors.toList());

		saveTopStories(stories);

		return stories;
	}

	public void saveTopStories(List<StoryDto> Stories) {

		for (StoryDto topStory : Stories) {
			TopStoryModel story = new TopStoryModel(topStory.getId(), topStory.getTitle(), topStory.getUrl(),
					topStory.getScore(), topStory.getTime(), topStory.getBy());
			topStoryRepo.save(story);
			
		}
	

	}


	public List<TopStoryModel> getPastStories() {

		return topStoryRepo.findAll();

	}

	@Override
	public List<commentDto> getComments(Long StoryId){
		  List<commentDto> comments = new ArrayList<>();
	        ItemDto item = hackerNewsApiClient.getItem(StoryId);
	        if (item != null && item.getKids() != null) {
	            for (Long commentId : item.getKids()) {
	                ItemDto comment = hackerNewsApiClient.getItem(commentId);
	                if (comment != null && comment.getType().equals("comment")) {
	                    commentDto commentDto = new commentDto(
	                    		comment.getId(),
	                    		comment.getKids() ,
	                    		comment.getParent(),
	                            comment.getText(),
	                            comment.getTime(),
	                            comment.getType(),
	                            comment.getBy()
	                          );
	                    comments.add(commentDto);
	                }
	            }
	        }
	        comments.sort(Comparator.comparing(commentDto::getKidSize).reversed());
	        return comments.subList(0, Math.min(comments.size(), 10));
	    }
	

}
