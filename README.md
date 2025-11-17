#  Employee Information Management System

![Language](https://img.shields.io/badge/language-java-blue)
![Platform](https://img.shields.io/badge/platform-Console-green)
![Status](https://img.shields.io/badge/status-Active-success)

*A Java Console Application with Payroll, Tax & Pension Calculation*

This is a **console-based employee payroll and information management system** written in Java.
The program calculates salary details (tax, allowances, pension, deductions, net pay) and stores records in a text file inside the user's home directory.

---

##  Features

### 🔹 **Add New Employee**

Collects and processes:

* Employee ID
* Full Name
* Gender
* Employment Type
* Position
* Basic Salary
* Days Worked
* Other Income

Automatically calculates:

* Earned Salary
* Position Allowance
* Transport Allowance
* Gross Pay
* Taxable Income
* Income Tax
* Pension (Employee & Employer)
* Total Deductions
* Net Pay

All records are saved to:

```
~/EmployeeInfoRecords/EmployeeInfo.txt
```

---

### 🔹 **Display All Employees**

Reads and prints **all employee entries** stored in the text file.

---

### 🔹 **Search Employee by ID**

Finds and displays specific employee information based on the ID.

---

### 🔹 **Delete Employee**

Removes a record from the file by rewriting it without that employee.

---

### 🔹 **Exit**

Cleanly closes the program.

---

## Project Structure

```
EmployeeInformationManagementSystem/
│── EmployeeInfoManagementSystems.java
│── README.md
└── EmployeeInfoRecords/
    └── EmployeeInfo.txt   (auto-created on first run)
```

---

## Payroll Logic (Built-in)

### ✔ Position Allowance

| Position                                | Allowance     |
| --------------------------------------- | ------------- |
| CEO                                     | 10% of salary |
| COO / CTO / CISO / Director / Dept Head | 0%            |
| Other Employees                         | 0%            |

### ✔ Transport Allowance

* CEO & management → **2220 (non-taxable)**
* Others → **600 (taxable)**

### ✔ Ethiopian Income Tax

```
0–600        = 0
601–1650     = 10%  - 60
1651–3200    = 15%  - 142.5
3201–5250    = 20%  - 302.5
5251–7800    = 25%  - 565
7801–10900   = 30%  - 965
10901+       = 35%  - 1500
```

### ✔ Pension (Full-Time Only)

* Employee: **7%** of basic salary
* Employer: **11%** of basic salary

---

## How to Run

### 1️⃣ Compile:

```bash
javac EmployeeInfoManagementSystems.java
```

### 2️⃣ Run:

```bash
java EmployeeInfoManagementSystems
```

The program will automatically create:

```
~/EmployeeInfoRecords/EmployeeInfo.txt
```

---

## Requirements

* Java JDK 8 or higher
* Terminal / CMD

---

## Future Improvements

* Update employee info
* Input validation
* Export to PDF/Excel
* GUI version (JavaFX/Swing)
* Database support (MySQL/SQLite)

---

## Contributing

Feel free to fork the repo and improve the project.

---

## License

This project is open-source. You may modify and use it freely.
