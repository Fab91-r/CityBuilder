package it.dstech.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.dstech.connection.ConnessioneDb;
import it.dstech.object.Citta;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String main(@RequestParam(name = "name", required = false, defaultValue = "") String nome
			, @RequestParam(name = "district", required = false, defaultValue = "") String distretto,
			@RequestParam(name = "states", required = false) String stato, Model model) throws ClassNotFoundException, SQLException {
     if (stato != null)
     {
		String code = ConnessioneDb.getCode(stato);
		int pop = ConnessioneDb.getPopolazione(stato);
		Citta nuovaCitta = new Citta(nome, code, distretto, pop);
		ConnessioneDb.insertCity(nuovaCitta);
		String messaggio = "Città inserita correttamente!";
		model.addAttribute("messaggio", messaggio);
     }
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
	public String mainCitta(@RequestParam(name = "states", required = false, defaultValue = "") String states, Model model) throws ClassNotFoundException, SQLException {

		List<String> listaCitta = ConnessioneDb.getCitta(states);
		model.addAttribute("listaCitta", listaCitta);
		model.addAttribute("states", states);
		return "citta";
	}
}
