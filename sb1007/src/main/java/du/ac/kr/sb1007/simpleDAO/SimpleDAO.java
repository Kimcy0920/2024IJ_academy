package du.ac.kr.sb1007.simpleDAO;

import du.ac.kr.sb1007.simpleDTO.SimpleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SimpleDAO implements ISimpleDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<SimpleDTO> selectDAO() {
        String sql = "select * from simple_bbs order by id desc";
        List<SimpleDTO> listDTO = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<SimpleDTO>(SimpleDTO.class));
        return listDTO;
    }

    @Override
    public SimpleDTO selectOneDAO(String id) {
        return null;
    }

    @Override
    public int insertDAO(String writer, String title, String content) {
        return 0;
    }

    @Override
    public int updateDAO(String id, String writer, String title, String content) {
        return 0;
    }

    @Override
    public int deleteDAO(String id) {
        return 0;
    }
}
