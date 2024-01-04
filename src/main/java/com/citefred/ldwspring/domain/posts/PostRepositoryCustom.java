package com.citefred.ldwspring.domain.posts;

import java.util.List;

public interface PostRepositoryCustom {
    List<Posts> findAllDescWithQueryDSL();
}
