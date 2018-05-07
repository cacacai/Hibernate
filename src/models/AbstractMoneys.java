package models;
// default package



/**
 * AbstractMoneys entity provides the base persistence definition of the Moneys entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMoneys  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer count;
     private String goodName;
     private Double money;


    // Constructors

    /** default constructor */
    public AbstractMoneys() {
    }

    
    /** full constructor */
    public AbstractMoneys(Integer count, String goodName, Double money) {
        this.count = count;
        this.goodName = goodName;
        this.money = money;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return this.count;
    }
    
    public void setCount(Integer count) {
        this.count = count;
    }

    public String getGoodName() {
        return this.goodName;
    }
    
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Double getMoney() {
        return this.money;
    }
    
    public void setMoney(Double money) {
        this.money = money;
    }
   








}