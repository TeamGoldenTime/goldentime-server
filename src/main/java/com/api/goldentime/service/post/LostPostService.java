package com.api.goldentime.service.post;

import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.domain.user.User;
import com.api.goldentime.repository.LostPostRepository;
import com.api.goldentime.repository.UserRepository;
import com.api.goldentime.web.dto.request.post.lost.LostPostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LostPostService {

    private final LostPostRepository lostPostRepository;
    private final UserRepository userRepository;

    @Transactional
    public LostPost save(LostPostSaveRequestDto lostPostSaveRequestDto)
    {
        Long userId = lostPostSaveRequestDto.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalStateException("존재하지 않는 유저 ID");
        }

        LostPost lostPost = lostPostSaveRequestDto.toEntity();
        lostPost.setWriter(user);

        return lostPostRepository.save(lostPost);
    }

}
