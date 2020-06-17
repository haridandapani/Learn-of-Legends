package hari.learnoflegends.gui;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hari.learnoflegends.quiz.Quiz;
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
      Quiz thisQuiz = request.session().attribute("quiz");
      Map<String, Object> variables = ImmutableMap.<String, Object>builder()
          .put("message", getResult()).put("quiz", thisQuiz.getAsListMap()).build();
      return new ModelAndView(variables, "query.ftl");
    } catch (NullPointerException e) {
      response.redirect("/");
      return null;
    }
  }
}
