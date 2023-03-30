import java.io.Serializable;

public class Staff extends Person implements PayRoll , Serializable {
    private String duty;
    private int workload;
    public Staff(int id, String name, String address, String phone, String email, String duty, int workload) {
        super(id, name, address, phone, email);
        this.duty = duty;
        this.workload = workload;
    }

    public String getDuty() {
        return duty;
    }

    public int getWorkload() {
        return workload;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }

    @Override
    public String getCategory() {
        return "Staff";
    }

    @Override
    public double computePayRoll() {
        int hours = Math.min(workload, 40); // cannot exceed 40 hours
        return (hours * 32 * 2) * 0.75;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", duty='" + duty + '\'' +
                ", workload=" + workload +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return getId() == staff.getId();
    }
}