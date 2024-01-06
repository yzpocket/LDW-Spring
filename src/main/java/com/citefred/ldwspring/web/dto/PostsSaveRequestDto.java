package com.citefred.ldwspring.web.dto;

import com.citefred.ldwspring.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private Long authorId;

    @Builder
    public PostsSaveRequestDto(String title, String content, Long authorId){
        this.title = title;
        this.content = content;
        this.authorId = authorId;
    }
}
