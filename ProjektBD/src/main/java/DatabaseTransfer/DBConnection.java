package DatabaseTransfer;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DBConnection {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private String colorname;
    public void setDataSource(DataSource dataSource){
          this.dataSource = dataSource;
          this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
}
