package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.*;
import java.util.ArrayList;

public class DaoPartidos extends DaoBase {
    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select *\n" +
                     "\tfrom partido p \n" +
                     "\tleft join seleccion sl on (p.seleccionLocal = sl.idSeleccion)\n" +
                     "    left join seleccion sv on (p.seleccionVisitante = sv.idSeleccion)\n" +
                     "\tleft join arbitro a on (p.arbitro = a.idArbitro)\n" +
                     "    left join estadio e on (sl.estadio_idEstadio = e.idEstadio)")) {

            while (rs.next()) {
                Partido partido = new Partido();
                partido.setIdPartido(rs.getInt(1));
                partido.setNumeroJornada(rs.getInt(6));
                partido.setFecha(rs.getString(5));

                Estadio estadio = new Estadio();
                estadio.setIdEstadio(rs.getInt(10));
                estadio.setNombre(rs.getString(19));
                estadio.setProvincia(rs.getString(20));

                Seleccion seleccionLocal = new Seleccion();
                seleccionLocal.setIdSeleccion(rs.getInt(7));
                seleccionLocal.setNombre(rs.getString(8));
                seleccionLocal.setTecnico(rs.getString(9));
                seleccionLocal.setEstadio(estadio);
                partido.setSeleccionLocal(seleccionLocal);

                Seleccion seleccionVisitante = new Seleccion();
                seleccionVisitante.setIdSeleccion(rs.getInt(11));
                seleccionVisitante.setNombre(rs.getString(12));
                seleccionVisitante.setTecnico(rs.getString(13));
                partido.setSeleccionVisitante(seleccionVisitante);

                Arbitro arbitro = new Arbitro();
                arbitro.setIdArbitro(rs.getInt(15));
                arbitro.setNombre(rs.getString(16));
                arbitro.setPais(rs.getString(17));
                partido.setArbitro(arbitro);

                partidos.add(partido);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return partidos;
    }

    public void crearPartido(Partido partido) {

        String sql = "INSERT INTO `lab9`.`partido` (`seleccionLocal`, `seleccionVisitante`, `arbitro`, `fecha`, `numeroJornada`) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, partido.getSeleccionLocal().getIdSeleccion());
            pstmt.setInt(2, partido.getSeleccionVisitante().getIdSeleccion());
            pstmt.setInt(3, partido.getArbitro().getIdArbitro());
            pstmt.setString(4, partido.getFecha());
            pstmt.setInt(5, partido.getNumeroJornada());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
