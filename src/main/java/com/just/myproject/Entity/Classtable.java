package com.just.myproject.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Classtable implements Serializable {
  private static final long serialVersionUID = 1L;
  private String weeknum;
  private String weekday;
  private String classOrder;
  private String course;
  private String user;
  private String roomnum;
  private String term;

  public String getTerm() {
    return term;
  }

  public void setTerm(String term) {
    this.term = term;
  }

  public String getWeeknum() {
    return weeknum;
  }

  public void setWeeknum(String weeknum) {
    this.weeknum = weeknum;
  }

  public String getWeekday() {
    return weekday;
  }

  public void setWeekday(String weekday) {
    this.weekday = weekday;
  }

  public String getClassOrder() {
    return classOrder;
  }

  public void setClassOrder(String classOrder) {
    this.classOrder = classOrder;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getRoomnum() {
    return roomnum;
  }

  public void setRoomnum(String roomnum) {
    this.roomnum = roomnum;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course= course;
  }



}
