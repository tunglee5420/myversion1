package com.just.myproject.Entity;


import java.util.Objects;

public class Classroom {

  private int id;
  private String schoolarea;
  private String roomnum;
  private int health;
  private int isUse;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getSchoolarea() {
    return schoolarea;
  }

  public void setSchoolarea(String schoolarea) {
    this.schoolarea = schoolarea;
  }


  public String getRoomnum() {
    return roomnum;
  }

  public void setRoomnum(String roomnum) {
    this.roomnum = roomnum;
  }


  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }


  public int getIsUse() {
    return isUse;
  }

  public void setIsUse(int isUse) {
    this.isUse = isUse;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Classroom classroom = (Classroom) o;
    return id == classroom.id &&
            health == classroom.health &&
            isUse == classroom.isUse &&
            Objects.equals(schoolarea, classroom.schoolarea) &&
            Objects.equals(roomnum, classroom.roomnum);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, schoolarea, roomnum, health, isUse);
  }

  @Override
  public String toString() {
    return "Classroom{" +
            "id=" + id +
            ", schoolarea='" + schoolarea + '\'' +
            ", roomnum='" + roomnum + '\'' +
            ", health=" + health +
            ", isUse=" + isUse +
            '}';
  }
}
