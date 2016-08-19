package Class.Entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Ярослав on 18.08.2016.
 */
@Entity
public class Ordering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_o;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column
    private String address;
    @Column
    private Double sum;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pizza_ordering",joinColumns = @JoinColumn(name = "id_o"), inverseJoinColumns = @JoinColumn(name = "id_p"))
    private List<Pizza> pizzaList;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    public Ordering() {this.date = Calendar.getInstance().getTime();

    }

    public int getId_o() {
        return id_o;
    }

    public void setId_o(int id_o) {
        this.id_o = id_o;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    @Override
//    public String toString() {
//        return "Ordering{" +
//                "id_o=" + id_o +
//                ", date=" + date +
//                ", address='" + address + '\'' +
//                ", sum=" + sum +
//                '}';
//    }

    @Override
    public String toString() {
        return "Ordering{" +
                "id_o=" + id_o +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", sum=" + sum +

                '}';
    }
}
