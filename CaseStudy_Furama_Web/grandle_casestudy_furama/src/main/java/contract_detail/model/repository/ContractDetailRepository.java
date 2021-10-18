package contract_detail.model.repository;

import contract.model.repository.ContractRepository;
import contract_detail.model.bean.ContractDetails;
import customers.model.repository.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContractDetailRepository {
    BaseRepository baseRepository = new BaseRepository();
    ContractRepository contractRepository = new ContractRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    AttachServiceDetailRepository attachServiceDetailRepository = new AttachServiceDetailRepository();
    private static final String INSERT_CONTRACT_DETAIL = "insert into contract_detail(contract_id,attach_service_id,quantity) values (?,?,?)";
//    private static final String SELECT_ALL_CONTRACT_DETAIL = "select * form contract_detail";

    public void insertContactDetail(ContractDetails contractDetails){
        Connection connection = baseRepository.getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(INSERT_CONTRACT_DETAIL);
            statement.setInt(1,contractDetails.getContractId().getContractId());
            statement.setInt(2,contractDetails.getAttachSerciceId().getAttachServiceId());
            statement.setInt(3,contractDetails.getQuantity());
            statement.executeUpdate();
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
    }

}
