package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;

import java.sql.*;
import java.util.ArrayList;

public class DaoArbitros extends DaoBase {
    public ArrayList<Arbitro> listarArbitros() {
        ArrayList<Arbitro> listarArbitros = new ArrayList<>();

        try(Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from arbitro")){

            while(rs.next()){
                Arbitro arbitro = new Arbitro();

                arbitro.setIdArbitro(rs.getInt(1));
                arbitro.setNombre(rs.getString(2));
                arbitro.setPais(rs.getString(3));

                listarArbitros.add(arbitro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listarArbitros;
    }

    public void crearArbitro(Arbitro arbitro) {
        String sql = "INSERT INTO arbitro (nombre, pais) VALUES (?,?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {


            pstmt.setString(1, arbitro.getNombre());
            pstmt.setString(2, arbitro.getPais());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> listaArbitros = new ArrayList<>();

        String sql = "SELECT * FROM arbitro WHERE lower(pais) like ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+pais+"%");

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Arbitro arbitro = new Arbitro();

                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));

                    listaArbitros.add(arbitro);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaArbitros;
    }


    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> listaArbitros = new ArrayList<>();

        String sql = "SELECT * FROM arbitro WHERE lower(nombre) like ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+nombre+"%");

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Arbitro arbitro = new Arbitro();

                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));

                    listaArbitros.add(arbitro);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaArbitros;
    }

    public Arbitro buscarArbitro(String nombre) {
        Arbitro arbitro = new Arbitro();
        /*
        Inserte su código aquí
        */
        return arbitro;
    }

    public void borrarArbitro(String id) {

        String sql = "DELETE from arbitro WHERE idArbitro = ?";

        try(Connection connection = getConnection();
            PreparedStatement pstmt=connection.prepareStatement(sql))
        {

            pstmt.setString(1,id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public ArrayList<Arbitro> buscaArbitro(String buscador,String elementoTabla){
        ArrayList<Arbitro> listaArbitros = new ArrayList<>();

        String sql = "SELECT * FROM arbitro WHERE lower("+elementoTabla+") like ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+buscador+"%");

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Arbitro arbitro = new Arbitro();

                    arbitro.setIdArbitro(rs.getInt(1));
                    arbitro.setNombre(rs.getString(2));
                    arbitro.setPais(rs.getString(3));

                    listaArbitros.add(arbitro);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaArbitros;
    }


}
