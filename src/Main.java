import com.polytech.bsm.hibernate.App;
import com.polytech.bsm.hibernate.controller.dao.DockDAO;
import com.polytech.bsm.hibernate.controller.dao.SpaceDAO;

public class Main
{

    public static void main(String[] args)
    {
        App app = new App();
        //app.createDatabase();
        SpaceDAO.getSpaceWithSailboat(app.getEntityManager());
        app.close();
    }

}
