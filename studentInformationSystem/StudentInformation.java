// package studentInformationSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentInformation {

    ArrayList<Student> stulist = new ArrayList<>();

    class Student {
        private String name;
        private StringBuilder regdno;
        private String gender;
        private String phon_no;
        private String address;
        private String course;

        int no[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        private StringBuilder createid() {
            StringBuilder id = new StringBuilder("");
            for (int i = 0; i < 10; i++) {
                int a = sendid();

                id.append(a);

            }
            return id;

        }

        private int sendid() {
            double random = Math.random();

            return (int) Math.floor(random * no.length);
        }

        public Student(String name, String gender, String phonno, String course) {
            this.name = name;
            this.regdno = createid();
            this.gender = gender;
            this.phon_no = phonno;
            this.course = course;
            stulist.add(this);

            System.out.println("Your regd no is " + this.regdno);

        }

    }

    // create account
    public static void createaccount() {
        Scanner sc =new Scanner(System.in);

        StudentInformation stu=new StudentInformation();

        System.out.print("Enter your name : ");
        String name = sc.nextLine();   // name 
        System.out.println("Write your gender : ");
        String gender  = sc.nextLine();  //  gender 
        System.out.print("White your phone number : ");
        String phon= sc.nextLine();
        System.out.print("Write your course name : ");
        System.out.println("Would you like to see our couse ( please write yes or no):");
        String cou= sc.nextLine();   // course select is panding 
        if(cou.toLowerCase() == "yes"){
            System.out.println("Our courses are : B-tech,MCA,MBA,BCA");

        }

        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String stu = null;
        int i = 1;
        while (stu.toLowerCase() != "yes" || stu.toLowerCase() != "no") {
            System.out.println("Are you a new student ?  ");
            stu = sc.nextLine();
            if (stu.toLowerCase() == "yes") {
                createaccount();
            } else if (stu.toLowerCase() == "no") {
                String see = null;
                int j = 1;
                while (see.toLowerCase() != "yes" || see.toLowerCase() != "no") {

                    System.out.println("Would you like to see your informtion . ");
                    see = sc.nextLine();

                    if (see.toLowerCase() == "yes ") {
                        System.out.print("Enter your registration number : ");
                        String regd = sc.nextLine();
                        StringBuilder reg = new StringBuilder();
                        reg.append(regd);
                        // showallinfo(reg);

                        System.out.print("Would you like to see all Student name , who has created account here . : ");
                        String allinfo = sc.nextLine();
                        if (allinfo.toLowerCase() == "yes") {
                            // showallistudent();
                        }
                    } else if (see.toLowerCase() == "no") {
                        System.out.print("Would you like to see all Student name , who has created account here . : ");
                        String allinfo = sc.nextLine();
                        if (allinfo.toLowerCase() == "yes") {
                            // showallistudent();
                        }

                    } else {
                        j++;
                        if (j == 3) {
                            System.out.println("This is your last chance to choose ");
                        }
                        if (j == 4) {
                            break;
                        }
                        System.out.println("Write yes or no ");
                    }
                }

            } else {
                i++;
                if (i == 3) {
                    System.out.println("This is your last chance to choose ");
                }
                if (i == 4) {
                    break;
                }
                System.out.println("Please Write yes or no ");

            }
        }

    }
}
