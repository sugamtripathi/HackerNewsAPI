package pro.question.questionPro.Services;

import pro.question.questionPro.service.impl.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import pro.question.questionPro.client.HackerNewsApiClient;
import pro.question.questionPro.dto.ItemDto;
import pro.question.questionPro.dto.StoryDto;
import pro.question.questionPro.dto.commentDto;
import pro.question.questionPro.model.TopStoryModel;
import pro.question.questionPro.repository.TopStoryRepository;
import pro.question.questionPro.service.impl.HackerNewsServiceImpl;

@SpringBootTest
class HackerNewsServiceImplTest {

	@Mock
    private TopStoryRepository topStoryRepo;

    @Mock
    private HackerNewsApiClient hackerNewsApiClient;

    @InjectMocks
    private HackerNewsServiceImpl hackerNewsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTopStories() {
        // Create test data
        List<Long> storyIds = new ArrayList();
        storyIds.add(123L);
        storyIds.add(456L);

        ItemDto item1 = new ItemDto();
        item1.setId(123);
        item1.setTitle("Test Story 1");
        item1.setType("story");
        item1.setUrl("http://test/story/1");
        item1.setScore(100);
        item1.setTime(1645274902L);
        item1.setBy("testuser1");

        ItemDto item2 = new ItemDto();
        item2.setId(456);
        item2.setTitle("Test Story 2");
        item2.setType("story");
        item2.setUrl("http://test/story/2");
        item2.setScore(200);
        item2.setTime(1645274902l);
        item2.setBy("testuser2");

        List<ItemDto> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        // Configure mock behavior
        when(hackerNewsApiClient.getTopStories()).thenReturn(storyIds);
        when(hackerNewsApiClient.getItem(123L)).thenReturn(item1);
        when(hackerNewsApiClient.getItem(456L)).thenReturn(item2);

        // Call the method under test
        List<StoryDto> result = hackerNewsService.getTopStories();

        // Verify the result
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo(456L);
        assertThat(result.get(0).getTitle()).isEqualTo("Test Story 2");
        assertThat(result.get(0).getUrl()).isEqualTo("http://test/story/2");
        assertThat(result.get(0).getScore()).isEqualTo(200);
        assertThat(result.get(0).getTime()).isEqualTo(1645274902);
        assertThat(result.get(0).getBy()).isEqualTo("testuser2");
        assertThat(result.get(1).getId()).isEqualTo(123L);
        assertThat(result.get(1).getTitle()).isEqualTo("Test Story 1");
        assertThat(result.get(1).getUrl()).isEqualTo("http://test/story/1");
        assertThat(result.get(1).getScore()).isEqualTo(100);
        assertThat(result.get(1).getTime()).isEqualTo(1645274902);
        assertThat(result.get(1).getBy()).isEqualTo("testuser1");
    }
    
    
    @Test
    public void testSaveTopStories() {
        // Create some sample stories
        StoryDto story1 = new StoryDto(1, "Story 1", "https://story1.com", 10, 1645122878l, "user1");
        StoryDto story2 = new StoryDto(2, "Story 2", "https://story2.com", 15, 1645122879l, "user2");
        List<StoryDto> stories = new ArrayList<>();
        stories.add(story1);
        stories.add(story2);

        // Mock the behavior of the repository's save method
        when(topStoryRepo.save(any(TopStoryModel.class))).thenReturn(new TopStoryModel());

        // Call the method to be tested
        hackerNewsService.saveTopStories(stories);

        // Verify that the save method was called for each story
        verify(topStoryRepo).save(new TopStoryModel(story1.getId(), story1.getTitle(), story1.getUrl(), story1.getScore(),
                story1.getTime(), story1.getBy()));
        verify(topStoryRepo).save(new TopStoryModel(story2.getId(), story2.getTitle(), story2.getUrl(), story2.getScore(),
                story2.getTime(), story2.getBy()));
    }
    
    
    @Test
    public void testGetPastStories() {
        
        List<TopStoryModel> topStories = new ArrayList<>();
        topStories.add(new TopStoryModel(1, "Test story 1", "http://test.com/story1", 100, 1645184800L, "testuser1"));
        topStories.add(new TopStoryModel(2, "Test story 2", "http://test.com/story2", 200, 1645171200L, "testuser2"));
       
        when(topStoryRepo.findAll()).thenReturn(topStories);
        

       
        List<TopStoryModel> result = hackerNewsService.getPastStories();

        
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getId()).isEqualTo(1);
        assertThat(result.get(1).getId()).isEqualTo(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Test story 1");
        assertThat(result.get(1).getTitle()).isEqualTo("Test story 2");
        assertThat(result.get(0).getScore()).isEqualTo(100);
        assertThat(result.get(1).getScore()).isEqualTo(200);
    }
    
    
//    @Test
//	public void testGetComments() {
//
//		 
//
//		Long itemId = 123l;
//		ItemDto storyItem = new ItemDto();
//		storyItem.setId(123);
//		List<Long> kids = new ArrayList<>();
//		kids.add(567l);
//		storyItem.setBy("StoryOwner");
//		storyItem.setKids(kids);
//		storyItem.setParent(000000l);
//		storyItem.setText("User_Story");
//		storyItem.setTime(11224578946l);
//		storyItem.setType("story");
//		storyItem.setKids(kids);
//
//		
//		ItemDto item1 = new ItemDto();
//		List<Long> kids1 = new ArrayList<>();
//		kids1.add(7894l);
//		kids1.add(7895l);
//		kids1.add(7893l);		
//		item1.setId(567);
//		item1.setKids(kids1);
//		item1.setType("comment");
//		item1.setBy("user1");
//		item1.setText("comment 1");
//		item1.setParent(567l);
//		item1.setTime(1314211127l);
//
//		ItemDto item2 = new ItemDto();
//		item2.setId(789);
//		List<Long> kids2 = new ArrayList<>();
//		kids2.add(8894l);
//		kids2.add(8895l);	
//		item2.setKids(kids2);
//		item2.setParent(567l);
//		item2.setType("comment");
//		item2.setBy("user2");
//		item2.setText("comment 2");
//		item2.setTime(1314211127l);
//			
//		List<commentDto> comments=new ArrayList<>();
//		comments.add(new commentDto(item1.getId(),item1.getKids(), item1.getParent(),item1.getText(), item1.getTime(), item1.getType(), item1.getBy()));
//		comments.add(new commentDto(item2.getId(),item2.getKids(), item2.getParent(),item2.getText(), item2.getTime(), item2.getType(), item2.getBy()));
//
//		
//		// mock the behavior of the external API
//		when(hackerNewsApiClient.getItem(itemId)).thenReturn(storyItem );
//		when(hackerNewsApiClient.getItem(456L)).thenReturn(item1);
//		when(hackerNewsApiClient.getItem(789L)).thenReturn(item2);
//		when(hackerNewsApiClient.getItem(itemId)).thenReturn(new ItemDto());
//
//		// execute the method under test
//		when(hackerNewsService.getComments(itemId)).thenReturn(comments);
//		List<commentDto> commentResult = hackerNewsService.getComments(itemId);
//
//		// assert the results
//		assertEquals(1, commentResult.size());
//		assertEquals("comment 1", commentResult.get(0).getText());
//		assertEquals("user1", commentResult.get(0).getBy());
//		assertEquals("comment 2", commentResult.get(1).getText());
//	    assertEquals("user2", commentResult.get(1).getBy());
//	}
//    
    
    
}
