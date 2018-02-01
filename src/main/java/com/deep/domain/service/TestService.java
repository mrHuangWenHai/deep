package com.deep.domain.service;

import com.deep.domain.model.TestModel;
import com.deep.infra.persistence.sql.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by huangwenhai on 2018/2/1.
 */

@Service
public class TestService {

  @Resource
  private TestMapper testMapper;

//  public TestMapper getTestMapper() {
//    return testMapper;
//  }
//
//  public void setTestMapper(TestMapper testMapper) {
//    this.testMapper = testMapper;
//  }

  public TestModel getTestModel(String index) {
    TestModel model = this.testMapper.getTestModelById(index);
    return model;
  }

}
