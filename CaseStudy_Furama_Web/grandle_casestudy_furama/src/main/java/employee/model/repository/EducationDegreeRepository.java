package employee.model.repository;

import employee.model.bean.EducationDegree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EducationDegreeRepository {
    BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_EDUCATION = "select education_degree_name from education_degree where education_degree_id =?";
    private static final String SELECT_ALL_EDUCATION = "select * from education_degree ";

    public EducationDegree selectEducation(int id){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        EducationDegree educationDegree = null;

        try {
            statement = connection.prepareStatement(SELECT_EDUCATION);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name = rs.getString("education_degree_name");
                educationDegree = new EducationDegree(id,name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ;

        }
        return educationDegree;
    }
    public List<EducationDegree> selectAllEducation(){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<EducationDegree> educationDegree = new ArrayList<>();

        try {
            statement = connection.prepareStatement(SELECT_ALL_EDUCATION);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("education_degree_id");
                String name = rs.getString("education_degree_name");
                educationDegree.add(new EducationDegree(id,name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ;

        }
        return educationDegree;
    }

}
