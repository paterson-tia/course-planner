public class GraduateStudent extends Student {

    private String researchArea;
    private String advisorName;

    // constructor

    public GraduateStudent(
            String studentId,
            String name,
            String researchArea,
            String advisorName) {

        super(studentId, name);
        this.researchArea = researchArea;
        this.advisorName = advisorName;
    }

    // getters

    public String getResearchArea() {
        return researchArea;
    }

    public String getAdvisorName() {
        return advisorName;
    }

    // setters
    public void setResearchArea(String newResearchArea) {
        if (newResearchArea != null && !newResearchArea.trim().isEmpty()) {
            this.researchArea = newResearchArea;
        }
        else {
            System.out.println("Warning: invalid research area rejected.");
        }
    }

    public void setAdvisorName(String newAdvisorName) {
        if (newAdvisorName != null && !newAdvisorName.trim().isEmpty()) {
            this.advisorName = newAdvisorName;
        } else {
            System.out.println("Warning: invalid advisor name rejected.");
        }
    }

    // toString method
    @Override
    public String toString() {
        return super.toString()
                + " | Research Area: " + researchArea
                + " | Advisor Name: " + advisorName;
    }
}
