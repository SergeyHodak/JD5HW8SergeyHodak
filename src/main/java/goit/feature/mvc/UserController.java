package goit.feature.mvc;

import goit.feature.auth.AuthService;
import goit.feature.role.Role;
import goit.feature.role.RoleDAO;
import goit.feature.user.User;
import goit.feature.user.UserDAO;
import goit.feature.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView list() {
        if (!authService.hasAuthority("ADMIN")) {
            return new ModelAndView("homepage");
        }
        ModelAndView result = new ModelAndView("admin/user");
        String error = null;
        List<UserDTO> users = new ArrayList<>();
        try {
            for (User user : userDAO.findAll()) {
                users.add(UserDTO.fromUser(user));
            }
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("users", users);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/create")
    public ModelAndView create(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("roles") String roles) {
        if (!authService.hasAuthority("ADMIN")) {
            return new ModelAndView("homepage");
        }
        ModelAndView result = new ModelAndView("admin/user");
        String error;
        List<UserDTO> users = new ArrayList<>();
        try {
            String encodePassword = passwordEncoder.encode(password);
            User user = new User(email, encodePassword, firstName, lastName);
            user.setRoles(parser(roles));
            userDAO.create(user);
            for (User unit : userDAO.findAll()) {
                users.add(UserDTO.fromUser(unit));
            }
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("users", users);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") UUID id,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("roles") String roles) {
        if (!authService.hasAuthority("ADMIN")) {
            return new ModelAndView("homepage");
        }
        ModelAndView result = new ModelAndView("admin/user");
        String error;
        List<UserDTO> users = new ArrayList<>();
        try {
            String encodePassword = passwordEncoder.encode(password);
            User user = new User(email, encodePassword, firstName, lastName);
            user.setId(id);
            user.setRoles(parser(roles));
            userDAO.update(user);
            for (User unit : userDAO.findAll()) {
                users.add(UserDTO.fromUser(unit));
            }
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("users", users);
        result.addObject("result", error);
        return result;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") UUID id) {
        if (!authService.hasAuthority("ADMIN")) {
            return new ModelAndView("homepage");
        }
        ModelAndView result = new ModelAndView("admin/user");
        String error;
        List<UserDTO> users = new ArrayList<>();
        try {
            userDAO.deleteById(id);
            for (User unit : userDAO.findAll()) {
                users.add(UserDTO.fromUser(unit));
            }
            error = "true";
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        result.addObject("users", users);
        result.addObject("result", error);
        return result;
    }

    private Set<Role> parser(String roles) {
        String[] split = roles
                .replace("[", "")
                .replace("]", "")
                .split(",");
        Set<Role> result = new HashSet<>();
        for (String role : split) {
            role = role.strip();
            result.add(roleDAO.findByName(role));
        }
        return result;
    }
}