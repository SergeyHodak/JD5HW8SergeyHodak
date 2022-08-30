package goit.feature.mvc;

import goit.feature.manufacturer.Manufacturer;
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
        ModelAndView result = new ModelAndView("admin/product");
        String error = null;
        List<ProductDTO> products = new ArrayList<>();
        List<String> manufacturers = new ArrayList<>();
        try {
            for (Product product : productDAO.findAll()) {
                products.add(ProductDTO.fromProduct(product));
            }
            for (Manufacturer manufacturer : manufacturerDAO.findAll()) {
                manufacturers.add(manufacturer.getName());
            }
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("products", products);
        result.addObject("manufacturers", manufacturers);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/create")
    public ModelAndView create(@RequestParam("name") String name,
                               @RequestParam("price") String price,
                               @RequestParam("manufacturer") String manufacturer) {
        ModelAndView result = new ModelAndView("admin/product");
        String error;
        List<ProductDTO> products = new ArrayList<>();
        List<String> manufacturers = new ArrayList<>();
        try {
            Product product = new Product(name, new BigDecimal(price));
            product.setManufacturer(manufacturerDAO.findByName(manufacturer));
            productDAO.create(product);
            for (Product unit : productDAO.findAll()) {
                products.add(ProductDTO.fromProduct(unit));
            }
            for (Manufacturer unit : manufacturerDAO.findAll()) {
                manufacturers.add(unit.getName());
            }
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("products", products);
        result.addObject("manufacturers", manufacturers);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") UUID id,
                               @RequestParam("name") String name,
                               @RequestParam("price") String price,
                               @RequestParam("manufacturer") String manufacturer) {
        ModelAndView result = new ModelAndView("admin/product");
        String error;
        List<ProductDTO> products = new ArrayList<>();
        List<String> manufacturers = new ArrayList<>();
        try {
            Product product = new Product(name, new BigDecimal(price));
            product.setId(id);
            product.setManufacturer(manufacturerDAO.findByName(manufacturer));
            productDAO.update(product);
            for (Product unit : productDAO.findAll()) {
                products.add(ProductDTO.fromProduct(unit));
            }
            for (Manufacturer unit : manufacturerDAO.findAll()) {
                manufacturers.add(unit.getName());
            }
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("products", products);
        result.addObject("manufacturers", manufacturers);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("admin/product");
        String error;
        List<ProductDTO> products = new ArrayList<>();
        List<String> manufacturers = new ArrayList<>();
        try {
            System.out.println("id = " + id);
            productDAO.deleteById(id);
            for (Product product : productDAO.findAll()) {
                products.add(ProductDTO.fromProduct(product));
                System.out.println("product = " + product);
            }
            for (Manufacturer manufacturer : manufacturerDAO.findAll()) {
                manufacturers.add(manufacturer.getName());
            }
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("products", products);
        result.addObject("manufacturers", manufacturers);
        result.addObject("result", error);
        return result;
    }
}