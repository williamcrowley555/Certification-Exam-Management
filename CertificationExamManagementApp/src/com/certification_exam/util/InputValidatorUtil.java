/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certification_exam.util;

import java.sql.Types;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author HP
 */
public class InputValidatorUtil {
    
    
    public static String isValidPattern(String value, String regex, String message) {
        if (value.isEmpty()) return " không được để trống";
        
        value = RemoveAccentUtil.removeAccent(value);
        value = value.trim();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        boolean result = matcher.find();
        if(result) {
            return message;
        }
        return "";
    }
    
    public static String isValidName(String name, boolean whitespace) {
        name = RemoveAccentUtil.removeAccent(name);
       
        name = name.trim();
        if (name.isEmpty()) return " không được để trống";
        
        String space = whitespace ? "\\s" : "";

        String regex = "[^A-Za-z" + space + "]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        boolean result = matcher.find();
        if(result) {
            String extraMessage = whitespace ? "" : ", khoảng trắng";
            return " không bao gồm số, ký tự đặc biệt" + extraMessage;
        }
        return "";
    }
    
    public static String isValidAddress(String name) {
        if (name.isEmpty()) return " không được để trống";
        
        boolean result = isVailidNumber(name).isEmpty();
        if(result) {
            return "Địa chỉ không hợp lệ";
        }
        
        name = RemoveAccentUtil.removeAccent(name);
        name = name.trim();
        String regex = "[^A-Za-z0-9.,\\-\\s\\/]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        result = matcher.find();
        if(result) {
            return "Địa chỉ không hợp lệ";
        }
        return "";
    }
    
    public static String isValidInvoiceNumber(String invoiceNumber) {
        if (invoiceNumber.isEmpty()) return " không được để trống";
        
        invoiceNumber = RemoveAccentUtil.removeAccent(invoiceNumber);
        invoiceNumber = invoiceNumber.trim();
        String regex = "[^A-Za-z0-9\\-\\s]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(invoiceNumber);
        boolean result = matcher.find();
        if(result) {
            return "Hóa đơn không hợp lệ";
        }
        return "";
    }
    
    public static String isValidBirthDate(LocalDate birthDate, int smallestAge) {
        if (birthDate == null) return " không được để trống";
        LocalDate toDay = LocalDate.now();
        int age = Period.between(birthDate, toDay).getYears();
            
            if (smallestAge > 0  && smallestAge <= 100)
            {
                if (age > 0 && age <= 100) 
                {
                     if (age < smallestAge) return " phải trên "+ smallestAge + " tuổi";
                     else return "";
                }
                else return "Tuổi không hợp lệ";
            }   
            else return "Tuổi nhỏ nhất không hợp lệ";  
           
    }
    
    public static String isValidStartDate(LocalDate startDate, String message) {     
        if (startDate == null) return " không được để trống";
        LocalDate toDay = LocalDate.now();
            if (startDate.isBefore(toDay))
            return message;
            else return "";         
    }
    
    public static String isValidEndDate(LocalDate startDate, LocalDate endDate, String message) {     
        if (endDate == null) return " không được để trống";
        if (endDate.isBefore(startDate))
            return message;
            else return "";         
    }
    
    public static String isValidEmail(String email) {
        if (email.isEmpty()) return " không được để trống";
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex) ? "" : "Email không hợp lệ";
    }
    
    public static String isInteger(String number) {
        try {
            Integer num = Integer.parseInt(number);
        } catch(Exception e) {
            return "Số nhập vào phải là số nguyên";
        }
        return "";
    }
    
    public static String isLong(String number) {
        try {
            Long num = Long.parseLong(number);
        } catch(Exception e) {
            return "Số nhập vào phải là số nguyên";
        }
        return "";
    }
    
    public static String isVailidNumber(String number) {
        if (number.isEmpty()) return " không được để trống";
        String regex = "[^0-9]";
        boolean result = number.matches(regex);
        if(result) {
            return "Số không hợp lệ";
        }
        
        String message = isInteger(number);
        if(!message.isEmpty()) return message;
        
        return "";
    }
    
    public static String isVailidNumber(String number, Integer min, Integer max) {
        if (number.isEmpty()) return " không được để trống";
        String regex = "[^0-9]";
        boolean result = number.matches(regex);
        if(result) {
            return "Số không hợp lệ";
        }
        
        String message = isInteger(number);
        if(!message.isEmpty()) return message;
        
        Integer num = Integer.parseInt(number);
        if(min != null) {
            if(num < min) {
                return "Số nhập vào tối thiểu là " + min;
            }
        }
        if(max != null) {
            if(num > max) {
                return "Số nhập vào tối đa là " + max;
            }
        }
        return "";
    }
    
    public static String isValidMoney(String money)
    {
        if (money.isEmpty()) return " không được để trống";
        String regex = "[^0-9]";
        boolean result = money.matches(regex);
        if(result) {
            return "Số tiền không hợp lệ";
        }
        String message = isInteger(money);
        if(!message.isEmpty()) return message;
        if(Long.parseLong(money)<0) return "Số tiền phải lớn hơn 0";
        return "";
    }
    
    public static String isVailidPhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) return " không được để trống";
        String regex = "0{1}\\d{9,10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean result = matcher.find();
        if(!result) {
            return "Số điện thoại không hợp lệ";
        }
        
        String message = isLong(phoneNumber); 
        if(!message.isEmpty()) return message;
        if(Long.parseLong(phoneNumber)<0) return "Số điện thoại không hợp lệ";
        return "";
    }
    
    public static String isVailidIdentityID(String id) {
        if (id.isEmpty()) return " không được để trống";
        String regex = "^\\d{9}$|^\\d{12}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        boolean result = matcher.find();
        if(!result) {
            return "CMND không hợp lệ";
        }
        
        String message = isLong(id);
        if(!message.isEmpty()) return message;
        
        return "";
    }
    
    public static void main(String[] args)
    {
        String rs = "123456789121";
        if (InputValidatorUtil.isVailidIdentityID(rs).isEmpty())
            System.out.println("ok");
        else System.out.println(InputValidatorUtil.isVailidIdentityID(rs));
    }
}
