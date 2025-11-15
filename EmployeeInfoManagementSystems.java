import java.io.*; 
import java.util.*; 
 
public class EmployeeInfoManagementSystems { 
    static Scanner scanner = new Scanner(System.in); 
 
    public static void main(String[] args) { 
        try { 
            String userHome = System.getProperty("user.home"); 
            String dirPath = userHome + File.separator + "EmployeeInfoRecords"; 
            File directory = new File(dirPath); 
            if (!directory.exists()) directory.mkdirs(); 
 
            String filePath = dirPath + File.separator + "EmployeeInfo.txt"; 
            File file = new File(filePath); 
            FileWriter writer = new FileWriter(file, true);  
 
            System.out.println("Welcome to Employees Information Management System"); 
            String action = selectOption("What would you like to do?", new String[]{"View Previous Records", 
"Add New Employee Data"}); 
 
          while (action.equals("View Previous Records")) { 
    System.out.println("\nPrevious Information Records:\n"); 
    if (file.exists() && file.length() > 0) { 
        BufferedReader reader = new BufferedReader(new FileReader(file)); 
        String line; 
        while ((line = reader.readLine()) != null) { 
            System.out.println(line); 
        } 
        reader.close(); 
        System.out.println("\nFile location: " + filePath); 
        return; 
    } else { 
        System.out.println("No previous records found."); 
     
        action = selectOption("What would you like to do instead?", new String[]{"Add New Employee Data", "Exit"}); 
        if (action.equals("Exit")) return; 
        else break; 
    } 
} 
 
            boolean addMore = true; 
            int employeeNumber = 1; 
 
            while (addMore) { 
                System.out.println("\n--- Enter data for Employee #" + employeeNumber + " ---"); 
 
                String name = input("Name"); 
                String gender = selectOption("Gender", new String[]{"Male", "Female"}); 
                String employmentType = selectOption("Employment Type", new String[]{"Full-time", "Part-time"}); 
                String position = selectOption("Position", new String[]{"CEO", "COO", "CTO", "CISO", "Director", 
"Dept Head", "Other Employee"}); 
                String bankAccount = input("Bank Account Number"); 
 
                double basicSalary = inputDouble("Basic Salary"); 
                int workingDays = (int) inputDouble("Working Days (1-30)"); 
                double otherIncome = inputDouble("Other Income (bonus/commission)"); 
 
                String useAutoDate = selectOption("Use today's date?", new String[]{"Yes", "No"}); 
                String employmentDate = useAutoDate.equals("Yes") ? new Date().toString() : input("Enter Employment Date (e.g. 2024-01-01)"); 
 
                double earnedSalary = workingDays * (basicSalary / 30); 
                double positionAllowance = getPositionAllowance(position, basicSalary); 
                boolean isPositioned = !position.equalsIgnoreCase("Other Employee"); 
                double transportAllowance = isPositioned ? 2220 : 600; 
 
                double nonTaxablePosition = position.equalsIgnoreCase("CEO") ? positionAllowance : 0; 
                double taxablePosition = position.equalsIgnoreCase("CEO") ? 0 : positionAllowance; 
                double nonTaxableTransport = isPositioned ? 2220 : 0; 
                double taxableTransport = isPositioned ? 0 : 600; 
 
                double grossPay = earnedSalary + positionAllowance + transportAllowance + otherIncome; 
                double taxableIncome = earnedSalary + taxablePosition + taxableTransport + otherIncome; 
                double incomeTax = calculateIncomeTax(taxableIncome); 
 
                double employeePension = employmentType.equals("Full-time") ? basicSalary * 0.07 : 0; 
                double employerPension = employmentType.equals("Full-time") ? basicSalary * 0.11 : 0; 
                double totalDeduction = incomeTax + employeePension; 
                double netPay = grossPay - totalDeduction; 
 
                String result = "\n--- Employee Information Summary for " + name + " ---\n" + 
                        "Name: " + name + "\n" + 
                        "Gender: " + gender + "\n" + 
                        "Position: " + position + "\n" + 
                        "Employment Type: " + employmentType + "\n" + 
                        "Employment Date: " + employmentDate + "\n" + 
                        "Bank Account: " + bankAccount + "\n" + 
                        "Basic Salary: " + basicSalary + " ETB\n" + 
                        "Working Days: " + workingDays + "\n" + 
                        "Earned Salary: " + earnedSalary + " ETB\n" + 
                        "Position Allowance: " + positionAllowance + " ETB\n" + 
                        "  - Non-Taxable: " + nonTaxablePosition + " ETB\n" + 
                        "  - Taxable: " + taxablePosition + " ETB\n" + 
                        "Transport Allowance: " + transportAllowance + " ETB\n" + 
                        "  - Non-Taxable: " + nonTaxableTransport + " ETB\n" + 
                        "  - Taxable: " + taxableTransport + " ETB\n" + 
                        "Other Income: " + otherIncome + " ETB\n" + 
                        "Gross Pay: " + grossPay + " ETB\n" + 
                        "Taxable Income: " + taxableIncome + " ETB\n" + 
                        "Income Tax: " + incomeTax + " ETB\n" + 
                        "Employee Pension: " + employeePension + " ETB\n" + 
                        "Employer Pension: " + employerPension + " ETB\n" + 
                        "Total Deductions: " + totalDeduction + " ETB\n" + 
                        "Net Payment: " + netPay + " ETB\n"; 
 
                System.out.println(result); 
                writer.write(result); 
                writer.flush(); 
 
                System.out.print("\nDo you want to enter another employee? (Yes/No): "); 
                String again = scanner.nextLine().trim().toLowerCase(); 
                if (again.startsWith("n")) { 
                    addMore = false; 
                } else { 
                    employeeNumber++; 
                } 
            } 
 
            writer.close(); 
            System.out.println("\nAll employees information saved."); 
            System.out.println("File location: " + filePath); 
 
        } catch (IOException e) { 
            System.out.println("Error: " + e.getMessage()); 
        } 
    } 
 
    static String input(String prompt) { 
        System.out.print(prompt + ": "); 
        return scanner.nextLine(); 
    } 
 
    static double inputDouble(String prompt) { 
        while (true) { 
            try { 
                System.out.print(prompt + ": "); 
                return Double.parseDouble(scanner.nextLine()); 
            } catch (NumberFormatException e) { 
                System.out.println("Invalid number. Try again."); 
            } 
        } 
    } 
 
    static String selectOption(String prompt, String[] options) { 
        while (true) { 
            System.out.println(prompt + ":"); 
            for (int i = 0; i < options.length; i++) { 
                System.out.println("  " + (i + 1) + ". " + options[i]); 
            } 
            System.out.print("Choose [1-" + options.length + "]: "); 
            try { 
                int choice = Integer.parseInt(scanner.nextLine()); 
                if (choice >= 1 && choice <= options.length) { 
                    return options[choice - 1]; 
                } 
            } catch (Exception ignored) {} 
            System.out.println("Invalid input. Try again."); 
        } 
    } 
 
    static double getPositionAllowance(String position, double salary) { 
        switch (position.toUpperCase()) { 
            case "CEO": return salary * 0.10; 
            case "COO": 
            case "CTO": 
            case "CISO": 
            case "DIRECTOR": 
            case "DEPT HEAD": 
            default: return 0; 
        } 
    } 
 
static double calculateIncomeTax(double income) { 
if (income <= 600) return 0; 
else if (income <= 1650) return income * 0.10 - 60; 
else if (income <= 3200) return income * 0.15 - 142.5; 
else if (income <= 5250) return income * 0.20 - 302.5; 
else if (income <= 7800) return income * 0.25 - 565; 
else if (income <= 10900) return income * 0.30 - 965; 
else return income * 0.35 - 1500; 
} 
} 