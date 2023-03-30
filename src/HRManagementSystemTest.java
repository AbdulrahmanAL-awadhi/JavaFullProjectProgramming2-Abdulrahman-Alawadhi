//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//import java.io.IOException;
//import java.util.List;
//
//public class HRManagementSystemTest {
//    private HRManagementSystemController controller;
//
//    @Before
//    public void setUp() throws StaffAlreadyInDepartmentException {
//        controller = new HRManagementSystemController();
//    }
//
//    @Test
//    public void testAddDepartment() throws Exception {
//        String departmentName = "Computer Science";
//        int departmentId = 2;
//        controller.addDepartment(departmentName, departmentId);
//        List<Department> departments = controller.getAllDepartments();
//        assertEquals(2, departments.size());
//        assertEquals(departmentName, departments.get(1).getName());
//        assertEquals(departmentId, departments.get(1).getId());
//    }
//
//    @Test(expected = DepartmentNotFoundException.class)
//    public void testAddDuplicateDepartment() throws Exception {
//        String departmentName = "Physics";
//        int departmentId = 1;
//        controller.addDepartment(departmentName, departmentId);
//    }
//
//    @Test(expected = DepartmentNotEmptyException.class)
//    public void testRemoveNonEmptyDepartment() throws Exception {
//        int departmentId = 1;
//        controller.removeDepartment(departmentId);
//    }
//
//    @Test
//    public void testRemoveEmptyDepartment() throws Exception {
//        int departmentId = 2;
//        controller.removeDepartment(departmentId);
//        List<Department> departments = controller.getAllDepartments();
//        assertEquals(1, departments.size());
//    }
//
//    @Test
//    public void testAddTeacherToDepartment() throws Exception {
//        int departmentId = 1;
//        Teacher teacher = new FullTimeTeacher(1, "John Smith", "123 Main St", "555-1234", "john.smith@example.com", "Math", "PhD");
//        controller.addTeacherToDepartment(departmentId, teacher);
//        Department department = controller.getDepartmentById(departmentId);
//        List<Teacher> teachers = department.getTeachers();
//        assertEquals(2, teachers.size());
//        assertTrue(teachers.contains(teacher));
//    }
//
//    @Test(expected = TeacherAlreadyInDepartmentException.class)
//    public void testAddDuplicateTeacherToDepartment() throws Exception {
//        int departmentId = 1;
//        Teacher teacher = controller.getTeacherById(1);
//        controller.addTeacherToDepartment(departmentId, teacher);
//    }
//
//    @Test
//    public void testRemoveTeacher() throws Exception {
//        int teacherId = 1;
//        controller.removeTeacher(teacherId);
//        assertNull(controller.getTeacherById(teacherId));
//    }
//
//    @Test
//    public void testAddStaffToDepartment() throws Exception {
//        int departmentId = 1;
//        Staff staffMember = new Staff(2, "Jane Doe", "456 Main St", "555-5678", "jane.doe@example.com", "Secretary", 40);
//        controller.addStaffToDepartment(departmentId, staffMember);
//        Department department = controller.getDepartmentById(departmentId);
//        List<Staff> staffMembers = department.getStaff();
//        assertEquals(2, staffMembers.size());
//        assertTrue(staffMembers.contains(staffMember));
//    }
//
//    @Test(expected = StaffAlreadyInDepartmentException.class)
//    public void testAddDuplicateStaffToDepartment() throws Exception {
//        int departmentId = 1;
//        Staff staffMember = controller.getStaffById(1);
//        controller.addStaffToDepartment(departmentId, staffMember);
//    }
//
//    @Test
//    public void testRemoveStaff() throws Exception {
//        int staffId = 1;
//        controller.removeStaff(staffId);
//        assertNull(controller.getStaffById(staffId));
//    }
//
//    @Test
//    public void testComputePayRoll() throws Exception {
//        double expectedPayroll = (32 * 112 * 2 * 0.85) + (32 * 82 * 2 * 0.76) + (40 * 32 * 2 * 0.75);
//        double actualPayroll = controller.computePayRoll();
//        assertEquals(expectedPayroll, actualPayroll, 0.001);
//    }
//}
