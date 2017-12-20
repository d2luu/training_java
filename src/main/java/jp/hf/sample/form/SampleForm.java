package jp.hf.sample.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class SampleForm {

    @NotEmpty
    @Pattern(regexp = "[0-9]+")
    String sampleId;
    @NotEmpty
    @Size(max = 500)
    String sampleName;

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

}
