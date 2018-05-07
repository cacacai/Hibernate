package models;
// default package



/**
 * AbstractGood entity provides the base persistence definition of the Good entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGood  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Double price;
     private String info;
     private Integer num;


    // Constructors

    /** default constructor */
    public AbstractGood() {
    }

    
    /** full constructor */
    public AbstractGood(String name, Double price, String info, Integer num) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.num = num;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInfo() {
        return this.info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getNum() {
        return this.num;
    }
    
    public void setNum(Integer num) {
        this.num = num;
    }
   








}