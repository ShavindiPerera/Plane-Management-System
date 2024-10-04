//Task 7: Create a new class file called Person
public class Person {
    private String name;
    private String surname;
    private String email;


    //Added constructors
    public Person(String name, String surname, String email){
        this.name=name;
        this.surname=surname;
        this.email=email;
    }

    //Added getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    //Added setters

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method used to print users information
    public void printPersonInfo(){
        System.out.println("Name: "+ name);
        System.out.println("Surname: "+ surname);
        System.out.println("Email: "+ email);

    }

}
