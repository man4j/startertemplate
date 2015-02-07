package template;


public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");
        
        new CustomDeployer().deploy();
    }
}
