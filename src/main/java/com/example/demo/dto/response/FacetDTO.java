package com.example.demo.dto.response;

import java.util.List;
import java.util.Objects;

public class FacetDTO {

    private String name;
    private List<FacetItemDTO> items;

    public FacetDTO() {
    }

    public FacetDTO(String name, List<FacetItemDTO> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FacetItemDTO> getItems() {
        return items;
    }

    public void setItems(List<FacetItemDTO> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacetDTO facetDTO = (FacetDTO) o;
        return Objects.equals(name, facetDTO.name) &&
                Objects.equals(items, facetDTO.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, items);
    }

    @Override
    public String toString() {
        return "FacetDTO{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}

