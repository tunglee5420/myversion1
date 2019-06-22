package com.just.myproject.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ErrorRecord {

  private int id;
  private String errorInfo;
  private String roomnum;
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
  private Date date;
  private String worknum;

  public String getErrorInfo() {
    return errorInfo;
  }

  public void setErrorInfo(String errorInfo) {
    this.errorInfo = errorInfo;
  }

  public String getRoomnum() {
    return roomnum;
  }

  public void setRoomnum(String roomnum) {
    this.roomnum = roomnum;
  }

  public String getWorknum() {
    return worknum;
  }

  public void setWorknum(String worknum) {
    this.worknum = worknum;
  }



  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ErrorRecord{" +
            "id=" + id +
            ", errorInfo='" + errorInfo + '\'' +
            ", roomnum='" + roomnum + '\'' +
            ", date=" + date +
            ", worknum='" + worknum + '\'' +
            '}';
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }


}
