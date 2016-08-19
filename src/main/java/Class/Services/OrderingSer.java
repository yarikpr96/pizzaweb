package Class.Services;

import Class.Entity.Ordering;

import java.util.List;

/**
 * Created by Ярослав on 18.08.2016.
 */
public interface OrderingSer {
    void addOrEdit(Ordering ordering);
    void delete(int id_o);
    Ordering findOne(int id_o);
    List<Ordering>findAll();





}
