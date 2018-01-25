package entity;

public class Address implements java.io.Serializable {   
    private String province;   
    private String city;   
    private String country;   
    public Address() {   
    }   
    public Address(String province, String city, String country) {   
        this.province = province;   
        this.city = city;   
        this.country = country;   
    }   
    public String getProvince() {   
        return province;   
    }   
    public void setProvince(String province) {   
        this.province = province;   
    }   
    public String getCity() {   
        return city;   
    }   
    public void setCity(String city) {   
        this.city = city;   
    }   
    public String getCountry() {   
        return country;   
    }   
    public void setCountry(String country) {   
        this.country = country;   
    }   
} 

