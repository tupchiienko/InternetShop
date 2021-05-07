import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;
import view.impl.LoginMenu;

public class Main {
    public static void main(String[] args) {
        new LoginMenu(new OrderServiceImpl(), new UserServiceImpl(), new ProductServiceImpl()).show();
    }
}
