
import java.util.ArrayList;
import java.util.Scanner;

public class MovieTicket {

    static ArrayList<Movie> movielist = new ArrayList<>();

    public class Movie { // Movie class

        String name;
        String type;
        double normalprice;
        double balcuniprice;
        int totalseat;
        int norticketab;
        int balcuniab;

        String[] ticket = new String[30];
        String[] ticket1 = new String[30];

        ArrayList<String[]> seat = new ArrayList<>();

        // constor method
        public Movie(String name, String type, double sittingprice, double balcuniprice) {
            this.name = name;
            this.type = type;
            this.normalprice = sittingprice;
            this.balcuniprice = balcuniprice;
            this.norticketab = 15;
            this.balcuniab = 15;
            this.totalseat = 30;

            this.seat.add(ticket); // add for morning show
            this.seat.add(ticket1); // add for evening show

            for (int i = 0; i < this.seat.size(); i++) {
                for (int j = 0; j < this.seat.get(0).length; j++) {

                    // this.seat.get(0)[j] = "N";
                    this.seat.get(i)[j] = "N";

                }
            }
            movielist.add(this);

        }
    }

    // show all movie
    public static void showallmovie() {
        Scanner sc = new Scanner(System.in);
        int i = 1;
        for (Movie item : movielist) {
            System.out.println("------------" + i + "-------------");
            System.out.println("Name : " + item.name);
            System.out.println("Movie Type : " + item.type);
            System.out.println("Seat avable : " + item.totalseat);
            System.out.println("Normal seat available  : " + item.norticketab);
            System.out.println("Balcony seat available : " + item.balcuniab);
            System.out.println("Normal seat price : " + item.normalprice);
            System.out.println("Balcony seat price : " + item.balcuniprice);
            i++;
        }

        System.out.print("Did you book a ticket? Please enter 1 for yes, otherwise, enter 0 : ");
        byte inputforbookcheck = sc.nextByte();

        if (inputforbookcheck == 1) {
            System.out.print("Ente movie number : ");
            int inputformovie = sc.nextInt();
            bookticket(movielist.get(inputformovie - 1));
            showallmovie();
        }
        return;

    }

    // for check current ticket in available or not
    public static boolean isitfull(Movie item, String showtype, int seat) {
        if (showtype.toLowerCase() == "morning") { // for morning show
            if (seat == 1) { // for normal seat
                for (int i = 0; i < 15; i++) {
                    if (item.seat.get(0)[i] == "N") {
                        return true;
                    }
                }
                return false;
            } else { // for balcony seat
                for (int i = 15; i < 30; i++) {
                    if (item.seat.get(0)[i] == "N") {
                        return true;
                    }
                }
                return false;
            }
        } else {
            if (seat == 1) { // for normal seat
                for (int i = 0; i < 15; i++) {
                    if (item.seat.get(1)[i] == "N") {
                        return true;
                    }
                }
                return false;
            } else { // for balcony seat
                for (int i = 15; i < 30; i++) {
                    if (item.seat.get(1)[i] == "N") {
                        return true;
                    }
                }
                return false;
            }
        }

    }

    // available for ticket

    public static boolean isitavable(Movie item, int seat, String showtype, int ticketno) {
        if (showtype == "morning") { // for morning show
            if (seat == 1) {
                if (item.seat.get(0)[ticketno - 1] == "N") {
                    return true;
                }
            }
            if (seat == 2) {
                if (item.seat.get(0)[ticketno + 14] == "N") {
                    return true;
                }
            }

        } else {
            if (seat == 1) {
                if (item.seat.get(1)[ticketno - 1] == "N") {
                    return true;
                }
            }
            if (seat == 2) {
                if (item.seat.get(1)[ticketno + 14] == "N") {
                    return true;
                }
            }
        }
        return false;
    }

    // deleteticket

    public static void deleteticket(ArrayList<Integer> tickets, int ticketno) {
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i) == ticketno) {
                tickets.remove(i);
                return;
            }
        }
        System.out.println("ticket number is not found ");
    }

    // cancel ticket
    public static void cancelticket(Movie item, int seat, String showtype, int cancelticktcount, int price,
            ArrayList<Integer> tickets) {

        Scanner sc = new Scanner(System.in);

        for (int j = 0; j < cancelticktcount; j++) {

            System.out.println("Please enter a ticket number. ");
            int ticketno = sc.nextInt();
            if (seat == 1) {
                price -= item.normalprice;
                if (showtype == "morning") {
                    // System.out.println(showtype == "morning");
                    item.seat.get(0)[ticketno - 1] = "N";
                    item.norticketab++;
                    for (int i = 0; i < 15; i++) {
                        System.out.print(item.seat.get(0)[i] + "  ");
                        if (i==4 || i == 9 || i==14) {
                            System.out.println();
                        }
                    }

                    deleteticket(tickets, ticketno);
                } else { // for evening

                    item.seat.get(1)[ticketno - 1] = "N";
                    item.norticketab++;
                    for (int i = 0; i < 15; i++) {
                        System.out.print(item.seat.get(0)[i] + "  ");
                        if (i==4 || i == 9 || i==14) {
                            System.out.println();
                        }
                    }

                    deleteticket(tickets, ticketno);
                }
                System.out.println("Your ticket has canceled");
                // ticketbook(item, seat, ticketcount - 1, showtype, price);

            } else {
                price -= item.balcuniprice;
                if (showtype == "morning") {
                    item.seat.get(0)[(ticketno + 14)] = "N";
                    item.balcuniab++;
                    for (int i = 15; i < 30; i++) {
                        System.out.print(item.seat.get(0)[i] + "  ");
                       if (i == 19 || i == 24 || i == 29) {
                            System.out.println();
                        }
                    }

                    deleteticket(tickets, (ticketno));
                } else {
                    item.seat.get(1)[(ticketno + 14)] = "N";
                    item.balcuniab++;
                    for (int i = 15; i < 30; i++) {
                        System.out.print(item.seat.get(0)[i] + "  ");
                        if (i == 19 || i == 24 || i == 29) {
                            System.out.println();
                        }
                    }
                    System.out.println();

                    deleteticket(tickets, (ticketno));
                }
                System.out.println("Your ticket has canceld");
                // ticketbook(item, seat, ticketcount - 1, showtype, price);
            }
        }

    }

    // Ticket book
    public static void ticketbook(Movie item, int seat, int ticketcount, String showtype, int price,
            ArrayList<Integer> tickets, int tic) { // here ticket booked
        Scanner sc = new Scanner(System.in);

        if (ticketcount == 0) { // base case
            System.out.println("Your price is : " + price);
            System.out.println("To make a payment, enter 1; if you want to cancel, enter 0: ");
            int forpay = sc.nextInt();
            if (forpay == 1) {
                System.out.println("All tickets have been booked, and your tickets are:  ");
                for (int i = 0; i < tickets.size(); i++) {
                    if (seat == 1) {
                        System.out.print(tickets.get(i) + "(Normal)," + " ");
                    } else {
                        System.out.print((tickets.get(i)) + "(Balcony)," + " ");
                    }
                }
                System.out.println();

            } else {

                while (forpay != 1) {
                    

                    System.out.println("How many tickets do you want to cancel ? ");
                    int cancelcount = sc.nextInt();
                    if(tic == 0 ){
                        return;
                    }  
                    while (cancelcount > tic) {
                        System.out.println("Sorry, please enter a valid number!");
                        System.out.println("How many tickets do you want to cancel ? ");
                        cancelcount = sc.nextInt();
                        tic -= cancelcount;

                    }

                    cancelticket(item, seat, showtype, cancelcount, price, tickets); // cancel method call

                    System.out.println("To make a payment, enter 1; if you want to cancel, enter 0: ");
                    forpay = sc.nextInt(); 
                }

            }

            return;
        }

        if (isitfull(item, showtype, seat)) {
            System.out.print("Enter your ticket number (only one at a time) : ");
            int ticketno = sc.nextInt();
            if (ticketno > 15 || ticketno <= 0) {
                System.out.print("Enter a valid seat number:");
                ticketbook(item, seat, ticketcount, showtype, price, tickets, tic);
            } else {
                if (isitavable(item, seat, showtype, ticketno)) {

                    if (seat == 1) {
                        price += item.normalprice;
                        if (showtype == "morning") {
                            // System.out.println(showtype == "morning");
                            item.seat.get(0)[ticketno - 1] = "B";
                            tickets.add(ticketno);
                            item.norticketab--;
                        } else {
                            item.seat.get(1)[ticketno - 1] = "B";
                            tickets.add(ticketno);
                            item.norticketab--;
                        }
                        System.out.println("Your ticket has booked.");
                        ticketbook(item, seat, ticketcount - 1, showtype, price, tickets, tic);

                    }
                    if (seat == 2) {
                        price += item.balcuniprice;

                        if (showtype == "morning") {

                            item.seat.get(0)[(ticketno + 14)] = "B";
                            tickets.add(ticketno);
                            item.balcuniab--;
                        } else {
                            item.seat.get(1)[(ticketno + 14)] = "B";
                            tickets.add(ticketno);
                            item.balcuniab--;
                        }
                        System.out.println("Your ticket has booked.");
                        ticketbook(item, seat, ticketcount - 1, showtype, price, tickets, tic);
                    }
                } else {
                    System.out.println("This seat has booked, So enter a valid seat no :  ");
                    ticketbook(item, seat, ticketcount, showtype, price, tickets, ticketno);
                }
            }
        } else {
            System.out.println("Sorry, not avable .");
            return;
        }

    }

    // Choose which show and whick seat (Normal,Balcony and both )
    public static void bookticket(Movie item) { // choose option for book ticket
        Scanner sc = new Scanner(System.in);
        System.out.print("For the morning, input 1, and for the evening, input 0 : ");
        int show = sc.nextInt();

        if (show == 1) { // for morning show

            System.out.println("Thanks for choose . ");
            System.out.println("------- Available ticket ---------");
            int i = 0;
            System.out.println("--------- Normal seat -------");

            for (i = 0; i < 30; i++) { // all tickets

                if (i <= 14) {

                    System.out.print(item.seat.get(0)[i] + "  ");

                    if (i == 4 || i == 9 || i == 14) {
                        System.out.println();
                    }

                } else if (i == 15) {
                    System.out.println("-------- Balcuni seat ---------");
                    System.out.print(item.seat.get(0)[i] + "  ");
                } else {
                    System.out.print(item.seat.get(0)[i] + "  ");
                    if (i == 19 || i == 24 || i == 29) {
                        System.out.println();
                    }

                }
            }
            System.out.println();

            System.out.print("For normal, write 1; for balcony, write 2; and for both, write 3. ");
            int choose = sc.nextInt();

            if (choose == 1) { // for normal
                System.out.println("Available seat : ");

                for (int j = 0; j < 15; j++) {
                    // if (item.seat.get(0)[j] == "N") {

                    System.out.print(item.seat.get(0)[j] + "  ");

                    if (j == 4 || j == 9 || j == 14) {
                        System.out.println();
                    }
                    // }
                }

            } else if (choose == 2) { // for bolcony
                System.out.println("Available seat : ");

                for (int j = 15; j < 30; j++) {
                    // if (item.seat.get(0)[j] == "N") {
                    System.out.print(item.seat.get(0)[j] + "  ");
                    if (j == 19 || j == 24 || j == 29) {
                        System.out.println();
                    }
                    // }
                }
            } else if (choose == 3) { // for both
                System.out.println("Available seat : ");
                int j = 0;
                System.out.println("------------ Normal seat------------");
                for (String moitem : item.seat.get(0)) {
                    // if (moitem == "N") {
                    System.out.print(moitem + "  ");

                    if (j == 4 || j == 9 || j == 14) {
                        System.out.println();
                    }
                    if (j == 14) {
                        System.out.println("----------- Balcony seat ------------");
                    }
                    if (j == 19 || j == 24 || j == 29) {
                        System.out.println();
                    }
                    j++;
                    // }
                }
            }

            // System.out.print("How many tickets would you like to book ? ");
            // int bookcount = sc.nextInt();

            if (choose == 3) { // for both

                System.out.println("How many tickets would you like to book for normal ? ");
                int choosee1normal = sc.nextInt();

                ticketbook(item, 1, choosee1normal, "morning", 0, new ArrayList<>(), choosee1normal);
                System.out.println("How many tickets would you like to book for balcony ? ");
                int choosebalcony = sc.nextInt();
                ticketbook(item, 2, choosebalcony, "morning", 0, new ArrayList<>(), choosebalcony);

            } else { // for normal and balcony
                System.out.print("How many tickets would you like to book ? ");
                int bookcount = sc.nextInt();
                ticketbook(item, choose, bookcount, "morning", 0, new ArrayList<>(), bookcount);
            }

        } else { // for evening show

            System.out.println("Thanks for choose . ");
            System.out.println("------- Available ticket ---------");
            int i = 0;
            System.out.println("--------- Normal seat -------");

            for (i = 0; i < 30; i++) {

                if (i <= 14) {
                    System.out.print(item.seat.get(1)[i] + "  ");

                    if (i == 4 || i == 9 || i == 14) {
                        System.out.println();
                    }
                    // if(i==14){
                    // System.out.println();
                    // }

                } else if (i == 15) {
                    System.out.println("-------- Balcuni seat ---------");
                    System.out.print(item.seat.get(1)[i] + "  ");
                } else {
                    System.out.print(item.seat.get(1)[i] + "  ");
                    if (i == 19 || i == 24 || i == 29) {
                        System.out.println();
                    }

                }
            }
            System.out.println();

            System.out.print("For normal, write 1; for balcony, write 2; and for both, write 3.");
            int choose = sc.nextInt();

            if (choose == 1) {
                System.out.println("Available seat : ");

                for (int j = 0; j < 15; j++) {
                    if (item.seat.get(1)[j] == "N") {

                        System.out.print(item.seat.get(1)[j] + "  ");

                        if (j == 4 || j == 9 || j == 14) {
                            System.out.println();
                        }
                    }
                }

            } else if (choose == 2) {
                System.out.println("Available seat : ");

                for (int j = 15; j < 30; j++) {
                    if (item.seat.get(1)[j] == "N") {
                        System.out.print(item.seat.get(1)[j] + "  ");
                        if (j == 19 || j == 24 || j == 29) {
                            System.out.println();
                        }
                    }
                }
            } else if (choose == 3) {
                System.out.println("Available seat : ");
                int j = 0;

                for (String moitem : item.seat.get(1)) {
                    if (moitem == "N") {
                        System.out.print(moitem);

                        if (j == 4 || j == 9 || j == 14) {
                            System.out.println();
                        }
                        if (j == 19 || j == 24 || j == 29) {
                            System.out.println();
                        }
                        j++;
                    }
                }
            }

            // for both
            if (choose == 3) {

                System.out.println("How many tickets would you like to book for normal ? ");
                int choosee1fornormal = sc.nextInt();

                ticketbook(item, 1, choosee1fornormal, "evening", 0, new ArrayList<>(), choosee1fornormal);
                System.out.println("How many tickets would you like to book for balcony ? ");
                int choosebalcony = sc.nextInt();
                ticketbook(item, 2, choosebalcony, "evening", 0, new ArrayList<>(), choosebalcony);
            } else {
                System.out.print("How many tickets would you like to book ? ");
                int bookcount = sc.nextInt();
                ticketbook(item, choose, bookcount, "evening", 0, new ArrayList<>(), choose);
            }

        }
    }

    // search a movie
    public static void searchmovie(String moviename) {
        for (Movie item : movielist) {
            if (moviename == item.name) {
                bookticket(item);
            }
        }

        System.out.println("Sorry, This movie has not any show in theater   .");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MovieTicket ss = new MovieTicket();

        Movie salar = ss.new Movie("Salaar", "Action", 250, 300);
        Movie Dunki = ss.new Movie("Dunki", "Romance", 200, 300);

        showallmovie();

    }
}
