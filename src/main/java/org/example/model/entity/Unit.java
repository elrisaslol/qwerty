package org.example.model.entity;

import java.util.Objects;

public class Unit {

    private String id;
    private String name;
    private Company company;

    public Unit(String name, Company company) {
        this.id = id;
        this.name = name;
        this.company = company;
    }

    public Unit() {
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(name, unit.name);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "type='" + id + '\'' +
                ", name='" + name + '\'' +
                ", company=" + company.getId() +","+ company.getName()+
                '}';
    }
}
