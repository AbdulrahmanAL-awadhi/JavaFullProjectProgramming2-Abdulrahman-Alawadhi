public class DepartmentNotEmptyException extends Exception {
    public DepartmentNotEmptyException() {
        super("Department not found");
    }
}