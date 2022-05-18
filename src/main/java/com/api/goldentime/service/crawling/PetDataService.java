package com.api.goldentime.service.crawling;

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
    public void save(PetData petData)
    {
       if(petData == null){
           throw new IllegalArgumentException();
       }

       petDataRepository.save(petData);

    }
}
