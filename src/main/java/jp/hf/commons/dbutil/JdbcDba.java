package jp.hf.commons.dbutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Scope("prototype")
public abstract class JdbcDba {

    @Autowired
    protected NamedParameterJdbcTemplate db;

    public int insert(Entity arg) {
        db.update(QueryBuilder.queryInsert(arg.getClass()),
                new BaseBeanPropertySqlParameterSource(arg));
        return lastId();
    }

    public int update(Entity arg) {
        return db.update(QueryBuilder.queryUpdate(arg.getClass()),
                new BaseBeanPropertySqlParameterSource(arg));
    }

    public int delete(Entity arg) {
        return db.update(QueryBuilder.queryDelete(arg.getClass()),
                new BaseBeanPropertySqlParameterSource(arg));
    }

    public int lastId() {
        return db.queryForObject("SELECT LAST_INSERT_ID()", new MapSqlParameterSource(),
                Integer.class);
    }

    public int save(Entity arg) {
        try {
            return this.insert(arg);
        } catch (DuplicateKeyException e) {
            return this.update(arg);
        }
    }

    public int deleteInsert(Entity arg) {
        try {
            this.delete(arg);
        } catch (Exception e) {
            // スルー
        }
        return this.insert(arg);
    }

}
