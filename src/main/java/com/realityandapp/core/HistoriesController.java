package com.realityandapp.core;

import com.realityandapp.constants.DefaultConfigs;
import com.realityandapp.model.History;
import com.realityandapp.model.v2.Celebrity;
import com.realityandapp.model.v2.Subject;

import java.util.*;

/**
 * Created by dd on 14-4-11.
 */
public class HistoriesController {
    public List<History> histories = new ArrayList<History>();

    protected static HistoriesController _factory = null;

    public static HistoriesController getFactory() {
        if (_factory == null)
            _factory = new HistoriesController();
        return _factory;
    }

    public static void setFactory(HistoriesController s) {
        _factory = s;
    }

    public void record(Subject subject){
        History history = new History();
        history.setType(History.Type.SUBJECT);
        history.setId(subject.getId());
        history.setName(subject.getTitle());
        history.setImages(subject.getImages());
        history.setView_at(Calendar.getInstance().getTime());
        histories.add(history);
        writeConfiguration();
    }

    public void record(Celebrity celebrity){
        History history = new History();
        history.setType(History.Type.CELEBRITY);
        history.setId(celebrity.getId());
        history.setName(celebrity.getName());
        history.setImages(celebrity.getAvatars());
        history.setView_at(Calendar.getInstance().getTime());
        histories.add(history);
        writeConfiguration();
    }


    public static void writeConfiguration() {
        PropertiesUtil.writeConfiguration(DefaultConfigs.SDCARD_PATH, DefaultConfigs.HISTORIES_FILENAME, getFactory());
    }

    public static void readConfiguration() {
        HistoriesController obj = PropertiesUtil.readConfiguration(DefaultConfigs.SDCARD_PATH,DefaultConfigs.HISTORIES_FILENAME,HistoriesController.class);
        if(obj != null)
            HistoriesController.setFactory(obj);
        else
            HistoriesController.getFactory();
    }
}
