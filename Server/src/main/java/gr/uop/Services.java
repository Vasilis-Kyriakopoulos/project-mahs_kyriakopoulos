package gr.uop;

import java.util.ArrayList;

public class Services {
    ArrayList<Product> list;
    Integer size ;
    public Services(){
        this.list = new ArrayList<Product>();
        this.list.add(new Product("Πλύσιμο εξωτερικό", 1, 7, 8, 6));
        this.list.add(new Product("Πλύσιμο εσωτερικό", 2, 6, 7, 0));
        this.list.add(new Product("Πλύσιμο εξωτ.+εσωτ.", 3, 12, 14, 0));
        this.list.add(new Product("Πλύσιμο εξωτ. σπέσιαλ", 4, 9, 10, 0));
        this.list.add(new Product("Πλύσιμο εσωτ. σπέσιαλ", 5, 8, 9, 0));
        this.list.add(new Product("Πλύσιμο εξωτ. + εσωτ. σπέσιαλ", 6, 15, 17, 0));
        this.list.add(new Product("Βιολογικός καθαρισμός εσωτ.", 7, 80, 80, 0));
        this.list.add(new Product("Κέρωμα‐Γυάλισμα",8, 80, 90, 40));
        this. list.add(new Product("Καθαρισμός κινητήρα",9, 20, 20, 10));
        this.list.add(new Product("Πλύσιμο σασί",10, 3, 3, 0));
        this.size = 10;
    }
    ArrayList<Product> getList(){
        return this.list;
    }
    Integer getSize(){
        return this.size;
    }
}
