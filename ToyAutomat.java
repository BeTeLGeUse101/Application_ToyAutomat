import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// Игровой автомат по розыгрышу игрушек 

public class ToyAutomat {

    private ArrayList<StorageToys> storage = new ArrayList<StorageToys>();

    // Добавление игрушки в автомат
    public void addToy(String nameToy, int quantity, Double weight, Scanner scannerLocal) {
        boolean flag = false;

        System.out.println("-----------------------------\n");

        for (StorageToys storageToys : getStorage()) {
            if (storageToys.getNameToy().equals(nameToy)) {
                System.out.print("Такая игрушка уже есть в автомате!\n" +
                        "Хотите добавить дополнительные игрушки? (yes, no) ");
                while (true) {
                    scannerLocal.nextLine();
                    String answer = scannerLocal.nextLine();

                    if (answer.equals("yes")) {
                        System.out.print("Дополнительное кол-во игрушек: ");
                        int quan = scannerLocal.nextInt();
                        storageToys.setQuantity(storageToys.getQuantity() + quan);
                        System.out.println("-----------------------------\n");
                        System.out.println(String.format("Кол-во игрушки %s увеличено!", storageToys.getNameToy()));
                        flag = true;
                        break;

                    } else if (answer.equals("no")) {
                        flag = true;
                        break;
                    } else
                        System.out.print("Неверная команда! Введите корректную команду - ");
                }
            }
        }
        if (!flag) {
            getStorage().add(new StorageToys(nameToy, quantity, weight));
            System.out.println("Игрушка успешно добавлена в автомат.");
        }

        System.out.println("-----------------------------\n");
    }

    // Функция изменения веса игрушки
    public void changeWeight(String nameToy, Double weight) {
        System.out.println("-----------------------------\n");
        boolean found = false;

        for (StorageToys storageToys : getStorage()) {
            if (storageToys.getNameToy().equals(nameToy)) {
                storageToys.setWeight(weight);
                System.out.println("Шанс выпадения игрушки '" + storageToys.getNameToy() + "' успешно изменен!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Игрушка в автомате не найдена.");
        }

        System.out.println("-----------------------------\n");
    }

    // Функция просмотра всех добавленных игрушек в автомате
    public void listToys() {
        System.out.println("-----------------------------\n");
        if (!getStorage().isEmpty()) {
            for (StorageToys storageToys : getStorage()) {
                System.out.println(String.format("%s - %d шт. (%.2f%%)", storageToys.getNameToy(),
                        storageToys.getQuantity(), storageToys.getWeight()));
            }
        } else
            System.out.println("Автомат пустой!");
        System.out.println("-----------------------------\n");
    }

    // Функция розыгрыша игрушек. Автомат каждую попытку розыгрывает определенную игрушку.
    // В зависимости от шанса выпадения игрушки, можно либо проиграть либо выиграть. 
    public String GetToy(Scanner scanner) {
        Random random = new Random();
        Double randomNum = random.nextDouble(0.000, 100.000);
        int number;
        boolean flag = true;

        if (getStorage().isEmpty()) {
            System.out.println("-----------------------------\n");
            System.out.println("Автомат пустой!");
            System.out.println("-----------------------------\n");
            return null;
        }

        System.out.println("-----------------------------\n");
        for (StorageToys storageToys : getStorage()) {
            System.out.println(String.format("(%d) %s - %d шт. (%.2f%%)", storageToys.getId(), storageToys.getNameToy(),
                    storageToys.getQuantity(), storageToys.getWeight()));
        }
        System.out.println("-----------------------------\n");

        while (flag) {
            System.out.print("Выберите номер игрушки: ");
            number = scanner.nextInt();

            for (StorageToys storageToys : getStorage()) {

                if (number == storageToys.getId()) {

                    if (storageToys.getQuantity() > 0) {

                        System.out.println("-----------------------------\n");
                        if (storageToys.getWeight() >= randomNum) {
                            System.out.println(String.format("Вы выиграли %s! :D", storageToys.getNameToy()));
                            storageToys.setQuantity(storageToys.getQuantity() - 1);
                            System.out.println("-----------------------------\n");
                            return String.format("id(%d) - %s - +%d", storageToys.getId(), storageToys.getNameToy(), 1);

                        } else {
                            System.out.println(String.format("Вы проиграли %s. :(", storageToys.getNameToy()));
                            System.out.println("-----------------------------\n");
                            return null;
                        }

                    } else {
                        System.out.println("-----------------------------\n");
                        System.out.println("Нет игрушки в наличии.");
                        System.out.println("-----------------------------\n");
                        flag = false;
                    }
                }
            }
            System.out.println("Игрушка не найдена!");
        }
        return null;
    }

    // Хранилище всех стеков с игрушками
    private ArrayList<StorageToys> getStorage() {
        return storage;
    }
}
