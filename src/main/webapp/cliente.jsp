<!DOCTYPE html>
<%@page import="org.eclipse.jdt.internal.compiler.ast.IfStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="br.com.curso.mavenweb.Cliente"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
<script type="text/javascript">
	function confirma(pi) {
		if (window.confirm("Confirma a exclusao (S/N) ?")) {
			location.href = "cliente.do?acao=exc&i=" + pi;
		}
	}
</script>
</head>
<body>
	<%
		Object msg = request.getAttribute("msg");
		if (msg != null) {
			String msgStr = (String) msg;
			out.print(msg);
		}

		Cliente cli = (Cliente) request.getAttribute("cli");

		Object icli = request.getAttribute("icli");
	%>

	<form action="cliente.do" method="post">
		<input type="hidden" name="i" value="<%=icli%>"> Email: <input
			type="text" value="<%=cli.getEmail()%>" name="email" /> <input
			type="submit" value="SALVAR" />
	</form>

	<table border="1">
		<tr> 
			<th>email</th>
			<th>Acao</th>
		</tr>
		<%
			List<Cliente> lista = (List<Cliente>) request.getAttribute("lista");
			int i = 0;
			for (Cliente c : lista) {
		%>
		
		<tr>
			<td>
				<%=c.getEmail()%>
			</td>
			
			<td>
				<a href="javascript:confirma(<%=i%>)">Delet</a> |
				<a href="cliente.do?i=<%=i%>&acao=edit">Edit</a>
			</td>
			
		</tr>
		<%
			i++;
			}
		%>


	</table>
</body>
</html>