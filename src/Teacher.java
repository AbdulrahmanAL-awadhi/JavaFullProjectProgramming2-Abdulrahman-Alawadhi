import java.io.Serializable;

public class Teacher extends Person implements Serializable {
    private String specialty;
    private String degree;

    public Teacher(int id, String name, String address, String phone, String email, String specialty, String degree) {
        super(id, name, address, phone, email);
        this.specialty = specialty;
        this.degree = degree;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getDegree() {
        return degree;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String getCategory() {
        return "Teacher";
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", specialty='" + specialty + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return getId() == teacher.getId();
    }
}
