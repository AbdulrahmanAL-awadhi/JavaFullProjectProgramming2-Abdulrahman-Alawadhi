import java.io.Serializable;

public class PartTimeTeacher extends Teacher implements PayRoll , Serializable {
    private int hoursWorked;

    public PartTimeTeacher(int id, String name, String address, String phone, String email, String specialty, String degree,int hoursWorked) {
        super(id, name, address, phone, email, specialty, degree);
        this.hoursWorked = hoursWorked;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    @Override
    public String toString() {
        return "PartTimeTeacher{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", specialty='" + getSpecialty() + '\'' +
                ", degree='" + getDegree() + '\'' +
                ", hoursWorked=" + hoursWorked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PartTimeTeacher)) return false;
        if (!super.equals(o)) return false;
        PartTimeTeacher that = (PartTimeTeacher) o;
        return hoursWorked == that.hoursWorked;
    }

    @Override
    public double computePayRoll() {
        double degreeRate;
        switch (getDegree()) {
            case "PhD":
                degreeRate = 112;
                break;
            case "Master":
                degreeRate = 82;
                break;
            default:
                degreeRate = 42;
        }
        return (hoursWorked * degreeRate * 2) * 0.76;
    }
}