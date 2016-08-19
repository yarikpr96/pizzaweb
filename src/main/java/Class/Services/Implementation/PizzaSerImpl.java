package Class.Services.Implementation;

import Class.DTO.PizzaDTO;
import Class.Entity.Pizza;
import Class.Repository.PizzaRepo;
import Class.Services.PizzaSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Created by Ярослав on 16.08.2016.
 */
@Service
public class PizzaSerImpl implements PizzaSer {

    @Autowired
    private PizzaRepo pizzaRepo;

    @Override
    public void addOrEdit(Pizza pizza) {
        pizzaRepo.save( pizza );
    }

    @Override
    public void edit(int id_p, String name, double price, String description, MultipartFile multipartFile) {
        Pizza pizza = pizzaRepo.findOne( id_p );
        pizza.setName( name );
        pizza.setDescription( description );
        pizza.setPrice( price );
        try {
            pizza.setImage( multipartFile.getBytes() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        pizzaRepo.save( pizza );
    }


    @Override
    public void delete(int id_p) {
        pizzaRepo.delete( id_p );

    }

    @Override
    public PizzaDTO findOne(int id_p) {
        Pizza pizza = pizzaRepo.findOne( id_p );
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId_p( pizza.getId_p() );
        pizzaDTO.setName( pizza.getName() );
        pizzaDTO.setDescription( pizza.getDescription() );
        pizzaDTO.setPrice( pizza.getPrice() );
        String image = Base64.getEncoder().encodeToString( pizza.getImage() );
        pizzaDTO.setImage( image );

        return pizzaDTO;


    }

    @Override
    public Pizza findOneById(int id_p) {
        return pizzaRepo.findOne( id_p );
    }


    @Override
    public List<PizzaDTO> findAll() {
        List<PizzaDTO> pizzaDTOs = new ArrayList<>();
        List<Pizza> pizzaList = pizzaRepo.findAll();

        for (Pizza pizza : pizzaList) {
            PizzaDTO pizzaDTO = new PizzaDTO();
            pizzaDTO.setId_p( pizza.getId_p() );
            pizzaDTO.setName( pizza.getName() );
            pizzaDTO.setDescription( pizza.getDescription() );
            pizzaDTO.setPrice( pizza.getPrice() );
            String image = Base64.getEncoder().encodeToString( pizza.getImage() );
            pizzaDTO.setImage( image );
            pizzaDTOs.add( pizzaDTO );
        }
        return pizzaDTOs;
    }

    @Override
    public List<PizzaDTO> findByName(String name) {
        List<PizzaDTO>pizzaDTOs = new ArrayList<>();
                List<Pizza>pizzaList = pizzaRepo.findByName(name);
        for (Pizza pizza: pizzaList){
            PizzaDTO pizzaDTO = new PizzaDTO();
            pizzaDTO.setId_p( pizza.getId_p() );
            pizzaDTO.setName( pizza.getName() );
            pizzaDTO.setPrice( pizza.getPrice());
            pizzaDTO.setDescription( pizza.getDescription() );
            String image = Base64.getEncoder().encodeToString( pizza.getImage() );
            pizzaDTO.setImage( image );
            pizzaDTOs.add( pizzaDTO );
        }
        return pizzaDTOs;
    }

}
