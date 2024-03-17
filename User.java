import java.time.LocalDate;
import java.time.Period;

public class User {
    private String firstName, secondName, middleName;
    private String dateBirth;
    private int age;
    private String gender;
    public boolean isAuth = false;

    public void setUserName(String strName) throws InvalidInputException {
        String[] arr = strName.trim().split(" ");
        if (arr.length != 3) {
            this.isAuth = false;
            throw new InvalidInputException("Неверные данные - ФИО");
        }
        this.secondName = capitalize(arr[0]);
        this.firstName = capitalize(arr[1]);
        this.middleName = capitalize(arr[2]);

        String subMiddleName = this.middleName.substring(this.middleName.length() - 2);
        if (subMiddleName.equals("ич") || subMiddleName.equals("лы")) {
            this.gender = "Мужчина";
        } else if (subMiddleName.equals("на") || subMiddleName.equals("зы") || subMiddleName.equals("ва") ||
                this.secondName.charAt(this.secondName.length() - 1) == 'а' ||
                this.secondName.charAt(this.secondName.length() - 1) == 'я') {
            this.gender = "Женщина";
        }
        isAuth = true;
    }

    public void  setUserAge(String dateBirth) throws InvalidInputException {
        String[] arr = dateBirth.trim().split("\\.");
        if (arr.length != 3) {
            this.isAuth = false;
            throw new InvalidInputException("Неверные данные - дд.мм.гг");
        }
        this.dateBirth = dateBirth;
        this.age = calculateAge(LocalDate.of(Integer.parseInt(arr[2]), Integer.parseInt(arr[1]), Integer.parseInt(arr[0])), LocalDate.now());
        isAuth = true;
    }

    public String getUserName() {
        return secondName + " " + firstName.charAt(0) + "." + middleName.charAt(0) + ".";
    }

    public String getSupportAge(int age) {
        if (age % 10 == 1)
            return "год";
        else if (age % 10 >= 5 && age % 10 < 9 || age % 10 == 0 || age % 100 >= 5 && age % 100 < 21)
            return "лет";
        return "года";
    }

    public String getInfoUser() {
        return getUserName() + "\n" +  getGender() + "\n" + getAge() + " " + getSupportAge(getAge());
    }

    private void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    private void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    private String capitalize(String str) {
        if(str == null || str.length()<=1) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}

