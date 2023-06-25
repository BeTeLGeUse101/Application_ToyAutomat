import java.util.ArrayList;
import java.util.Random;

// Хранилище игрушек 

public class StorageToys {
    private static ArrayList<Integer> listId = new ArrayList<>();

    private int id;
    private String nameToy;
    private int quantity;
    private Double weight;

    // Конструктор по созданию стека с игрушками и присвоение каждому стеку неповторяющийся id при работе программы
    public StorageToys(String nameToy, int quantity, Double weight) {
        Random random = new Random();
        id = random.nextInt(1000, 10000);

        while (true) {
            if (!listId.isEmpty()) {
                for (int i = 0; i < listId.size(); i++) {
                    Integer idT = getListId().get(i);
                    if (idT.equals(id)) {
                        id = random.nextInt(1000, 10000);
                        i = 0;
                    }
                }
            }
            break;
        }

        this.nameToy = nameToy;
        this.quantity = quantity;
        this.weight = weight;
    }

    public static ArrayList<Integer> getListId() {
        return listId;
    }

    public int getId() {
        return id;
    }

    public String getNameToy() {
        return nameToy;
    }

    public void setNameToy(String nameToy) {
        this.nameToy = nameToy;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("id = %d\n" + "Имя игрушки: %s\n" + "Кол-во: %d\n" + "Шанс выпадения: %.2f%%", id, nameToy,
                quantity, weight);
    }
}