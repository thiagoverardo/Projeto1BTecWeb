package mvc.controller;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mvc.model.Jogos;
import mvc.model.JogosDAO;

@Controller
public class JogosController {
	
	@RequestMapping("ordem")
		public String ordem(Jogos jogo, @ModelAttribute("variable") String ordem, HttpSession session) {
		JogosDAO dao;
		try {
			dao = new JogosDAO();
	
			if (ordem.contentEquals("User")) {
				ordem = "user";
			} else if (ordem.contentEquals("Data")) {
				ordem = "date";
			} else if (ordem.contentEquals("Jogo")){
				ordem = "jogo";
			} else if (ordem.contentEquals("Genero")){
				ordem = "genero";
			} else if (ordem.contentEquals("Preco")){
				ordem = "preco";
			} else {
				ordem = "";
			}
			
			dao.getOrdem(ordem);
			session.setAttribute("user", jogo.getUser());
			session.setAttribute("ordem", ordem);
			
			return "teste_2";
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "teste_2";
		
	}
	@RequestMapping("adicionaJogo")
	public String adiciona(Jogos jogo,@ModelAttribute("compra") String date, HttpSession session) throws ParseException {
		JogosDAO dao;
			try {
				dao = new JogosDAO();
				
				Date data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				Calendar dataCompra = Calendar.getInstance();
				dataCompra.setTime(data);
				jogo.setData(dataCompra);
				
				dao.adicionaJogo(jogo);
				session.setAttribute("user", jogo.getUser());
				session.setAttribute("ordem", "");
				
				dao.close();
				
				return "teste_2";
				
			
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "teste_2";
		
		}
	@RequestMapping("removeJogo")
	public String remove(Jogos jogo,  @ModelAttribute("id") String id, HttpSession session) {
		JogosDAO dao;
		try {
			dao = new JogosDAO();
	
			if (id != null) {
				dao.removeJogo(Integer.valueOf(id));
				session.setAttribute("user", jogo.getUser());
				session.setAttribute("ordem", "");
				dao.close();
				return "teste_2";
			} else {
				dao.removeJogo(0);
				session.setAttribute("user", jogo.getUser());
				session.setAttribute("ordem", "");
				return "teste_2";
			
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "teste_2";
	}
	@RequestMapping("edita_get")
	public String edita(Jogos jogo, @ModelAttribute("id") String id, @ModelAttribute("user") String user, @ModelAttribute("user1") String user1, 
		@ModelAttribute("jogo") String jogoatual, @ModelAttribute("genero") String genero, @ModelAttribute("preco") String preco,
		@ModelAttribute("compra") String date, HttpSession session) throws ParseException {
			
			try {
				JogosDAO dao = new JogosDAO();
				
				Jogos jogos = new Jogos();
				
				Date data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				Calendar dataCompra = Calendar.getInstance();
				dataCompra.setTime(data);
				jogo.setData(dataCompra);
				

				session.setAttribute("user", user);
				session.setAttribute("user1", user1);
				session.setAttribute("jogo", jogoatual);
				session.setAttribute("genero", genero);
				session.setAttribute("preco", preco);
				session.setAttribute("data", dataCompra);
				session.setAttribute("ordem", "");
				session.setAttribute("id", id);
				
				jogos.setId(Integer.valueOf(id));
				
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			return "editar";
		}
	@RequestMapping("edita_post")
	public String enviar(Jogos jogo,@ModelAttribute("compra") String date, HttpSession session) throws ParseException {
		JogosDAO dao;
			try {
				dao = new JogosDAO();
				
				Date data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				Calendar dataCompra = Calendar.getInstance();
				dataCompra.setTime(data);
				jogo.setData(dataCompra);
				
				dao.atualizaJogo(jogo);
				session.setAttribute("user", jogo.getUser());
				session.setAttribute("ordem", "");
				
				dao.close();
				
				return "teste_2";
				
			
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "teste_2";
		
		}
}