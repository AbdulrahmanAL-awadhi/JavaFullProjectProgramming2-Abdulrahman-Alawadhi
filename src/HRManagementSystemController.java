import java.io.*;
import java.util.*;

public class HRManagementSystemController implements PayRoll {
    private ArrayList<Department> departments;
    private Map<Integer, Teacher> teachers;
    private Map<Integer, Staff> staffMembers;
    Department department;


    public HRManagementSystemController() throws StaffAlreadyInDepartmentException {

        departments=new ArrayList<>();
        teachers = new HashMap<>();
        staffMembers = new HashMap<>();
        department=new Department(1,"h");

    }

    public void addDepartment(String name,int id) throws DepartmentAlreadyExistsException, IOException, ClassNotFoundException {
        department.addDepartment(new Department(id,name));
    }

    public void removeDepartment(int departmentId) throws DepartmentNotFoundException, DepartmentNotEmptyException, IOException, ClassNotFoundException {
        Department departmentToRemove = getDepartmentById(departmentId);
        if (departmentToRemove.getTeachers().size() > 0 || departmentToRemove.getStaff().size() > 0) {
            throw new DepartmentNotEmptyException();
        }
        department.removeDepartment(departmentId);
    }

    public void addTeacherToDepartment(int departmentId, Teacher teacher) throws DepartmentNotFoundException, TeacherAlreadyInDepartmentException, IOException, ClassNotFoundException {
        Department department = getDepartmentById(departmentId);
        if (department.getTeachers().contains(teacher)) {
            throw new TeacherAlreadyInDepartmentException();
        }
        if (!teachers.containsKey(teacher.getId())) {
            teachers.put(teacher.getId(), teacher);
        }
        department.addTeacher(teacher);

    }

    public void removeTeacher(int teacherId) throws TeacherNotFoundException, IOException, ClassNotFoundException {
        department.removeTeacher(teacherId);

    }

    public void addStaffToDepartment(int departmentId, Staff staffMember) throws DepartmentNotFoundException, StaffAlreadyInDepartmentException, IOException, ClassNotFoundException {
        Department department = getDepartmentById(departmentId);
        if (department.getStaff().contains(staffMember)) {
            throw new StaffAlreadyInDepartmentException();
        }
        if (!staffMembers.containsKey(staffMember.getId())) {
            staffMembers.put(staffMember.getId(), staffMember);
        }
        department.addStaff(staffMember);

    }

    public void removeStaff(int staffId) throws StaffNotFoundException, IOException, ClassNotFoundException {
        Staff staffToRemove = getStaffById(staffId);
        for (Department department : departments) {
            if (department.getStaff().contains(staffToRemove)) {
                department.removeStaff(staffId);
            }
        }
        staffMembers.remove(staffId);

    }

    public List<Department> getAllDepartments() {
        return departments;
    }

    public Department getDepartmentById(int departmentId) throws DepartmentNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(department.departmentFile));
        ArrayList<Department> departments=(ArrayList)ois.readObject();
        ois.close();
        for (Department department : departments) {
            if (department.getId() == departmentId) {
                return department;
            }
        }
        throw new DepartmentNotFoundException();
    }

    public Department getDepartmentByName(String name) throws DepartmentNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(department.departmentFile));
        ArrayList<Department> departments=(ArrayList)ois.readObject();
        ois.close();
        for (Department department : departments) {
            if (department.getName().equalsIgnoreCase(name)) {
                return department;
            }
        }
        throw new DepartmentNotFoundException();
    }

    public Teacher getTeacherById(int teacherId) throws TeacherNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(department.teacherFile));
        ArrayList<Teacher> teachers=(ArrayList)ois.readObject();
        ois.close();
        for (Teacher teacher:teachers
             ) {if(teacher.getId()==teacherId);
            return teacher;
        }
        throw new TeacherNotFoundException();
    }

    public Staff getStaffById(int staffId) throws StaffNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(department.staffFile));
        ArrayList<Staff> staff=(ArrayList)ois.readObject();
        ois.close();
        for (Staff staff1:staff
        ) {if(staff1.getId()==staffId);
            return staff1;
        }
        throw new StaffNotFoundException();
    }

    @Override
    public double computePayRoll() throws IOException, ClassNotFoundException {
        double totalPayroll = 0;
        for (Department department : departments) {
            for (Teacher teacher : department.getTeachers()) {
                if (teacher instanceof FullTimeTeacher) {
                    FullTimeTeacher fullTimeTeacher = (FullTimeTeacher) teacher;
                    double degreeRate = getDegreeRate(fullTimeTeacher.getDegree());
                    double salary = (32 * degreeRate * 2) * 0.85;
                    totalPayroll += salary;
                } else if (teacher instanceof PartTimeTeacher) {
                    PartTimeTeacher partTimeTeacher = (PartTimeTeacher) teacher;
                    double degreeRate = getDegreeRate(partTimeTeacher.getDegree());
                    double salary = (partTimeTeacher.getHoursWorked() * degreeRate * 2) * 0.76;
                    totalPayroll += salary;
                }
            }
            for (Staff staffMember : department.getStaff()) {
                double workload = Math.min(staffMember.getWorkload(), 40);
                double salary = (workload * 32 * 2) * 0.75;
                totalPayroll += salary;
            }
        }
        return totalPayroll;
    }



    private double getDegreeRate(String degree) {
        if (degree.equalsIgnoreCase("PhD")) {
            return 112;
        } else if (degree.equalsIgnoreCase("Master")) {
            return 82;
        } else {
            return 42;
        }
    }


    }


