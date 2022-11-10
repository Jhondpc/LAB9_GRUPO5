package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Dao.DaoArbitros;
import com.example.lab9_base.Dao.DaoPartidos;
import com.example.lab9_base.Dao.DaoSelecciones;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;
        Partido partido;
        DaoPartidos daoPartidos = new DaoPartidos();

        switch (action) {

            case "guardar":
                partido = new Partido();
                partido.setNumeroJornada(Integer.parseInt(request.getParameter("jornada")));
                partido.setFecha(request.getParameter("fecha"));
                Seleccion seleccionLocal = new Seleccion();
                seleccionLocal.setIdSeleccion(Integer.parseInt(request.getParameter("local")));
                partido.setSeleccionLocal(seleccionLocal);
                Seleccion seleccionVisitante = new Seleccion();
                seleccionVisitante.setIdSeleccion(Integer.parseInt(request.getParameter("visitante")));
                partido.setSeleccionVisitante(seleccionVisitante);
                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(Integer.parseInt(request.getParameter("arbitro")));
                partido.setArbitro(arbitro);
                daoPartidos.crearPartido(partido);
                response.sendRedirect("PartidoServlet");
                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        DaoPartidos daoPartidos = new DaoPartidos();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoArbitros daoArbitros = new DaoArbitros();
        switch (action) {
            case "lista":
                request.setAttribute("listaPartidos",daoPartidos.listaDePartidos());
                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaSelecciones", daoSelecciones.listarSelecciones());
                request.setAttribute("listaArbitros", daoArbitros.listarArbitros());
                view = request.getRequestDispatcher("partidos/form.jsp");
                view.forward(request, response);
                break;

        }

    }
}
