public class DepartmentAlreadyExistsException extends Exception {
    public DepartmentAlreadyExistsException() {
        super("Department already exists");
    }
}