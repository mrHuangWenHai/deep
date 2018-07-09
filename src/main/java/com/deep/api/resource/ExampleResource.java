package com.deep.api.resource;


import com.deep.api.Utils.AgentUtil;
import com.deep.api.response.Greeting;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.TestModel;
import com.deep.domain.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by huangwenhai on 2018/1/31.
 */

@RestController
public class ExampleResource {

  private final Logger logger = LoggerFactory.getLogger(ExampleResource.class);
  @Resource
  private TestService testService;

  @RequestMapping("/")
  String example() {
    return "Hello World!";
  }

  @RequestMapping("/greeting")
  public Response greeting(@RequestParam(value="name", defaultValue="World") String name) {
    logger.info("invoke greeting {}",name);
    Response response = Responses.successResponse();
    Greeting greeting = new Greeting(10, name);
    HashMap<String, Object> data = new HashMap<>();
    data.put("greet", greeting);
    response.setData(data);
    return response;
  }

  @RequestMapping("/getTest")
  public Response getTestModel() {
    logger.info("invoke getTest");
    TestModel model = testService.getTestModel("1");
    Response response = Responses.successResponse();
    HashMap<String, Object> data = new HashMap<>();
    data.put("test", model);
    response.setData(data);
    return response;
  }

  @GetMapping("/test/{id}")
  public Response getTestUtilFactory(@PathVariable("id") String id) {
    Response response = Responses.successResponse();
    HashMap<String, Object> data = new HashMap<>();
    data.put("test", AgentUtil.getFactory(id));
    response.setData(data);
    return response;
  }

  @GetMapping("test/agent/{id}")
  public Response getTestUtilAgent(@PathVariable("id") String id) {
    Response response = Responses.successResponse();
    HashMap<String, Object> data = new HashMap<>();
    data.put("test", AgentUtil.getSubordinate(id));
    response.setData(data);
    return response;
  }

  @GetMapping("test/agent/factory/{id}")
  public Response getTestUtilAgentFactory(@PathVariable("id") String id) {
    Response response = Responses.successResponse();
    HashMap<String, Object> data = new HashMap<>();
    data.put("List", AgentUtil.getSubordinateFactory(id));
    response.setData(data);
    return response;
  }

  @GetMapping("test/agent/factory/id/{id}")
  public Response getTestUtilAgentFactoryID(@PathVariable("id") String id) {
    Response response = Responses.successResponse();
    HashMap<String, Object> data = new HashMap<>();
    data.put("List", AgentUtil.getSubordinateFactoryID(id));
    response.setData(data);
    return response;
  }

  @GetMapping("test/isagent/{id}")
  public Response getTestUtilIsAgent(@PathVariable("id") String id) {
    Response response = Responses.successResponse();
    HashMap<String, Object> data = new HashMap<>();
    data.put("List", AgentUtil.isAgent(id));
    response.setData(data);
    return response;
  }

  @GetMapping("test/value")
  public String getValue() {
    return "redirect: test/value/other";
  }

  @GetMapping("test/value/other")
  public Response getResponse() {
    return Responses.errorResponse("错误");
  }

  @GetMapping("test/getFactory/{id}")
  public Response getHaHaHa(@PathVariable("id") String id) {
    Response response = Responses.successResponse();
    HashMap<String, Object> data = new HashMap<>();
    data.put("List", AgentUtil.getAllSubordinateFactory(id));
    response.setData(data);
    return response;
  }

  @GetMapping("test/getAgent/{id}")
  public Response getWaWaWa(@PathVariable("id") String id) {
    Response response = Responses.successResponse();
    HashMap<String, Object> data = new HashMap<>();
    data.put("List", AgentUtil.getAllSubordinateAgent(id));
    response.setData(data);
    return response;
  }
}
