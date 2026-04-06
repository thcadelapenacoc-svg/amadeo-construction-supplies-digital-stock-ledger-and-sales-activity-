import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class Amadeo_Tracker_and_Logger
{
    private static HashMap <String, String>            User_Data            = new HashMap  <>();
    private static HashMap <String, String>            Admin_Data           = new HashMap  <>();
    private static HashMap <String, Integer>           Inventory_Quantities = new HashMap  <>();
    private static HashMap <String, Double>            Inventory_Prices     = new HashMap  <>();
    private static HashMap <String, String>            Inventory_Suppliers  = new HashMap  <>();
    private static ArrayList<HashMap <String, String>> Transaction_Data     = new ArrayList<>();
    private static HashMap <String, String>            Record_Transaction   = new HashMap  <>();
    
    
    private static  Scanner scanner = new Scanner(System.in);








    // ========== ADMIN FUNCTIONS ==========

    //Admin Menu
    public static void Admin_Menu()
    {
        String Admin_Choice;
        String Update_Stocks;
        String Update_Quantity;

        do
        {
            System.out.println("\n======== Welcome to Admin Menu ========\n1. Add/Remove Stocks \n2. Increase/Decrease Quantity \n3. View Transactions \n4. Back to Main Menu\n ");
            System.out.print("\nEnter ===> : ");
        
            
            Admin_Choice = scanner.nextLine();


            switch (Admin_Choice) 
            {
                case "1":
                    while (true)
                    {
                        System.out.print("\n============= Add/Remove Stocks =============\n1. Add Item\n2. Remove Item\n3. Back to Admin Menu\nEnter ===> : ");
                        Update_Stocks = scanner.nextLine();

                        if (Update_Stocks.equals("1"))
                        {
                            Add_Item();
                        }
                        else if (Update_Stocks.equals("2"))
                        {
                            Remove_Item();
                        }
                        else if (Update_Stocks.equals("3"))
                        {
                            break;
                        }
                        else
                        {
                            System.out.println("\n----------- Invalid Input -----------");
                        }
                    }
                    break;
                case "2":
                    while (true) 
                    {
                        System.out.print("\n============= Increase/Decrease Stock Quantity =============\n1. Increase Quantity\n2. Decrease Quantity\n3. Back to Admin Menu\nEnter ===> : ");
                        Update_Quantity = scanner.nextLine();

                        if (Update_Quantity.equals("1"))
                        {
                            Increase_Quantity();
                        }
                        else if (Update_Quantity.equals("2"))
                        {
                            Decrease_Quantity();
                        }
                        else if (Update_Quantity.equals("3"))
                        {
                            break;
                        }
                        else 
                        {
                            System.out.println("\n----------- Invalid Input -----------");
                        }
                    }
                    break;
                case "3":
                    View_Transactions();
                    break;
                case "4":
                    System.out.println("\n------- Logged out Successfully -------\n");
                    return;
                default:
                    System.out.println("\n----------- Invalid Input -----------");
            }
        }while (true);
    }


    // Add Stock to Inventory
    public static void Add_Item()
    {
        String New_Supplier;
        String New_Stock_Name;
        String New_Stock_Qty;
        String New_Stock_Price;
        

        // Add Supplier
        do {
            System.out.println("\n============= Add Item (1/4 Add Supplier) =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter Supplier    : ");
            New_Supplier = scanner.nextLine();
        
            if (New_Supplier.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (New_Supplier.isEmpty() || !New_Supplier.matches("^[a-zA-Z\\s]+$"))
            {
                System.out.println("\nInvalid Input, Supplier cannot be empty or contain numbers");
            }
            else if (Inventory_Suppliers.containsKey(New_Supplier.toLowerCase()))
            {
                System.out.println("\nInvalid Input, Supplier already exists");
            }
            else
            {
                break;
            }
        } 
        while (true);
        
        
        // Add Stock/Item Name
        do {
            System.out.println("\n============= Add Item (2/4 Add Stock Name) =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter New Stock Name   : ");
            New_Stock_Name = scanner.nextLine();
        
            if (New_Stock_Name.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (New_Stock_Name.isEmpty() || !New_Stock_Name.matches("^[a-zA-Z\\s]+$"))
            {
                System.out.println("\nInvalid Input, Stock Name cannot be empty or contain numbers");
            }
            else if (Inventory_Suppliers.containsKey(New_Stock_Name.toLowerCase()) && Inventory_Suppliers.containsValue(New_Supplier.toLowerCase()))
            {
                System.out.println("\nInvalid Input, Stock already exist");
            }
            else
            {
                break;
            }
        } 
        while (true);
        
        
        // Add Stock Quantity
        do {
            System.out.println("\n============= Add Item (3/4 Add Quantity) =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter Item Quantity: ");
            New_Stock_Qty = scanner.nextLine();
        
            if (New_Stock_Qty.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (New_Stock_Qty.isEmpty() || !New_Stock_Qty.matches("^[0-9]+$") || New_Stock_Qty.length() > 5 || Integer.valueOf(New_Stock_Qty) <= 0)
            {
                System.out.println("\nInvalid Quantity Format");
            }
            else
            {
                break;
            }
        } 
        while (true);
        

        // Add Stock Price
        do 
        {
            System.out.println("\n============= Add Item (4/4 Add Price) =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter Item Price   : ");
            New_Stock_Price = scanner.nextLine();
        
            if (New_Stock_Price.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (New_Stock_Price.isEmpty() || !New_Stock_Price.matches("^[0-9]+(\\.[0-9]{1,2})?$"))
            {
                System.out.println("\nInvalid Price Format");
            }
            else if (Double.valueOf(New_Stock_Price) <= 0)
            {
                System.out.println("\nInvalid Input, Price must be greater than 0");
            }
            else
            {
                break;
            }
        } 
        while (true);


        // Confirm Add Stock/Item to Inventory
        System.out.print("\nAdd Stock/Item to Inventory?\n1. [Y] Yes | [Any Other Key] No\n\nEnter ===> : ");
        String Choice = scanner.nextLine();

        if (Choice.equalsIgnoreCase("y"))
        {
            // Item Added to Inventory
            Inventory_Quantities.put(New_Stock_Name.toLowerCase(), Integer.valueOf(New_Stock_Qty));
            Inventory_Prices    .put(New_Stock_Name.toLowerCase(), Double.valueOf(New_Stock_Price));
            Inventory_Suppliers .put(New_Stock_Name.toLowerCase(), New_Supplier.toLowerCase());
            
            LocalDateTime Get_DateTime = LocalDateTime.now();
            String Current_DateTime = Get_DateTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a"));
            
            System.out.println("\nDate Added: " + Current_DateTime);

            System.out.println("\n------ Item Added Successfully ------\n");
        }
        else
        {
            System.out.println("\n------ Canceled Successfully ------\n");
            return;
        }
    }
        


    // Remove Stock from Inventory
    public static void Remove_Item()
    {
        String Remove_Item_Name;


        do 
        {
            System.out.println("\n============= Remove Stock =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter Item to Remove: ");
            Remove_Item_Name = scanner.nextLine();
        
            if (Remove_Item_Name.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (Remove_Item_Name.isEmpty() || !Remove_Item_Name.matches("^[a-zA-Z\\s]+$"))
            {
                System.out.println("\nInvalid Input, Item name cannot be empty or contain numbers");
            }
            else if (!Inventory_Quantities.containsKey(Remove_Item_Name.toLowerCase()))
            {
                System.out.println("\n----------- Item Not Found -----------\n");
            }
            else
            {
            
                // Remove Item from Inventory?
                System.out.print("Remove Item?\n[Y] Yes | [Any Key] No\nEnter ===> : ");
                String Choice = scanner.nextLine();

                if (Choice.equalsIgnoreCase("y"))
                {

                    Inventory_Quantities.remove(Remove_Item_Name.toLowerCase());
                    Inventory_Prices    .remove(Remove_Item_Name.toLowerCase());
                    Inventory_Suppliers .remove(Remove_Item_Name.toLowerCase());

                    System.out.println("\n------ Item Removed Successfully ------\n");
                }
                else
                {
                    System.out.println("\n------ Canceled Successfully ------\n");
                    return;
                }
            }
        }
        while (true); 
    }



    // Increase Quantity to Existing Stock in Inventory
    public static void  Increase_Quantity()
    {
        String Increase_Item_Name;
        String Increase_Item_Qty;
        String Current_Quantity;
        
        // Add Item Name to Increase Quantity
        do 
        {
            System.out.println("\n============= Increase Quantity (1/2 Enter Item Name) =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter Item to Increase: ");
            Increase_Item_Name = scanner.nextLine();
            

            // Check if Item Exists in Inventory
            if (Increase_Item_Name.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (Increase_Item_Name.isEmpty() || !Increase_Item_Name.matches("^[a-zA-Z\\s]+$"))
            {
                System.out.println("\nInvalid Input, Item name cannot be empty or contain numbers");
            }
            else if (!Inventory_Quantities.containsKey(Increase_Item_Name.toLowerCase()))
            {
                System.out.println("\n----------- Item Not Found -----------\n");
            }
            else
            {
                break;
            }
        }
        while (true);


        // Get Current Quantity of Item in Inventory
        Current_Quantity = String.valueOf(Inventory_Quantities.get(Increase_Item_Name.toLowerCase()));


        // Increase Quantity of Item
        do 
        {
            System.out.println("\n============= Increase Quantity (2/2 Enter Quantity) =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter Quantity: ");
            Increase_Item_Qty = scanner.nextLine();
            

            // Quantity Validation
            if (Increase_Item_Qty.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (Increase_Item_Qty.isEmpty() || !Increase_Item_Qty.matches("^[0-9]+$") || Integer.valueOf(Increase_Item_Qty) <= 0)
            {
                System.out.println("\n----------- Invalid Quantity -----------\n");
            }
            else
            {
                // Quantity Added to Inventory
                Inventory_Quantities.put(Increase_Item_Name.toLowerCase(), Integer.valueOf(Current_Quantity) + Integer.valueOf(Increase_Item_Qty));

                System.out.println("\n---- Quantity Increased Successfully ---\n");
                return;
            }
        }
        while (true);
    }



    // Decrease Quantity to Existing Stock in Inventory
    public static void Decrease_Quantity()
    {
        String Decrease_Item_Name;
        String Decrease_Item_Qty;
        String Current_Quantity;
        
        // Add Item Name to Decrease Quantity
        do 
        {
            System.out.println("\n============= Decrease Quantity (1/2 Enter Item Name to Decrease) =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter Item to Decrease: ");
            Decrease_Item_Name = scanner.nextLine();
            

            // Check if Item Exists in Inventory
            if (Decrease_Item_Name.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (Decrease_Item_Name.isEmpty() || !Decrease_Item_Name.matches("^[a-zA-Z\\s]+$"))
            {
                System.out.println("\nInvalid Input, Item name cannot be empty or contain numbers");
            }
            else if (!Inventory_Quantities.containsKey(Decrease_Item_Name.toLowerCase()))
            {
                System.out.println("\n----------- Item Not Found -----------\n");
            }
            else if (Inventory_Quantities.containsKey(Decrease_Item_Name.toLowerCase()) && Inventory_Quantities.get(Decrease_Item_Name.toLowerCase()).equals(0))
            {
                System.out.println("\n------------ Item Out of Stock ------------\n");
            }
            else
            {
                break;
            }
        }
        while (true);


        Current_Quantity = String.valueOf(Inventory_Quantities.get(Decrease_Item_Name.toLowerCase()));

        // Decrease Quantity of Item
        do 
        {
            System.out.println("\n============= Decrease Quantity (2/2 Enter Quantity) =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter Quantity: ");
            Decrease_Item_Qty = scanner.nextLine();
            

            // Quantity Validation
            if (Decrease_Item_Qty.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (Decrease_Item_Qty.isEmpty() || !Decrease_Item_Qty.matches("^[0-9]+$") || Integer.valueOf(Decrease_Item_Qty) <= 0 || Integer.valueOf(Decrease_Item_Qty) > Integer.valueOf(Current_Quantity))
            {
                System.out.println("\n----------- Invalid Quantity -----------\n");
            }
            else
            {
                // Quantity Reduced from Inventory
                Inventory_Quantities.put(Decrease_Item_Name.toLowerCase(), Integer.valueOf(Current_Quantity) - Integer.valueOf(Decrease_Item_Qty));

                System.out.println("\n---- Quantity Reduced Successfully ---\n");
                return;
            }
        }
        while (true);
    }



    // View Transaction Record
    public static void View_Transactions()
    {
        System.out.println("\n========= Transaction Record =========\n");
        

        // Check if No Transactions Recorded
        if (Transaction_Data.isEmpty())
        {
            System.out.println("\n------ No Transactions Recorded ------\n");
            return;
        }
        else
        {
            for (HashMap<String, String> Transaction : Transaction_Data)
            {
                System.out.println
                (
                           "Product Name    : " + Transaction.get("Product Name") + 
                    "\n" + "Product Quantity: " + Transaction.get("Product Quantity") + 
                    "\n" + "Total Price     : " + Transaction.get("Total Price") + 
                    "\n" + "Date and Time   : " + Transaction.get("datetime") + 
                    "\n" + "------------------------------------------"
                );
            }
        }
        
        System.out.println("\n======================================\n");
    }
    // ========== END OF ADMIN FUNCTIONS ==========
    




    // STAFF FUNCTIONS

    // Staff Menu
    public static void Staff_Menu()
    {
        String Staff_Choice;
        
        
        do
        {
            System.out.println("\n========= Welcome to Staff Menu =========\n1. View Inventory \n2. Add Sale \n3. Back to Main Menu");
            System.out.print("\nEnter ===> : ");
            
            Staff_Choice = scanner.nextLine();

            switch (Staff_Choice) 
            {
                case "1":
                    View_Inventory();
                    break;
                case "2":
                    Add_Sale();
                    break;
                case "3":
                    System.out.println("\n------- Logged out Successfully -------\n");
                    return;
                default:
                    System.out.println("\n----------- Invalid Input -----------\n");
            }

        }while (true);
    }
    


    // View Inventory
    static void View_Inventory()
    {
        System.out.println("\n============= Inventory =============\n");
        

        // Check if No Items in Inventory
        if (Inventory_Quantities.isEmpty())
        {
            System.out.println("\n------ No Items in Inventory ------\n");
            return;
        }
        else
        {
            for (String Item_Name : Inventory_Quantities.keySet())
            {
                System.out.println("Item Name    : " + Item_Name + "\n" + 
                                   "Item Quantity: " + Inventory_Quantities.get(Item_Name) + "\n" + 
                                   "Item Price   : P" + Inventory_Prices.get(Item_Name) + "\n" + "-----------------------------");
            }
        }
    }
    


    // Add Sale and Record Transaction
    public static void Add_Sale()
    {
        String Product_Name;
        String Product_Quantity;
        Double Total_Price;
        String Record_Sale;
        String Current_DateTime;

        // Add Product Name
        do 
        {
            System.out.println("\n============= Add Sale (1/2 Enter Product Name) =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter Product Name: ");
            Product_Name= scanner.nextLine();
            

            // Check if Item Exists in Inventory
            if (Product_Name.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (Product_Name.isEmpty() || !Product_Name.matches("^[a-zA-Z\\s]+$"))
            {
                System.out.println("\nInvalid Input, Item name cannot be empty or contain numbers");
            }
            else if (!Inventory_Quantities.containsKey(Product_Name.toLowerCase()))
            {
                System.out.println("\n----------- Item Not Found -----------\n");
            }
            else if (Inventory_Quantities.get(Product_Name.toLowerCase()) <= 0)
            {
                System.out.println("\n------------ Out of Stock ------------\n");
            }
            else
            {
                System.out.println("\n~ Product Information ~" + "\n" + 
                                    "Product Name: " + Product_Name.toLowerCase() + "\n" + 
                                    "Price       : " + Inventory_Prices.get(Product_Name.toLowerCase()) + "\n");
                break;
            }
        }
        while (true);


        // Add Product Quantity
        do 
        {
            System.out.println("\n============= Add Sale (2/2 Enter Product Quantity) =============\n\n[b] to Go back to Admin Menu\n");
            System.out.print("Enter Product Quantity: ");
            Product_Quantity= scanner.nextLine();
            

            // Quantity Validation
            if (Product_Quantity.equalsIgnoreCase("b"))
            {
                return;
            }
            else if (Product_Quantity.isEmpty() || !Product_Quantity.matches("^[0-9]+$"))
            {
                System.out.println("\nInvalid Input, Quantity cannot be empty or contain letters");
            }
            else if (Integer.valueOf(Product_Quantity) > Inventory_Quantities.get(Product_Name.toLowerCase()) || Integer.valueOf(Product_Quantity) <= 0)
            {
                System.out.println("\n--------- Invalid Quantity ---------\n");
            }
            else if (Inventory_Quantities.get(Product_Name.toLowerCase()) <= 0)
            {
                System.out.println("\n------------ Out of Stock ------------\n");
            }
            else
            {
                
                break;
            }
        }
        while (true);


        Total_Price = Inventory_Prices.get(Product_Name.toLowerCase()) * Integer.valueOf(Product_Quantity);

        LocalDateTime Get_DateTime = LocalDateTime.now();
        Current_DateTime = Get_DateTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss a"));
        

        // Print Receipt
        System.out.println("\n-----------------------------");
        System.out.println("\n~ Amadeo Construction Supplies ~" + "\n\n" +
                           "Product Name    : " + Product_Name.toLowerCase() + "\n" + 
                           "Product Price   : " + Inventory_Prices.get(Product_Name.toLowerCase()) + "\n" + 
                           "Product Quantity: " + Product_Quantity + "\n" + 
                           "Total Price     : " + Total_Price + "\n" + 
                           "Date and Time   : " + Current_DateTime + "\n");
        System.out.println("-----------------------------");


        
        // Record Transaction and Update Inventory?
        while (true)
        {
            System.out.print("\nAdd Sale?\n1. [Y] Yes | [Any Key] No\n\nEnter ===> : ");
            Record_Sale = scanner.nextLine();

            if (Record_Sale.equalsIgnoreCase("y"))
            {
                // Item Recorded to Transaction Record and Inventory Updated
                Record_Transaction = new HashMap<>();
                Record_Transaction.put("Product Name", Product_Name.toLowerCase());
                Record_Transaction.put("Product Quantity", String.valueOf(Product_Quantity));
                Record_Transaction.put("Total Price", String.valueOf(Total_Price));
                Record_Transaction.put("datetime", Current_DateTime);
                Transaction_Data  .add(Record_Transaction);

                Inventory_Quantities.put(Product_Name.toLowerCase(), Inventory_Quantities.get(Product_Name.toLowerCase()) - Integer.valueOf(Product_Quantity));

                System.out.println("\n----- Sale Recorded Successfully -----");
                break;

            }
            else
            {
                System.out.println("\n------ Canceled Successfully ------\n");
                return;
            }
        }
    }
    // ========== END OF STAFF FUNCTIONS ==========
    




    // ========== MAIN FUNCTION ==========
    public static void main(String[] args)
    {
        User_Data .put("User1", "321");
        Admin_Data.put("Admin1", "123");
        
        
        String User_username;
        String User_password;

        String Admin_username;
        String Admin_password;
        
        String Main_Choice;
        
        // MAIN MENU
        do
        {
            System.out.println("\n===== Amadeo Construction Supplies Digital Stock Ledger and Sales Activity Logger =====\n1. Enter as Admin \n2. Enter as Staff \n3. Exit");
            System.out.print("\nEnter ===> : ");


            Main_Choice = scanner.nextLine();
            

            switch (Main_Choice) 
            {
                case "1":
                    System.out.print("Enter Admin Username: ");
                    Admin_username = scanner.nextLine();

                    System.out.print("Enter Admin Password: ");
                    Admin_password = scanner.nextLine();
                    
                    if (Admin_Data.containsKey(Admin_username) && Admin_Data.get(Admin_username).equalsIgnoreCase(Admin_password))
                    {
                        Admin_Menu();
                    }
                    else
                    {
                        System.out.println("\n-----Invalid Username or Password-----\n");
                    }
                    break;
                case "2":
                    System.out.print("Enter Staff Username: ");
                    User_username = scanner.nextLine();

                    System.out.print("Enter Staff Password: ");
                    User_password = scanner.nextLine();

                    if (User_Data.containsKey(User_username) && User_Data.get(User_username).equalsIgnoreCase(User_password))
                    {
                        Staff_Menu();
                    }
                    else
                    {
                        System.out.println("\n-----Invalid Username or Password-----\n");
                    }
                    break;
                case "3":
                    System.out.println("\n----------Exit Successfully----------\n");
                    return;
                default:
                    System.out.println("\n------------Invalid Input------------\n");
            }

        }while (true);
    }
}  