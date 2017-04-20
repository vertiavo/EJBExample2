/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wipb.jee.clientdemo.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author ma
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Book.findAll", query = "select b from Book b"),
        @NamedQuery(name = "Book.update", query = "update Book b set b.title = :ptitle where b.id = :pid"),
        @NamedQuery(name = "Book.findByLength", query = "select b from Book b where :plength = LENGTH(b.title)")
})

public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
