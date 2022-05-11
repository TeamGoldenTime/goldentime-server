package com.api.goldentime.domain.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Image {

  @Id
  @GeneratedValue()
  @Column(name = "image_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "lost_post_id")
  private LostPost post;

  @Column
  private String name;

  @Column
  private String location;
}
