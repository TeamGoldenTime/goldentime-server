package com.api.goldentime.domain.post;

import com.api.goldentime.domain.user.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatchPost {

    @Id
    @GeneratedValue
    @Column(name= "catch_post_id")
    private Long id;

    @Embedded
    private Region region;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<CatchImage> catchImages = new ArrayList<>();

    @Column
    private String area;

    @Column
    private String kind;

    @Column
    private String gender;

    @Column
    private String color;

    @Column
    private String remark;

    @Column
    @Enumerated(EnumType.STRING)
    private PostType type;

    @Column
    private LocalDateTime date;



    public void addImages(List<CatchImage> catchImages) {
        setCatchImages(catchImages);
        for (CatchImage catchImage : catchImages) {
            catchImage.setPost(this);
        }
    }

}
