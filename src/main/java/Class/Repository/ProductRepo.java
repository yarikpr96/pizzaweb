package Class.Repository;

import Class.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Ярослав on 16.08.2016.
 */
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("FROM Product ")
    List<Product> findAll();


}
