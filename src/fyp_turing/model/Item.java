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
public class Item {
    Long id;
    String name;
    String description;
    Float price;
    Integer stock;
    String category;
    byte[] imagePath;
    
   public Item(){
       
   }
    public Item(Long id,String name,String description,Float price,Integer stock,String category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
       
    }
    public Item(Long id,String name,String description,Float price,Integer stock, String category,byte[] imagePath){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imagePath = imagePath;
    }


    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Float getPrice(){
        return price;
    }
    public void setPrice(Float price){
        this.price = price;
    }
    public Integer getStock(){
        return stock;
    }
    public void setStock(Integer stock){
        this.stock = stock;
    }
    public byte[] getImagePath(){
        return imagePath;
    }
    public void setImagePath(byte[] imagePath){
        this.imagePath = imagePath;
    }
}
