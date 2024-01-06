package com.citefred.ldwspring.web;

import com.citefred.ldwspring.config.auth.UserDetailsImpl;
import com.citefred.ldwspring.service.PostsService;
import com.citefred.ldwspring.web.dto.MessageDto;
import com.citefred.ldwspring.web.dto.PostsResponseDto;
import com.citefred.ldwspring.web.dto.PostsSaveRequestDto;
import com.citefred.ldwspring.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public ResponseEntity<MessageDto> save(@RequestBody PostsSaveRequestDto requestDto,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok().body(postsService.save(requestDto, userDetails.getUser()));
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id,
                       @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
