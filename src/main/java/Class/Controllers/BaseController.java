package Class.Controllers;

import Class.DTO.PizzaDTO;
import Class.Entity.Customer;
import Class.Entity.Ordering;
import Class.Entity.Pizza;
import Class.Entity.Product;
import Class.Services.CustomerSer;
import Class.Services.OrderingSer;
import Class.Services.PizzaSer;
import Class.Services.ProductSer;
import Class.Validations.CustomersVal;
import com.google.common.collect.Maps;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

import static Class.Controllers.AbstractSample.encodeParams;

@Controller
public class BaseController {
    @Autowired
    private ProductSer productSer;
    @Autowired
    private PizzaSer pizzaSer;
    @Autowired
    private OrderingSer orderingSer;
    @Autowired
    private CustomerSer customerSer;
    @Autowired
    private CustomersVal customersVal;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String home() {
        return "views-base-home";
    }

    @RequestMapping(value = "/gallery", method = RequestMethod.GET)
    private String gallery() {
        return "views-base-gallery";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    private String contact() {
        return "views-base-contact";
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.GET)
    private String delivery() {
        return "views-base-delivery";
    }

    @RequestMapping(value = "/pizzaNew", method = RequestMethod.GET)
    private String createNewProduct() {
        return "views-pizza-pizzaNew";
    }

    @RequestMapping(value = "/pizzaNew", method = RequestMethod.POST)
    public String createPizza(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("price") double price, @RequestParam("image") MultipartFile multipartFile) {
        Pizza pizza = new Pizza();
        pizza.setName( name );
        pizza.setDescription( description );
        pizza.setPrice( price );
        try {
            pizza.setImage( multipartFile.getBytes() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        pizzaSer.addOrEdit( pizza );
        return "redirect:/allPizza";
    }

    @RequestMapping(value = "/allPizza", method = RequestMethod.GET)
    private String allPizza(Model model) {
        List<PizzaDTO> pizzaDTOList = pizzaSer.findAll();
        List<PizzaDTO> dto = pizzaSer.findByName( "My pizza" );
        Iterator<PizzaDTO> iter = pizzaDTOList.iterator();
        for (PizzaDTO p : dto) {
            while (iter.hasNext()) {
                if (iter.next().getName().equals( p.getName() )) {
                    iter.remove();
                }
            }
            model.addAttribute( "pizza", pizzaDTOList );
        }
        return "views-pizza-allPizza";
    }

    @RequestMapping(value = "/editP/{id_p}", method = RequestMethod.GET)
    public String edit(@PathVariable String id_p, Model model) {
        model.addAttribute( "pizza", pizzaSer.findOne( Integer.parseInt( id_p ) ) );
        return "views-pizza-editP";
    }

    @RequestMapping(value = "/editP", method = RequestMethod.POST)
    public String edit(@RequestParam("id_p") int id_p,
                       @RequestParam("name") String name,
                       @RequestParam("description") String description,
                       @RequestParam("price") double price,
                       @RequestParam("image") MultipartFile multipartFile) {
        pizzaSer.edit( id_p, name, price, description, multipartFile );
        return "redirect:/allPizza";
    }

    @RequestMapping(value = "/pizza/delete/{id_p}", method = RequestMethod.POST)
    public String delete(@PathVariable String id_p) {
        pizzaSer.delete( Integer.parseInt( id_p ) );
        return "redirect:/allPizza";
    }

    @RequestMapping(value = "/allProduct", method = RequestMethod.GET)
    private String allProduct(Model model) {
        List<Product> productList = productSer.findAll();
        model.addAttribute( "product", productList );
        return "views-product-allProduct";

    }

    @RequestMapping(value = "/productNew", method = RequestMethod.GET)
    private String createNewProduct(Model model) {
        model.addAttribute( "product", new Product() );
        return "views-product-productNew";
    }


    @RequestMapping(value = "/productNew", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute Product product) {
        productSer.addOrEdit( product );
        return "redirect:/allProduct";
    }

    @RequestMapping(value = "/productEdit/{id}", method = RequestMethod.GET)
    public String editPr(@PathVariable String id, Model model) {
        model.addAttribute( "product", productSer.findOne( Integer.parseInt( id ) ) );
        return "views-product-productEdit";
    }

    @RequestMapping(value = "/productEdit", method = RequestMethod.POST)
    public String editPr(@ModelAttribute Product product) {
        productSer.addOrEdit( product );
        return "redirect:/allProduct";
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.POST)
    public String deleteP(@PathVariable String id) {
        productSer.delete( Integer.parseInt( id ) );
        return "redirect:/allProduct";
    }

    @RequestMapping(value = "/loginpage", method = RequestMethod.GET)
    public String login() {
        return "views-base-login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute( "customer", new Customer() );
        return "views-base-registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute Customer customer, BindingResult bindingResult) {
        customersVal.validate( customer, bindingResult );
        if (bindingResult.hasErrors()) {
            return "views-base-registration";
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            customer.setPassword( bCryptPasswordEncoder.encode( customer.getPassword() ) );
            customerSer.addOrEdit( customer );
            return "redirect:/";
        }
    }

    //додати піщу в замовлення
    @RequestMapping(value = "/pizz/{id_p}", method = RequestMethod.GET)
    public String addToBasket(HttpSession httpSession, @PathVariable String id_p) {
        List<Pizza> pizzas = (List<Pizza>) httpSession.getAttribute( "pizzas" );
        Pizza pizza = pizzaSer.findOneById( Integer.parseInt( id_p ) );
        if (pizzas == null) {
            pizzas = new ArrayList<Pizza>();
        }
        pizzas.add( pizza );
        System.out.println( pizzas );
        httpSession.setAttribute( "pizzas", pizzas );
        return "redirect:/allPizza";
    }

    //все замовлення
    @RequestMapping(value = "/pizz", method = RequestMethod.GET)
    private String addToBasket(HttpSession httpSession, Model model) {
        List<Pizza> pizzas = (List<Pizza>) httpSession.getAttribute( "pizzas" );
        if (pizzas == null) {
            pizzas = new ArrayList<Pizza>();
        }
        double d = 0;
        for (Pizza p : pizzas) {
            d += p.getPrice();
        }
        model.addAttribute( "price", d );
        model.addAttribute( "pizzas", pizzas );
        return "views-pizza-pizz";
    }

    //додати замовлення
    @RequestMapping(value = "/pizz", method = RequestMethod.POST)
    public String addToBasket(HttpSession httpSession, @ModelAttribute Ordering ordering, Principal
            principal, @RequestParam("address") String address) {
        List<Pizza> pizzaList = (List<Pizza>) httpSession.getAttribute( "pizzas" );
        if (pizzaList == null) {
            pizzaList = new ArrayList<Pizza>();
        }
        ordering.setAddress( address );
        ordering.setPizzaList( pizzaList );
        ordering.setCustomer( customerSer.findOne( Integer.parseInt( principal.getName() ) ) );
        ordering.setSum( (double) 0 );
        for (Pizza o : pizzaList) {
            ordering.setSum( (double) ordering.getSum() + (double) o.getPrice() );
        }
        for (Pizza p : pizzaList) {
            if (p != null) {
                orderingSer.addOrEdit( ordering );
                httpSession.removeAttribute( "pizzas" );

            }
        }
//        List<Pizza> list = pizzaSer.findByName( "My pizza" );
//        if (list!=null){
//        for (Pizza pizza:list){
//        pizzaSer.delete(pizza.getId_p() );
//        }}

        return "redirect:/oneOrder";
    }

    //очистити всю корзину
    @RequestMapping(value = "/pizzdel", method = RequestMethod.POST)
    public String dellBasket(HttpSession httpSession) {
        List<Pizza> pizzas = (List<Pizza>) httpSession.getAttribute( "pizzas" );
        httpSession.removeAttribute( "pizzas" );
        return "redirect:/allPizza";
    }

    //видалити з  корзини
    @RequestMapping(value = "/pizzdell/{id_p}", method = RequestMethod.POST)
    public String dellBasketid(HttpSession httpSession, @PathVariable String id_p) {
        List<Pizza> pizzas = (List<Pizza>) httpSession.getAttribute( "pizzas" );
        Pizza pizzas1 = pizzaSer.findOneById( Integer.parseInt( id_p ) );
        Iterator<Pizza> iter = pizzas.iterator();
        int count = 0;
        while (iter.hasNext()) {
            if (iter.next().getId_p() == pizzas1.getId_p() && (count == 0)) {
                iter.remove();
                count++;
            }
            pizzas = (List<Pizza>) httpSession.getAttribute( "pizzas" );
        }
        return "redirect:/pizz";
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public String addToPizza(HttpSession httpSession, @PathVariable String id) {
        List<Product> products = (List<Product>) httpSession.getAttribute( "products" );
        Product product = productSer.findOne( Integer.parseInt( id ) );
        if (products == null) {
            products = new ArrayList<Product>();
        }
        products.add( product );
//        System.out.println( products );

        httpSession.setAttribute( "products", products );
        return "redirect:/allProduct";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    private String addToPizza(HttpSession httpSession, Model model) {
        List<Product> products = (List<Product>) httpSession.getAttribute( "products" );
        if (products == null) {
            products = new ArrayList<Product>();
        }
        double d = 66;
        for (Product o : products) {
            d += o.getPrice();
        }

        model.addAttribute( "price", d );
        model.addAttribute( "product", products );
        return "views-product-test";
    }

    @RequestMapping(value = "/testt", method = RequestMethod.POST)
    public String adMyBasket(HttpSession httpSession, @ModelAttribute Pizza pizza) {
        List<Pizza> pizzas = (List<Pizza>) httpSession.getAttribute( "pizzas" );
        List<Product> products = (List<Product>) httpSession.getAttribute( "products" );
        if (pizzas == null) {
            pizzas = new ArrayList<Pizza>();
        }
        Pizza pizza1 = new Pizza();
        pizza1.setPrice( 66 );
        for (Product product : products) {
            pizza1.setPrice( pizza1.getPrice() + product.getPrice() );
        }
        pizza1.setName( "My pizza" );
        pizza1.setDescription( " " );
        for (Product product : products) {
            pizza1.setDescription( pizza1.getDescription() + product.getName() + " " );
        }
        pizzaSer.addOrEdit( pizza1 );
        pizzas.add( pizza1 );
        httpSession.setAttribute( "pizzas", pizzas );
        System.out.println( pizzas );
        return "redirect:/pizz";
    }

    @RequestMapping(value = "/testdd/{id}", method = RequestMethod.POST)
    public String dellm(HttpSession httpSession, @PathVariable String id) {
        List<Product> products = (List<Product>) httpSession.getAttribute( "products" );
        Product product1 = productSer.findOne( Integer.parseInt( id ) );
        Iterator<Product> iter = products.iterator();
        int count = 0;
        while (iter.hasNext()) {
            if (iter.next().getId() == product1.getId() && (count == 0)) {
                iter.remove();
                count++;
            }
            products = (List<Product>) httpSession.getAttribute( "products" );
        }
        return "redirect:/test";
    }

    //очистити всю корзину
    @RequestMapping(value = "/testd", method = RequestMethod.POST)
    public String dellMP(HttpSession httpSession) {
        List<Product> products = (List<Product>) httpSession.getAttribute( "products" );
        httpSession.removeAttribute( "products" );
        return "redirect:/allProduct";
    }

    @RequestMapping(value = "/allOrder", method = RequestMethod.GET)
    private String allOrder(Model model) {
        List<Ordering> orderingList = orderingSer.findAll();
        if (orderingList == null) {
            orderingList = new ArrayList<Ordering>();
        }
        model.addAttribute( "order", orderingList );
        return "views-ordering-allOrder";

    }

    @RequestMapping(value = "/allOrder/delete/{id_o}", method = RequestMethod.POST)
    public String deleteOrder(@PathVariable String id_o) {
        orderingSer.delete( Integer.parseInt( id_o ) );
        return "redirect:/allOrder";
    }

    @RequestMapping(value = "/oneOrder", method = RequestMethod.GET)
    private String oneOrder(Model model, Principal principal) throws IOException, JSONException {

        List<Ordering> orderingList = orderingSer.findAll();
        List<Ordering> oneorderingList = new ArrayList<>();
        List<Ordering> oneeorderingList = new ArrayList<>();
        String des = null;
        int time = 0;
        if (oneorderingList == null) {
            oneorderingList = new ArrayList<Ordering>();
        }


        for (Ordering o : orderingList) {
            if (o.getCustomer().getId() == Integer.parseInt( principal.getName() )) {
                oneorderingList.add( o );
              }
        }
        oneorderingList.sort(Comparator.comparing(Ordering::getDate).reversed());
        if (oneeorderingList == null) {
            oneeorderingList = new ArrayList<Ordering>();
        }
        int co = 0;

        for (Ordering o : oneorderingList) {
            if (co<1) {
                oneeorderingList.add( o );
                co++;
            }
        }
        for (Ordering o : oneeorderingList) {
            des = o.getAddress();

            final String proxy = "http://anonymouse.org/cgi-bin/anon-www.cgi/";
            final String baseUrl = proxy + "http://maps.googleapis.com/maps/api/directions/json";// путь к Geocoding API по
            final Map<String, String> params = Maps.newHashMap();
            params.put( "sensor", "false" );
            params.put( "language", "en" );
            params.put( "mode", "walking" );// способ перемещения, может быть driving, walking, bicycling
            params.put( "origin", "Україна, Львів, вулиця Джерельна , 23" );

            params.put( "destination", "Україна, Львів," + des );// адрес или текстовое значение широты и долготы
            final String url = baseUrl + '?' + encodeParams( params );// генерируем путь с параметрами
            final JSONObject response = JsonReader.read( url );// делаем запрос к вебсервису и получаем от него ответ
            JSONObject location = response.getJSONArray( "routes" ).getJSONObject( 0 );
            location = location.getJSONArray( "legs" ).getJSONObject( 0 );
            final String duration = location.getJSONObject( "duration" ).getString( "text" );
//            StringBuilder sb = new StringBuilder();
//            sb.append( duration );
//            sb.deleteCharAt( sb.length() - 1 );
//            sb.deleteCharAt( sb.length() - 1 );
//            sb.deleteCharAt( sb.length() - 1 );
//            sb.deleteCharAt( sb.length() - 1 );
//            sb.deleteCharAt( sb.length() - 1 );
//            time = 30 + Integer.parseInt( sb.toString() );

        model.addAttribute( "time", duration );
        model.addAttribute( "order", oneeorderingList );}
        return "views-ordering-oneOrder";

    }
}
