package hari.learnoflegends.league;

import java.util.Objects;

public class Ability {

  String name;
  String description;

  public Ability(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "Ability{" + "name=" + this.name + ", description=" + this.description + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Ability ability = (Ability) o;
    return name.equals(ability.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
