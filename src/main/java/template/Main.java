package template;


public class Main {
    public static void main(String[] args) {
        if (System.getProperty("spring.profiles.active") == null && System.getenv("spring_profiles_active") == null) {
            System.setProperty("spring.profiles.active", "dev");
        } 
        
        new CustomDeployer().deploy();
    }
}
