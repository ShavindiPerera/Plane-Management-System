import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


//Task 8: Create a new class file called Ticket.
public class Ticket {
    private char row;
    private int seat;
    private double price;
    private Person person;

    //Added constructors
    public Ticket(char row,int seat,double price,Person person){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.person=person;

    }

    // added getters


    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }


    // added setters

    public void setRow(char row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    // Task 10:Method used to print ticket information
    public void printTicketInfo() {
        System.out.println("Ticket Information:");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: $" + price);
        System.out.println("Person Information:");
        person.printPersonInfo(); // Print person information using Person's printPersonInfo() method
    }


    //Task 12:Method to save Ticket and Person information to a file when a ticket is purchased.
    public void save() {

        String fileName = row + "" + seat + ".txt";
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("Ticket Information:\n");
            writer.write("Row: " + row+ ", Seat: " + seat + "\n");
            writer.write("Price: £" + price + "\n");
            writer.write("Person Information:\n");
            writer.write("Name: " + person.getName() + "\n");
            writer.write("Surname: " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the ticket information.");
            e.printStackTrace();
        }
    }


    //Method to delete the file when the purchased ticket is cancelled.
    public void delete() {
        String fileName = row + "" + seat + ".txt";
        File file = new File(fileName);

        // Check if the file exists before attempting to delete it
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File " + fileName + " deleted successfully.");
            } else {
                System.out.println("Failed to delete file " + fileName);
            }
        } else {
            System.out.println("File " + fileName + " does not exist.");
        }
    }
}
