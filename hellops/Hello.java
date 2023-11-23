
import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.Date;

public interface Hello extends EJBObject {

    void addHello(String hello) throws RemoteException;

    String sayHello() throws RemoteException;
}
