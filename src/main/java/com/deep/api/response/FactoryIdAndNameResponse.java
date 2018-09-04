package com.deep.api.response;

public class FactoryIdAndNameResponse {
    private Long id;
    private String breedName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    @Override
    public String toString() {
        return "FactoryIdAndNameResponse{" +
                "id=" + id +
                ", breedName='" + breedName + '\'' +
                '}';
    }
}
