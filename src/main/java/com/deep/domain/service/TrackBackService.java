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
    GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesMapper.getGenealogicalFilesModelByImmuneEartag(immuneEarTag);
    return genealogicalFilesModel;
  }

  public GenealogicalFilesModel getGenealogicalFilesModelBytradeMarkEartag(String tradeMarkEartag) {
    GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesMapper.getGenealogicalFilesModelByTradeMarkEartag(tradeMarkEartag);
    return genealogicalFilesModel;
  }

  public GenealogicalFilesModel getGenealogicalFilesModelByNativeEartag(String nativeEartag) {
    GenealogicalFilesModel genealogicalFilesModel = genealogicalFilesMapper.getGenealogicalFilesModelByNativeEartag(nativeEartag);
    return genealogicalFilesModel;
  }

}
