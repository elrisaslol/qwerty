package org.example.model.dao;



import org.example.model.connection.ConnectionMariaDB;

import org.example.model.entity.General;
import org.example.model.entity.Company;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO implements DAO<Company,String> {
    private static final String FINDALL ="SELECT b.id,b.name,b.id_general FROM company AS b";
    private static final String FINDALLNONEDITABLE ="SELECT b.id,b.name,b.id_general FROM companynoneditable AS b";
    private static final String FINDBYID ="SELECT b.id,b.name,b.id_general FROM company AS b WHERE b.id=?";
    private static final String INSERT ="INSERT INTO company (name,id_general) VALUES (?,?)";
    private static final String UPDATE ="UPDATE company SET name=? WHERE id=?";
    private static final String DELETE ="DELETE FROM company WHERE id=?";
    private static final String FINDBYAUTHOR ="SELECT b.id,b.name,b.id_general FROM company AS b WHERE b.id_general=?";


    private Connection conn;
    public CompanyDAO(){
        conn = ConnectionMariaDB.getConnection();
    }

    @Override
    public Company save(Company entity) {
        Company result=entity;
        if(entity!=null){
            String id = entity.getId();
                Company isInDataBase = findById(id);
                if(isInDataBase==null){
                    //INSERT
                    try(PreparedStatement pst = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                        ResultSet res = pst.getGeneratedKeys();
                        if(res.next()){
                            entity.setId(res.getString(1));
                        }
                        pst.setString(1,entity.getName());
                        pst.setString(2,entity.getGeneral().getId());
                        pst.executeUpdate();
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }else{
                    //UPDATE
                    try(PreparedStatement pst = conn.prepareStatement(UPDATE)) {
                        pst.setString(1,entity.getName());
                        pst.setString(2,entity.getId());
                        pst.executeUpdate();
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }

        }
        return result;
    }

    @Override
    public void delete(Company entity) {
        if(entity!=null) {
            try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
                pst.setString(1, entity.getId());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                entity = null;
            }
        }
    }

    @Override
    public Company findById(String key) {
        Company result = null;
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setString(1,key);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    Company b = new Company();
                    b.setId(res.getString("id"));
                    //Eager
                    b.setGeneral(GeneralDAO.build().findById(res.getString("id_general")));
                    b.setName(res.getString("name"));
                    result=b;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Company> findAll() {
       List<Company> result = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement(FINDALL)){
            try(ResultSet res = pst.executeQuery()){
                while(res.next()){
                    Company b = new Company();
                    b.setId(res.getString("id"));
                   //Eager
                    b.setGeneral(GeneralDAO.build().findById(res.getString("id_general")));

                    b.setName(res.getString("name"));
                    result.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public List<Company> findAllNonEditable() {
        return List.of();
    }

    public List<Company> findByAuthor(General a) {
        List<Company> result = new ArrayList<>();
        if(a==null || a.getId()==null) return result;
        try(PreparedStatement pst = conn.prepareStatement(FINDBYAUTHOR)){
            pst.setString(1,a.getId());
            try(ResultSet res = pst.executeQuery()){
                while(res.next()){
                    Company b = new Company();
                    b.setId(res.getString("id"));
                    b.setGeneral(a);
                    b.setName(res.getString("name"));
                    result.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public void close() throws IOException {

    }

    public static CompanyDAO build(){
        return new CompanyDAO();
    }
}
