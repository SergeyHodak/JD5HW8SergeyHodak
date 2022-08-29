package goit.feature.mvc;

import goit.feature.manufacturer.ManufacturerDAO;
import goit.feature.product.Product;
import goit.feature.product.ProductDAO;
import goit.feature.product.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {
    private final ProductDAO productDAO;
    private final ManufacturerDAO manufacturerDAO;

    @GetMapping
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("product");
        String error = null;
        List<ProductDTO> products = new ArrayList<>();
        try {
            for (Product product : productDAO.findAll()) {
                products.add(ProductDTO.fromProduct(product));
            }
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("products", products);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/create")
    public ModelAndView create(@RequestParam("name") String name,
                               @RequestParam("price") String price,
                               @RequestParam("manufacturer") String manufacturer) {
        ModelAndView result = new ModelAndView("product");
        String error;
        List<ProductDTO> products = new ArrayList<>();
        try {
            Product product = new Product(name, new BigDecimal(price));
            product.setManufacturer(manufacturerDAO.findByName(manufacturer));
            productDAO.create(product);
            for (Product unit : productDAO.findAll()) {
                products.add(ProductDTO.fromProduct(unit));
            }
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("products", products);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") UUID id,
                               @RequestParam("name") String name,
                               @RequestParam("price") String price,
                               @RequestParam("manufacturer") String manufacturer) {
        ModelAndView result = new ModelAndView("product");
        String error;
        List<ProductDTO> products = new ArrayList<>();
        try {
            Product product = new Product(name, new BigDecimal(price));
            product.setId(id);
            product.setManufacturer(manufacturerDAO.findByName(manufacturer));
            productDAO.update(product);
            for (Product unit : productDAO.findAll()) {
                products.add(ProductDTO.fromProduct(unit));
            }
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("products", products);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("product");
        String error;
        List<ProductDTO> products = new ArrayList<>();
        try {
            productDAO.deleteById(id);
            for (Product product : productDAO.findAll()) {
                products.add(ProductDTO.fromProduct(product));
            }
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("products", products);
        result.addObject("result", error);
        return result;
    }
}