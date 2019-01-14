import com.polytech.bsm.hibernate.App;
import com.polytech.bsm.hibernate.view.MainView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main  {

    public static void main(String[] args) {

        // Init
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tpHibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Scanner
        Scanner reader = new Scanner(System.in);

        //Manualy create database from App class
        //App app = new App(entityManager);
        //app.createDatabase();


        // Run the main menu
        MainView.mainMenu(entityManager);

        // Close everything
        entityManager.close();
        entityManagerFactory.close();
        reader.close();
    }
}
