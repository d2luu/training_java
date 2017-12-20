package jp.hf.commons.dbutil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;

public class BaseBeanPropertySqlParameterSource extends BeanPropertySqlParameterSource {

    public BaseBeanPropertySqlParameterSource(Object object) {
        super(object);
    }

    @Override
    public Object getValue(String paramName) throws IllegalArgumentException {
        Object result = super.getValue(paramName);
        if (result != null && result instanceof LocalDateTime) {
            return Timestamp.valueOf((LocalDateTime) result);
        } else {
            return result;
        }
    }

}
