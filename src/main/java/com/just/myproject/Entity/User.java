package com.just.myproject.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

  private int id;
  private String worknum;
  private String name;
  @JsonIgnore
  private String password;
  private String email;
  private String phone;
  private String department;
  private int isAdmin;
  private String nickname;
  private String headimg;

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getHeadimg() {
    return headimg;
  }

  public void setHeadimg(String headimg) {
    this.headimg = headimg;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", worknum='" + worknum + '\'' +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", department='" + department + '\'' +
            ", isAdmin=" + isAdmin +
            '}';
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }






  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getWorknum() {
    return worknum;
  }

  public void setWorknum(String worknum) {
    this.worknum = worknum;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }


  public int getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(int isAdmin) {
    this.isAdmin = isAdmin;
  }

}
