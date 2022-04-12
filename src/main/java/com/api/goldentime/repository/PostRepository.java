package com.api.goldentime.repository;

import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.domain.post.PostPK;
import com.api.goldentime.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<LostPost, PostPK> {
}
