package Class.Repository;

import Class.Entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Ярослав on 16.08.2016.
 */
public interface PizzaRepo extends JpaRepository<Pizza,Integer> {
    @Query("FROM Pizza ")
    List<Pizza> findAll();

    @Query("from Pizza p where p.name LIKE :name")
    List<Pizza> findByName (@Param("name")String name);

}
