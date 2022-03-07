package daoIMP;

import bean.Student;
import connection.DataBaseConnection;
import dao.StudentDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOIMP implements StudentDAO {
    // 添加操作
    @Override
    public void insert(Student s) {
        String sql = "INSERT INTO student (id, name) values (?,?)";
        PreparedStatement pstmt = null;
        DataBaseConnection conn = null;
        //针对数据库的具体操作
        try {
            conn = new DataBaseConnection();

            // .getConnection(url, user, password): 获取链接
            // .prepareStatement(sql): 预编译
            pstmt = conn.getConnection().prepareStatement(sql);
            pstmt.setLong(1, s.getID());
            //pstmt.setString(1,s.getID());
            pstmt.setString(2, s.getName());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
        }
    }

    @Override
    public void update(Student s) {
        String sql = "UPDATE student SET name = ? WHERE id = ?";
        PreparedStatement pstmt = null;
        DataBaseConnection conn = null;

        try {
            conn = new DataBaseConnection();

            pstmt = conn.getConnection().prepareStatement(sql);
            pstmt.setString(1, s.getName());
            pstmt.setLong(2, s.getID());

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String iD) {
        String sql = "DELETE FROM student WHERE id = ?";
        PreparedStatement pstmt = null;
        DataBaseConnection conn = null;

        try {
            conn = new DataBaseConnection();

            pstmt = conn.getConnection().prepareStatement(sql);
            pstmt.setString(1, iD);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List findAll() throws SQLException {
        String sql = "SELECT * FROM student";
        PreparedStatement pstmt = null;
        DataBaseConnection conn = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<Student>();

        try {
            conn = new DataBaseConnection();

            pstmt = conn.getConnection().prepareStatement(sql);

            resultSet = pstmt.executeQuery();

            Student student = new Student();
            //判断下一条是否有数据
            if (resultSet.next()) {
                student.setID(resultSet.getLong(1));
                student.setName(resultSet.getString(2));
                list.add(student);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Student findByID(long iD) throws SQLException {
        String sql = "SELECT * FROM student WHERE id = ?";
        PreparedStatement pstmt = null;
        DataBaseConnection conn = null;
        ResultSet resultSet = null;
        Student student = new Student();

        try {
            conn = new DataBaseConnection();

            pstmt = conn.getConnection().prepareStatement(sql);
            pstmt.setLong(1, iD);

            resultSet = pstmt.executeQuery();

            student.setID(resultSet.getLong(1));
            student.setName(resultSet.getString(2));

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

}
