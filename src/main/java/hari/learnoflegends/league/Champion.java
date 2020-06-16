package hari.learnoflegends.league;

import java.util.List;
import java.util.Objects;

import hari.learnoflegends.WebScraper;

public class Champion {

  private String name;
  private Ability passive;
  private Ability q;
  private Ability w;
  private Ability e;
  private Ability r;
  private String imgUrl;

  public Champion(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Ability getByIndex(int index) {
    switch (index) {
      case 0:
        return passive;
      case 1:
        return q;
      case 2:
        return w;
      case 3:
        return e;
      case 4:
        return r;
      default:
        return passive;
    }
  }

  public void getAbilities() {
    if (r == null) {
      List<Ability> abilities = WebScraper.getAbilities(ChampionManager.toAbilitiesUrlStyle(name));
      passive = abilities.get(0);
      q = abilities.get(1);
      w = abilities.get(2);
      e = abilities.get(3);
      r = abilities.get(4);
    }
  }

  public String getImageUrl() {
    if (imgUrl == null) {
      imgUrl = WebScraper.getImage(ChampionManager.toAbilitiesUrlStyle(name));
    }
    return imgUrl;
  }

  @Override
  public String toString() {
    return "Champion{" + "name=" + this.name + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Champion champion = (Champion) o;
    return name.equals(champion.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  public Ability getPassive() {
    return passive;
  }

  public Ability getQ() {
    return q;
  }

  public Ability getW() {
    return w;
  }

  public Ability getE() {
    return e;
  }

  public Ability getR() {
    return r;
  }
}
