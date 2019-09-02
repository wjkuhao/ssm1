package com.wangjian.web;

import com.wangjian.Role;
import com.wangjian.UserInfo;
import com.wangjian.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping("/findAll")
    /*使用jsr250在方法级别上进行权限控制*/
//    @RolesAllowed("User")
    /*使用@Secured进行方法级别上进行权限控制，要把角色的全部名称都写上*/
//    @Secured("ROLE_USER")
//    @PreAuthorize("authentication.principal.username=='tom'")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list = userInfoService.findAll();
        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/save")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String save(UserInfo userInfo){
        userInfoService.save(userInfo);
        return "redirect:findAll";
    }
    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userInfoService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("/findUserByIdAndAllRole")
    public  ModelAndView findUserByIdAndAllRole(@RequestParam(name ="id") String id){
        ModelAndView mv = new ModelAndView();
        UserInfo byId = userInfoService.findById(id);
        mv.addObject("user",byId);
        List<Role> userByIdAndAllRole = userInfoService.findUserByIdAndAllRole(id);
        mv.addObject("roleList",userByIdAndAllRole);
        mv.setViewName("user-role-add");
        return mv;
    }
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId") String id, @RequestParam(name = "ids",required = true) String[] roleIds){
        userInfoService.addRoleToUser(id,roleIds);
        return "redirect:findAll";
    }
}
