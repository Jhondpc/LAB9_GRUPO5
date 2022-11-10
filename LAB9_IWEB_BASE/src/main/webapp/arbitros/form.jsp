<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab9_base.Controller.ArbitroServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<ArbitroServlet> listaPaises = (ArrayList<ArbitroServlet>) request.getAttribute("listaPaises");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
        <title>LAB 9</title>
    </head>
    <body>
        <div class='container'>

            <div class="row mb-4">
                <div class="col"></div>
                <div class="col-md-6">
                    <h1 class='mb-3'>Crear un Árbitro</h1>
                    <form method="post" action="<%=request.getContextPath()%>/ArbitroServlet?action=guardar">
                        <div >
                            <label for="Nombres" >Nombre</label>
                            <input required type="text" class="form-control" name="Nombres" id="Nombres" placeholder="Nombres">
                        </div>
                        <div >
                            <label for="Pais">País</label>
                            <select required name="Pais" id="Pais" placeholder="Pais" class="form-control">
                                <option value="">Seleccione una opción</option>
                                <%for (int i=0;i<=listaPaises.size()-1;i++){%>
                                <option value="<%=listaPaises.get(i)%>"><%=listaPaises.get(i)%></option>
                                <%}%>
                            </select>
                        </div>
                        <br>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                        <a href="<%= request.getContextPath()%>/ArbitroServlet" class="btn btn-danger">Cancelar</a>
                    </form>
                </div>
                <div class="col"></div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>

    </body>
</html>
