package hari.learnoflegends.gui;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import hari.learnoflegends.App;
import hari.learnoflegends.quiz.Question;
import hari.learnoflegends.quiz.Quiz;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class QuestionGUI implements TemplateViewRoute {

  @Override
  public ModelAndView handle(Request request, Response response) throws Exception {
    Quiz q = App.getQuiz();
    try {
    } catch (NumberFormatException e) {
      response.redirect("/");
      return null;
    }
    if (q == null) {
      response.redirect("/");
      return null;
    }
    Question question = q.pullNext();
    if (question == null) {
      String result = "You answered " + q.getCorrect() + " correct out of " + q.getLength()
          + " questions.";
      EndGUI.setResult(result);
      response.redirect("/end");
      return null;
    }
    Map<String, Object> variables = ImmutableMap.<String, Object>builder()
        .put("question", question.getQuestion()).put("choices", question.getAsListMap()).build();
    return new ModelAndView(variables, "quiz.ftl");
  }

}
