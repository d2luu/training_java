package jp.hf.sample.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.hf.sample.entity.SampleEntity;

@Component
public class SampleView {

    List<SampleEntity> sampleList;

    public SampleView() {
        super();
        sampleList = new ArrayList<>();
    }

    public List<SampleEntity> getSampleList() {
        return sampleList;
    }

    public void setSampleList(List<SampleEntity> sampleList) {
        this.sampleList = sampleList;
    }

}
