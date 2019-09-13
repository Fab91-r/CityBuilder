package it.dstech.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.dstech.connection.ConnessioneDb;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String main(Model model) throws ClassNotFoundException, SQLException {

		List<String> listaCont = ConnessioneDb.getContinenti();
		model.addAttribute("listaCont", listaCont);
		return "welcome";
	}
	
	@GetMapping("/stati")
	public String mainStati(@RequestParam(name = "cont", required = false, defaultValue = "") String cont, Model model) throws ClassNotFoundException, SQLException {

		List<String> listaStati = ConnessioneDb.getStati(cont);
		model.addAttribute("listaStati", listaStati);
		return "stati";
	}
	
	@GetMapping("/citta")
	public String mainCitta(Model model) throws ClassNotFoundException, SQLException {

		List<String> listaCitta = ConnessioneDb.getCitta();
		model.addAttribute("listaCitta", listaCitta);
		return "citta";
	}
}
