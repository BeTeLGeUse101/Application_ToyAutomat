import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class User {

    // Функция записи выигранных игрушек в файл
    public static void PutInMyBackpack(ArrayList<String> arrayList) {
        String filename = "MyBackpack.txt";

        try (FileWriter writer = new FileWriter(filename, true)) {

            for (String list : arrayList) {
                writer.write(list + "\n");
            }
            System.out.println("-----------------------------\n");
            System.out.println("Игрушки добавлены в рюкзак: " + filename);
            System.out.println("-----------------------------\n");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении игрушки в рюкзак " + filename + ": " + e.getMessage());
        }
    }

    // Главный метод с выводом интерфейса для пользователя
    public static void main(String[] args) {
        ArrayList<String> saveStorage = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        ToyAutomat toyAutomat = new ToyAutomat();
        boolean flag = true;
        String func;

        while (flag) {
            System.out.print("____Автомат \"COOL-TOY\"____\n" +
                    "-----------------------------\n" +
                    "Выберите функцию:\n" +
                    "1 - Посмотреть список игрушек\n" +
                    "2 - Выиграть игрушку\n" +
                    "3 - Выход из программы\n" +
                    "-----------------------------\n" +
                    "Служебные функции(для сотрудников):\n" +
                    "add - Добавить игрушку в автомат\n" +
                    "chmod - Изменить шанс выпадения игрушки\n" +
                    "-----------------------------\n" +
                    "Команда: ");

            func = scanner.next();

            switch (func) {
                case "1":
                    toyAutomat.listToys();
                    break;

                case "2":
                    String save = toyAutomat.GetToy(scanner);
                    if (save != null && !save.isEmpty()) {
                        saveStorage.add(save);
                    }
                    break;

                case "3":
                    System.out.println("Спасибо за использование автомата \"COOL-TOY\"!");
                    flag = false;
                    break;

                case "add":
                    System.out.print("Введите имя игрушки: ");
                    String name = scanner.next();
                    System.out.print("Введите кол-во экземпляров игрушки: ");
                    int quan = scanner.nextInt();
                    System.out.print("Введите шанс выпадения игрушки: ");
                    Double weight = scanner.nextDouble();

                    toyAutomat.addToy(name, quan, weight, scanner);
                    break;

                case "chmod":
                    System.out.print("Введите имя игрушки: ");
                    String name2 = scanner.next();
                    System.out.print("Введите новый шанс выпадения игрушки: ");
                    Double weight2 = scanner.nextDouble();
                    toyAutomat.changeWeight(name2, weight2);
                    break;

                default:
                    System.out.println("-----------------------------\n");
                    System.out.println("Неверная команда!");
                    System.out.println("-----------------------------\n");
                    break;
            }
        }
        PutInMyBackpack(saveStorage);
        scanner.close();
    }
}