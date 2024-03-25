package it.contrader.model;

public class UrineTest {
    private int id;
    private float color;
    private float ph;
    private float protein;
    private float hemoglobin;
    private int idAdmin;
    private int idUser;
    private Boolean isChecked;

    public UrineTest() {

    }

    public UrineTest(int id, float color, float ph, float protein, float hemoglobin, int idAdmin, int idUser, Boolean isChecked) {
        this.id = id;
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked = isChecked;

    }

    public UrineTest(float color, float hemoglobin, float ph, float protein) {
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
    }

    public UrineTest(float color, float ph, float protein, float hemoglobin, int idAdmin, int idUser, Boolean isChecked) {
        this.color = color;
        this.ph = ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked = isChecked;
    }

    public UrineTest(int id, float color,float ph,float protein, float hemoglobin, boolean isChecked) {
        this.id = id;
        this.color= color;
        this.ph =ph;
        this.protein = protein;
        this.hemoglobin = hemoglobin;
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
}
