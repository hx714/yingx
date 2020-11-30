package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "yx_video")
public class Video {
    @Id
    private String id;

    private String title;

    private String britf;
    @Column(name = "cover_path")
    private String coverPath;
    @Column(name = "video_path")
    private String videoPath;
    @Column(name = "upload_time")
    private Date uploadTime;
    @Column(name = "like_count")
    private Integer likeCount;
    @Column(name = "play_count")
    private Integer playCount;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "group_id")
    private String groupId;

}