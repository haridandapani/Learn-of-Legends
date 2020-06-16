package hari.learnoflegends.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.ImmutableMap;

public class Question {

  private String question;
  private List<AnswerChoice> choices;
  private AnswerChoice submittedAnswer;
  private int index;

  public Question(String question, List<AnswerChoice> choices) {
    this.question = question;
    this.choices = choices;
    this.submittedAnswer = null;
  }

  public String getQuestion() {
    return question;
  }

  public void setSubmittedAnswer(int index) {
    this.index = index;
    this.submittedAnswer = this.choices.get(index);
  }

  public int getIndex() {
    return index;
  }

  public Map<String, Object> getAsMap() {
    return ImmutableMap.<String, Object>builder().put("question", this.getQuestion())
        .put("choices", this.getAsListMap()).put("submitted", this.getIndex()).build();
  }

  public boolean answeredCorrectly() {
    return submittedAnswer.isCorrect();
  }

  public AnswerChoice getSubmittedAnswer() {
    return submittedAnswer;
  }

  public List<Map<String, Object>> getAsListMap() {
    List<Map<String, Object>> answers = new ArrayList<>();
    for (AnswerChoice c : choices) {
      answers.add(c.asMap());
    }
    return answers;
  }

  public String getAnswerChoicesEasy() {
    String output = "";
    for (AnswerChoice s : choices) {
      output += "<br>" + s.getText();
    }
    return output;
  }

  @Override
  public String toString() {
    String output = question;
    for (AnswerChoice s : choices) {
      output += System.lineSeparator() + s.getText();
    }
    return output;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Question quest = (Question) o;
    return question.equals(quest.getQuestion());
  }

  @Override
  public int hashCode() {
    return Objects.hash(question);
  }
}
