package service.student;

import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudentService{
    private Connection connection = config.Connection.getConnection();

    @Override
    public boolean insert(Student student) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Student student) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Student findById(int id) throws SQLException {
        Student student = null;
        PreparedStatement statement = connection.prepareStatement("select * from student where id=?;");
        statement.setInt(1,id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            String name = rs.getString("name");
            String className = rs.getString("className");
            student = new Student(id,name,className);
        }
        return student;
    }

    @Override
    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement("select * from student;");
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String className = rs.getString("className");
            students.add(new Student(id,name,className));

        }
        return students;
    }

}
