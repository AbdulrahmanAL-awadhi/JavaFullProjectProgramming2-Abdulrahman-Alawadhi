import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Department implements Serializable {
    private int id;
    private String name;

    private FullTimeTeacher dean;
    File departmentFile = new File("departments.txt");
    File teacherFile = new File("teachers.txt");
    File staffFile = new File("staff.txt");
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
   public void initfiles() throws IOException {

       ArrayList<Department> departments=new ArrayList<>();
       ArrayList<Staff> staff=new ArrayList<>();
       ArrayList<Teacher> teachers=new ArrayList<>();
       departments.add(new Department(56,"cs"));
       staff.add(new Staff(34,"h","i","678","tyujk","fgh",8));
       teachers.add(new Teacher(34,"g","fds","fd","eds","gfds","fdsa"));
       ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(teacherFile));
       oos.writeObject(teachers);
       oos.close();
       ObjectOutputStream oos1=new ObjectOutputStream(new FileOutputStream(departmentFile));
       oos1.writeObject(departments);
       oos1.close();
       ObjectOutputStream oos2=new ObjectOutputStream(new FileOutputStream(staffFile));
       oos2.writeObject(staff);
       oos2.close();

    }
    public FullTimeTeacher getDean() {
        return dean;
    }

    public void setDean(FullTimeTeacher dean) {
        this.dean = dean;
    }
    public List<Teacher> getTeachers() throws IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(teacherFile));
        List<Teacher> teachers=(ArrayList)ois.readObject();
        ois.close();
        return teachers;
    }
    public List<Staff> getStaff() throws IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(staffFile));
        List<Staff> staff=(ArrayList)ois.readObject();
        ois.close();
        return staff;
    }

    public void addTeacher(Teacher teacher) throws TeacherAlreadyInDepartmentException, IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(teacherFile));
        ArrayList<Teacher> teachers=(ArrayList)ois.readObject();
        ois.close();
        if (teachers.contains(teacher)) {
            throw new TeacherAlreadyInDepartmentException();
        }
        teachers.add(teacher);
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(teacherFile));
        oos.writeObject(teachers);
        oos.close();
    }
    public void addDepartment(Department department) throws DepartmentAlreadyExistsException, IOException, ClassNotFoundException, DepartmentAlreadyExistsException {
        ArrayList<Department> departments=new ArrayList<>();
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(departmentFile));
        departments=(ArrayList)ois.readObject();
        ois.close();
        if (departments.contains(department)) {
            throw new DepartmentAlreadyExistsException();
        }
        departments.add(department);
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(departmentFile));
        oos.writeObject(departments);
        oos.close();
    }
    public void removeDepartment(int departmentId) throws DepartmentNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(departmentFile));
        ArrayList<Department> departments=(ArrayList)ois.readObject();
        ois.close();
        for (Department department : departments) {
            if (department.getId() == departmentId) {
                departments.remove(department);
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(departmentFile));
                oos.writeObject(departments);
                oos.close();
                return;
            }
        }
        throw new DepartmentNotFoundException();
    }
    public void removeTeacher(int teacherId) throws TeacherNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(teacherFile));
        ArrayList<Teacher> teachers=(ArrayList)ois.readObject();
        ois.close();
        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                teachers.remove(teacher);
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(teacherFile));
                oos.writeObject(teachers);
                oos.close();
                return;
            }
        }
        throw new TeacherNotFoundException();
    }

    public void addStaff(Staff staffMember) throws StaffAlreadyInDepartmentException, IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(staffFile));
        ArrayList<Staff> staff=(ArrayList)ois.readObject();
        ois.close();
        if (staff.contains(staffMember)) {
            throw new StaffAlreadyInDepartmentException();
        }
        staff.add(staffMember);
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(staffFile));
        oos.writeObject(staff);
        oos.close();
    }

    public void removeStaff(int staffId) throws StaffNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(staffFile));
        ArrayList<Staff> staff=(ArrayList)ois.readObject();
        ois.close();
        for (Staff staffMember : staff) {
            if (staffMember.getId() == staffId) {
                staff.remove(staffMember);
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(staffFile));
                oos.writeObject(staff);
                oos.close();
                return;
            }
        }
        throw new StaffNotFoundException();
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dean=" + dean +
                '}';
    }
}