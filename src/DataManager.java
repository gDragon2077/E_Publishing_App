// DataManager.java - gestioneaza salvarea si incarcarea datelor in/din fisiere

import java.io.*;
import java.util.List;

public class DataManager {

    public static void saveData(Object data, String filename) {
        // Comentariu: Salveaza obiectul serializabil in fisier
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object loadData(String filename) {
        // Comentariu: Incarca obiectul serializat din fisier
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metoda auxiliara pentru incarcarea unei liste
    @SuppressWarnings("unchecked")
    public static <T> List<T> loadList(String filename) {
        Object obj = loadData(filename);
        if (obj instanceof List<?>) {
            return (List<T>) obj;
        } else {
            return null;
        }
    }
}
