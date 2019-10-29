import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.curso.mavenweb.Cliente;
import br.com.curso.mavenweb.ClienteService;

@WebServlet(urlPatterns = { "/cliente.do", "/clienteServlet", "/clienteController" })
public class ClienteServlet extends HttpServlet {

	ClienteService clienteService = new ClienteService();

	public ClienteServlet() {
		// TODO Auto-generated constructor stub
		System.out.println("Criando o Servlet");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		clienteService = new ClienteService();
		super.init(config);

		System.out.println("Inicializando o Servlet");
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, res);
		System.out.println("Chamou Service");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Cliente cli = new Cliente();
		
		int indice =-1;
		cli.setEmail("");
		String i = req.getParameter("i");
		if(i!= null && i!="") {
			indice=Integer.parseInt(i);
		}	
		String acao = req.getParameter("acao");
		if (i != null && i != "" && acao != null && acao != "") {
			if (acao.equals("exc")) {
				clienteService.excluir(indice);
			    }else if(acao.equals("edit")) {
				
				 
				cli = clienteService.buscarPorIndice(indice);
					
			    }
		     }
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		req.setAttribute("cli", cli);
		req.setAttribute("icli", indice);
		req.setAttribute("lista", clienteService.getTodosCliente());
		
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String i = req.getParameter("i");
		int indice = -1; 
		if (i !=null  && i!="") {
			 indice = Integer.parseInt(i);
		 }
		
		Cliente cli = new Cliente();
		cli.setEmail(email);
		
		clienteService.salvar(indice, cli);
		
		cli = new Cliente();
		cli.setEmail("");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
		req.setAttribute("msg", "Cadastrado com sucesso!!!");
		req.setAttribute("cli", cli);
		req.setAttribute("icli", -1);	
		req.setAttribute("lista", clienteService.getTodosCliente());
		
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Finalizando o Servlet");
		super.destroy();

	}

}
