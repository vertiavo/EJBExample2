package wipb.jee.clientdemo.ejb;

import wipb.jee.clientdemo.model.Book;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless
@Interceptors(Interceptor.class)
public class MessageBean implements MessageBeanRemote {

    @EJB
    private BookDao bookDao;
    
    @Override
    public String getMessage() {
        return "Hello from Remote EJB Bean!";
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void remove(Long id) {
        bookDao.remove(id);
    }

    @Override
    public void update(Long id, String title) {
        bookDao.update(id, title);
    }

    @Override
    public List<Book> findByLength(Integer length) {
        return bookDao.findByLength(length);
    }

    @Override
    public void sendEmail(String address, String message) {
        bookDao.sendEmail(address, message);
    }
}
