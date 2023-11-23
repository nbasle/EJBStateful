
import javax.ejb.SessionBean;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.EJBException;
import java.util.Date;
import java.rmi.RemoteException;

public class HelloBean implements SessionBean {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String buffer = "";

    // ======================================
    // =            Constructors            =
    // ======================================
    public HelloBean() {
    }

    // ======================================
    // =     Category Business Methods      =
    // ======================================
    public String sayHello() {
        return buffer;
    }

    public void addHello(String hello) {
        buffer += hello + " ";
    }

    // ======================================
    // =            EJB callbacks           =
    // ======================================
    public void ejbCreate() throws CreateException {
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {
    }

    public void ejbRemove() throws EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }
}
