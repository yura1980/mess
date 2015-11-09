package com.beingjavaguys.controller;

import com.beingjavaguys.model.SprAvtors;
import java.util.List;

import org.apache.log4j.Logger;
import com.beingjavaguys.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beingjavaguys.model.Table1;
import com.beingjavaguys.services.DataServices;

@Controller
@RequestMapping("/mess")
public class RestController {

    @Autowired
    DataServices dataServices;

    static final Logger logger = Logger.getLogger(RestController.class);

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Status addEmployee(@RequestBody Table1 ms) {
        try {
            if(ms.getSprAvtorsId().getId()<0){
                SprAvtors au = new SprAvtors();
                au.setAvtor(ms.getSprAvtorsId().getAvtor());
                au = dataServices.addEntityAut(au);
                ms.setSprAvtorsId(au);
            }
            dataServices.addEntity(ms);
            return new Status(1, "Messege added Successfully !");
        } catch (Exception e) {
            // e.printStackTrace();
            return new Status(0, e.toString());
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Table1 getEmployee(@PathVariable("id") long id) {
        Table1 ms = null;
        try {
            ms = dataServices.getEntityById(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ms;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<Table1> getEmployee() {

        List<Table1> msList = null;
        try {
            msList = dataServices.getEntityList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msList;
    }
    
    @RequestMapping(value = "/listAu", method = RequestMethod.GET)
    public @ResponseBody List<SprAvtors> getEmployeeAu() {

        List<SprAvtors> msList = null;
        try {
            msList = dataServices.getEntityListAu();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return msList;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public @ResponseBody Status deleteEmployee(@PathVariable("id") long id) {

        try {
            dataServices.deleteEntity(id);
            return new Status(1, "Messege deleted Successfully !");
        } catch (Exception e) {
            return new Status(0, e.toString());
        }

    }
}
