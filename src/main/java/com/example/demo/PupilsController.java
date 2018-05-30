package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PupilsController {


    Pupils pupils = new Pupils();

    @RequestMapping(value = "/pupil" , method = RequestMethod.GET)
    public List<PupilsItem> getPupilItems(@RequestParam(value="searchstring", defaultValue = "") String searchString) {

        return pupils.getPupils(searchString);
    }


    @RequestMapping(value = "/pupil/{id}" , method = RequestMethod.GET)
    public PupilsItem getPupilItem(@PathVariable("id") String id) {

        return pupils.getPupilById(id);
    }

    @RequestMapping(value = "/pupil" , method = RequestMethod.POST)
    public PupilsItem postPupilItem(@RequestBody PupilsItem item) {

        pupils.addPupil(item);
        pupils.writeJsonToFile();
        return item;
    }

    @RequestMapping(value = "/pupil/{id}" , method = RequestMethod.DELETE)
    public List<PupilsItem> deletePupilItem(@PathVariable("id") String id ) {

        //ta hand om ev. illegalstateexception/concurrencymodificationexception o dylikt.
        try {
            pupils.removePupilItem(id);
            pupils.writeJsonToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pupils.getPupilsList();
    }


    @RequestMapping(value = "/pupil/{id}/{name}" , method = RequestMethod.PUT)
    public List<PupilsItem> updatePupilItem(@PathVariable("id") String id, @PathVariable("name") String name) {

        String[] params = {id, name};
        List <PupilsItem> pupilsItemList = pupils.updatePupil(params);
        pupils.writeJsonToFile();
        return  pupilsItemList;
    }

}
