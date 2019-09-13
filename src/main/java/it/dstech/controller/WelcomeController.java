package it.dstech.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.dstech.connection.ConnessioneDb;

@Controller
public class WelcomeController {

	
	   @GetMapping("/")
   public String main(Model model) throws ClassNotFoundException, SQLException {      

    List <String> listaCont = ConnessioneDb.getContinenti();

    model.addAttribute("listaCont", listaCont);
     return "welcome"; 
 }
}
