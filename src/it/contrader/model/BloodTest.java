package it.contrader.model;

public class BloodTest {

    private Integer id;
    private Float redBloodCell;
    private Float whiteBloodCell;

    private Float platelets;

    private Float hemoglobin;

    private Integer idAdmin;
    private Integer idUser;

    private Boolean isChecked;

    public BloodTest(Integer id, Float redBloodCell, Float whiteBloodCell, Float platelets, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked) {
        this.id = id;
        this.redBloodCell = redBloodCell;
        this.whiteBloodCell = whiteBloodCell;
        this.platelets = platelets;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked = isChecked;
    }

    public BloodTest(Float redBloodCell, Float whiteBloodCell, Float platelets, Float hemoglobin, Integer idAdmin, Integer idUser, Boolean isChecked) {
        this.redBloodCell = redBloodCell;
        this.whiteBloodCell = whiteBloodCell;
        this.platelets = platelets;
        this.hemoglobin = hemoglobin;
        this.idAdmin = idAdmin;
        this.idUser = idUser;
        this.isChecked=isChecked;
    }

    public BloodTest(Integer id, Float redBloodCell, Float whiteBloodCell, Float platelets, Float hemoglobin, Integer idUser, Boolean isChecked) {
        this.id = id;
        this.redBloodCell = redBloodCell;
        this.whiteBloodCell = whiteBloodCell;
        this.platelets = platelets;
        this.hemoglobin = hemoglobin;
        this.idUser = idUser;
        this.isChecked = isChecked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getRedBloodCell() {
        return redBloodCell;
    }

    public void setRedBloodCell(Float redBloodCell) {
        this.redBloodCell = redBloodCell;
    }

    public Float getWhiteBloodCell() {
        return whiteBloodCell;
    }

    public void setWhiteBloodCell(Float whiteBloodCell) {
        this.whiteBloodCell = whiteBloodCell;
    }

    public Float getPlatelets() {
        return platelets;
    }

    public void setPlatelets(Float platelets) {
        this.platelets = platelets;
    }

    public Float getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(Float hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
