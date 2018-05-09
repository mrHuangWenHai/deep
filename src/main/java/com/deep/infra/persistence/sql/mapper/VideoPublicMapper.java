package com.deep.infra.persistence.sql.mapper;

import com.deep.api.request.VideoPublishRequest;
import com.deep.domain.model.VideoPublish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by huangwenhai on 2018/5/8.
 */

@Mapper
public interface VideoPublicMapper {
  int addVideoPublish(@Param("videoPublish") VideoPublish videoPublish);

  List<VideoPublish> selectVideoFile(@Param("videoPublishRequest") VideoPublishRequest videoPublishRequest,
                                     RowBounds rowBounds);

  int allTotal();

  int deleteVideoFile(@Param("id") int id);
}
