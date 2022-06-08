package com.api.goldentime.repository;

import com.api.goldentime.domain.post.CatchPost;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatchPostRepository extends JpaRepository<CatchPost, Long> {

  @Query("SELECT c from CatchPost c INNER JOIN c.catchImages i WHERE i.location=:imgUrl")
  Optional<CatchPost> findByImgUrl(@Param("imgUrl") String imgUrl);
}
