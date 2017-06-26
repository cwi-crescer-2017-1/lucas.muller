/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.aula5;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lucas.muller
 */
@WebServlet(name = "cliente", urlPatterns = "/cliente")
public class ClienteServlet extends HttpServlet {
    
    private ArrayList<String> nomes = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (final PrintWriter out = resp.getWriter();) {
            out.append("<!DOCTYPE html>");
            out.append("<html>");
            out.append("<head>");
            out.append("<title>Java - aula5</title>");
            out.append("<meta charset=\"UTF-8\">");
            out.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.append("</head>");
            out.append("<body>");
            out.append("<h1>Cliente</h1>");
            out.append("<form method=\"post\" action=\"/aula5/cliente\">");
            out.append("<input type=\"text\" name=\"nome\" required>");
            out.append("<button type=\"submit\">Enviar</button>");
            out.append("</form>");
            out.append("<table><tr><th>Nome</th></tr>");
            nomes.forEach(nome -> {
                out.append("<tr><th>").append(nome).append("</th></tr>");
            });
            out.append("</table>");
            out.append("</body>");
            out.append("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        if(nome != null)
            nomes.add(nome);
        resp.sendRedirect("/aula5/cliente");
    }
    
}
