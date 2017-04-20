/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wipb.jee.clientdemo.ejb;

import wipb.jee.clientdemo.model.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/*
@DataSourceDefinition(
        name="java:global/DemoDataSource",
        className="org.apache.derby.jdbc.ClientDataSource",
        minPoolSize = 1,
        initialPoolSize = 1,
        portNumber = 1527,
        serverName = "localhost",
        user = "app",
        password = "app",
        databaseName = "testdb1",
        properties = {"connectionAttributes=;create=true"}       
)
*/
@Stateless
public class BookDao {
    @PersistenceContext(unitName = "wipb_ee-std-client-demo-ejb_ejb_1.0PU")
    private EntityManager em;
    
    public void save(Book book) {
        em.persist(book);
    }

    public void remove(Long id) {
        em.remove(em.getReference(Book.class, id));
    }

    public List<Book> findAll() {
        TypedQuery<Book> q = em.createNamedQuery("Book.findAll", Book.class);
        return q.getResultList();
    }

    public void update(Long id, String title) {
        TypedQuery<Book> q = em.createNamedQuery("Book.update", Book.class);
        q.setParameter("pid", id);
        q.setParameter("ptitle", title);

        em.getTransaction().begin();
        q.executeUpdate();
        em.getTransaction().commit();
    }

    public List<Book> findByLength(Integer length) {
        TypedQuery<Book> q = em.createNamedQuery("Book.findByLength", Book.class);
        q.setParameter("plength", length);

        return q.getResultList();
    }

    public void sendEmail(String address, String message) {
        System.out.println("Sending email to: " + address + "\n\"" + message);
    }

}
