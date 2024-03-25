package it.contrader.dto;

public class UrineTestDTO {
    private int id;
    private float color;
    private float ph;
    private float protein;
    private float hemoglobin;
    private int idAdmin;
    private int idUser;
    private Boolean isChecked;

    public UrineTestDTO() {

        this.color = 0;
        this.ph = 0;
        this.protein = 0;
        this.hemoglobin = 0;


    }

    public UrineTestDTO(int id, float color, float ph, float protein, float hemoglobin, int idAdmin, int idUser, boolean isChecked) {
        this.id = id;
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked = isChecked;
    }

    public UrineTestDTO(float color, float hemoglobin, float ph, float protein) {
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
    }

    public UrineTestDTO(float color, float ph, float protein, Float hemoglobin, Integer idAdmin, int idUser) {
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
    }

    public UrineTestDTO(Integer id, float color, float ph, float protein, Float hemoglobin) {
        this.id = id;
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;

    }

    public UrineTestDTO(Integer id, Boolean isChecked) {
        this.id = id;
        this.isChecked = isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getColor() {
        return color;
    }

    public void setColor(float color) {
        this.color = color;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public float getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(float hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public String toString() {
        return  id + "\t"  + color +"\t\t" +   ph + "\t\t" + protein+ "\t\t" + hemoglobin+ "\t\t" + idAdmin+ "\t\t" + idUser+ "\t\t" + isChecked;
    }
}
