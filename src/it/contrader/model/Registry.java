package it.contrader.model;

import java.util.Date;

/**
 * Per dettagli vedi guida sez 4 Model
 */
public class Registry {

    /**
     * Qui sotto definisco gli attributi di Registry.
     */
    private int id;
    private String name;
    private String surname;
    private String birthDate;
    private String email;
    private String nationality;
    private String city;
    private String address;
    private String cf;
    private Integer idUser;


    /**
     * Definisco i due costruttori, uno vuoto e uno con tre parametri per costrire oggetti di tipo Registry
     */
    public Registry() {

    }

    public Registry ( String name, String surname, String birthDate, String email, String nationality, String city, String address, String cf, Integer idUser) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.nationality = nationality;
        this.city = city;
        this.address = address;
        this.cf = cf;
        this.idUser = idUser;
    }

    public Registry (int id, String name, String surname, String birthDate,String email,String nationality, String city,String address,String cf,Integer idUser) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.nationality = nationality;
        this.city = city;
        this.address = address;
        this.cf = cf;
        this.idUser = idUser;
    }


    public Registry(int id, String name, String surname, String birthDate, String email, String nationality, String city, String address, String cf) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.nationality = nationality;
        this.city = city;
        this.address = address;
        this.cf = cf;
    }
    public Registry(Integer idUser, String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Getter e Setter: servono alle altre classi a recuperare e modificare gli attributi di Registry
     */


    //Trasforma un oggetto in una stringa
    @Override
    public String toString() {
        return  id + "\t"  + name +"\t\t" +   surname + "\t\t" + birthDate + "\t\t" + email + "\t\t" + nationality + "\t\t" + city + "\t\t" + address + "\t\t" + cf + "\t\t" + idUser ;

    }

    //Metodo per il confronto degli oggetti
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Registry other = (Registry) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        if (birthDate== null) {
            if (other.birthDate != null)
                return false;
        } else if (!birthDate.equals(other.birthDate))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (nationality == null) {
            if (other.nationality != null)
                return false;
        } else if (!nationality.equals(other.nationality))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (cf == null) {
            if (other.cf != null)
                return false;
        } else if (!cf.equals(other.cf))
            return false;
        if (idUser == null) {
            if (other.idUser != null)
                return false;
        } else if (!idUser.equals(other.idUser))
            return false;
        return true;
    }
}
