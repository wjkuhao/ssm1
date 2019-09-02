package com.wangjian.web;

import com.wangjian.SysLog;
import com.wangjian.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogControle {
    @Autowired
    private SysLogService sysLogService;
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<SysLog> all = sysLogService.findAll();
        mv.addObject("",all);
        mv.setViewName("sysLogs");
        return mv;
    }
}
