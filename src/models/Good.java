package models;
// default package



/**
 * Good entity. @author MyEclipse Persistence Tools
 */
public class Good extends AbstractGood implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Good() {
    }

    
    /** full constructor */
    public Good(String name, Double price, String info, Integer num) {
        super(name, price, info, num);        
    }
   
}
