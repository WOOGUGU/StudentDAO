package dao;
import bean.*;

import java.sql.SQLException;
import java.util.*;
public interface StudentDAO{
    public void insert(Student s);
    public void update(Student s);
    public void delete(String iD);
    public Student findByID(long iD) throws SQLException;
    public List<Student> findAll() throws SQLException;
} 

