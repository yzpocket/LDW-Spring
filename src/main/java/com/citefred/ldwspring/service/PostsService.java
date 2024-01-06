package com.citefred.ldwspring.service;

import com.citefred.ldwspring.domain.posts.Posts;
import com.citefred.ldwspring.domain.posts.PostsRepository;
import com.citefred.ldwspring.domain.user.Role;
import com.citefred.ldwspring.domain.user.User;
import com.citefred.ldwspring.domain.user.UserRepository;
import com.citefred.ldwspring.web.dto.*;
import lombok.RequiredArgsConstructor;
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
    public MessageDto save(PostsSaveRequestDto requestDto, User loginUser) {
        // 로그인한 사용자 확인
        validateUserAuthority(loginUser);

        Posts newPost = new Posts(requestDto, loginUser);
        postsRepository.save(newPost);
        return new MessageDto("게시글 추가가 완료되었습니다.");
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

    private void validateUserAuthority(User loginUser) {
        if (!loginUser.getRole().equals(Role.USER)){
            throw new IllegalArgumentException("해당 작업을 수행할 권한이 없습니다.");
        }
    }
}
