package com.api.goldentime.service.post;

import com.api.goldentime.domain.crawling.PetData;
import com.api.goldentime.repository.PetDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PetDataService {

  private final PetDataRepository petDataRepository;

  @Transactional
  public void save(PetData petData) {
    if (petData == null) {
      throw new IllegalArgumentException();
    }

    petDataRepository.save(petData);

  }

  public PetData findById(Long id) {
    PetData post = petDataRepository.findById(id).orElse(null);

    if (post == null) {
      throw new IllegalStateException("존재하지 않는 게시물");
    }

    return post;
  }
}
