/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp_turing.model;

/**
 *
 * @author ayekaungmyaphyu
 */
public class User {
    Long id;
    String name;
    String username;
    String password;
    String phone;
    String position;
    
    public User(String name, String username,String password,String phone,String position){
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.position = position;
    }
    
   public User(){
       
   }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getUserName(){
        return username;
    }
    public void setUserName(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
        this.position = position;
    }

    
    
}

