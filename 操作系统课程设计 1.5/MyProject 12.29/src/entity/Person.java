package entity;



public class Person implements java.io.Serializable {   
    private String name;   
    private String sex;   
    private Integer age;   
    private Address address;   
    public String getName() {   
        return name;   
    }   
    public void setName(String name) {   
        this.name = name;   
    }   
    public String getSex() {   
        return sex;   
    }   
    public void setSex(String sex) {   
        this.sex = sex;   
    }   
    public Integer getAge() {   
        return age;   
    }   
    public void setAge(Integer age) {   
        this.age = age;   
    }   
    public Address getAddress() {   
        return address;   
    }   
    public void setAddress(Address address) {   
        this.address = address;   
    }   
} 

