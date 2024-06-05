package org.example.model.dao;


import org.example.model.connection.ConnectionMariaDB;
import org.example.model.entity.Company;
import org.example.model.entity.General;
import org.example.model.entity.Unit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitDAO implements DAO<Unit,String> {
    private static final String FINDALL ="SELECT c.id,c.name,c.id_company FROM unit AS c";
    private static final String FINDALLNONEDITABLE ="SELECT c.id,c.name,c.id_company FROM unitnoneditable AS c";
    private static final String FINDBYID ="SELECT c.id,c.name,c.id_company FROM unit AS c WHERE c.id=?";
    private static final String INSERT ="INSERT INTO unit (name,id_company) VALUES (?,?)";
    private static final String UPDATE ="UPDATE unit SET name=? WHERE id=?";
    private static final String DELETE ="DELETE FROM unit WHERE id=?";
    private static final String FINDBYAUTHOR ="SELECT c.id,c.name,c.id_company FROM unit AS b WHERE c.id_company=?";


    private Connection conn;
    public UnitDAO(){
        conn = ConnectionMariaDB.getConnection();
    }

    @Override
    public Unit save(Unit entity) {
        Unit result=entity;
        if(entity!=null){
            String id = entity.getId();
            if(id !=null){
                Unit isInDataBase = findById(id);
                if(isInDataBase==null){
                    //INSERT
                    try(PreparedStatement pst = conn.prepareStatement(INSERT)) {
                        ResultSet res = pst.getGeneratedKeys();
                        if(res.next()){
                            entity.setId(res.getString(1));
                        }
                        pst.setString(1,entity.getName());
                        pst.setString(2,entity.getCompany().getId());
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
        }
        return result;
    }

    @Override
    public void delete(Unit entity) {
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
    public Unit findById(String key) {
        Unit result = null;
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setString(1,key);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    Unit b = new Unit();
                    b.setId(res.getString("id"));
                    //Eager
                    b.setCompany(CompanyDAO.build().findById(res.getString("id_company")));
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
    public List<Unit> findAll() {
       List<Unit> result = new ArrayList<>();
        try(PreparedStatement pst = conn.prepareStatement(FINDALL)){
            try(ResultSet res = pst.executeQuery()){
                while(res.next()){
                    Unit b = new Unit();
                    b.setId(res.getString("id"));
                   //Eager
                    b.setCompany(CompanyDAO.build().findById(res.getString("id_company")));

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
    public List<Unit> findAllNonEditable() {
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
                    b.setAuthor(a);
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

    public static UnitDAO build(){
        return new UnitDAO();
    }
}
