package hari.learnoflegends.gui;

import hari.learnoflegends.quiz.Quiz;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class GenerateQuizGUI implements TemplateViewRoute {

  @Override
  public ModelAndView handle(Request request, Response response) throws Exception {

    QueryParamsMap vars = request.queryMap();
    String show = vars.value("showAfterQuestion");
    boolean showAfterQuestion = show != null && show.equals("showAfterQuestion");
    Quiz q = new Quiz(Integer.valueOf(vars.value("numQuestions")), showAfterQuestion);
    request.session().attribute("quiz", q);
    response.redirect("/question");
    return null;
  }

}
