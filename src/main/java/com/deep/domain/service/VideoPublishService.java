package com.deep.domain.service;

import com.deep.domain.model.VideoPublish;
import com.deep.infra.persistence.sql.mapper.VideoPublicMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by huangwenhai on 2018/5/8.
 */
@Service
public class VideoPublishService {

  @Resource
  VideoPublicMapper videoPublicMapper;

  public int addVideoPublish(VideoPublish videoPublish) {
    return videoPublicMapper.addVideoPublish(videoPublish);
  }

  public List<VideoPublish> selectVideoFile(VideoPublish videoPublish) {
    return videoPublicMapper.selectVideoFile(videoPublish);
  }

}
