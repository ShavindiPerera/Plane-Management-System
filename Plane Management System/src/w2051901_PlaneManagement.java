import java.util.Scanner;
import java.util.InputMismatchException;


//Task 1: Create a class file which includes the main method.
public class w2051901_PlaneManagement {

    public static Scanner input = new Scanner(System.in);

    //An array to store sold tickets.
    private static final Ticket[] tickets = new Ticket[52];
    private static int ticketsIndex = 0;



/* Main method:
       * This displays the welcome message and the options menu for user selection
       * Prompts user for inputs according to the tasks and execute them by calling the other methods
       * Error handling when invalid inputs are entered
       * Exits the program at users command  */

    public static void main(String[] args) {

        //Initializing a 2d array to track and store seats
        int[][] seats = new int[4][];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];

        // Initializing all the seats to 0 or as available
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                seats[i][j] = 0;
            }
        }
        //Display welcome message
        System.out.println("Welcome to the Plane Management application");

        //Initializing choice variable to store user inputs
        int choice = -1;
        //Display menu options
        do {



            System.out.println("**************************************************");
            System.out.println("*                  MENU OPTIONS                  *");
            System.out.println("**************************************************");
            System.out.println("1) Buy a seat");
            System.out.println("2) Cancel a seat");
            System.out.println("3) Find first available seat");
            System.out.println("4) Show seating planning");
            System.out.println("5) Print ticket information and total sales");
            System.out.println("6) Search ticket");
            System.out.println("0) Quit");
            System.out.println("**************************************************");
            System.out.println("Please select an option: ");





            try{
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        buy_seat(seats);                 //Task 3 and 9
                        break;
                    case 2:
                        cancel_seat(seats);              //Task 4 and 9

                        break;
                    case 3:
                        find_first_available(seats);    //Task 5
                        break;
                    case 4:
                        show_seating_plan(seats);      //Task 6
                        break;
                    case 5:
                        print_tickets_info(tickets);   //Task 10
                        break;
                    case 6:
                        search_ticket();              //Task 11
                        break;
                    case 0:
                        //Exit the program
                        System.out.println("Thank you for using the Plane Management System!");
                        break;

                    default:
                        System.out.println("Invalid Option Selection! Please enter a number between 0 and 6.");


                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Option Selection! Please enter a number between 0 and 6.");
                input.next(); // Consume the invalid input
            }

        }
        while (choice != 0);
        input.close();

    }

    /* Task 3 and 9 : Method buy_seat to reserve seats
            *User is prompted to enter required seat details
            * Row letter and seat number will be validated.
            * If the seat is available prompts user for users details and book the seat
            * Stores person and Ticket information in a text file and respective arrays */

    public static void buy_seat(int[][] seats) {

       int[] userInputs = new int[2];
       seatValidation(userInputs);   //input validation
       int rowIndex = userInputs[0];
       int seatNumber = userInputs[1];


            //Check if the seat is available
            if (seats[rowIndex][seatNumber - 1] == 0) {

                //Prompts user for details and while loops are used for error handling
                System.out.println("Enter your first name: ");
                String name = input.next();
                while(!(name.matches("[a-zA-Z]+"))){
                    System.out.println("Invalid first name! enter only letters");
                    System.out.println("Enter your first name: ");
                    name = input.next();

                }


                System.out.println("Enter your surname: ");
                String surname = input.next();
                while(!(surname.matches("[a-zA-Z]+"))){
                    System.out.println("Invalid surname! enter only letters");
                    System.out.println("Enter your surname: ");
                    surname = input.next();

                }

                System.out.println("Enter your email: ");
                String email = input.next();
                while(!(email.contains("@") && email.contains("."))){
                    System.out.println("Invalid email!");
                    System.out.println("Enter your email: ");
                    email = input.next();

                }

                //Declare price of the tickets according to the seating plan
                double price ;
                if (seatNumber <=5){
                    price = 200;
                } else if (seatNumber<=9){
                    price = 150;
                } else {
                    price = 180;
                }

                Person person = new Person(name, surname, email);
                char rowLetter = (char) ('A' + rowIndex);
                Ticket ticket = new Ticket(rowLetter,seatNumber, price, person);
                // Add the ticket to the tickets array at the appropriate index
                tickets[ticketsIndex] = ticket;

                // Increment the ticketsIndex for the next ticket
                ticketsIndex++;
                //save the ticket details to a file
                ticket.save();

                //Assigning 1 to state that the seat is sold
                seats[rowIndex][seatNumber - 1] = 1;

                System.out.println("Seat " + rowLetter + seatNumber + " purchased successfully!");


            } else {
                System.out.println("Seat is unavailable.");
            }


    }

    /*Task 4 : Method to cancel reserved seats
          *user enters the seat details that needs to be cancelled
          * if that particular seat is already reserved it will be cancelled
          * Removed from the ticket array and the file will be deleted */
    private static void cancel_seat(int[][] seats) {


        int[] userInputs = new int[2];
        seatValidation(userInputs);
        int rowIndex = userInputs[0];
        int seatNumber = userInputs[1];


        if (seats[rowIndex][seatNumber - 1] == 1) {
            char rowLetter = (char) ('A' + rowIndex);

            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] != null && tickets[i].getSeat() == seatNumber && tickets[i].getRow() == rowLetter) {
                    tickets[i].delete(); //Delete the particular ticket file
                    tickets[i] = null;// Remove ticket
                    ticketsIndex--;

                    seats[rowIndex][seatNumber - 1] = 0;
                    System.out.println("Seat " + rowLetter + seatNumber + " is  cancelled");
                    break;
                }
                 else {
                System.out.println("Seat " + rowLetter + seatNumber + " was not sold to cancel");
                }
            }
        }

    }


    //Task 5 : Method to find the first available seat
    private static void find_first_available(int[][] seats) {
        for (int rowIndex = 0; rowIndex < seats.length; rowIndex++) {
            for (int seatNumber = 0; seatNumber < seats[rowIndex].length; seatNumber++) {
                if (seats[rowIndex][seatNumber] == 0) {
                    // to-do -- row should be displayed as letters
                    System.out.println("First available seat found: Row " + rowIndex+1 + ", Seat " + (seatNumber + 1));
                    return;
                } else {
                    System.out.println("Sorry all the seats are unavailable");
                }

            }
        }
    }

    /*Task 6 : Method to display the seating plan
          *Available seats will be represented by "X"
          *Unavailable seats will be represented by "O*/
    private static void show_seating_plan(int[][] seats) {
        System.out.println("Seating plan:\n");
        for (int[] seat : seats) {
            for (int i : seat)
                if (i == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            System.out.println();
        }
        System.out.println("\n");
    }


    //Task 10: Method to print ticket information and calculate total sales and print
    private static void print_tickets_info(Ticket[] tickets) {
        double totalSales = 0;
        for (Ticket ticket : tickets) {
            if (ticket != null) {


                ticket.printTicketInfo();
                totalSales += ticket.getPrice();


            }

        }
        System.out.println("\nTotal Sales: £" + totalSales);
    }

    //Task 11: Method to search for a ticket and identify whether the seat is available or not.
    public static void search_ticket(){
        int[] userInputs = new int[2];
        seatValidation(userInputs);
        int rowIndex = userInputs[0];
        int seatNumber = userInputs[1];

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null && tickets[i].getSeat() == seatNumber && tickets[i].getRow() == rowIndex) {
                tickets[i].printTicketInfo();

            }
            else{
                System.out.println("Seat is available");
                break;
            }
        }
    }

    /* This method is used to prompt the user for row Letter and seat number
    * It will then check the validity of the user inputs according to the conditions given
    * Error handling when an invalid input is given*/
    public static void seatValidation(int [] userInputs) {

        int seatNumber;


        while (true) {
            try {
                System.out.println("Please enter your row - A,B,C,D:");
                char rowLetter = input.next().toUpperCase().charAt(0);
                if (rowLetter < 'A' || rowLetter > 'D') {
                    System.out.println("Invalid row entered.");
                    continue;
                }
                int rowIndex = rowLetter - 'A';

                System.out.println("Please enter your seat number - 1-14 (A&D ROWS), 1-12 (B&C ROWS):");
                seatNumber = input.nextInt();

                if ((rowIndex == 0 || rowIndex == 3) && (seatNumber < 1 || seatNumber > 14)) {
                    System.out.println("Invalid seat number for rows A and D.");
                    continue;
                } else if ((rowIndex == 1 || rowIndex == 2) && (seatNumber < 1 || seatNumber > 12)) {
                    System.out.println("Invalid seat number for rows B and C.");
                    continue;
                }
                userInputs[0] = rowIndex;
                userInputs[1] = seatNumber;

                break;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid seat number.");
                input.next(); // Consume the invalid input
            }

        }

    }


}
