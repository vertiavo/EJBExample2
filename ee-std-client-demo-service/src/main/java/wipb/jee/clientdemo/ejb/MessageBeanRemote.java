package wipb.jee.clientdemo.ejb;

import wipb.jee.clientdemo.model.Book;

import javax.ejb.Asynchronous;
import javax.ejb.Remote;
import java.util.List;

/**
 *
 * @author dr
 */
@Remote
public interface MessageBeanRemote {

    String getMessage();
    
    void save(Book book);
    List<Book> findAll();
    void remove(Long id);
    void update(Long id, String title);
    @Asynchronous
    List<Book> findByLength(Integer length);
    @Asynchronous
    void sendEmail(String address, String message);
    
}
