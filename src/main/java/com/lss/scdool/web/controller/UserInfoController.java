package com.lss.scdool.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lss.scdool.annotation.NoAuthentication;
import com.lss.scdool.entity.UserInfo;
import com.lss.scdool.exception.ScException;
import com.lss.scdool.service.UserInfoService;
import com.lss.scdool.util.HttpHelper;
import com.lss.scdool.util.SimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Magic
 * @date 11:34 2018/4/13
 * @description
 */

@Api(tags = {"用户信息相关接口"})
@Slf4j
@RestController
public class UserInfoController {


    private final static String URL = "https://api.weixin.qq.com/sns/jscode2session";
    private final static String APPID="wx6cb527ac53411f8d";
    private final static String SECRET="98243329ed3d440bafa3b56c269bc863";


    @Autowired
    private UserInfoService userInfoService;


    @NoAuthentication
    @ApiOperation(value="小程序用户注册接口",notes = "需要用户部分信息（改接口不需要token验证）")
    @PostMapping("/user")
    public SimpleResponse register(UserInfo info){
        return userInfoService.register(info);
    }


    @NoAuthentication
    @ApiOperation(value="根据openId获取用户信息",notes = "需要openId（改接口不需要token验证）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="openId",value="微信openId")
    })
    @GetMapping("/user")
    public SimpleResponse getUser(@RequestParam(name = "openId") String openId){
        UserInfo user = userInfoService.getUserByOpenId(openId);
        if(user==null){
            return new SimpleResponse(1,"该用户尚未注册",null);
        }
        return new SimpleResponse(userInfoService.getUserByOpenId(openId));
    }


    @NoAuthentication
    @ApiOperation(value="根据code获取微信用户openId",notes = "需要小程序code（改接口不需要token验证）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="code",value="微信code ")
    })
    @GetMapping("/getOpenId")
    public SimpleResponse getOpenId(String code) throws IOException {
        String params = "appid="+APPID+"&secret="+SECRET+"&js_code="+code+"&grant_type=authorization_code";
        String result = HttpHelper.get(URL,params);
        if(StringUtils.isEmpty(result)){
            throw new ScException("获取微信信息失败");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(result,Map.class);
        if(map.get("openid")==null){
            throw new ScException("获取微信信息失败");
        }
        return new SimpleResponse(map);
    }

    @NoAuthentication
    @ApiOperation(value="获取所有教练",notes = "根据科目类型和驾照类型，服务器根据用户自取")
    @GetMapping("/app/trainees")
    public SimpleResponse getTrainees(){
        return  userInfoService.getTrainees();
    }


    @GetMapping("/")
    public void index(HttpServletResponse response) throws Exception{
        response.sendRedirect("/swagger-ui.html");
    }
}
