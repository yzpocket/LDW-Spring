package com.citefred.ldwspring.web.dto;

import com.citefred.ldwspring.domain.posts.Posts;
import com.citefred.ldwspring.domain.user.User;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private User author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
