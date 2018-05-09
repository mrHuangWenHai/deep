package com.deep.domain.service;

import com.deep.api.request.VideoPublishRequest;
import com.deep.domain.model.VideoPublish;
import com.deep.infra.persistence.sql.mapper.VideoPublicMapper;
import org.apache.ibatis.session.RowBounds;
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

  public List<VideoPublish> selectVideoFile(VideoPublishRequest videoPublishRequest, RowBounds rowBounds) {
    return videoPublicMapper.selectVideoFile(videoPublishRequest, rowBounds);
  }

  public int allTotal() {
    return videoPublicMapper.allTotal();
  }

  public int deleteVideoFile(int id) {
    return videoPublicMapper.deleteVideoFile(id);
  }

}
