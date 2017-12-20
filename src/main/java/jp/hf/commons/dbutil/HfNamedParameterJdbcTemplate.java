package jp.hf.commons.dbutil;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class HfNamedParameterJdbcTemplate extends NamedParameterJdbcTemplate {

    public HfNamedParameterJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

}
