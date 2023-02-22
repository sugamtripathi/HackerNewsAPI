package pro.question.questionPro.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pro.question.questionPro.dto.ItemDto;

@Service
@FeignClient(name = "hacker-news-api", url = "https://hacker-news.firebaseio.com/v0/")
public interface HackerNewsApiClient {

	@GetMapping("topstories.json")
	List<Long> getTopStories();

	@GetMapping("item/{id}.json")
	ItemDto getItem(@PathVariable("id") Long id);
	
}
