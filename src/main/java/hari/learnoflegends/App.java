package hari.learnoflegends;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import hari.learnoflegends.gui.AnswerGUI;
import hari.learnoflegends.gui.EndGUI;
import hari.learnoflegends.gui.GenerateQuizGUI;
import hari.learnoflegends.gui.QuestionGUI;
import hari.learnoflegends.gui.QuizGUI;
import hari.learnoflegends.league.ChampionManager;
import hari.learnoflegends.quiz.Quiz;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

public class App {
  private static ChampionManager manager;

  private static final int DEFAULT_PORT = 4567;
  // URL PATHS
  public static final String LANDING_PAGE = "/";
  public static Quiz currentQuiz;

  public static void main(String[] args) {
    manager = WebScraper.getChampions();
    new App(args).run();
  }

  private App(String[] args) {
  }

  private void run() {
    runSparkServer();
  }

  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return DEFAULT_PORT;
  }

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n", templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  private void runSparkServer() {
    Spark.port(getHerokuAssignedPort());
    Spark.externalStaticFileLocation("src/main/resources/static");

    FreeMarkerEngine freeMarker = createEngine();

    // Setup Spark Routes
    EndGUI.setResult("");

    String homeChamp = "<img class = \"home\" src=" + manager.getRandomChampion().getImageUrl()
        + ">";
    Spark.get(LANDING_PAGE, new QuizGUI(homeChamp), freeMarker);
    Spark.get("/end", new EndGUI(), freeMarker);
    Spark.post("/quiz", new GenerateQuizGUI(), freeMarker);
    Spark.get("/question", new QuestionGUI(), freeMarker);
    Spark.post("/question", new QuestionGUI(), freeMarker);
    Spark.post("/answer", new AnswerGUI(), freeMarker);
    Spark.get("/answer", new AnswerGUI(), freeMarker);
    Spark.get("*", new QuizGUI(homeChamp), freeMarker);
  }

  public static ChampionManager getManager() {
    return manager;
  }

  public static Quiz getQuiz() {
    return currentQuiz;
  }

  public static void setManager(ChampionManager newManager) {
    manager = newManager;
  }

  public static void setQuiz(Quiz newQuiz) {
    currentQuiz = newQuiz;
  }
}
