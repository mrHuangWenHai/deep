package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.DisinfectionArchives;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by huangwenhai on 2018/4/16.
 */

@Mapper
public interface DisinfectionArchivesMapper {

  /**
   * 列出所有消毒信息
   * @return List
   */

  List<DisinfectionArchives> getAllDisinfectionArchives(RowBounds rowBounds);

  /**
   * 通过id 查找消毒档案
   * @return DisinfectionArchives
   */
  DisinfectionArchives getDisinfectionArchivesById(@Param("id") int id);

  /**
   * 通过日期来查消毒档案
   * @return DisinfectionArchives
   */
  DisinfectionArchives getDisinfectionArchivesByDisinfectionTime(@Param("disinfectionTime") Timestamp disinfectionTime);

  /**
   * 查询从某个日期开始的数据
   * @return DisinfectionArchives
   */
  List<DisinfectionArchives> getDisinfectionArchivesByBoundsTime(@Param("startTime") Timestamp startTime,
                                                                 RowBounds rowBounds);
  /**
   * 批量插入
   * @return 插入数据的数量
   */

  int addDisinfectionArchives(List<DisinfectionArchives> list);



}
