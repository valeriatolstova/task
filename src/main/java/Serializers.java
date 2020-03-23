import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Serializers {
    public static void serialize(List<Animal> listOfAnimals, Path path) throws IOException {
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(listOfAnimals);
        }
    }

    public static List<Animal> deserialize(Path path) throws IOException, ClassNotFoundException {
        List<Animal> newAnimalList = new ArrayList<>();
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(Files.newInputStream(path))) {
            newAnimalList = (List<Animal>) inputStream.readObject();
        }

        return newAnimalList;
    }

    public static void serializeMyself(List<Animal> listOfAnimals, Path path) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(Files.newOutputStream(path))) {
            dataOutputStream.writeInt(listOfAnimals.size());
            for (Animal currentAnimal : listOfAnimals) {
                dataOutputStream.writeUTF(currentAnimal.getName());
                dataOutputStream.writeUTF(currentAnimal.getType().name());
                dataOutputStream.writeInt(currentAnimal.getAge());
                dataOutputStream.writeInt(currentAnimal.getArrayOfFood().size());
                for (Food currentFood : currentAnimal.getArrayOfFood()) {
                    dataOutputStream.writeUTF(currentFood.getName());
                    dataOutputStream.writeInt(currentFood.getCount());
                }
            }
        }
    }

    public static List<Animal> deserializeMyself(Path path) throws IOException {
        List<Animal> newAnimalList = new ArrayList<>();
        String nameAnimal;
        TypeOfAnimals type;
        int age;
        String nameFood;
        int countFood;
        try (DataInputStream dataInputStream = new DataInputStream(Files.newInputStream(path))) {
            int countOfAnimals = dataInputStream.readInt();
            for (int i = 0; i < countOfAnimals; i++) {
                nameAnimal = dataInputStream.readUTF();
                type = TypeOfAnimals.valueOf(dataInputStream.readUTF());
                age = dataInputStream.readInt();
                countFood = dataInputStream.readInt();
                List<Food> arrayOfFood = new ArrayList<>();
                for (int j = 0; j < countOfAnimals; i++) {
                    nameFood = dataInputStream.readUTF();
                    countFood = dataInputStream.readInt();
                    arrayOfFood.add(new Food(nameFood, countFood));
                }
                newAnimalList.add(new Animal(nameAnimal, type, age, arrayOfFood));
            }
        }
        return newAnimalList;
    }
}
