import java.util.Scanner;

class Task{
    int taskId;
    String taskName;
    String status;
    Task(int taskId,String taskName, String status){
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }
    public String display() {
        return "TaskID: "+taskId+", TaskName: "+taskName+", Status: "+status;
    }
}

class Node{
    Task task;
    Node next;
    Node(Task task){
        this.task=task;
        this.next=null;
    }
}

public class TaskManagement {
    Node head;
    public void add(Task task){
        Node newNode= new Node(task);
        if(head==null)
        {
            head=newNode;
            return;
        }
        Node current = head;
        while (current.next!=null){
            current=current.next;            
        }
        current.next=newNode;
    }

    public Task search(int taskId){
        Node current=head;
        while(current!=null){
            if(current.task.taskId==taskId)
                return current.task;
            current=current.next;
        }
        return null;
    }

    public void traverse(){
        Node current=head;
        while (current!=null) {
            System.out.println(current.task.display());
            current=current.next;
        }
    }

    public void delete(int taskId){
        if(head==null){
            System.out.println("Task list is empty.");
            return;
        }
        if(head.task.taskId==taskId){
            head=head.next;
            return;
        }
        Node current=head;
        while(current.next!=null){
            if(current.next.task.taskId==taskId)
                break;
            current=current.next;
        }
        if(current.next==null)
            System.out.println("Task not found");
        else
            current.next=current.next.next;
    }
    public static void main(String[] args) {
        TaskManagement taskManager = new TaskManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an operation:\n1. Add \n2. Search \n3. Traverse \n4. Delete Employee \n5. To terminate the loop");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("Enter Task ID:");
                    int taskId=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Task Name:");
                    String taskName=sc.nextLine();
                    System.out.println("Enter Status:");
                    String status=sc.nextLine();
                    taskManager.add(new Task(taskId, taskName, status));
                    break;

                case 2:
                    System.out.println("Enter Task ID to search:");
                    int searchId=sc.nextInt();
                    Task task=taskManager.search(searchId);
                    if (task!=null)
                        System.out.println("Task found");
                    else
                        System.out.println("Task not found");
                    break;

                case 3:
                    taskManager.traverse();
                    break;

                case 4:
                    System.out.println("Enter Task ID to delete:");
                    int deleteId=sc.nextInt();
                    taskManager.delete(deleteId);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
            if(ch==5)
                break;
        }
        sc.close();
    }
}