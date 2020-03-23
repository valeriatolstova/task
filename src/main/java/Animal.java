import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Animal implements Serializable {
    private final String name;
    private final TypeOfAnimals type;
    private final int age;
    private final List<Food> arrayOfFood;

    public Animal(String name, TypeOfAnimals type, int age, List<Food> arrayOfFood) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.arrayOfFood = arrayOfFood;
    }

    String getName() {
        return name;
    }

    TypeOfAnimals getType() {
        return type;
    }

    int getAge() {
        return age;
    }

    List<Food> getArrayOfFood() {
        return arrayOfFood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age &&
                Objects.equals(name, animal.name) &&
                type == animal.type &&
                Objects.equals(arrayOfFood, animal.arrayOfFood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, age, arrayOfFood);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", age=" + age +
                ", arrayOfFood=" + arrayOfFood +
                '}';
    }
}

