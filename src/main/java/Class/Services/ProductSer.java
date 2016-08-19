package Class.Services;

import Class.Entity.Product;

import java.util.List;

/**
 * Created by Ярослав on 16.08.2016.
 */
public interface ProductSer {
    void addOrEdit(Product product);
    void delete(int id);
    Product findOne(int id);
    List<Product> findAll();
}
