package com.api.goldentime.service.post;

import com.api.goldentime.domain.post.Address;
import com.api.goldentime.domain.post.LostPost;
import com.api.goldentime.domain.post.PostType;
import com.api.goldentime.domain.user.User;
import com.api.goldentime.repository.LostPostRepository;
import com.api.goldentime.repository.UserRepository;
import com.api.goldentime.web.dto.request.post.lostPost.LostPostSaveRequestDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LostPostService {

    private final LostPostRepository lostPostRepository;
    private final UserRepository userRepository;

    @Transactional
    public LostPost save(LostPostSaveRequestDto lostPostSaveRequestDto, Address address)
    {
        Long userId = lostPostSaveRequestDto.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalStateException("존재하지 않는 유저 ID");
        }

        LostPost lostPost = lostPostSaveRequestDto.toEntity();
        lostPost.setType(PostType.LOST);
        lostPost.setAddress(address);
        lostPost.setWriter(user);

        return lostPostRepository.save(lostPost);
    }

    @Transactional
    public List<LostPost> getLostPostList()
    {
        return lostPostRepository.findAll();
    }

    @Transactional
    public LostPost findById(Long id)
    {
        LostPost post = lostPostRepository.findById(id).orElse(null);

        if(post == null)
        {
            throw new IllegalStateException("존재하지 않는 게시물");
        }

        return post;
    }

    public List<LostPost> getLostPostByUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        return lostPostRepository.getLostPostsByWriterOrderByDateDesc(user);
    }


}
