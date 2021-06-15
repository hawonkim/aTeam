package com.example.my_miniproject2.DTO;

public class MemberDTO {
    private String   nick , addr , pw , gender ;
    int id , age;

    public MemberDTO( int id , String pw ,String nick, String addr, int age, String gender) {
        this.nick = nick;
        this.addr = addr;
        this.pw = pw;
        this.gender = gender;
        this.id = id;
        this.age = age;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
