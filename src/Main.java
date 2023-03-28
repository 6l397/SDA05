import com.google.gson.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Competition> storage = new ArrayList<>();
    static String[] Menu = {"Додати", "Видалити", "Показати", "Змінити", "Сортувати", "Порівняти"};

    private static void from_db_to_arr() throws IOException {
        JsonParser parser = new JsonParser();
        String file = new String(Files.readAllBytes(Paths.get("src/Data.json")));
        JsonObject jo = parser.parse(file).getAsJsonObject();
        JsonArray listArray = jo.getAsJsonArray("Competition");
        for (JsonElement e : listArray) {
            JsonObject obj = e.getAsJsonObject();
            String Faculty = obj.get("Faculty").getAsString();
            Integer AmountOfStudents = obj.get("AmountOfStudents").getAsInt();
            Double GPAbyFaculty = obj.get("GPAbyFaculty").getAsDouble();
            Integer StellarStudents = obj.get("StellarStudents").getAsInt();
            Integer TheNumberOfBinaries = obj.get("AbysmalStudents").getAsInt();
            Competition ide = new Competition(Faculty, AmountOfStudents, GPAbyFaculty,
                    StellarStudents, TheNumberOfBinaries);
            storage.add(ide);
        }
    }

    private static void ShowArr(ArrayList<Competition> arr) {
        String leftAlignFormat = "| %-2d | %-25s | %-6d | %-3.1f | %-4d | %-3d | %n";
        System.out.format("|------------------------------------------------------------|%n");
        System.out.format("| №  | Факультет      | Кількість | Середній бал | :) | :( |+%n");
        System.out.format("|------------------------------------------------------------|%n");
        for (int i = 0; i < arr.size(); i++) {
             System.out.format(leftAlignFormat, (i + 1), arr.get(i).getFaculty(),
                    arr.get(i).getAmountOfStudents(), arr.get(i).getGPAbyFaculty(),
                    arr.get(i).getStellarStudents(), arr.get(i).getAbysmalStudents());
        }
        System.out.format("+------------------------------------------------------------+%n");
        }
    private static void Sorting() {
        ArrayList<Competition> newArr = new ArrayList<>(storage);
        System.out.println("""
                0. Вихід;
                1. Назва;
                2. Кількість;
                3. Середній бал;
                4. Відмінники;
                5. Двієчники;
                """);
        int i = scanner.nextInt();
        switch (i) {
            case 0 -> {
                System.out.println("Повернутися до меню");
            }
            case 1 -> {
                newArr.sort(Comparator.comparing(Competition::getFaculty));
                System.out.println("Сортувати за назвою: ");
                ShowArr(newArr);
                Sorting();
            }
            case 2 -> {
                newArr.sort(Comparator.comparing(Competition::getAmountOfStudents));
                System.out.println("Сортувати за студентами: ");
                ShowArr(newArr);
                Sorting();
            }
            case 3 -> {
                newArr.sort(Comparator.comparing(Competition::getGPAbyFaculty));
                System.out.println("Сортувати за середнім балом: ");
                ShowArr(newArr);
                Sorting();
            }
            case 4 -> {
                newArr.sort(Comparator.comparing(Competition::getStellarStudents));
                System.out.println("Сортувати за кількістю відмінників: ");
                ShowArr(newArr);
                Sorting();
            }
            case 5 -> {
                newArr.sort(Comparator.comparing(Competition::getAbysmalStudents));
                System.out.println("Сортувати за кількістю двієчників: ");
                ShowArr(newArr);
                Sorting();
            }
            default -> {
                System.out.println("Уведіть число 0 до 5");
                Sorting();
            }
        }
    }

    private static Boolean Compare(Competition b_student, Competition a_student) {
        return b_student.getAmountOfStudents() > a_student.getAmountOfStudents();
    }

    private static void Add() throws IOException {
        System.out.println("Уведіть назву факультету: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Уведіть кількість студентів: ");
        int amount = scanner.nextInt();
        System.out.println("Уведіть середній бал: ");
        String gpa = scanner.next();
        System.out.println("Уведіть кількість відмінників: ");
        int stellar = scanner.nextInt();
        System.out.println("Уведіть кількість двієчників: ");
        int abysmal = scanner.nextInt();
        Competition newIde = new Competition(name, amount, Double.parseDouble(gpa),
                stellar, abysmal);
        storage.add(newIde);
        Gson gson = new Gson();
        Map<String, ArrayList<Competition>> stringMap = new LinkedHashMap<>();
        stringMap.put("Competition", (ArrayList<Competition>) storage);
        FileWriter fw3 = new FileWriter("src/Data.json");
        gson.toJson(stringMap, fw3);
        fw3.close();
    }

    private static void Change() throws IOException {
        System.out.println("Оберіть, що ви хочете змінити");
        ShowArr((ArrayList<Competition>) storage);
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice > storage.size() || choice < 1) {
                    System.out.println("Уведіть число від 1 до " + storage.size());
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Уведіть число");
            }
        }
        System.out.println("Уведіть назву факультету: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Уведіть кількість студентів: ");
        int amount = scanner.nextInt();
        System.out.println("Уведіть середній бал: ");
        double gpa = scanner.nextDouble();
        System.out.println("Уведіть кількість відмінників: ");
        int stellar = scanner.nextInt();
        System.out.println("Уведіть кількість двієчників: ");
        int abysmal = scanner.nextInt();

        storage.get(choice - 1).setFaculty(name);
        storage.get(choice - 1).setAmountOfStudents(amount);
        storage.get(choice - 1).setGPAbyFaculty(gpa);
        storage.get(choice - 1).setStellarStudents(stellar);
        storage.get(choice - 1).setAbysmalStudents(abysmal);
        Gson gson = new Gson();
        Map<String, ArrayList<Competition>> stringMap = new LinkedHashMap<>();
        stringMap.put("Competition", (ArrayList<Competition>) storage);
        FileWriter fw3 = new FileWriter("src/Data.json");
        gson.toJson(stringMap, fw3);
        fw3.close();
    }

    private static void Delete() throws IOException {
        System.out.println("Оберіть, що ви хочете видалити");
        ShowArr((ArrayList<Competition>) storage);
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                if (choice > storage.size() || choice < 1) {
                    System.out.println("Уведіть число від 1 до " + storage.size());
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Уведіть число");
            }
        }
        storage.remove(choice - 1);

        Gson gson = new Gson();
        Map<String, ArrayList<Competition>> stringMap = new LinkedHashMap<>();
        stringMap.put("Competition", (ArrayList<Competition>) storage);
        FileWriter fw3 = new FileWriter("src/Data.json");
        gson.toJson(stringMap, fw3);
        fw3.close();
    }

    public static void main(String[] args) throws IOException {
        from_db_to_arr();
        for (int i = 0; i < Menu.length; i++) {
            System.out.println((i + 1) + ". " + Menu[i]);
        }
        while (true) {
            int input = scanner.nextInt();
            if (input == 0) {
                System.out.println("Вихід");
                break;
            } else if (input == 1) {
                System.out.println("Додати: ");
                Add();
            } else if (input == 2) {
                System.out.println("Видалити: ");
                Delete();
            } else if (input == 3) {
                System.out.println("Показати: ");
                ShowArr((ArrayList<Competition>) storage);
            } else if (input == 4) {
                System.out.println("Змінити: ");
                Change();
            } else if (input == 5) {
                System.out.println("Сортувати: ");
                Sorting();
            } else if (input == 6) {
                System.out.println("Порівняти: ");
                System.out.println("Оберіть два елемента зі списку");
                ShowArr((ArrayList<Competition>) storage);
                int e1;
                int e2;
                while (true) {
                    try {
                        e1 = scanner.nextInt();
                        e2 = scanner.nextInt();
                        if (e1 > storage.size() || e2 > storage.size() || e1 < 0 || e2 < 0) {
                            System.out.println("Уведіть число від 1 до " + storage.size());
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Уведіть число");
                    }
                }
                if (Compare(storage.get(e1 - 1), storage.get(e2 - 1))) {
                    System.out.println(storage.get(e1 - 1).getAmountOfStudents() + " має вищий бал за "
                            + storage.get(e2 - 1));
                } else {
                    System.out.println(storage.get(e2 - 1).getAmountOfStudents() + " має вищий бал за "
                            + storage.get(e1 - 1));
                }
            } else {
                System.out.println("Щось пішло не так :(");
            }
        }
    }
}