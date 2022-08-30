package goit.feature.mvc;

import goit.feature.manufacturer.Manufacturer;
import goit.feature.manufacturer.ManufacturerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/manufacturer")
@RestController
public class ManufacturerController {
    private final ManufacturerDAO manufacturerDAO;

    @GetMapping
    public ModelAndView list() {
        ModelAndView result = new ModelAndView("admin/manufacturer");
        String error = null;
        List<Manufacturer> manufacturers = null;
        try {
            manufacturers = manufacturerDAO.findAll();
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("manufacturers", manufacturers);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/create")
    public ModelAndView create(@RequestParam("name") String name) {
        ModelAndView result = new ModelAndView("admin/manufacturer");
        String error;
        List<Manufacturer> manufacturers = null;
        try {
            Manufacturer manufacturer = new Manufacturer(name);
            manufacturerDAO.create(manufacturer);
            manufacturers = manufacturerDAO.findAll();
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("manufacturers", manufacturers);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") UUID id,
                               @RequestParam("name") String name) {
        ModelAndView result = new ModelAndView("admin/manufacturer");
        String error;
        List<Manufacturer> manufacturers = null;
        try {
            Manufacturer manufacturer = new Manufacturer(name);
            manufacturer.setId(id);
            manufacturerDAO.update(manufacturer);
            manufacturers = manufacturerDAO.findAll();
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("manufacturers", manufacturers);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("admin/manufacturer");
        String error;
        List<Manufacturer> manufacturers = null;
        try {
            manufacturerDAO.deleteById(id);
            manufacturers = manufacturerDAO.findAll();
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("manufacturers", manufacturers);
        result.addObject("result", error);
        return result;
    }
}