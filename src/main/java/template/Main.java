package template;


public class Main {
    public static void main(String[] args) {
        if (System.getProperty("spring.profiles.active") == null) {
            System.setProperty("spring.profiles.active", "dev");
        }
        
        new CustomDeployer().deploy();
    }
}
