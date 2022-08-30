package goit.feature.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@RestController
public class HomepageController {
    @GetMapping
    public ModelAndView get() {
        return new ModelAndView("admin/homepage");
    }
}