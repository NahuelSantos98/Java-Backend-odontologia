package com.backend.OdontologiaBackend.dao.impl;


import com.backend.OdontologiaBackend.dao.IDao;
import com.backend.OdontologiaBackend.dbconnection.H2Connection;
import com.backend.OdontologiaBackend.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class OdontologoDaoH2 implements IDao<Odontologo> {

    private final Logger logger = LoggerFactory.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        String insert = "INSERT INTO ODONTOLOGOS(NUMEROMATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
        Connection connection = null;
        Odontologo odontologoObtenido = null;

        try {

            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getNumeroMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());
            preparedStatement.execute();

            connection.commit();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                odontologoObtenido = new Odontologo(resultSet.getInt("ID"), odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());
            }



            logger.info("El odontologoObtenido: " + odontologoObtenido);

            connection.setAutoCommit(true);

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    logger.info("Problemas en el rollback");
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    ex.printStackTrace();
                } finally {
                    try {
                        connection.close();
                    } catch (Exception exception) {
                        logger.error("No se pudo cerrar la conexion: " + exception.getMessage());
                    }
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                logger.error("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return odontologoObtenido;
    }

    @Override
    public List<Odontologo> listarTodos() {

        Connection connection = null;
        List<Odontologo> odontologosLista = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement selectAll = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet resultSet = selectAll.executeQuery();

            while (resultSet.next()){
                Odontologo odontologoParaListar = crearObjetoOdontologo(resultSet);
                odontologosLista.add(odontologoParaListar);
                logger.info("El odontologo listado fue: "+ odontologoParaListar);
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                logger.error("Error al cerrar la conexión: " + ex.getMessage());
            }
        }

        return odontologosLista;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        Connection connection = null;
        Odontologo odontologo = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                odontologo = crearObjetoOdontologo(rs);
            }
            logger.info("El Odontologo con el ID " + id + " es: " + odontologo);

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                logger.error("Ha ocurrido un error al intentar cerrar la Base de Datos. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologo;
    }


    private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {
        return new Odontologo(resultSet.getInt("ID"), resultSet.getInt("NUMEROMATRICULA"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO"));
    }

}

