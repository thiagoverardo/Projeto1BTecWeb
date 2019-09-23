package mvc.controller;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.Cadastro;
import mvc.model.DAO;

@Controller
public class LoginController {
	
	@RequestMapping("/")
	public String execute() {
		System.out.println("Não tem nada");
		return "info";
	}
	@RequestMapping("login")
	public String form() {
		return "login";
		}
	@RequestMapping("fazerLogin")
	public String login(Cadastro cadastro, HttpSession session) {
		try {
			DAO dao = new DAO();

			if (dao.verifica(cadastro)) {
				session.setAttribute("user", cadastro.getUser());
				session.setAttribute("ordem", "");
				return "teste_2";
			}
			dao.close();
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "falha";
		
	}
	@RequestMapping("cadastro") 
	public String cadastro() {
		return "cadastro";
	}
	
	@RequestMapping("signin")
	public String signin(Cadastro cadastro, @ModelAttribute("password") String password, @ModelAttribute("password_check") String password_check, HttpSession session) {
		DAO dao;
			try {
				dao = new DAO();
				if (password.contentEquals(password_check)) {
					dao.adiciona(cadastro);
					session.setAttribute("user", cadastro.getUser());
					session.setAttribute("ordem", "");
					return "teste_2";
				}
				dao.close();
		
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "falha_c";
		
		}
}