package com.citefred.ldwspring.service;

import com.citefred.ldwspring.domain.posts.Posts;
import com.citefred.ldwspring.domain.posts.PostsRepository;
import com.citefred.ldwspring.domain.user.User;
import com.citefred.ldwspring.domain.user.UserRepository;
import com.citefred.ldwspring.web.dto.PostsListResponseDto;
import com.citefred.ldwspring.web.dto.PostsResponseDto;
import com.citefred.ldwspring.web.dto.PostsSaveRequestDto;
import com.citefred.ldwspring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        User author = requestDto.getAuthor();
        if (author == null) {
            throw new IllegalArgumentException("작성자 정보가 없습니다.");
        }
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    //JPQL 쿼리 튜닝
    //@Transactional(readOnly = true)
    //public List<PostsListResponseDto> findAllDesc(){
    //    return postsRepository.findAllDesc().stream()
    //            .map(PostsListResponseDto::new)
    //            .collect(Collectors.toList());
    //}

    //QueryDSL 쿼리 튜닝
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDescUsingQueryDSL() {
        return postsRepository.findAllDescWithQueryDSL().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}
