package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Dao.DaoArbitros;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {
    private ArrayList listaPaises;
    private ArrayList listaOpciones;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        Arbitro arbitro=new Arbitro();
        DaoArbitros daoArbitros=new DaoArbitros();
        RequestDispatcher view;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");

        setListaOpciones(opciones);

        switch (action) {

            case "buscar":

                String searchText = request.getParameter("searchText");
                String opcionjsp = request.getParameter("tipo");

                if (opcionjsp.equals("nombre")){
                    ArrayList<Arbitro> listaBuscarArbitro =daoArbitros.busquedaNombre(searchText);
                    request.setAttribute("listarArbitros", listaBuscarArbitro);
                    request.setAttribute("listarFiltro", getListaOpciones());
                } else if (opcionjsp.equals("pais")) {
                    ArrayList<Arbitro> listaBuscarArbitro =daoArbitros.busquedaPais(searchText);
                    request.setAttribute("listarArbitros", listaBuscarArbitro);
                    request.setAttribute("listarFiltro", getListaOpciones());
                }

                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;

            case "guardar":

                arbitro.setNombre(request.getParameter("Nombres"));
                arbitro.setPais(request.getParameter("Pais"));

                int centinela=0;
                ArrayList<Arbitro> comparar=daoArbitros.listarArbitros();
                for (Arbitro arbitro1: comparar){
                    if (arbitro1.getNombre().toLowerCase().equals(request.getParameter("Nombres").toLowerCase())){
                        centinela=1;
                    }
                }

                if (centinela==1){
                    response.sendRedirect(request.getContextPath()+"/ArbitroServlet?action=crear");
                }else {
                    daoArbitros.crearArbitro(arbitro);
                    response.sendRedirect(request.getContextPath()+"/ArbitroServlet");
                }
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
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");

        setListaOpciones(opciones);
        setListaPaises(paises);

        switch (action) {
            case "lista":
                request.setAttribute("listarArbitros", daoArbitros.listarArbitros());
                request.setAttribute("listarFiltro", getListaOpciones());

                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;
            case "crear":
                request.setAttribute("listaPaises", getListaPaises());
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

    public ArrayList getListaPaises() {
        return listaPaises;
    }

    public void setListaPaises(ArrayList listaPaises) {
        this.listaPaises = listaPaises;
    }

    public ArrayList getListaOpciones() {
        return listaOpciones;
    }

    public void setListaOpciones(ArrayList listaOpciones) {
        this.listaOpciones = listaOpciones;
    }
}
