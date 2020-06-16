package hari.learnoflegends.gui;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class QuizGUI implements TemplateViewRoute {

  private String message;

  public QuizGUI(String message) {
    this.message = message;
  }

  @Override
  public ModelAndView handle(Request request, Response response) throws Exception {
    Map<String, Object> variables = ImmutableMap.<String, Object>builder()
        .put("message", this.message).build();
    return new ModelAndView(variables, "query.ftl");
  }

}
