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
    private String name;

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
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    @JsonCreator  // Jackson에게 어떤 생성자를 사용해야 하는지 알려줌
    public static User create(String name, String email, String picture, Role role) {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(role)
                .build();
    }
}