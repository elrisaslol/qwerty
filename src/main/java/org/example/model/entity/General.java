package org.example.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class General {
    private String id;
    private String name;
    private List<Company> companies;



    public General(String name) {
        this.id = id;
        this.name = name;

    }

    public General() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public List<Company> getBooks() {
        return companies;
    }

    public void setBooks(List<Company> medals) {
        this.companies = medals;
    }

    public void addBook(Company company){
        if(this.companies ==null){
            this.companies = new ArrayList<>();
        }
        if(!this.companies.contains(company)){
            this.companies.add(company);
        }
    }
    public void removeBook(Company company){
        if(this.companies !=null){
            this.companies.remove(company);
        }
    }
    public Company getBook(Company company){
        Company result=null;
        if(this.companies !=null){
            int i= this.companies.indexOf(company);
            result = this.companies.get(i);
        }
        return result;
    }*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        General general = (General) o;
        return Objects.equals(id, general.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "General (" +
                "id='" + id + '\'' +
                ", nombre='" + name + '\'' +
                ')';
    }
}
