package hari.learnoflegends.gui;

import java.util.Map;

import hari.learnoflegends.App;
import hari.learnoflegends.quiz.Question;
import hari.learnoflegends.quiz.Quiz;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class AnswerGUI implements TemplateViewRoute {

  @Override
  public ModelAndView handle(Request request, Response response) throws Exception {

    Quiz q = App.getQuiz();
    Question question = q.getUnanswered();
    if (question == null) {
      question = q.getBefore();
    } else {
      QueryParamsMap answer = request.queryMap();
      question.setSubmittedAnswer(Integer.valueOf(answer.value("choice")));
    }
    if (q.getShowAfterQuestion()) {
      Map<String, Object> variables = question.getAsMap();
      return new ModelAndView(variables, "answer.ftl");
    }
    response.redirect("/question");
    return null;
  }

}
