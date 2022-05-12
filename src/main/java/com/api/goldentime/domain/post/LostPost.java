package com.api.goldentime.domain.post;

import com.api.goldentime.domain.user.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
public class LostPost {

  @Id
  @GeneratedValue
  @Column(name= "lost_post_id")
  private Long id;

  @Embedded
  private Region region;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User writer;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<LostImage> lostImages = new ArrayList<>();

  @Column
  private String area;

  @Column
  private String kind;

  @Column
  private String name;

  @Column
  private String color;

  @Column
  private String remark;

  @Column
  private int age;

  @Column
  private LocalDateTime date;

  @Column
  private int reward;


  public void addImages(List<LostImage> lostImages) {
    setLostImages(lostImages);
    for (LostImage lostImage : lostImages) {
      lostImage.setPost(this);
    }
  }
}


