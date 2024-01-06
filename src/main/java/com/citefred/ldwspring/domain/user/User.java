package com.citefred.ldwspring.domain.user;

import com.citefred.ldwspring.domain.BaseTimeEntity;
import com.citefred.ldwspring.domain.posts.Posts;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = true)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // User와 Posts 간의 1:N 관계 설정
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Posts> posts = new ArrayList<>();

    @Builder
    public User(String username, String email, String picture, Role role) {
        this.username = username;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String username, String picture) {
        this.username = username;
        this.picture = picture;

        return this;
    }

    //@JsonCreator  // Jackson에게 어떤 생성자를 사용해야 하는지 알려줌
    //public static User create(String username, String email, String picture, Role role) {
    //    return User.builder()
    //            .username(username)
    //            .email(email)
    //            .picture(picture)
    //            .role(role)
    //            .build();
    //}
}