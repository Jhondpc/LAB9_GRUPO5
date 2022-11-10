package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.OpcionesArbitro;
import com.example.lab9_base.Dao.DaoArbitros;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        Arbitro arbitro=new Arbitro();
        DaoArbitros daoArbitros=new DaoArbitros();
        RequestDispatcher view;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");

        switch (action) {

            case "buscar":

                /*String searchText = request.getParameter("searchText");

                dArrayList<Arbitro> lista = aoArbitros.buscarUsuarios(searchText);
                request.setAttribute("lista", lista);

                requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
                requestDispatcher.forward(request, response);*/
                break;

            case "guardar":

                arbitro.setNombre(request.getParameter("Nombres"));
                arbitro.setPais(request.getParameter("Pais"));

                daoArbitros.guardarArbitro(arbitro);

                response.sendRedirect(request.getContextPath()+"/ArbitroServlet");
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        DaoArbitros daoArbitros = new DaoArbitros();
        ArrayList<String> paises = new ArrayList<>();
        String idArbitro;
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");
        OpcionesArbitro opcionesArbitro = new OpcionesArbitro();
        opcionesArbitro.setListaPaises(paises);
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        opcionesArbitro.setListaOpciones(opciones);

        switch (action) {
            case "lista":
                request.setAttribute("listarArbitros", daoArbitros.listarArbitros());
                request.setAttribute("listarFiltro",opcionesArbitro.getListaOpciones());
                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaPaises", opcionesArbitro.getListaPaises());
                view = request.getRequestDispatcher("/arbitros/form.jsp");
                view.forward(request, response);
                break;
            case "borrar":
                idArbitro = request.getParameter("id");
                daoArbitros.borrarArbitro(idArbitro);

                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");
                break;
        }
    }
}
