package pro.question.questionPro.cacheManager;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Bean
    public Caffeine caffeine15MinuteConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(15, TimeUnit.MINUTES)
                .maximumSize(100);
    }
    
    @Bean
    public Caffeine caffeine30MinuteConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .maximumSize(100);
    }

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManagerNewStory = new CaffeineCacheManager("topStory");
       
        cacheManagerNewStory.setCaffeine(caffeine15MinuteConfig());
  
        return cacheManagerNewStory;
    }
    
    @Bean
    public CacheManager cacheManager1() {
        CaffeineCacheManager cacheManagerOldStory = new CaffeineCacheManager("pastStory");
        cacheManagerOldStory.setCaffeine(caffeine30MinuteConfig());
        return cacheManagerOldStory;
    }
    
    
    
    
}