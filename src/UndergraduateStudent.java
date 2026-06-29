public class UndergraduateStudent extends Student {

    private String major;
    private String classification;

    // constructor
    public UndergraduateStudent(
            String studentId,
            String name,
            String major,
            String classification) {

        super(studentId, name);
        this.major = major;
        this.classification = classification;
    }

    // getters
    public String getMajor() {

        return major;
    }

    public String getClassification() {

        return classification;
    }

    // setters
    public void setMajor (String newMajor) {
        if (newMajor != null && !newMajor.trim().isEmpty()) {
            this.major = newMajor;
        }
        else {
            System.out.println("Warning: invalid major rejected.");
        }
    }

    public void setClassification(String newClassification) {
        if (newClassification != null && !newClassification.trim().isEmpty()) {
            this.classification = newClassification;
        }
        else {
            System.out.println("Warning: invalid classification rejected.");
        }
    }

    @Override
    public String toString() {
        return super.toString()
                + " | Major: " + major
                + " | Classification: " + classification;
    }
}
