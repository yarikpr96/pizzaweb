package Class.Services.Implementation;

import Class.Entity.Product;
import Class.Repository.ProductRepo;
import Class.Services.ProductSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ярослав on 16.08.2016.
 */
@Service
public class ProductSerImpl implements ProductSer {
    @Autowired
    private ProductRepo productRepo;

    @Override
    public void addOrEdit(Product product) {
        productRepo.save( product );

    }

    @Override
    public void delete(int id) {
        productRepo.delete( id );

    }

    @Override
    public Product findOne(int id) {
        return productRepo.findOne( id );
    }

    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }
}
