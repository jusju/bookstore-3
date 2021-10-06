package com.example.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import com.example.bookstore.domain.Signup;
import com.example.bookstore.domain.User;
import com.example.bookstore.domain.UserRepository;

@Controller
public class UserController {
	@Autowired
    private UserRepository repository; 
	
    @RequestMapping(value = "rekisteroidy")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new Signup());
        return "rekisteroidy";
    }	
    
    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") Signup signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) { // ei validi
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // onko salasanaa	
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
		    	if (repository.findByUsername(signupForm.getUsername()) == null) { // onko käyttäjää
		    		repository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "rekisteroidy";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "rekisteroidy";
    		}
    	}
    	else {
    		return "rekisteroidy";
    	}
    	return "redirect:/kirjaudu";    	
    }    
    
}