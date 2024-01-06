package com.citefred.ldwspring.domain.posts;

import com.citefred.ldwspring.domain.BaseTimeEntity;
import com.citefred.ldwspring.domain.user.User;
import com.citefred.ldwspring.web.dto.PostsSaveRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // Posts와 User 간의 N:1 관계 설정
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Builder
    public Posts(PostsSaveRequestDto postsSaveRequestDto, User author){
        this.title = postsSaveRequestDto.getTitle();
        this.content = postsSaveRequestDto.getContent();
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
