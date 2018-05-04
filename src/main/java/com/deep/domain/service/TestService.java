package com.deep.domain.service;
import com.deep.domain.model.TestModel;
import com.deep.infra.persistence.sql.mapper.TestMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by huangwenhai on 2018/2/1.
 */

@Service
public class TestService {

  @Resource
  private TestMapper testMapper;

  public TestModel getTestModel(String index) {
    TestModel model = this.testMapper.getTestModelById(index);
    return model;
  }


}
