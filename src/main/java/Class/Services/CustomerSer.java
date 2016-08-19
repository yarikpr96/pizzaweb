package Class.Services;

import Class.Entity.Customer;

import java.util.List;


public interface CustomerSer {
    void addOrEdit(Customer ordering);
    void delete(int id);
    Customer findOne(int id);
    List<Customer> findAll();
}
