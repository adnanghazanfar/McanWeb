package com.drs.mcan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eamtar.mccn.model.Video;
import com.eamtar.mccn.service.VideoService;
import com.eamtar.mccn.util.Developer;

@Controller
@RequestMapping(value = "/api")
public class VideoRestController {

	@Autowired
	VideoService videoService;
	
    @RequestMapping(value = "/developer", method = RequestMethod.GET)
    public @ResponseBody Developer getDeveloperDetails() {
        Developer dev = new Developer();
        dev.setName("adnan ghazanfar");
        dev.setPhone("+971501455397");
        dev.setWorking("emirates dubai");
        dev.setCountry("pakistan");
        return dev;
    }
    
    @RequestMapping(value = "/video", method = RequestMethod.GET)
    public @ResponseBody List<Video> getAllVideo() {
    	Video videoDTO = new Video();
        List<Video> list = videoService.findViaCriteria(videoDTO, null);
        return list;
    }

}
