package gr.uop;




public class Product {
    private String name;
    private Integer id;
    private Integer priceCar;
    private Integer priceJeep;
    private Integer priceMoto; 
    public Product(String name,Integer id ,Integer priceCar, Integer priceJeep,Integer priceMoto) {
        this.name = name;
        this.id = id;
        this.priceCar = priceCar;
        this.priceJeep = priceJeep;
        this.priceMoto = priceMoto;
    }
    public String getName(){
        return this.name;
    }    
    public Integer getId(){
        return  this.id;
    }
    public Integer getPriceCar(){
        return  this.priceCar;
    }
    public Integer getPriceJeep(){
        return  this.priceJeep;
    }
    public Integer getPriceMoto(){
        return  this.priceMoto;
    }
    public void setName(String name){
        this.name = name;
    }    
    public void setId(Integer id){
        this.id = id;
    }
    public void setPriceCar(Integer priceCar){
        this.priceCar = priceCar;
    }
    public void setPriceJeep(Integer priceJeep){
        this.priceJeep = priceJeep;
    }
    public void setPriceMoto(Integer priceMoto){
        this.priceMoto = priceMoto;
    }
}
