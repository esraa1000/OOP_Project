import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SampleFileGenerator {

    public static void main(String[] args) {
        try {
            createSampleFile("customers.txt",
                    "john_doe,password123,1990-05-15,123 Main St\n" +
                            "jane_smith,pass456,1985-11-20,456 Elm St\n" +
                            "mike_brown,mike789,1992-07-30,789 Oak Ave\n"
            );

            createSampleFile("products.txt",
                    "Laptop,1200.99,1,10\n" +
                            "Mouse,25.50,2,100\n" +
                            "Keyboard,45.75,2,50\n" +
                            "Monitor,300.00,1,20\n"
            );

            createSampleFile("orders.txt",
                    "CreditCard,10.0,5.0,15.0,1\n" +
                            "PayPal,5.0,2.5,10.0,2\n" +
                            "DebitCard,20.0,8.0,20.0,3\n"
            );

            createSampleFile("categories.txt",
                    "Electronics,1,4\n" +
                            "Accessories,2,3\n" +
                            "Furniture,\n"
            );

            createSampleFile("carts.txt",
                    "1,1,2\n" +
                            "2,3\n" +
                            "3,4,1\n"
            );


            createSampleFile("admins.txt",
                    "admin1,password1,1985-10-15,Manager,40\n" +
                            "admin2,password2,1990-02-20,Developer,45\n" +
                            "admin3,password3,1980-07-30,HR,35\n"
            );

            System.out.println("Sample files created successfully!");

        } catch (IOException e) {
            System.out.println("Error creating sample files: " + e.getMessage());
        }
    }

    private static void createSampleFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        }
    }
}
