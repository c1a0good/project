package se.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.DAO.UsersRepository;
import se.pckg.User;


public class UserController {
//    private final UsersRepository userDAO;
//    private User user;
//
//
//    @Autowired
//    public UserController(UsersRepository userDAO) {
//        this.userDAO = userDAO;
//    }
//    @GetMapping()
//    public String index(Model model) {
//        model.addAttribute("user", new User());
//        return "mainMenu";
//    }
//
//    @PostMapping()
//    public String indexx(@ModelAttribute("user") User user) {
//        if(userDAO.getByLogPass(user.getLogin(),user.getPassword())!=null)
//        {
//            if(userDAO.getByLogPass(user.getLogin(),user.getPassword()).getRole()==0)
//            {
//                return "people/userMenu";
//            }
//            if(userDAO.getByLogPass(user.getLogin(),user.getPassword()).getRole()==1)
//            {
//                return "people/Menu";
//            }
//        }
//
//        //userDAO.create(person);
//        return "redirect:/menu";
//    }
//
//
//
//
//    @PostMapping("/user")
//    public String create(@ModelAttribute("user") User user) {
//        userDAO.create(user);
//
//
//        return "redirect:/";
//    }
//
//
//    @GetMapping("/new")
//    public String newPerson(@ModelAttribute("user") User user)
//    {
//        return "people/UserRegistration";
//    }
}
