public class Competition {
    private String Faculty;
    private int AmountOfStudents;
    private double GPAbyFaculty;
    private int StellarStudents;
    private int AbysmalStudents;

    public Competition(String faculty, int amountOfStudents, double gpaByFaculty,
                       int stellarStudents, int abysmalStudents) {
        Faculty = faculty;
        AmountOfStudents = amountOfStudents;
        GPAbyFaculty = gpaByFaculty;
        StellarStudents = stellarStudents;
        AbysmalStudents = abysmalStudents;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String faculty) {
        Faculty = faculty;
    }

    public int getAmountOfStudents() {
        return AmountOfStudents;
    }

    public void setAmountOfStudents(int amountOfStudents) {
        AmountOfStudents = amountOfStudents;
    }

    public double getGPAbyFaculty() {
        return GPAbyFaculty;
    }

    public void setGPAbyFaculty(double gpaByFaculty) {
        this.GPAbyFaculty = gpaByFaculty;
    }

    public int getStellarStudents() {
        return StellarStudents;
    }

    public void setStellarStudents(int stellarStudents) {
        StellarStudents = stellarStudents;
    }

    public int getAbysmalStudents() {
        return AbysmalStudents;
    }

    public void setAbysmalStudents(int abysmalStudents) {
        AbysmalStudents = abysmalStudents;
    }

    public String toString() {
        return "Назва факультету: " + getFaculty() + ", Кількість учнів: " + getAmountOfStudents() +
                ", Середній бал по факультету: " + getGPAbyFaculty()
                + ", Кількість відмінників: " + getStellarStudents() + ", Кількість двієчників: "
                + getAbysmalStudents();
    }
}