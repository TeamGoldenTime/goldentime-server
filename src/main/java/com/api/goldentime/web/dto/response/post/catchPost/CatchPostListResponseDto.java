package com.api.goldentime.web.dto.response.post.catchPost;


import com.api.goldentime.domain.post.CatchPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatchPostListResponseDto {

    private List<CatchPost> catchPostList;
}
