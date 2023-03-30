public class DepartmentNotFoundException extends Exception {
    public DepartmentNotFoundException() {
        super("Department not found");
    }
}