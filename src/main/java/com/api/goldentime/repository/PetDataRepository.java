package com.api.goldentime.repository;

import com.api.goldentime.domain.crawling.PetData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetDataRepository extends JpaRepository<PetData, Long> {

    Optional<PetData> findByImgUrl(String imgUrl);
}
