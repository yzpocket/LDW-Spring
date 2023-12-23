package com.citefred.ldwspring.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
    
    @Autowired
    PostsRepository postsRepository;
    
    //@After
    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }
    
    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("citefred@gmail.com")
                .build());
                
        //when
        List<Posts> postsList = postsRepository.findAll();
        
        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle().equals(title));
        assertThat(posts.getContent().equals(content));
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2023,12,23,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        ////DateFormat 변경하는 경우 1
        //String localDateTimeFormat1
        //        = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        //
        ////DateFormat 변경하는 경우 2
        //String localDateTimeFormat2
        //        = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS")
        //);


        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println("-> createDate"+posts.getCreatedDate()+"-> modifiedDate"+posts.getModifiedDate());
        //System.out.println(localDateTimeFormat1);//DateFormat 변경 확인1
        //System.out.println(localDateTimeFormat2);//DateFormat 변경 확인2


        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}
