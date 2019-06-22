package com.just.myproject.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UseRecord {

  private int id;
  private int cardReturn;
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
  private Date borrowTime;

  @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",locale = "zh",timezone = "GMT+8")
  private Date returnTime;
  private int userId;
  private int roomId;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getRoomId() {
    return roomId;
  }

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

  public Date getBorrowTime() {
    return borrowTime;
  }

  public void setBorrowTime(Date borrowTime) {
    this.borrowTime = borrowTime;
  }

  public Date getReturnTime() {
    return returnTime;
  }

  public void setReturnTime(Date returnTime) {
    this.returnTime = returnTime;
  }



  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }





  public int getCardReturn() {
    return cardReturn;
  }

  public void setCardReturn(int cardReturn) {
    this.cardReturn = cardReturn;
  }




}
