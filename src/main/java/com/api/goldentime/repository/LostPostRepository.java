package com.api.goldentime.repository;

import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.domain.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostPostRepository extends JpaRepository<LostPost, Long> {
  List<LostPost> getLostPostsByWriterOrderByDateDesc(User writer);
}
