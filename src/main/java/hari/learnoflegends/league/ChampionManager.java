package hari.learnoflegends.league;

import java.util.ArrayList;
import java.util.List;

public class ChampionManager {

  private List<Champion> champions;

  public ChampionManager() {
    champions = new ArrayList<>();
  }

  public void add(Champion e) {
    champions.add(e);
  }

  public List<Champion> getChampions() {
    return new ArrayList<Champion>(champions);
  }

  public Champion getRandomChampion() {
    int index = (int) (Math.random() * champions.size());
    return champions.get(index);
  }

  public static List<String> toImgUrlStyle(String name) {
    // Maintains capitalization, but only sometimes
    // first letter always capitalized
    // KhaZix -> Khazix, but KogMaw -> KogMaw
    String noPunc = name.replace("'", "").replace(" ", "").replace(".", "");
    List<String> possible = new ArrayList<>();
    possible.add(noPunc);
    return possible;
  }

  public static String toAbilitiesUrlStyle(String name) {
    if (name.contains("&")) {
      return "nunu";
    }
    String noPunc = name.replace("'", "-").replace(" ", "-").replace(".", "");
    return noPunc.toLowerCase();
  }
}
