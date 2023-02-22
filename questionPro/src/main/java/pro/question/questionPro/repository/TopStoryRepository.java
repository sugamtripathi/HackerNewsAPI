package pro.question.questionPro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.question.questionPro.model.TopStoryModel;

public interface TopStoryRepository extends JpaRepository<TopStoryModel,Integer> {
	
	 List<TopStoryModel> findAll();

}
