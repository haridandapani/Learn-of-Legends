package hari.learnoflegends.quiz;

import java.util.Map;
import java.util.Objects;

import com.google.common.collect.ImmutableMap;

public class AnswerChoice {

  private String text;
  private boolean isCorrect;

  public AnswerChoice(String text, boolean isCorrect) {
    this.text = text;
    this.isCorrect = isCorrect;
  }

  public String getText() {
    return text;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public Map<String, Object> asMap() {
    return ImmutableMap.<String, Object>builder().put("text", text).put("correct", isCorrect)
        .build();
  }

  @Override
  public String toString() {
    return "Choice{" + "option=" + this.text + ", isCorrect: " + this.isCorrect + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AnswerChoice champion = (AnswerChoice) o;
    return text.equals(champion.getText());
  }

  @Override
  public int hashCode() {
    return Objects.hash(text);
  }
}
