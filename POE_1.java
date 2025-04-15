/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poe_1;
import javax.swing.*;
import java.util.regex.Pattern;
/**
 *
 * @author RC_Student_lab
 */
public class POE_1 {
    public String Username,Password,Cellphone,Name,Surname;
    
    //Create a method thhat checks the user
   public void checkUsername(){
       //Conditions that are required for usesrname to be checked
       boolean underscore = Username.contains("_");
       if (underscore == true && Username.length()<=5){
           JOptionPane.showMessageDialog(null,"Username is successfuly captured");
       }else{
           JOptionPane.showMessageDialog(null,"Your username is invalid. Please ensure that it consists of an underscore and is no more than FIVE characters long");
           Username = JOptionPane.showInputDialog(null,"Enter your username");
           //Call the method until it is valid
           checkUsername();
       }
   }
//Create a method that checks the complexity of a password
   public static boolean checkPasswordComplexity(String password){
       boolean hasUppercase = false,hasDigit = false,hasSpecialChar = false;
       //Define special characters
       String SpecialChar = "!@#$%^&*()_+{}|:?><,./';[]\":;~`";
       //Check each character in Password
       for (char ch:password.toCharArray()){
           if (Character.isUpperCase(ch))hasUppercase = true;
           if (Character.isDigit(ch))hasDigit = true;
           if (SpecialChar.contains(String.valueOf(ch)))hasSpecialChar = true;
       }
       //Validate all the conditions
       return password.length()>=8 && hasUppercase && hasDigit && hasSpecialChar;
   }
   //Create a method that checks the validity of a cellphone number
   public boolean checkCellphone(String cellNumber){
       if (cellNumber == null || cellNumber.trim().isEmpty()){
           return false;
       }
       return Pattern.matches("^\\+27\\d{9}$", cellNumber.trim());
       
   }
   //Create a method that registers the user
   public void registerUser(){
       //Ask the user to input their  details
       Name = JOptionPane.showInputDialog("Please enter your name");
       Surname = JOptionPane.showInputDialog( "Please enter your surname");
       //Prompt the user to enter their username
       Username = JOptionPane.showInputDialog("Please enter your username");
       //Call the method that checks the user
       checkUsername();
       //Prompt the user to enter their Password
       Password = JOptionPane.showInputDialog("Please enter your password");
       //Check password complexity
       while (!checkPasswordComplexity(Password)){
           JOptionPane.showMessageDialog(null,"Your password is invalid,please make sure that it is 8 characters long and at least contains one special character and a number");
           Password = JOptionPane.showInputDialog("Please enter your password");
       }
       //Prompt the user to enter their cellphone number
       String CellInput = JOptionPane.showInputDialog("Please enter your cellphone number e.g.(+27821234567)");
       //Validate cellphone number format
       while (!checkCellphone(CellInput)){
           JOptionPane.showMessageDialog(null,"Your cellphone number is invalid,please make sure your cellphone number starts with +27");
           CellInput = JOptionPane.showInputDialog("Please enter your cellphone number");
       }
       Cellphone = CellInput;
           //After all validations ,show succes message
           JOptionPane.showMessageDialog(null,"""
                                              User registration successful!
                                              Name:  """ + Name + " " + Surname + "\n" +
                   "Username: " + Username + "\n" +
                   "Password: " + Password + "\n" +
                   "Cellphone: " + Cellphone);
       
       
       
           
   }
   //Login class
   public static class UserLogin{
      private String storedUsername;
      private String storedPassword;
      
      //A constructor that sets the username and password
      public UserLogin(String Username, String Password){
          this.storedUsername = Username;
          this.storedPassword = Password;
      }
       //A method that authenticates the user
      public boolean authenticate(String Username, String Password){
          return storedUsername.equals(Username) && storedPassword.equals(Password);
      }
      //Create a login method
      public void LoginUser(){
          String usernameInput = JOptionPane.showInputDialog("Enter your Username");
          String passwordInput = JOptionPane.showInputDialog("Enter your Password");
          if (authenticate(usernameInput,passwordInput)){
              JOptionPane.showMessageDialog(null,"Login successful");
          }else{
              JOptionPane.showMessageDialog(null,"Invalid Username or Password");
          }
      }
   }
  
   
    public static void main(String[] args) {
        POE_1 userSignIn = new POE_1();
        userSignIn.registerUser();
        
        //Create login after registration
        UserLogin userLogin = new UserLogin(userSignIn.Username,userSignIn.Password);
        
        //Allow the user to login
        int loginOption = JOptionPane.showConfirmDialog(null,"Do you still want to login?", "UserLogin",JOptionPane.YES_NO_OPTION);
        
        if (loginOption == JOptionPane.YES_OPTION){
            userLogin.LoginUser();
        }else{
            JOptionPane.showMessageDialog(null,"Tnank you for registering");
        }
    }
}



