package dat152.ajax.dataaccess;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import dat152.ajax.model.Student;

@Stateless
public class StudentEAO {

    @PersistenceContext(name = "studentPersistenceUnit")
    private EntityManager em;

    public void addStudent(Student t) {
        em.persist(t);
    }
    
    public List<Student> getStudents() {
        TypedQuery<Student> query = em.createNamedQuery("Student.findAll", Student.class);
        return query.getResultList();
    }

    public void deleteStudent(Integer studentId) {
        Student s = em.find(Student.class, studentId);
        if (s != null) {
            em.remove(s);
        }
    }
}
