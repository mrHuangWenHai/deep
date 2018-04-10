package com.deep.domain.service;

import com.deep.domain.model.GenealogicalFilesModel;
import com.deep.infra.persistence.sql.mapper.GenealogicalFilesMapper;
import com.deep.infra.persistence.sql.mapper.GenealogicalFilesTransMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by huangwenhai on 2018/4/10.
 */

@Service
public class TrackBackService {

  @Resource
  GenealogicalFilesMapper genealogicalFilesMapper;

  @Resource
  GenealogicalFilesTransMapper genealogicalFilesTransMapper;

  public GenealogicalFilesModel getGenealogicalFilesModelByimmuneEartag(String immuneEarTag) {
    GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesMapper.getGenealogicalFilesModelByimmuneEartag(immuneEarTag);
    return genealogicalFilesModel;
  }

  public GenealogicalFilesModel getGenealogicalFilesModelBytradeMarkEartag(String tradeMarkEartag) {
    GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesMapper.getGenealogicalFilesModelBytradeMarkEartag(tradeMarkEartag);
    return genealogicalFilesModel;
  }


}
