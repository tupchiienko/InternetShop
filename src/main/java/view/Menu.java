package view;

public interface Menu {
    void show();

    void exit();

    default void showItems(String[] items) {
        System.out.println("-------------");
        for (String item : items) {
            System.out.println(item);
        }
        System.out.println("-------------");
    }
}
