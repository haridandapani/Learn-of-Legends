package hari.learnoflegends.gui;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hari.learnoflegends.App;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class EndGUI implements TemplateViewRoute {

  private static String result;

  public static void setResult(String res) {
    result = res;
  }

  public static String getResult() {
    return result;
  }

  @Override
  public ModelAndView handle(Request request, Response response) throws Exception {
    try {
      Map<String, Object> variables = ImmutableMap.<String, Object>builder()
          .put("message", getResult()).put("quiz", App.getQuiz().getAsListMap()).build();
      return new ModelAndView(variables, "query.ftl");
    } catch (NullPointerException e) {
      response.redirect("/");
      return null;
    }
  }
}
