package com.example.pawsome.web;

import com.example.pawsome.entities.Dog;
import com.example.pawsome.entities.Payment;
import com.example.pawsome.entities.User;
import com.example.pawsome.repositories.DogRepository;
import com.example.pawsome.repositories.PaymentRepository;
import com.example.pawsome.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@AllArgsConstructor
@SessionAttributes({"a", "e"})
public class ControllerMain {

    @Autowired
    private UserRepository userRepository;
    private DogRepository dogRepository;
    private PaymentRepository paymentRepository;

    @ModelAttribute("loggedIn")
    public boolean loggedIn() {
        return false;
    }

    static int num = 0;

    @GetMapping(path = "/")
    public String displayMain(Model model) {

        return "home/homePage";
    }

    @GetMapping(path = "/registration")
    public String userRegister(Model model) {
        model.addAttribute("user", new User());
        return "home/registrationPage";
    }

    @PostMapping(path = "/register")
    public String save(Model model, User user, BindingResult bindingResult, ModelMap mm) {
        if (bindingResult.hasErrors()) {
            return "home/registrationPage";
        } else {
            userRepository.save(user);

            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:/";
        }
    }

    @GetMapping(path = "/login")
    public String displayLogin(Model model) {
        return "home/loginPage";
    }

    @GetMapping(path = "/contact")
    public String displayContact(Model model) {
        return "home/contactUS";
    }


    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model,
            HttpSession session
    ) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // Login successful
            session.setAttribute("loggedIn", true);
            if (user.getAccount().equalsIgnoreCase("owner")) {
                return "redirect:/owner";
            } else if (user.getAccount().equalsIgnoreCase("renter")) {
                return "redirect:/renter";
            }
        }
        model.addAttribute("error", "Invalid email or password");
        return "home/loginPage";
    }

    @GetMapping("/owner")
    public String ownerPage(Model model) {
        List<Dog> dogs = dogRepository.findAll();
        model.addAttribute("dogs", dogs);
        return "/owner/owner";
    }

    @GetMapping("/owner/addDog")
    public String addDogForm(Model model) {
        model.addAttribute("newDog", new Dog());
        return "/owner/addDog";
    }

    @PostMapping("/owner/addDog")
    public String addDog(@ModelAttribute("newDog") Dog newDog) {
        newDog.setAvailable(true);
        newDog.setPayed(false);
        dogRepository.save(newDog);
        return "redirect:/owner";
    }

    @GetMapping("/owner/{id}/edit")
    public String editDogForm(@PathVariable("id") Integer id, Model model) {
        Dog dog = dogRepository.findById(id).orElse(null);
        model.addAttribute("dog", dog);
        return "/owner/editDog";
    }

    @PostMapping("/owner/{id}/edit")
    public String editDog(@PathVariable("id") Integer id, @ModelAttribute Dog updateDog) {
        dogRepository.save(updateDog);
        return "redirect:/owner";
    }

    @GetMapping("/owner/{id}/delete")
    public String deleteDog(@PathVariable("id") Integer id) {
        dogRepository.deleteById(id);
        return "redirect:/owner";
    }

    @GetMapping("/renter")
    public String renterPage(Model model) {
        List<Dog> dogs = dogRepository.findAll();
        model.addAttribute("dogs", dogs);
        return "/renter/renter";
    }

    @GetMapping("/payment/{id}")
    public String displayPaymentForm(@PathVariable("id") Integer id, Model model) {
        Payment pay = paymentRepository.findById(id).orElse(null);
        model.addAttribute("pay", pay);
        model.addAttribute("payment", new Payment());
        Dog dog = dogRepository.findById(id).orElse(null);
        model.addAttribute("dog", dog);
        return "/renter/payment";
    }

    @PostMapping("/payment/{id}")
    public String makePayment(@PathVariable("id") Integer id, @ModelAttribute Payment payment, Model model) {
        Dog dog = dogRepository.findById(id).orElse(null);
        if (dog != null) {
            dog.setAvailable(false);
            dog.setPayed(true);
            dogRepository.save(dog);
        }
        Payment savedPayment = paymentRepository.save(payment);
        System.out.println("Payment details: " + savedPayment);

        model.addAttribute("payment", savedPayment);
        model.addAttribute("paymentSuccess", true);

        return "redirect:/renter";
    }
}
