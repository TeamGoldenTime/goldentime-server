package com.api.goldentime.repository;

import com.api.goldentime.domain.post.CatchPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatchPostRepository extends JpaRepository<CatchPost, Long> {
}
