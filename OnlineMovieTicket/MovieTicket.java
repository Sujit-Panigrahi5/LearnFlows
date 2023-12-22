// package OnlineMovieTicket;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieTicket {

    static ArrayList<Movie> movielist = new ArrayList<>();

    public class Movie {

        String name;
        String type;
        double sittingprice;
        double balcuniprice;
        int totalseat;
        int norticketab;
        int balcuniab;

        String[] ticket = new String[30];

        ArrayList<String[]> seat = new ArrayList<>();

        public Movie(String name, String type, double sittingprice, double balcuniprice) {
            this.name = name;
            this.type = type;
            this.sittingprice = sittingprice;
            this.balcuniprice = balcuniprice;
            this.norticketab = 15;
            this.balcuniab = 15;
            this.totalseat = 30;

            this.seat.add(ticket); // add for morning show
            this.seat.add(ticket); // add for evening show

            // int i=1;
            // for (String[] is : seat) {
            // for (String item : is) {
            // System.out.println("sujit1 : "+item);
            // item = "N";
            // System.out.println("sujit2 : "+item);
            // }
            // }

            for (int i = 0; i < seat.size(); i++) {
                for (int j = 0; j < seat.get(i).length; j++) {
                    seat.get(i)[j] = "N";
                }
            }
            movielist.add(this);

        }
    }

    public static void showallmovie() {
        Scanner sc = new Scanner(System.in);
        int i = 1;
        for (Movie item : movielist) {
            System.out.println("------------" + i + "-------------");
            System.out.println("Name : " + item.name);
            System.out.println("Movie Type : " + item.type);
            System.out.println("Seat avable : " + item.totalseat);
            System.out.println("Normar seat avable  : " + item.norticketab);
            System.out.println("Balcuni seat avable : " + item.balcuniab);
            System.out.println("Normal seat price : " + item.sittingprice);
            System.out.println("Balcuni seat price : " + item.balcuniprice);
            i++;
        }

        System.out.print("Are you went to book a ticket , please enter 1 ,otherwise; enter 0 : ");
        byte inputforbookcheck = sc.nextByte();

        if (inputforbookcheck == 1) {
            System.out.print("Ente movie number : ");
            int inputformovie = sc.nextInt();
            bookticket(movielist.get(inputformovie - 1));
            showallmovie();
        }
        return;

    }

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

    // avable for ticket

    public static boolean isitavable(Movie item, int seat, String showtype, int ticketno) {
        if (showtype == "morning") { // for morning show
            if (item.seat.get(0)[ticketno - 1] == "N") {
                return true;
            }

        } else {
            if (item.seat.get(0)[ticketno - 1] == "N") {
                return true;
            }

        }
        return false;
    }

    
    // deleteticket 
    
    public static void deleteticket(ArrayList<Integer> tickets, int ticketno){
        for(int i=0;i<tickets.size();i++){
            if(tickets.get(i) == ticketno){
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
            System.out.println("Your tickets "); 
            for (int i = 0; i < tickets.size(); i++) {
                System.out.println(tickets.get(i));
            }
            System.out.println("Enter a ticket number ");
            int ticketno = sc.nextInt();
            if (seat == 1) {
                price -= item.sittingprice;
                if (showtype == "morning") {
                    System.out.println(showtype == "morning");
                    item.seat.get(0)[ticketno - 1] = "N";
                    deleteticket(tickets,ticketno - 1);
                } else {
                    item.seat.get(1)[ticketno - 1] = "N";
                    deleteticket(tickets,ticketno - 1);
                }
                System.out.println("Your ticket has canceled");
                // ticketbook(item, seat, ticketcount - 1, showtype, price);

            } else {
                price -= item.balcuniprice;
                if (showtype == "morning") {
                    item.seat.get(0)[(ticketno + 14) - 1] = "N";
                    deleteticket(tickets,(ticketno + 14) - 1);
                } else {
                    item.seat.get(1)[(ticketno + 14) - 1] = "N";
                    deleteticket(tickets,(ticketno + 14) - 1);
                }
                System.out.println("Your ticket has canceld");
                // ticketbook(item, seat, ticketcount - 1, showtype, price);
            }
        }

    }

    public static void ticketbook(Movie item, int seat, int ticketcount, String showtype, int price,
            ArrayList<Integer> tickets , int tic) { // here ticket booked
        Scanner sc = new Scanner(System.in);

        if (ticketcount == 0) { // base case
            System.out.println("Your price is : " + price);
            System.out.println("For pay enter 1 , if you want to cancel then enter 0 : ");
            int forpay = sc.nextInt();
            if (forpay == 1) {
                System.out.println("All ticket has booked  and  your tickets are :  ");
                for (Integer i : tickets) {
                    if (seat == 1) {
                        System.out.print(i + " ");
                    } else {
                        System.out.println((i - 14) + " ");
                    }

                }
                System.out.println();

            } else {

                System.out.println("How much ticket , you want to cancel ");
                int cancelcount = sc.nextInt();
                while (cancelcount > tic) {
                    System.out.println("Sorry enter a valid number !");
                    System.out.println("How much ticket , you want to cancel ");
                    cancelcount = sc.nextInt();
                }

                cancelticket(item, seat, showtype, cancelcount, price, tickets); // cancel method call
            }

            return;
        }


        if (isitfull(item, showtype, seat)) {
            System.out.print("enter your  ticket number (only one at a time ): ");
            int ticketno = sc.nextInt();
            if (ticketno > 15 || ticketno <= 0) {
                System.out.print("Enter a valid seat no : ");
                ticketbook(item, seat, ticketcount, showtype, price,tickets,tic);
            } else {
                if (isitavable(item, seat, showtype, ticketno)) {
                    if (seat == 1) {
                        price += item.sittingprice;
                        if (showtype == "morning") {
                            System.out.println(showtype == "morning");
                            item.seat.get(0)[ticketno - 1] = "B";
                        } else {
                            item.seat.get(1)[ticketno - 1] = "B";
                        }
                        System.out.println("Your ticket has booked");
                        ticketbook(item, seat, ticketcount - 1, showtype, price,tickets,tic);

                    } else if(seat == 2) {
                        price += item.balcuniprice;
                        if (showtype == "morning") {
                            item.seat.get(0)[(ticketno + 14) - 1] = "B";
                        } else {
                            item.seat.get(1)[(ticketno + 14) - 1] = "B";
                        }
                        System.out.println("Your ticket has booked");
                        ticketbook(item, seat, ticketcount - 1, showtype, price,tickets,tic);
                    }
                }
            }
        } else {
            System.out.println("Sorry, not avable .");
            return;
        }

    }

    public static void bookticket(Movie item) { // choose option for book ticket
        Scanner sc = new Scanner(System.in);
        System.out.print("For morning input 1 and for evening input 0");
        int show = sc.nextInt();
        // String movieshow = show.toLowerCase();
        // System.out.println(movieshow == "morning");
        if (show == 1) {
            System.out.println("Thanks for choose . ");
            System.out.println("------- Avable ticket ---------");
            int i = 0;
            System.out.println("--------- Normal seat -------");

            for (i = 0; i < 30; i++) {

                if (i <= 14) {
                    System.out.print(item.seat.get(0)[i] + "  ");

                    if (i == 4 || i == 9 || i == 14) {
                        System.out.println();
                    }
                    // if(i==14){
                    // System.out.println();
                    // }

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

            System.out.print("For normal write 1 , for baalcony write 2 and for both write 3 ");
            int choose = sc.nextInt();

            if (choose == 1) { // for normal
                System.out.println("Avable seat : ");

                for (int j = 0; j < 15; j++) {
                    if (item.seat.get(0)[j] == "N") {

                        System.out.print(item.seat.get(0)[j] + "  ");

                        if (j == 4 || j == 9 || j == 14) {
                            System.out.println();
                        }
                    }
                }

            } else if (choose == 2) { // for bolcony
                System.out.println("Avable seat : ");

                for (int j = 15; j < 30; j++) {
                    if (item.seat.get(0)[j] == "N") {
                        System.out.print(item.seat.get(0)[j]);
                        if (j == 19 || j == 24 || j == 29) {
                            System.out.println();
                        }
                    }
                }
            } else if (choose == 3) { // for both
                System.out.println("Avable seat : ");
                int j = 0;

                for (String moitem : item.seat.get(0)) {
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

            System.out.print("How much ticket , you want to book .");
            int bookcount = sc.nextInt();

            if (choose == 3) { // for both
                System.out.println("for normal seat ");

                System.out.println("How much ticket , you want to book in normal ");
                int choosee1normal = sc.nextInt();

                ticketbook(item, 1, choosee1normal, "morning", 0, new ArrayList<>(),choosee1normal);
                System.out.println("How much ticket , you want to book in balcony ");
                int choosebalcony = sc.nextInt();
                ticketbook(item, 2, choosebalcony, "morning", 0,new ArrayList<>(),choosebalcony);
            }
            ticketbook(item, choose, bookcount, "morning", 0,new ArrayList<>(),choose);

        } else {
            System.out.println("Thanks for choose . ");
            System.out.println("------- Avable ticket ---------");
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

            System.out.print("For normal write 1 , for baalcony write 2 and for both write 3 ");
            int choose = sc.nextInt();

            if (choose == 1) {
                System.out.println("Avable seat : ");

                for (int j = 0; j < 15; j++) {
                    if (item.seat.get(1)[j] == "N") {

                        System.out.print(item.seat.get(0)[j] + "  ");

                        if (j == 4 || j == 9 || j == 14) {
                            System.out.println();
                        }
                    }
                }

            } else if (choose == 2) {
                System.out.println("Avable seat : ");

                for (int j = 15; j < 30; j++) {
                    if (item.seat.get(1)[j] == "N") {
                        System.out.print(item.seat.get(0)[j]);
                        if (j == 19 || j == 24 || j == 29) {
                            System.out.println();
                        }
                    }
                }
            } else if (choose == 3) {
                System.out.println("Avable seat : ");
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

            System.out.print("How much ticket , you want to book .");
            int bookcount = sc.nextInt();

            if (choose == 3) {
                System.out.println("for normal seat ");

                System.out.println("How much ticket , you want to book in normal ");
                int choosee1fornormal = sc.nextInt();

                ticketbook(item, 1, choosee1fornormal, "morning", 0, new ArrayList<>(),choosee1fornormal);
                System.out.println("How much ticket , you want to book in balcony ");
                int choosebalcony = sc.nextInt();
                ticketbook(item, 2, choosebalcony, "morning", 0,new ArrayList<>(),choosebalcony);
            }
            ticketbook(item, choose, bookcount, "morning", 0,new ArrayList<>(),choose);
        }
    }

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

        Movie s = ss.new Movie("su", "Horror", 03, 03);

        // System.out.println("Are you want to search movie , please enter 1 or enter 0
        // : ");
        // byte input = sc.nextByte();
        // if (input == 1) {
        // System.out.print("Enter your movie name : ");
        // String moviename = sc.nextLine();
        // searchmovie(moviename);
        // }

        // for(int i=0;i<15 ;i++){
        // System.out.println(s.seat.get(0)[i]);
        // }
        showallmovie();

    }
}
