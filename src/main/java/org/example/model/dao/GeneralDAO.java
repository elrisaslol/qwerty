package org.example.model.dao;



import org.example.model.connection.ConnectionMariaDB;
import org.example.model.entity.General;
import org.example.model.entity.Company;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneralDAO implements DAO<General,String> {
    private final static String INSERT="INSERT INTO general (name) VALUES (?)";
    private final static String UPDATE="UPDATE general SET name=? WHERE id=?";
    private final static String FINDALL="SELECT a.id,a.name FROM general AS a";
    private final static String FINDALLNONEDITABLE="SELECT a.id,a.name FROM generalnoeditable AS a";
    private final static String FINDBYDNI="SELECT a.id,a.name FROM general AS a WHERE a.id=?";
    private final static String DELETE="DELETE FROM general AS a WHERE a.id=?";

    @Override
    public General save(General entity) {
        General result = entity;
        if(entity==null) return result;
        General a = findById(entity.getId());  //si no está devuelve un autor por defecto
        if(a.getId()==null){
            //INSERT
            try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                //si fuera autoincremental yo tendría que leer getGeneratedKeys() -> setDNI

                        ResultSet res = pst.getGeneratedKeys();
                        if(res.next()){
                            entity.setId(res.getString(1));
                        }
                        pst.setString(1,entity.getName());
                        pst.executeUpdate();

                        //save cascade -> opcional
                        if(entity.getBooks()!=null) {
                            for (Company b : entity.getBooks()) {
                                CompanyDAO.build().save(b);
                            }
                        }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            //UPDATE
            try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(UPDATE)) {
                pst.setString(1,entity.getName());
                pst.setString(2,entity.getId());
                pst.executeUpdate();

                //update cascada --> opcional
                if(entity.getBooks()!=null){
                    List<Company> booksBefore = CompanyDAO.build().findByAuthor(entity);
                    List<Company> booksAfter = entity.getBooks();

                    List<Company> booksToBeRemoved = new ArrayList<>(booksBefore);
                    booksToBeRemoved.removeAll(booksAfter);

                    for(Company b:booksToBeRemoved){
                        CompanyDAO.build().delete(b);
                    }
                    for(Company b:booksAfter){
                        CompanyDAO.build().save(b);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public General delete(General entity) throws SQLException {
        if(entity==null || entity.getId()==null) return entity;
        try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(DELETE)) {
            pst.setString(1,entity.getId());
            pst.executeUpdate();
        }
        return entity;
    }

    @Override
    public General findById(String key) {
        General result = new GeneralLazy();
        if(key==null) return result;

        try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDBYDNI)) {
            pst.setString(1,key);
            ResultSet res = pst.executeQuery();
            if(res.next()){
                //result.setId(res.getString("dni"));
                result.setName(res.getString("name"));
                //Lazy
                //BookDAO bDAO = new BookDAO();
                //result.setBooks(bDAO.findByAuthor(result));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<General> findAll() {
        List<General> result = new ArrayList<>();

        try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALL)) {

            ResultSet res = pst.executeQuery();
            while(res.next()){
                General a = new GeneralLazy();
                a.setId(res.getString("id"));
                a.setName(res.getString("name"));
                //Lazy
                // a.setBooks(BookDAO.build().findByAuthor(a));
                result.add(a);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<General> findAllNonEditable() {
        List<General> result = new ArrayList<>();

        try(PreparedStatement pst = ConnectionMariaDB.getConnection().prepareStatement(FINDALLNONEDITABLE)) {

            ResultSet res = pst.executeQuery();
            while(res.next()){
                General a = new GeneralLazy();
                a.setId(res.getString("id"));
                a.setName(res.getString("name"));
                //Lazy
                // a.setBooks(BookDAO.build().findByAuthor(a));
                result.add(a);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }

    public static GeneralDAO build(){
        return new GeneralDAO();
    }
}
class GeneralLazy extends General {
 @Override
 public List<Company> getBooks(){
        if(super.getBooks()==null){
            setBooks(CompanyDAO.build().findByAuthor(this));
        }
        return super.getBooks();
 }
}
