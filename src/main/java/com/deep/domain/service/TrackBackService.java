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
  private GenealogicalFilesMapper genealogicalFilesMapper;

  @Resource
  private GenealogicalFilesTransMapper genealogicalFilesTransMapper;

  public GenealogicalFilesModel getGenealogicalFilesModelByImmuneEartag(String immuneEarTag) {
    return genealogicalFilesMapper.getGenealogicalFilesModelByImmuneEartag(immuneEarTag);
  }

  public GenealogicalFilesModel getGenealogicalFilesModelByTradeMarkEartag(String tradeMarkEartag) {
    return genealogicalFilesMapper.getGenealogicalFilesModelByTradeMarkEartag(tradeMarkEartag);
  }

  public GenealogicalFilesModel getGenealogicalFilesModelByNativeEartag(String nativeEartag) {
    return genealogicalFilesMapper.getGenealogicalFilesModelByNativeEartag(nativeEartag);
  }


}
