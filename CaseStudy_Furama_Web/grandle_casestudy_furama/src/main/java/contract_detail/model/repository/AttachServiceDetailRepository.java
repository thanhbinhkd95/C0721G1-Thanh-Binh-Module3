package contract_detail.model.repository;

import contract_detail.model.bean.AttachService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttachServiceDetailRepository {
    BaseRepository baseRepository = new BaseRepository();
    private static final String SELECT_ATTACH = "select * from attach_service where attach_service_id = ? ";
    private static final String SELECT_ALL_ATTACH = "select * from attach_service";

    public AttachService selectAttach(int id){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        AttachService attachService = null;
        try {
            statement = connection.prepareStatement(SELECT_ATTACH);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String name = rs.getString("attach_service_name");
                double cost = rs.getDouble("attach_service_cost");
                int unit = rs.getInt("attach_service_unit");
                String status = rs.getString("attach_service_status");
                attachService = new AttachService(id,name,cost,unit,status);
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

        }
        return attachService;
    }
    public List<AttachService> selecAlltAttach(){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;
        List<AttachService> attachService = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_ALL_ATTACH);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("attach_service_id");
                String name = rs.getString("attach_service_name");
                double cost = rs.getDouble("attach_service_cost");
                int unit = rs.getInt("attach_service_unit");
                String status = rs.getString("attach_service_status");
                attachService.add(new AttachService(id,name,cost,unit,status));
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

        }
        return attachService;
    }
}
