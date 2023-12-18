

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.Style;

public class ECommerce {

    ArrayList<String> stordaccount = new ArrayList<>();

    public class Item {
        String name;
        int price;

        public Item (String name1, int price1) {
            this.name = name1;
            this.price = price1;
        }
    }

    ArrayList<Item> itemlist = new ArrayList<>();

    public  void additem(String itemname, int price) {
        Item newitem = new Item(itemname, price);
        itemlist.add(newitem);
    }

    public class Createaccount {

        private String username;
        private String password;
        private StringBuilder userid = new StringBuilder();
        private String email;
        private int supercoin;
        private int giftcard;
        private int totalmoneyincart;
        ArrayList<Item> orderhistory = new ArrayList<>();
        ArrayList<Item> likeitem = new ArrayList<>();
        ArrayList<Item> cart = new ArrayList<>();

        // change user name
        public void changeusername(String newname) {
            this.username = newname;
            System.out.println("New name is : " + this.username);
        }

        // change password
        public void changepassword(String newpassword) {
            this.password = newpassword;
            System.out.println("Your password successfully updated. ");
            Scanner sc = new Scanner(System.in);
            System.out.println("Are you want you see you password ? ");
            String see = sc.nextLine();
            String opinion = see.toLowerCase();
            if (see == "yes") {
                System.out.println("Your password is  " + this.password);
            }
            return;
        }

        // show username 
        public void showusername(){
            System.out.println(this.username);
        }

        // show email 
        public void showemail(){
            System.out.println(this.email);
        }

        // show password 
        public void showpassword(){
            System.out.println(this.password);
        }

        // show like item 
        public void showlikeitem(){

            System.out.println("++++++++++++++  Add to cart item  +++++++++++++++++++");

            for(int i=0;i<likeitem.size();i++){
                System.out.println("-----------" + (i + 1) + "----------");
                System.out.println("item name : " + likeitem.get(i).name);
                System.out.println("item price : " + likeitem.get(i).price);
            }
        }

        // show add to cart item 

        public void showAddtocart(){

            System.out.println("++++++++++++++  Add to cart item  +++++++++++++++++++");

            Scanner sc =new Scanner(System.in);
            for(int i=0;i<cart.size();i++){
                System.out.println("-----------" + (i + 1) + "----------");
                System.out.println("item name : " + cart.get(i).name);
                System.out.println("item price : " + cart.get(i).price);

                // for save and like item
                System.out.print("If you want to save this item for feature profit then you can enter item no otherwise  enter zero : ");
                int saveno = sc.nextInt();
                if (saveno != 0) {
                    likeitem.add(itemlist.get(saveno - 1));
                }

                // for addtocart

                System.out.println("If you want to add at add to cart then please enter item no otherwise enter zero : ");
                int addtocartno = sc.nextInt();
                if (addtocartno ==1) {
                    cart.add(itemlist.get(addtocartno - 1));
                }
            }
        }

        // show all item 

        public void showallitem(){
            System.out.println("++++++++++++++  All item  +++++++++++++++++++");

            Scanner sc =new Scanner(System.in);
            for(int i=0;i<cart.size();i++){
                System.out.println("-----------" + (i + 1) + "----------");
                System.out.println("item name : " + cart.get(i).name);
                System.out.println("item price : " + cart.get(i).price);

                // for save and like item
                System.out.print("If you want to save this item for feature profit then you can enter item no otherwise  enter zero : ");
                int saveno = sc.nextInt(); 
                if (saveno == 1) {
                    likeitem.add(itemlist.get(saveno - 1));
                } 

                // for addtocart

                System.out.println("If you want to add at add to cart then please enter item no otherwise enter zero : ");
                int addtocartno = sc.nextInt();
                if (addtocartno ==1) {
                    cart.add(itemlist.get(addtocartno - 1));
                }
            }
        }

        // orderhistory

        public void showorderhistory() {

            Scanner sc = new Scanner(System.in);
            
            for (int i = 0; i < orderhistory.size(); i++) {
                System.out.println("sujit");
                System.out.println("-----------" + (i + 1) + "----------");
                System.out.println("item name : " + orderhistory.get(i).name);
                System.out.println("item price : " + orderhistory.get(i).price);

                // for save and like item
                System.out.print("If you want to save this item for feature profit then you can enter item no otherwise  enter zero : ");
                int saveno = sc.nextInt();
                if (saveno ==1) {
                    addinlikeitem(orderhistory.get(i));
                }

                // for addtocart

                System.out.println("If you want to add at add to cart then please enter 1 otherwise enter zero : ");
                int addtocartno = sc.nextInt();
                if (addtocartno ==1) {
                    addtocart(orderhistory.get(i));
                }
            }

            


            System.out.print("Are you want to order any product then enter 1 otherwise enter 0 : ");
            byte input=sc.nextByte();
            while (input == 1) {
                System.out.print("Enter item number  for  order that item  (if you don't want to order any product then input the 0 ) :  ");
                int itemno = sc.nextInt();

                if (itemno ==1) {
                    order(itemno);
                }

                System.out.print("Are you want to order any product then enter 1 otherwise enter 0 : ");
                input=sc.nextByte();


            }

            showallinfo();

            return;
        }

        // order
        public void order(int itemno) {
            orderhistory.add(itemlist.get(itemno - 1));
            System.out.println("Your product ordered succcessfully  and you got 10 supercoin ");
            this.supercoin+=10;
            Scanner sc = new Scanner(System.in);

            System.out.println("If you want to see your order history then enter 1 otherwise enter 0 ");
            int input = sc.nextInt();
            if (input == 1) {
                showorderhistory();
            }

            return;

        }


        // is include 
        public boolean isinclude(ArrayList<Item> checklist, Item item){
            for(int i=0;i<checklist.size();i++){
                if(checklist.get(i).name == item.name){
                    return false;
                }
            }

            return true;
        }

        // add like item 
        public void addinlikeitem(Item item){
            if(isinclude(likeitem, item)){  // if it gives false then , the item will not add in the like item list 
                likeitem.add(item);
            } else{
                System.out.println("This item already exsit in like item ");
            }
        }

        // add to cart 

        public void addtocart(Item item){
            if(isinclude(cart, item)){  // if it gives false then , the item will not add in the add to cart  list 
                cart.add(item);
                this.totalmoneyincart+=item.price;
            } else{
                System.out.println("This item already exsit in cart ");
            }
        }
        

        // oreder item
        public void oredritem() {

            Scanner sc = new Scanner(System.in);

            for (int i = 0; i < itemlist.size(); i++) {
                System.out.println("-------------" + (i + 1) + "-------------");
                System.out.println("item name : " + itemlist.get(i).name);
                System.out.println("item price : " + itemlist.get(i).price);

                // for save and like item
                System.out.print("If you want to save this item for feature profit then you can enter 1 otherwise  enter zero : ");
                int saveno = sc.nextInt();
                if (saveno == 1) {
                    addinlikeitem(itemlist.get(i));
                }

                // for addtocart

                System.out.println("If you want to add at add to cart then please enter 1 otherwise enter zero : ");
                int addtocartno = sc.nextInt();
                if (addtocartno == 1 ) {
                    addtocart(itemlist.get(i));
                }
            }

            System.out.print("Are you want to order any product then enter 1 otherwise enter 0 : ");
            byte input=sc.nextByte();
            while (input == 1) {
                
                System.out.print("Enter item number for oreder that item  (if you don't want to order any product then input the 0 ) :  ");
                int itemno = sc.nextInt();

                if (itemno != 0) {
                    order(itemno);
                }
                System.out.print("Are you want to order any product then enter 1 otherwise enter 0 : ");
                input=sc.nextByte();
                
                
            }

            showallinfo();



            return;
        }

        // showallinfo 
        private void showallinfo(){
            Scanner sc =new Scanner(System.in);
            System.out.println("for see the user information enter 1, for see the orderhistory enter 2 , for see the like item or save item enter 3 and for see add to cart to item enter 4 (If you don't want to see any of them then please enter 0 )");
            int enterno=sc.nextInt();
            if(enterno == 1){
                System.out.println("Name : " + this.username);
                System.out.println("UserId : " + this.userid);
                System.out.println("email : " + this.email);
                System.out.println("Supercoin : " + this.supercoin);

                showallinfo();

            } else if(enterno == 2 ){
                showorderhistory();
                showallinfo();
            } else if(enterno == 3 ){
                showlikeitem();
                showallinfo();
            } else if(enterno == 4 ){
                showAddtocart();
                showallinfo();
            }
            return;
        }

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

        // constructor function
        public  Createaccount(String username, String password, String email) {

            String emailpattern = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
            

            Pattern emailregexpat = Pattern.compile(emailpattern);

            if (emailregexpat.matcher(email).matches()) {

                this.username = username;
                this.password = password;
                this.userid = createid();
                this.email = email;
                this.supercoin = 0;
                this.giftcard = 0;
                this.totalmoneyincart = 0;
                System.out.println("Your account created and your id is  : " + this.userid);

            } else {
                System.out.println("Enter a valid email id ");
                return;
            }

        }

    }

    public static void main(String[] args) {
        ECommerce ecommerce= new ECommerce();

        

        ecommerce.additem("Iphone",23000);
        ecommerce.additem("sumsung", 47000);
        ecommerce.additem("dresss", 0);

        Createaccount su=ecommerce.new Createaccount("sujit panigrahi","Suj@_243","sujitpanigrahi855@gmail.com"); 
        su.oredritem();
       
       


    }
}
