package Class.Services.Implementation;

import Class.Entity.Ordering;
import Class.Repository.OrderingRepo;
import Class.Services.OrderingSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ярослав on 18.08.2016.
 */
@Service
public class OrderingSerImpl implements OrderingSer {
    @Autowired
     private OrderingRepo orderingRepo;

    @Override
    public void addOrEdit(Ordering ordering) {
        orderingRepo.save( ordering );
    }

    @Override
    public void delete(int id_o) {
        orderingRepo.delete( id_o );

    }

    @Override
    public Ordering findOne(int id_o) {
        return orderingRepo.findOne( id_o );
    }

    @Override
    public List<Ordering> findAll() {
        return orderingRepo.findAll();
    }
}
