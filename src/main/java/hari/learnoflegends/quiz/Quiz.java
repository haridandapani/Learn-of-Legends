package hari.learnoflegends.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import hari.learnoflegends.App;
import hari.learnoflegends.league.Champion;
import hari.learnoflegends.league.ChampionManager;

public class Quiz {

  private List<Question> questions;
  int length;
  int iterator;
  boolean showAfterQuestion;

  public Quiz(int length, boolean showAfterQuestion) {
    iterator = 0;
    this.length = length;
    questions = new ArrayList<>();
    this.showAfterQuestion = showAfterQuestion;
  }

  public boolean getShowAfterQuestion() {
    return showAfterQuestion;
  }

  public Question getNext() {
    if (iterator >= length) {
      return null;
    }
    iterator++;
    Question q = generateQuestion();
    questions.add(q);
    return q;
  }

  public Question getBefore() {
    return questions.get(iterator - 1);
  }

  public Question pullNext() {
    for (Question q : questions) {
      if (q.getSubmittedAnswer() == null) {
        return q;
      }
    }
    return getNext();
  }

  public Question getUnanswered() {
    for (Question q : questions) {
      if (q.getSubmittedAnswer() == null) {
        return q;
      }
    }
    return null;

  }

  public int getCorrect() {
    int correct = 0;
    for (Question q : questions) {
      if (q.answeredCorrectly()) {
        correct++;
      }
    }
    return correct;
  }

  public int getLength() {
    return length;
  }

  public int getIterator() {
    return iterator;
  }

  public Question generateQuestion() {
    ChampionManager manager = App.getManager();
    QuestionType type = getQuestionType();
    Question output;
    switch (type) {
      case CHAMPIONABILITY:
        output = generateChampionAbilityQuestion(manager);
        break;
      case CHAMPIONDESCRIPTION:
        output = generateChampionDescriptionQuestion(manager);
        break;
      case HASWHICHNAME:
        output = generateHasWhichName(manager);
        break;
      case HASWHICHDESCRIPTION:
        output = generateHasWhichDescription(manager);
        break;
      case IMAGE:
        output = generateImageQuestion(manager);
        break;
      default:
        output = generateChampionAbilityQuestion(manager);
        break;
    }
    if (questions.contains(output)) {
      return generateQuestion();
    }
    return output;
  }

  private Question generateChampionDescriptionQuestion(ChampionManager manager) {
    Champion correct = manager.getRandomChampion();
    correct.getAbilities();
    String quest;
    String rawDescription = correct.getByIndex((int) (Math.random() * 5)).getDescription();
    String stripped = stripName(rawDescription, getAllNames(correct.getName()));
    quest = "Which champion has the following ability description: " + stripped;
    List<AnswerChoice> choices = new ArrayList<>();
    choices.add(new AnswerChoice(correct.getName(), true));
    while (choices.size() < 4) {
      Champion random = manager.getRandomChampion();
      AnswerChoice nextOption = new AnswerChoice(random.getName(), false);
      if (!choices.contains(nextOption)) {
        choices.add(nextOption);
      }
    }

    Collections.shuffle(choices);
    Question q = new Question(quest, choices);
    return q;
  }

  private Question generateChampionAbilityQuestion(ChampionManager manager) {
    Champion correct = manager.getRandomChampion();
    correct.getAbilities();
    String quest;
    quest = "Which champion has the following ability: "
        + correct.getByIndex((int) (Math.random() * 5)).getName();
    List<AnswerChoice> choices = new ArrayList<>();
    choices.add(new AnswerChoice(correct.getName(), true));
    while (choices.size() < 4) {
      Champion random = manager.getRandomChampion();
      AnswerChoice nextOption = new AnswerChoice(random.getName(), false);
      if (!choices.contains(nextOption)) {
        choices.add(nextOption);
      }
    }

    Collections.shuffle(choices);
    Question q = new Question(quest, choices);
    return q;
  }

  private Question generateHasWhichName(ChampionManager manager) {
    Champion correct = manager.getRandomChampion();
    correct.getAbilities();
    String quest;
    quest = correct.getName() + " has which of the following abilities?";
    List<AnswerChoice> choices = new ArrayList<>();
    choices.add(new AnswerChoice(correct.getByIndex((int) (Math.random() * 5)).getName(), true));
    while (choices.size() < 4) {
      Champion random = manager.getRandomChampion();
      if (!random.equals(correct)) {
        random.getAbilities();
        AnswerChoice nextOption = new AnswerChoice(
            random.getByIndex((int) (Math.random() * 5)).getName(), false);
        if (!choices.contains(nextOption)) {
          choices.add(nextOption);
        }
      }
    }

    Collections.shuffle(choices);
    Question q = new Question(quest, choices);
    return q;
  }

  private Question generateHasWhichDescription(ChampionManager manager) {
    Champion correct = manager.getRandomChampion();
    correct.getAbilities();
    String quest;
    quest = correct.getName() + " has which of the following ability descriptions?";
    String rawDescription = correct.getByIndex((int) (Math.random() * 5)).getDescription();
    String stripped = stripName(rawDescription, getAllNames(correct.getName()));
    List<AnswerChoice> choices = new ArrayList<>();
    choices.add(new AnswerChoice(stripped, true));
    while (choices.size() < 4) {
      Champion random = manager.getRandomChampion();
      if (!random.equals(correct)) {
        random.getAbilities();
        rawDescription = random.getByIndex((int) (Math.random() * 5)).getDescription();
        stripped = stripName(rawDescription, getAllNames(random.getName()));
        AnswerChoice nextOption = new AnswerChoice(stripped, false);
        if (!choices.contains(nextOption)) {
          choices.add(nextOption);
        }
      }
    }

    Collections.shuffle(choices);
    Question q = new Question(quest, choices);
    return q;
  }

  private Question generateImageQuestion(ChampionManager manager) {
    Champion correct = manager.getRandomChampion();
    correct.getAbilities();
    String quest;
    quest = "Which champion is featured in the following image: <br>"
        + "<img class = \"resize\" src=" + correct.getImageUrl() + ">";
    List<AnswerChoice> choices = new ArrayList<>();
    choices.add(new AnswerChoice(correct.getName(), true));
    while (choices.size() < 4) {
      Champion random = manager.getRandomChampion();
      AnswerChoice nextOption = new AnswerChoice(random.getName(), false);
      if (!choices.contains(nextOption)) {
        choices.add(nextOption);
      }
    }

    Collections.shuffle(choices);
    Question q = new Question(quest, choices);
    return q;
  }

  public List<Map<String, Object>> getAsListMap() {
    List<Map<String, Object>> lister = new ArrayList<>();
    for (Question q : questions) {
      lister.add(q.getAsMap());
    }
    return lister;
  }

  public QuestionType getQuestionType() {
    return QuestionType.values()[new Random().nextInt(QuestionType.values().length)];
  }

  public static String stripName(String description, List<String> names) {
    for (String s : names) {
      description = description.replace(s, "[Champion]");
    }
    return description;
  }

  public static List<String> getAllNames(String champion) {
    List<String> names = new ArrayList<>();
    names.add(champion);
    names.add("Nunu");
    if (champion.equals("Nunu & Willump")) {
      names.add("Nunu");
      names.add("Willump");
    } else if (champion.equals("Quinn")) {
      names.add("Valor");
    } else if (champion.equals("Kindred")) {
      names.add("Wolf");
      names.add("Lamb");
    }
    return names;

  }
}
