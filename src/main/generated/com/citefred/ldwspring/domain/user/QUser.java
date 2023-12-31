package com.citefred.ldwspring.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 470185893L;

    public static final QUser user = new QUser("user");

    public final com.citefred.ldwspring.domain.QBaseTimeEntity _super = new com.citefred.ldwspring.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath password = createString("password");

    public final StringPath picture = createString("picture");

    public final ListPath<com.citefred.ldwspring.domain.posts.Posts, com.citefred.ldwspring.domain.posts.QPosts> posts = this.<com.citefred.ldwspring.domain.posts.Posts, com.citefred.ldwspring.domain.posts.QPosts>createList("posts", com.citefred.ldwspring.domain.posts.Posts.class, com.citefred.ldwspring.domain.posts.QPosts.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

