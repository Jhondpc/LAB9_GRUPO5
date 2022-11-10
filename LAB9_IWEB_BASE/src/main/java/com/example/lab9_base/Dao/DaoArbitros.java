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

    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí
        */
        return arbitros;
    }

    public Arbitro buscarArbitro(int id) {
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
/*
   public void guardarArbitro(Arbitro arbitro){

        String sql = "INSERT INTO arbitro (nombre, pais) VALUES (?,?)";

        try(Connection connection = DriverManager.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql)) {


            pstmt.setNull(9, Types.VARCHAR);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
*/
}
