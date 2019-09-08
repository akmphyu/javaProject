/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fyp_turing.model.dao;

import fyp_turing.model.Category;
import fyp_turing.model.Item;
import fyp_turing.model.User;
import fyp_turing.model.Voucher;
import fyp_turing.model.ui.Admin_Home;
import fyp_turing.model.ui.Chaiser_Home;
import fyp_turing.model.ui.Register_Form;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ayekaungmyaphyu
 */
public class DAOConnection {
     static
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String connectionString = "jdbc:mysql://localhost:3306/fyp";
    Connection conn = null;
    static DAOConnection singleton ;
    
    public static DAOConnection getDao()
    {
        if(singleton == null)
        {
            singleton = new DAOConnection();
        }
        return singleton;
    }
    private DAOConnection()
    {
        this.connect();
    }
    public void connect()
    {
        try
        {
            this.conn = (Connection) DriverManager.getConnection(connectionString, "root", "");
            System.out.println("Connection "+conn);
                    
        } catch (SQLException ex)
        {
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("No Connection ");
        }
    }
    public void insertUser(User user){
        try{
            PreparedStatement st = this.conn.prepareStatement("INSERT INTO users (name,username,password,phone,position) VALUES(?,?,?,?,?)");
            st.setString(1, user.getName());
            st.setString(2,user.getUserName());
            st.setString(3, user.getPassword());
            st.setString(4,user.getPhone());
            st.setString(5, user.getPosition());
           
            if(st.executeUpdate() != 0){
                            JOptionPane.showMessageDialog(null, "Your Account Has Been Created");
                        }else{
                            JOptionPane.showMessageDialog(null, "Error: Check Your Information");
                        }
        }catch(SQLException ex){
            System.out.println("user cannot create");
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertCategory(Category category){
         try{
            PreparedStatement st = this.conn.prepareStatement("INSERT INTO category (name) VALUES(?)");
            st.setString(1, category.getName());
           
 
            if(st.executeUpdate() != 0){
                            JOptionPane.showMessageDialog(null, "New Category Has Been Created");
                        }else{
                            JOptionPane.showMessageDialog(null, "There is some error");
                        }
        }catch(SQLException ex){
          
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void insertItem(Item item,String imagePath){
        try{
            PreparedStatement st = this.conn.prepareStatement("INSERT INTO item(name,description,price,stock,image,catName) VALUES(?,?,?,?,?,?)");
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setFloat(3, item.getPrice());
            st.setInt(4, item.getStock());
            st.setString(6,item.getCategory());
            //insert images
            try {
                        //save the image as blob in the database
                        
                        if(imagePath != null){
                            InputStream image = new FileInputStream(new File(imagePath));
                            st.setBlob(5, image);
//                               st.setBinaryStream(5, image);
                        }else{
                            st.setNull(5, java.sql.Types.NULL);
                        }
                        
                        if(st.executeUpdate() != 0){
                            JOptionPane.showMessageDialog(null, "New Item has been created");
                        }else{
                            JOptionPane.showMessageDialog(null, "There are some error. Please check");
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
            imagePath = null;
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public Integer updateQuantity(Integer stock,Integer quantity, String itemName, boolean Increase){
      Integer remain = 0;
       try{
            PreparedStatement st = this.conn.prepareStatement("UPDATE `item` SET `stock`=? WHERE `name`=?");
            
            
            if(Increase){
                remain  = stock + quantity;
            }else{
                remain = stock - quantity;
            }
                   
            st.setInt(1,remain);
            st.setString(2, itemName);
            if(st.executeUpdate() != 0){
                            JOptionPane.showMessageDialog(null, "Category has been updated");
                        }else{
                            JOptionPane.showMessageDialog(null, "There are some error. Please check");
                        }
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       return remain;
   }
   public void updateCategory(Category category, String newName){
       try{
            PreparedStatement st = this.conn.prepareStatement("UPDATE `category` SET `name`=? WHERE `id`=?");
            
            st.setString(1, newName);
            st.setLong(2, category.getId());
            if(st.executeUpdate() != 0){
                            JOptionPane.showMessageDialog(null, "New Item has been updated");
                        }else{
                            JOptionPane.showMessageDialog(null, "There are some error. Please check");
                        }
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    public void editItem(Item item,String imagePath){
        if(imagePath == null){
            try{
            PreparedStatement st = this.conn.prepareStatement("UPDATE `item` SET `name`=?,`description`=?,`price`=?,`stock`=?,catName=? WHERE `id`=?");
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setFloat(3, item.getPrice());
            st.setInt(4, item.getStock());
            st.setString(5,item.getCategory());
            st.setLong(6,item.getId());
            if(st.executeUpdate() != 0){
                    JOptionPane.showMessageDialog(null, "Item has been updated");
            }else{
                    JOptionPane.showMessageDialog(null, "There are some error. Please check");
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        try{
            PreparedStatement st = this.conn.prepareStatement("UPDATE `item` SET `name`=?,`description`=?,`price`=?,`stock`=?,`image`=?,catName=? WHERE `id`=?");
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setFloat(3, item.getPrice());
            st.setInt(4, item.getStock());
            st.setString(6,item.getCategory());
            st.setLong(7,item.getId());
            //insert images
            try {
                        //save the image as blob in the database
                        
                        if(imagePath != null){
                            InputStream image = new FileInputStream(imagePath);
                            st.setBlob(5, image);

                        }else{
                            st.setNull(5, java.sql.Types.NULL);
                        }
                        
                       
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Register_Form.class.getName()).log(Level.SEVERE, null, ex);
                    }
            if(st.executeUpdate() != 0){
                    JOptionPane.showMessageDialog(null, "Item has been updated");
            }else{
                    JOptionPane.showMessageDialog(null, "There are some error. Please check");
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    public void deleteItem(String itemName){
        try{
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM `item` where name = ?");
            st.setString(1, itemName);
            if(st.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "Item Deleted");
            }else{
                JOptionPane.showMessageDialog(null, "No Item Exist With This name");
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteCategory(String catName){
        try{
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM `category` where name = ?");
            st.setString(1, catName);
            if(st.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null, "Category Deleted");
            }else{
                JOptionPane.showMessageDialog(null, "No Category Existed With This name");
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Integer getItemStock(String itemName){
        Integer stock = 0;
        try{
            PreparedStatement st;
            ResultSet res;
            String query = "SELECT * FROM `item` WHERE `name` = ?"; 
            st = this.conn.prepareStatement(query);
            st.setString(1, itemName);
            res = st.executeQuery();
            while(res.next()){
               stock = res.getInt("stock");
               
            }
            res.close();
            st.close();
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stock;
    }
    public ArrayList<Item> getItem(String category){
        ArrayList<Item> items = new ArrayList<Item>();
        try{

            PreparedStatement st;
            ResultSet res;
            String query;
            if(category.equals("All")){
                query = "SELECT * FROM `item`"; 
                st = this.conn.prepareStatement(query);
            }else{
                 query = "SELECT * FROM `item` WHERE `catName` = ?"; 
                st = this.conn.prepareStatement(query);
                st.setString(1, category);
            }
            
            res = st.executeQuery();
            while(res.next()){
               Long id = res.getLong("id");
                String name = res.getString("name");
                String description = res.getString("description");
                Integer stock = res.getInt("stock");
                Float price = res.getFloat("price");
               String catName = res.getString("catName");
  
                Item item = new Item(id,name,description,price,stock,catName,res.getBytes("image"));
                items.add(item);
            }
            res.close();
            st.close();
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    public ArrayList<Category> getCategory(){
        ArrayList<Category> categories = new ArrayList<Category>();
         try{
            Statement st = this.conn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM category");
            while(res.next()){
               Integer id = res.getInt("id");
                String name = res.getString("name");
             
                Category category = new Category(id,name);
                categories.add(category);
            }
            res.close();
            st.close();
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    public ArrayList<Voucher> getDailyReport(String date){
        ArrayList<Voucher> vouchers = new ArrayList<Voucher>();
        try{
            PreparedStatement st;
            ResultSet res;
            String query = "SELECT * FROM `voucher` WHERE `date` = ?"; 
            st = this.conn.prepareStatement(query);
            st.setString(1, date);
            res = st.executeQuery();
            while(res.next()){
                String itemName = res.getString("itemName");
                Float itemPrice = res.getFloat("itemPrice");
                Integer itemQuantity = res.getInt("itemQuantity");
                Float totalCost = res.getFloat("totalCost");
                Voucher voucher = new Voucher(itemName, itemPrice, itemQuantity, totalCost);
                vouchers.add(voucher);
            }
            res.close();
            st.close();
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vouchers;
    }
     public ArrayList<Voucher> getMonthlyReport(String startDate,String endDate){
        ArrayList<Voucher> vouchers = new ArrayList<Voucher>();
        try{
            PreparedStatement st;
            ResultSet res;
            String query = "SELECT * FROM `voucher` WHERE `date` >= ? AND `date`<= ?"; 
            st = this.conn.prepareStatement(query);
            st.setString(1, startDate);
            st.setString(2, endDate);
            res = st.executeQuery();
            while(res.next()){
                String itemName = res.getString("itemName");
                Float itemPrice = res.getFloat("itemPrice");
                Integer itemQuantity = res.getInt("itemQuantity");
                Float totalCost = res.getFloat("totalCost");
                Voucher voucher = new Voucher(itemName, itemPrice, itemQuantity, totalCost);
                vouchers.add(voucher);
            }
            res.close();
            st.close();
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vouchers;
    }
    public User getUser(String username,String password){
//        String position="";
    User user = new User();
        try{
            PreparedStatement st;
            ResultSet res;
            String query = "SELECT * FROM `users` WHERE `username` = ? AND `password` = ?"; 
            
                if(username.trim().equals("username")){
                    JOptionPane.showMessageDialog(null,"Enter your username","Empty Username",2);
                }else if(password.trim().equals("password")){
                    JOptionPane.showMessageDialog(null, "Enter your password","Empty Password",2);
                }else{
                  
            st = this.conn.prepareStatement(query);
            st.setString(1, username);
            st.setString(2, password);
            res = st.executeQuery();
           if(res.next()){
                //show a new form
                String position = res.getString("position");
               String name = res.getString("username");
               user = new User(null,name,null,null,position);
                
                //this.dispose();
            }else{
                //error message
              
                JOptionPane.showMessageDialog(null, "Invalid Username / Password", "Login Error",2);
            }
             }
        }catch(SQLException ex){
            Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
  
  public void insertVoucher(Voucher voucher){
      try{
          PreparedStatement st = this.conn.prepareStatement("INSERT INTO `voucher`(date,itemName,itemPrice,itemQuantity,totalCost) VALUES(?,?,?,?,?)");
          st.setString(1, voucher.getDate());
          st.setString(2, voucher.getItemName());
          st.setFloat(3, voucher.getItemPrice());
          st.setInt(4, voucher.getItemQuantity());
          st.setFloat(5,voucher.getTotalCost());
          st.executeUpdate();
          
      }catch(SQLException ex){
          Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  public boolean checkUsername(String username){
      boolean username_exist = false;
      try{
          PreparedStatement st = this.conn.prepareStatement("SELECT * FROM `users` WHERE `username`=?");
          st.setString(1, username);
          ResultSet res = st.executeQuery();
          if(res.next()){
              username_exist = true;
              JOptionPane.showMessageDialog(null,"This Username is already taken, choose another one", "Username Failed", 2);
          }
          
      }catch(SQLException ex){
          Logger.getLogger(DAOConnection.class.getName()).log(Level.SEVERE, null, ex);
      }
      return username_exist;
  }

    public static void main(String []args)
    {
        DAOConnection dao = new DAOConnection();
        
    }
}
