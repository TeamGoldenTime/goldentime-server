package com.api.goldentime.domain.post;

import com.api.goldentime.domain.user.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostPK implements Serializable {

    private User writer;
    private String postDate;
}
