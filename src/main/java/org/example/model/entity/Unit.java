package org.example.model.entity;

import java.util.Objects;

public class Unit {

    private String type;
    private String name;
    private Company company;

    public Unit(String type, String name, Company company) {
        this.type = type;
        this.name = name;
        this.company = company;
    }

    public Unit() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return Objects.hash(name);
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
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", company=" + company +
                '}';
    }
}
