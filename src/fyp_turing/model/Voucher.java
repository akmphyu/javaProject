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
public class Voucher {
    Long id;
    String date;
    String itemName;
    Float itemPrice;
    Integer itemQuantity;
    Float totalCost;
    
    public Voucher(){
        
    }
    public Voucher(String date,String itemName,Float itemPrice,Integer itemQuantity,Float totalCost){
        this.date = date;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.totalCost = totalCost;
    }
     public Voucher(String itemName,Float itemPrice,Integer itemQuantity,Float totalCost){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.totalCost = totalCost;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getItemName(){
        return itemName;
    }
   public void setItemName(String itemName){
       this.itemName = itemName;
   }
   public Float getItemPrice(){
       return itemPrice;
   }
   public void setItemPrice(Float itemPrice){
       this.itemPrice = itemPrice;
   }
   public Integer getItemQuantity(){
       return itemQuantity;
   }
   public void setItemQuantity(Integer itemQuantity){
       this.itemQuantity = itemQuantity;
   }
    public Float getTotalCost(){
        return totalCost;
    }
    public void setTotalCost(Float totalCost){
        this.totalCost = totalCost;
    }
}
