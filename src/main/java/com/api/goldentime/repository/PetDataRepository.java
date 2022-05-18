package com.api.goldentime.repository;

import com.api.goldentime.domain.crawling.PetData;
import com.api.goldentime.domain.post.LostPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetDataRepository extends JpaRepository<PetData, Long> {
}
