package com.api.goldentime.service.post;

import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.repository.LostPostRepository;
import com.api.goldentime.web.dto.request.post.lost.LostPostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LostPostService {

    private final LostPostRepository lostPostRepository;

    @Transactional
    public LostPost save(LostPostSaveRequestDto lostPostSaveRequestDto)
    {
        return lostPostRepository.save(lostPostSaveRequestDto.toEntity());
    }

}
