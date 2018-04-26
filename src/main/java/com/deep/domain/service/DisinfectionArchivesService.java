package com.deep.domain.service;

import com.deep.domain.model.DisinfectionArchives;
import com.deep.infra.persistence.sql.mapper.DisinfectionArchivesMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by huangwenhai on 2018/4/16.
 */

@Service
public class DisinfectionArchivesService {

  @Resource
  private DisinfectionArchivesMapper disinfectionArchivesMapper;

  public List<DisinfectionArchives> getAllDisinfectionArchives(RowBounds rowBounds) {
    return disinfectionArchivesMapper.getAllDisinfectionArchives(rowBounds);
  }

  public DisinfectionArchives getDisinfectionArchivesById(int id) {
    return disinfectionArchivesMapper.getDisinfectionArchivesById(id);
  }

  public DisinfectionArchives getDisinfectionArchivesByDisinfectionTime(Timestamp disinfectionTime) {
    return disinfectionArchivesMapper.getDisinfectionArchivesByDisinfectionTime(disinfectionTime);
  }

  public List<DisinfectionArchives> getDisinfectionArchivesByBoundsTime(Timestamp disinfectionTime,
                                                                        RowBounds rowBounds) {
    return disinfectionArchivesMapper.getDisinfectionArchivesByBoundsTime(disinfectionTime, rowBounds);
  }

  public int addDisinfectionArchives(List<DisinfectionArchives> list) {
    return disinfectionArchivesMapper.addDisinfectionArchives(list);
  }



}
