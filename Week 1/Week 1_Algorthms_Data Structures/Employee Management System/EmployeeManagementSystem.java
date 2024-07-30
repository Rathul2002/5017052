import java.util.Scanner;

class Employee{
    int employeeId;
    String name;
    String position;
    double salary;

    public Employee(int employeeId, String name, String position, double salary){
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    public int getEmployeeId(){
        return employeeId;
    }
    public String display() {
        return "EmployeeID: " + employeeId + ", Name: " + name + ", Position: " + position + ", Salary: " + salary;
    }
}


class EmployeeManagementSystem{
    private Employee[] employees;
    private int capacity;
    private int index=0;
    EmployeeManagementSystem(int capacity){
        this.capacity=capacity;
        employees=new Employee[capacity];
        index=0;
    }
    public void add(Employee obj){
        if(index==capacity){
            System.out.println("Array is full");
            return;
        }
        employees[index++]=obj;
    }
    public Employee search(int employeeId){
        for(int i=0;i<index;i++){
            if(employees[i].getEmployeeId()==employeeId)
                return employees[i];
        }
        return null;
    }
    public void traverse(){
        for(int i=0;i<index;i++){
            System.out.println(employees[i].display());
        }
    }
    public void delete(int employeeId){
        int ind=-1;
        for(int i=0;i<index;i++){
            if(employees[i].getEmployeeId()==employeeId)
                ind=i;
        }
        if(ind==-1)
        {
            System.out.println("Emplyee not there");
            return;
        }
        for(int i=ind+1;i<index;i++){
            employees[i-1]=employees[i];
        }
        employees[--index]=null;
    }
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an operation:\n1. Add \n2. Search \n3. Traverse \n4. Delete Employee \n5. To terminate the loop");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Enter Employee ID:");
                    int employeeId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Name:");
                    String name = sc.nextLine();
                    System.out.println("Enter Position:");
                    String position = sc.nextLine();
                    System.out.println("Enter Salary:");
                    double salary = sc.nextDouble();
                    ems.add(new Employee(employeeId, name, position, salary));
                    break;
                case 2:
                    System.out.println("Enter Employee ID to search:");
                    int searchId = sc.nextInt();
                    Employee employee = ems.search(searchId);
                    if (employee != null) 
                        System.out.println("Employee found" );
                    else
                        System.out.println("Employee not found.");
                    break;
                case 3:
                    ems.traverse();
                    break;

                case 4:
                    System.out.println("Enter Employee ID to delete:");
                    int deleteId = sc.nextInt();
                    ems.delete(deleteId);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
            if(ch==5)
                break;
        }
        sc.close();
    }
}