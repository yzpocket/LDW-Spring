//package com.citefred.ldwspring.web;
//
//import com.citefred.ldwspring.domain.posts.Posts;
//import com.citefred.ldwspring.domain.posts.PostsRepository;
//import com.citefred.ldwspring.domain.user.Role;
//import com.citefred.ldwspring.domain.user.User;
//import com.citefred.ldwspring.domain.user.UserRepository;
//import com.citefred.ldwspring.web.dto.PostsSaveRequestDto;
//import com.citefred.ldwspring.web.dto.PostsUpdateRequestDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class PostsApiControllerTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @BeforeEach
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//
//    @AfterEach
//    public void tearDown() throws Exception{
//        postsRepository.deleteAll();
//    }
//
//    @Test
//    @WithMockUser(roles = "USER")
//    public void Posts_등록된다() throws Exception{
//        //given
//        String title = "title";
//        String content = "content";
//
//        User user = userRepository.save(User.builder()
//                .name("Test User")
//                .email("test@example.com")
//                .role(Role.USER)
//                .build());
//
//        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
//                .title(title)
//                .content(content)
//                .author(user)
//                .build();
//
//        String url = "http://localhost:" +port+"/api/v1/posts";
//
//        //when
//        mvc.perform(post(url)
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//        //then
//        List<Posts> postsLists =postsRepository.findAll();
//        assertThat(postsLists.get(0).getTitle()).isEqualTo(title);
//        assertThat(postsLists.get(0).getContent()).isEqualTo(content);
//
//    }
//
//
//
//
//    @Test
//    @WithMockUser(roles = "USER")
//    public void Posts_수정된다() throws Exception{
//        //given
//        User user = userRepository.save(User.builder()
//                .name("Test User")
//                .email("test@example.com")
//                .role(Role.USER)
//                .build());
//
//        Posts savedPosts = postsRepository.save(Posts.builder()
//                .title("title")
//                .content("content")
//                .author(user)
//                .build());
//
//        Long updateId = savedPosts.getId();
//        String expectedTitle = "title2";
//        String expectedContent = "content2";
//
//        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
//                .title(expectedTitle)
//                .content(expectedContent)
//                .build();
//
//        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;
//
//        //when
//        mvc.perform(put(url)
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//        //then
//
//        List<Posts> postsLists =postsRepository.findAll();
//        assertThat(postsLists.get(0).getTitle()).isEqualTo(expectedTitle);
//        assertThat(postsLists.get(0).getContent()).isEqualTo(expectedContent);
//    }
//
//}