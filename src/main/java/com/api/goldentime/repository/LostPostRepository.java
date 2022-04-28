package com.api.goldentime.repository;

import com.api.goldentime.domain.post.LostPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostPostRepository extends JpaRepository<LostPost, Long> {
}
