import java.io.Serializable;

public class FullTimeTeacher extends Teacher implements PayRoll , Serializable {
    public FullTimeTeacher(int id, String name, String address, String phone, String email, String specialty, String degree) {
        super(id, name, address, phone, email, specialty, degree);
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
        return (32 * degreeRate * 2) * 0.85;
    }
}
