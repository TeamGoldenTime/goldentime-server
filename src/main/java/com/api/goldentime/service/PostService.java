package com.api.goldentime.service;

import com.api.goldentime.repository.PostRepository;
import com.api.goldentime.web.dto.request.post.LostPostSaveRequestDto;
import com.api.goldentime.web.dto.request.post.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void save(LostPostSaveRequestDto lostPostSaveRequestDto)
    {
        postRepository.save(lostPostSaveRequestDto.toEntity());
    }

}
