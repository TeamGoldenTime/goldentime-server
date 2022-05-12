package com.api.goldentime.web.dto.response.post.catchPost;

import com.api.goldentime.domain.user.User;
import com.api.goldentime.web.dto.request.post.catchPost.CatchImageDto;
import com.api.goldentime.web.dto.request.post.lostPost.LostImageDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
public class CatchPostResponseDto {

    private User writer;
    private List<CatchImageDto> images;
    private Double latitude;
    private Double longitude;
    private String kind;
    private String gender;
    private String color;
    private String remark; //특이사항
    private String area;
    private LocalDateTime date;

}
