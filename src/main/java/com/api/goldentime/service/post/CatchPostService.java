package com.api.goldentime.service.post;

import com.api.goldentime.domain.post.Address;
import com.api.goldentime.domain.post.CatchPost;
import com.api.goldentime.domain.post.PostType;
import com.api.goldentime.domain.user.User;
import com.api.goldentime.repository.CatchPostRepository;
import com.api.goldentime.repository.UserRepository;
import com.api.goldentime.web.dto.request.post.catchPost.CatchPostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatchPostService {

    private final CatchPostRepository catchPostRepository;
    private final UserRepository userRepository;

    @Transactional
    public CatchPost save(CatchPostSaveRequestDto catchPostSaveRequestDto, Address address)
    {
        Long userId = catchPostSaveRequestDto.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new IllegalStateException("존재하지 않는 유저 ID");
        }

        CatchPost catchPost = catchPostSaveRequestDto.toEntity();
        catchPost.setType(PostType.CATCH);
        catchPost.setAddress(address);
        catchPost.setWriter(user);

        return catchPostRepository.save(catchPost);
    }

    @Transactional
    public List<CatchPost> getCatchPostList()
    {
        return catchPostRepository.findAll();
    }

    @Transactional
    public CatchPost findById(Long id)
    {
        CatchPost post = catchPostRepository.findById(id).orElse(null);

        if(post == null)
        {
            throw new IllegalStateException("존재하지 않는 게시물");
        }

        return post;
    }
}
