package org.example.model.entity;

import java.util.Objects;

public class Company {
    private String id;
    private String name;
    private General general;


    public Company(String name,General general) {
        this.id = id;
        this.name = name;
        this.general = general;

    }

    public Company() {
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

    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Compañia " +
                "Nº id='" + id + '\'' +
                ", nombre='" + name + '\'' +
                ", general=" + general.getId() +","+ general.getName()+
                '}';
    }
}
