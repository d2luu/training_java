package jp.hf.sample.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jp.hf.sample.dao.SampleDao;
import jp.hf.sample.view.SampleView;

@Scope("prototype")
@Component
public class SampleModel {

    @Autowired
    SampleView view;
    @Autowired
    SampleDao dao;

    public SampleView findAll() {
        view.setSampleList(dao.findAll());
        return view;
    }

}
