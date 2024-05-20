package org.example.model.entity;

import java.util.List;
import java.util.Objects;

public class Company {
    private String id;
    private String name;
    private General general;

    private List<Unit> Units;

    public Company(String id, String name, General general) {
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

    public General getAuthor() {
        return general;
    }

    public void setAuthor(General general) {
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
        return "Medals{" +
                "isbn='" + id + '\'' +
                ", title='" + name + '\'' +
                ", general=" + general.getId() +","+ general.getName()+
                '}';
    }
}
