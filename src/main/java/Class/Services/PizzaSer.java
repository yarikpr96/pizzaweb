package Class.Services;

import Class.DTO.PizzaDTO;
import Class.Entity.Pizza;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Ярослав on 16.08.2016.
 */
public interface PizzaSer {
    void addOrEdit(Pizza pizza);
    void edit(int id_p , String name ,double price , String description , MultipartFile multipartFile);
    void delete(int id_p);

    PizzaDTO findOne(int id_p);
    Pizza findOneById(int id_p);

    List<PizzaDTO> findAll();
    List<PizzaDTO> findByName(String name);
}
