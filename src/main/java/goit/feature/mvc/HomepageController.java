package goit.feature.mvc;

import goit.feature.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/")
@RestController
public class HomepageController {
    private final AuthService authService;

    @GetMapping
    public ModelAndView get() {
        if (!authService.hasAuthority("ADMIN")) {
            return new ModelAndView("homepage");
        }
        return new ModelAndView("admin/homepage");
    }
}