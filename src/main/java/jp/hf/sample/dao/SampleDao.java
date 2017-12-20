package jp.hf.sample.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.hf.commons.dbutil.JdbcDba;
import jp.hf.sample.entity.SampleEntity;

@Repository
public class SampleDao extends JdbcDba {

    public List<SampleEntity> findAll() {
        // @formatter:off
        List<SampleEntity> list = db.query("select * from t_sample",
                new MapSqlParameterSource(),
                new SampleEntity());
        // @formatter:on
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

}
