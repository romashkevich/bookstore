package users.dao.entity;

import java.util.Objects;

public class Adress {
    private int id;
    private String country;
    private String city;
    private String street;
    private int strNum;
    private int apart;
    public Adress(){}
    public Adress(String country, String city, String street, int strNum, int apart) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.strNum = strNum;
        this.apart = apart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adress adress = (Adress) o;
        return id == adress.id && strNum == adress.strNum && apart == adress.apart && Objects.equals(country, adress.country) && Objects.equals(city, adress.city) && Objects.equals(street, adress.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, street, strNum, apart);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Adress {" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", strNum=" + strNum +
                ", apart=" + apart +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStrNum() {
        return strNum;
    }

    public void setStrNum(int strNum) {
        this.strNum = strNum;
    }

    public int getApart() {
        return apart;
    }

    public void setApart(int apart) {
        this.apart = apart;
    }
}
