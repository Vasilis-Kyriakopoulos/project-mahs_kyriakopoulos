package gr.uop;

import java.io.Serializable;

public class Customer implements Serializable{
    private Integer price;
    private String pinakida;
    private String date;
    private String departureDate;
    
    public Customer(String pinakida,Integer price,String date){
        this.price = price;
        this.pinakida = pinakida;
        this.date = date;
    }
    public Customer(String pinakida,String date){
        this.pinakida = pinakida;
        this.date = date;
    }
    public Customer(String pinakida,Integer price,String date,String departureDate){
        this.pinakida = pinakida;
        this.price = price;
        this.date = date;
        this.departureDate = departureDate;
    }
    public Integer getPrice(){
        return  this.price;
    }
    public String getDepartureDate(){
        return  this.departureDate;
    }
    public String getPinakida(){
        return  this.pinakida;
    }
    public String getDate(){
        return  this.date;
    }
    public void setPrice(Integer price){
         this.price = price;
    }
    public void setDepartureDate(String departureDate){
         this.departureDate = departureDate;
    }
    public void setPinakida(String pinakida){
          this.pinakida = pinakida;
    }
    public void setDate(String date){
         this.date = date;
    }
    public String toString(){
        return "Pinakida:" + this.getPinakida() + " Timi:" + this.getPrice()+" Ημερομηνία:"+ this.date+" Αναχώρηση:"+ this.getDepartureDate(); 
    }
}
