import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HRManagementSystem extends JFrame{
    private final JPanel mainPanel;
    private final JButton addDepartmentButton;
    private final JButton addTeacherButton;
    private final JButton addStaffButton;
    private final JButton removeDepartmentButton;
    private final JButton removeTeacherButton;
    private final JButton removeStaffButton;
    private final JButton showDepartmentButton;
    private final JButton showTeacherButton;
    private final JButton showStaffButton;
    private final JLabel label;
    private final HRManagementSystemController controller;
    public HRManagementSystem() throws StaffAlreadyInDepartmentException {
        super("Human Resources Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        controller=new HRManagementSystemController();
        mainPanel.setLayout(null);

        addDepartmentButton = new JButton("Add Department");
        addDepartmentButton.setBounds(100,100,150,70);
        addDepartmentButton.setBackground(Color.green);
        addDepartmentButton.setFocusable(false);
        addDepartmentButton.addActionListener(e -> {
            String departmentName = JOptionPane.showInputDialog(this, "Enter department name:");
            String departmentId = JOptionPane.showInputDialog(this, "Enter department ID");
            int id=Integer.parseInt(departmentId);
            if (departmentName != null && !departmentName.trim().isEmpty()) {
                try {
                    controller.addDepartment(departmentName,id);
                    JOptionPane.showMessageDialog(this, "Department added successfully");
                } catch (DepartmentAlreadyExistsException | IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addTeacherButton = new JButton("Add Teacher");
        addTeacherButton.setBounds(280,100,150,70);
        addTeacherButton.setBackground(Color.green);
        addTeacherButton.setFocusable(false);
        addTeacherButton.addActionListener(e -> {   String departmentIdStr = JOptionPane.showInputDialog(this, "Enter department ID:");
            if (departmentIdStr != null && !departmentIdStr.trim().isEmpty()) {
                try {
                    int departmentId = Integer.parseInt(departmentIdStr);
                    String teacherType = (String) JOptionPane.showInputDialog(this, "Select teacher type:", "Add Teacher",
                            JOptionPane.QUESTION_MESSAGE, null, new String[]{"Full-time", "Part-time"}, "Full-time");
                    if (teacherType != null) {
                        String name = JOptionPane.showInputDialog(this, "Enter name:");
                        String address = JOptionPane.showInputDialog(this, "Enter address:");
                        String phone = JOptionPane.showInputDialog(this, "Enter phone:");
                        String email = JOptionPane.showInputDialog(this, "Enter email:");
                        String degree = (String) JOptionPane.showInputDialog(this, "Select degree:", "Add Teacher", JOptionPane.QUESTION_MESSAGE, null,
                                new String[]{"PhD", "Master", "Bachelor"}, "PhD");
                        if (degree != null) {
                            if (teacherType.equals("Full-time")) {
                                String specialty = JOptionPane.showInputDialog(this, "Enter specialty:");
                                FullTimeTeacher teacher = new FullTimeTeacher(0, name, address, phone, email, specialty, degree);
                                controller.addTeacherToDepartment(departmentId, teacher);
                                JOptionPane.showMessageDialog(this, "Teacher added successfully");
                            } else {
                                String specialty = JOptionPane.showInputDialog(this, "Enter specialty:");
                                String hoursWorkedStr = JOptionPane.showInputDialog(this, "Enter hours worked:");
                                if (hoursWorkedStr != null && !hoursWorkedStr.trim().isEmpty()) {
                                    int hoursWorked = Integer.parseInt(hoursWorkedStr);
                                    PartTimeTeacher teacher = new PartTimeTeacher(0, name, address, phone, email,
                                            specialty, degree, hoursWorked);
                                    controller.addTeacherToDepartment(departmentId, teacher);
                                    JOptionPane.showMessageDialog(this, "Teacher added successfully");
                                }
                            }
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid department ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DepartmentNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (TeacherAlreadyInDepartmentException | IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }});
        addStaffButton = new JButton("Add Staff");
        addStaffButton.setBounds(480,100,150,70);
        addStaffButton.setBackground(Color.green);
        addStaffButton.setFocusable(false);
        addStaffButton.addActionListener(e -> {
            String departmentIdStr = JOptionPane.showInputDialog(this, "Enter department ID:");
            if (departmentIdStr != null && !departmentIdStr.trim().isEmpty()) {
                try {
                    int departmentId = Integer.parseInt(departmentIdStr);
                    String name = JOptionPane.showInputDialog(this, "Enter name:");
                    String address = JOptionPane.showInputDialog(this, "Enter address:");
                    String phone = JOptionPane.showInputDialog(this, "Enter phone:");
                    String email = JOptionPane.showInputDialog(this, "Enter email:");
                    String duty = JOptionPane.showInputDialog(this, "Enter duty:");
                    String workloadStr = JOptionPane.showInputDialog(this, "Enter workload:");
                    if (workloadStr != null && !workloadStr.trim().isEmpty()) {
                        int workload = Integer.parseInt(workloadStr);
                        Staff staffMember = new Staff(0, name, address, phone, email, duty, workload);
                        controller.addStaffToDepartment(departmentId, staffMember);
                        JOptionPane.showMessageDialog(this, "Staff member added successfully");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid department ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DepartmentNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (StaffAlreadyInDepartmentException | IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        removeDepartmentButton = new JButton("Remove Department");
        removeDepartmentButton.setBounds(100,250,150,70);
        removeDepartmentButton.setBackground(Color.green);
        removeDepartmentButton.setFocusable(false);
        removeDepartmentButton.addActionListener(e -> {
            String departmentIdStr = JOptionPane.showInputDialog(this, "Enter department ID:");
            if (departmentIdStr != null && !departmentIdStr.trim().isEmpty()) {
                try {
                    int departmentId = Integer.parseInt(departmentIdStr);
                    controller.removeDepartment(departmentId);
                    JOptionPane.showMessageDialog(this, "Department removed successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid department ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DepartmentNotFoundException | IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DepartmentNotEmptyException ex) {
                    JOptionPane.showMessageDialog(this, "Error");
                }}
        });
        removeTeacherButton = new JButton("Remove Teacher");
        removeTeacherButton.setBounds(280,250,150,70);
        removeTeacherButton.setBackground(Color.green);
        removeTeacherButton.setFocusable(false);
        removeTeacherButton.addActionListener(e->{
            String teacherIdStr = JOptionPane.showInputDialog(this, "Enter Teacher ID:");                if (teacherIdStr != null && !teacherIdStr.trim().isEmpty()) {
                try {
                    int teacherId = Integer.parseInt(teacherIdStr);
//                    HRManagementSystemController controller= null;
                    controller.removeTeacher(teacherId);
                    JOptionPane.showMessageDialog(this, "Teacher removed successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid teacher ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (TeacherNotFoundException | IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }}
        });
        removeStaffButton = new JButton("Remove Staff");
        removeStaffButton.setBounds(480,250,150,70);
        removeStaffButton.setBackground(Color.green);
        removeStaffButton.setFocusable(false);
        removeStaffButton.addActionListener(e -> {
            String staffIdStr = JOptionPane.showInputDialog(this, "Enter Staff ID:");                if (staffIdStr != null && !staffIdStr.trim().isEmpty()) {
                try {
                    int staffId = Integer.parseInt(staffIdStr);
                    controller.removeStaff(staffId);
                    JOptionPane.showMessageDialog(this, "Staff member removed successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid staff member ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (StaffNotFoundException | IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        showDepartmentButton = new JButton("Show Department");
        showDepartmentButton.setBounds(100,400,150,70);
        showDepartmentButton.setBackground(Color.green);
        showDepartmentButton.setFocusable(false);
        showDepartmentButton.addActionListener(e -> {
            String departmentIdStr = JOptionPane.showInputDialog(this, "Enter department ID:");
            if (departmentIdStr != null && !departmentIdStr.trim().isEmpty()) {
                try {
                    int departmentId = Integer.parseInt(departmentIdStr);
                    Department department = controller.getDepartmentById(departmentId);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Department ID: ").append(department.getId()).append("\n");
                    sb.append("Department name: ").append(department.getName()).append("\n");
                    sb.append("Dean: ").append(department.getDean()).append("\n");
                    sb.append("Teachers: \n");
                    for (Teacher teacher : department.getTeachers()) {
                        sb.append(teacher).append("\n");
                    }
                    sb.append("Staff members: \n");
                    for (Staff staffMember : department.getStaff()) {
                        sb.append(staffMember).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, sb.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid department ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (DepartmentNotFoundException | IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        showTeacherButton = new JButton("Show Teacher");
        showTeacherButton.setBounds(280,400,150,70);
        showTeacherButton.setBackground(Color.green);
        showTeacherButton.setFocusable(false);
        showTeacherButton.addActionListener(e -> {
            String teacherIdStr = JOptionPane.showInputDialog(this, "Enter teacher ID:");
            if (teacherIdStr != null && !teacherIdStr.trim().isEmpty()) {
                try {
                    int teacherId = Integer.parseInt(teacherIdStr);
                    Teacher teacher = controller.getTeacherById(teacherId);
                    JOptionPane.showMessageDialog(this, teacher.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid teacher ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (TeacherNotFoundException | IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }}
        });
        showStaffButton = new JButton("Show Staff");
        showStaffButton.setBounds(480,400,150,70);
        showStaffButton.setBackground(Color.green);
        showStaffButton.setFocusable(false);
        showStaffButton.addActionListener(e -> {
            String staffIdStr = JOptionPane.showInputDialog(this, "Enter staff member ID:");
            if (staffIdStr != null && !staffIdStr.trim().isEmpty()) {
                try {
                    int staffId = Integer.parseInt(staffIdStr);
                    Staff staffMember = controller.getStaffById(staffId);
                    JOptionPane.showMessageDialog(this, staffMember.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid staff member ID", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (StaffNotFoundException | IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        label=new JLabel("HR Management System");
        label.setBounds(150,10,500,60);
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Times New Roman",1,40));

        mainPanel.add(addDepartmentButton);
        mainPanel.add(addTeacherButton);
        mainPanel.add(addStaffButton);
        mainPanel.add(removeDepartmentButton);
        mainPanel.add(removeTeacherButton);
        mainPanel.add(removeStaffButton);
        mainPanel.add(showDepartmentButton);
        mainPanel.add(showTeacherButton);
        mainPanel.add(showStaffButton);
        mainPanel.add(label);
        mainPanel.setBackground(Color.BLACK);

        add(mainPanel, BorderLayout.CENTER);

    }
  public static void main(String[] args) throws StaffAlreadyInDepartmentException, IOException {

//            Department department=new Department(2,"4");
//            department.initfiles();
            HRManagementSystem system = new HRManagementSystem();
            system.setVisible(true);
        }
    }