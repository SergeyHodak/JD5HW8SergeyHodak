package goit.feature.mvc;

import goit.feature.auth.AuthService;
import goit.feature.email.EmailFormatCheck;
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
        String error = "";
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
        String error = "";
        List<UserDTO> users = new ArrayList<>();
        try {
            Set<Role> parser = parser(roles);
            System.out.println("parser = " + parser);
            if (!EmailFormatCheck.isTheEmailCorrect(email)) {
                error = "Invalid email!";
            } else if (parser.size() == 0) {
                error = "The role cannot be absent!";
            } else {
                String encodePassword = passwordEncoder.encode(password);
                User user = new User(email, encodePassword, firstName, lastName);
                user.setRoles(parser);
                userDAO.save(user);
                error = "true";
            }
            for (User unit : userDAO.findAll()) {
                users.add(UserDTO.fromUser(unit));
            }
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
        String error = "";
        List<UserDTO> users = new ArrayList<>();
        try {
            Set<Role> parser = parser(roles);
            if (!EmailFormatCheck.isTheEmailCorrect(email)) {
                error = "Invalid email!";
            } else if (parser.size() == 0) {
                error = "The role cannot be absent!";
            } else {
                String encodePassword = passwordEncoder.encode(password);
                User user = new User(email, encodePassword, firstName, lastName);
                user.setId(id);
                user.setRoles(parser);
                userDAO.save(user);
                error = "true";
            }
            for (User unit : userDAO.findAll()) {
                users.add(UserDTO.fromUser(unit));
            }
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
            Role unit = roleDAO.findByName(role);
            if (unit != null) {
                result.add(unit);
            }
        }
        return result;
    }
}