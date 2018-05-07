package models;
// default package



/**
 * Moneys entity. @author MyEclipse Persistence Tools
 */
public class Moneys extends AbstractMoneys implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Moneys() {
    }

    
    /** full constructor */
    public Moneys(Integer count, String goodName, Double money) {
        super(count, goodName, money);        
    }
   
}
