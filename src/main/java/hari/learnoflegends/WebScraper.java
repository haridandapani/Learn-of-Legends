package hari.learnoflegends;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import hari.learnoflegends.league.Ability;
import hari.learnoflegends.league.Champion;
import hari.learnoflegends.league.ChampionManager;

public class WebScraper {

  public static ChampionManager getChampions() {
    String url = "https://na.leagueoflegends.com/en-us/champions/";
    try {
      Document doc = Jsoup.connect(url).get();

      Elements names = doc.getElementsByClass("style__Text-sc-12h96bu-3 gPUACV");
      ChampionManager manager = new ChampionManager();
      List<String> allChamps = names.eachText();
      for (String champion : allChamps) {
        Champion newChamp = new Champion(champion);
        manager.add(newChamp);
      }
      return manager;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static List<Ability> getAbilities(String champion) {
    List<Ability> abilities = new ArrayList<>();
    String url = "https://na.leagueoflegends.com/en-us/champions/" + champion;
    try {
      Document doc = Jsoup.connect(url).get();
      List<String> abilityNames = doc
          .getElementsByClass("style__AbilityInfoItemName-ulelzu-10 cjXBRP").eachText();
      if (champion.equals("blitzcrank")) {
        // they messed up the blitzcrank html
        abilityNames.add(0, "Mana Barrier");
      }
      List<String> abilityDescriptions = doc
          .getElementsByClass("style__AbilityInfoItemDesc-ulelzu-11 jERSYr").eachText();
      for (int i = 0; i < 5; i++) {
        Ability ability = new Ability(abilityNames.get(i), abilityDescriptions.get(i));
        abilities.add(ability);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return abilities;
  }

  public static String getImage(String champion) {
    String url = "https://na.leagueoflegends.com/en-us/champions/" + champion;
    try {
      Document doc = Jsoup.connect(url).get();
      Element image = doc.getElementsByAttributeValue("property", "og:image").get(0);
      return image.attr("content");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
