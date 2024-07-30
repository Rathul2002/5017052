//Model Class
class Student{
    private String name;
    private int id;
    private String grade;

    public Student(String name, int id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public String getGrade() {
        return grade;
    }
}

//View Class
class StudentView{
    public void displayStudentDetails(String name, int id, String grade){
        System.out.println("Student Details:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Grade: " + grade);
    }
}

// Controller Class
class StudentController{
    private Student model;
    private StudentView view;
    StudentController(Student model, StudentView view){
        this.model = model;
        this.view = view;
    }
    public void setStudentName(String studentName){
        model.setName(studentName);
    }
    public void setStudentID(int studentID){
        model.setId(studentID);
    }
    public void setStudentGrade(String studentGrade){
        model.setGrade(studentGrade);
    }
    public String getStudentName(){
        return model.getName();
    }
    public int getStudentID(){
        return model.getId();
    }
    public String getStudentGrade(){
        return model.getGrade();
    }
    public void updateView(){
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}

class Main{
    public static void main(String[] args) {
        Student s1 = new Student("Rahul Sen",1001,"A");
        StudentView v = new StudentView();
        StudentController c = new StudentController(s1, v);
        c.updateView();

        c.setStudentName("Mohit Kaur");
        c.setStudentID(1234);
        c.setStudentGrade("B");
        c.updateView();
    }
}