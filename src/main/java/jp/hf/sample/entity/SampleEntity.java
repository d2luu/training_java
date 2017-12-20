package jp.hf.sample.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import jp.hf.commons.annotation.AutoIncrement;
import jp.hf.commons.annotation.Col;
import jp.hf.commons.annotation.Pkey;
import jp.hf.commons.dbutil.BaseRowMapper;
import jp.hf.commons.dbutil.Entity;

public class SampleEntity extends BaseRowMapper<SampleEntity> implements Entity {

    @Pkey("sample_id")
    @AutoIncrement
    private long sampleId;
    @Col("sample_name")
    private String sampleName;

    @Override
    public SampleEntity mapRowImpl(ResultSet rs, int rownum) throws SQLException {
        SampleEntity ret = new SampleEntity();
        ret.setSampleId(getLong("sample_id"));
        ret.setSampleName(getString("sample_name"));

        return ret;
    }

    public long getSampleId() {
        return sampleId;
    }

    public void setSampleId(long sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }
}
