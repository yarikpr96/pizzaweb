package Class.Entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id_p;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private double price;
    @Column
    @Lob
    private byte [] image = new byte[1];
    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "pizza")
    List<Product>productList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pizza_ordering",joinColumns = @JoinColumn(name = "id_p"), inverseJoinColumns = @JoinColumn(name = "id_o"))
    private List<Ordering>orderingList;

    public Pizza() {
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public List<Ordering> getOrderingList() {
        return orderingList;
    }

    public void setOrderingList(List<Ordering> orderingList) {
        this.orderingList = orderingList;
    }

//    @Override
//    public String toString() {
//        return "|Pizza|" +" "+
//                ", name" +" "+ name +
//                ", description"+" " + description +
//                ", price"+" " + price +" грн."+" "
//                ;
//    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id_p=" + id_p +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +

                '}';
    }
}
