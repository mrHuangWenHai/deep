package com.deep.api.resource;

import com.deep.api.response.Greeting;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.TestModel;
import com.deep.domain.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by huangwenhai on 2018/1/31.
 */
@RestController
public class ExampleResource {

  @Resource
  private TestService testService;

  @RequestMapping("/")
  String example() {
    return "Hello World!";
  }

  @RequestMapping("/greeting")
  public Response greeting(@RequestParam(value="name", defaultValue="World") String name) {
    Response response = Responses.successResponse();
    Greeting greeting = new Greeting(10, name);
    HashMap<String, Object> data = new HashMap<>();
    data.put("greet", greeting);
    response.setData(data);
    return response;
  }

  @RequestMapping("/getTest")
  public Response getTestModel() {
    TestModel model = testService.getTestModel("1");
    Response response = Responses.successResponse();
    HashMap<String, Object> data = new HashMap<>();
    data.put("test", model);
    response.setData(data);
    return response;
  }
}
