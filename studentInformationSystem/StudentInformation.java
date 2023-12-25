// package studentInformationSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentInformation {

    static ArrayList<Student> stulist = new ArrayList<>();

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

    // show all info
    public static void showallinfo(StringBuilder regd) {
        Scanner sc = new Scanner(System.in);
        for (Student stu : stulist) {
            if (stu.regdno.equals(regd)) {
                System.out.println("Name : " + stu.name);
                System.out.println("Regd no : " + stu.regdno);
                System.out.println("Course  : " + stu.course);
                System.out.println("Gender  : " + stu.gender);
                System.out.println("Phone Number : " + stu.phon_no);
                System.out.println("Thank you ");
                return;
            }
        }
        System.out.println("Enter a valid regd no ");
        System.out.print("Enter your registration number :  ");
        String regd1 = sc.nextLine();
        StringBuilder reg = new StringBuilder();
        reg.append(regd1);
        showallinfo(reg);
        showallinfo(reg);
        return;

    }

    // show all information of
    public static void showallistudent() {
        int i = 1;
        for (Student stu : stulist) {
            System.out.println("---------- " + i + "---------");
            System.out.println("Name : " + stu.name);
            System.out.println("Regd no : " + stu.regdno);
            System.out.println("Course  : " + stu.course);
            System.out.println("Gender  : " + stu.gender);
            System.out.println("Phone Number : " + stu.phon_no);
            i++;

        }
        System.out.println("Thank You ");
    }

    // create account
    public static void createaccount() {
        Scanner sc = new Scanner(System.in);

        StudentInformation stu = new StudentInformation();

        System.out.print("Enter your name : ");
        String name = sc.nextLine(); // name
        System.out.println("Write your gender : ");
        String gender = sc.nextLine(); // gender
        System.out.print("White your phone number : ");
        String phon = sc.nextLine();
        System.out.print("Write your course name (B-tech , MCA, MBA, BCA): ");
        String course = sc.nextLine();

        // System.out.println("Would you like to see our couse ( please write yes or
        // no):");
        // String cou= sc.nextLine(); // course select is panding
        // if(cou.toLowerCase() == "yes"){
        // System.out.println("Our courses are : B-tech,MCA,MBA,BCA");

        // }
        Student s1 = stu.new Student(name, gender, phon, course);
        System.out.println("Would you like to see your informtion .(1 for yes and 2 for no ) ");
        int see = sc.nextInt();
        if (see == 1) {
            System.out.print("Enter your registration number :  ");
            String regd = sc.nextLine();
            StringBuilder reg = new StringBuilder();
            reg.append(regd);
             showallinfo(reg);
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int stu = 0;
        int i = 1;
        while (stu != 1) {
            System.out.println("Are you a new student ? (1 for yes and 2 for no ) ");
            stu = sc.nextInt();
            if (stu == 1) {
                createaccount();
            } else if (stu == 2) {
                int see = 0;
                int j = 1;
                while (see != 1) {

                    System.out.println("Would you like to see your informtion .(1 for yes and 2 for no ) ");
                    see = sc.nextInt();

                    if (see == 1) {
                        System.out.print("Enter your registration number :  ");
                        String regd = sc.nextLine();
                        StringBuilder reg = new StringBuilder();
                        reg.append(regd);
                        showallinfo(reg);

                        System.out.print(
                                "Would you like to see all Student name , who has created account here . : (1 for yes and 2 for no ) ");
                        int allinfo = sc.nextInt();
                        if (allinfo == 1) {
                            showallistudent();
                        }
                    } else if (see == 2) {
                        System.out.print(
                                "Would you like to see all Student name , who has created account here . : (1 for yes and 2 for no ) ");
                        int allinfo = sc.nextInt();
                        if (allinfo == 1) {
                            showallistudent();
                        }
                        break;

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
                return;

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
