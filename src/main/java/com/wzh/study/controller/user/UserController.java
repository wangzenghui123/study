package com.wzh.study.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzh.study.code.ResponseCode;
import com.wzh.study.service.user.UserService;
import com.wzh.study.util.DataResult;
import com.wzh.study.vo.reqVO.user.UserLoginReqVO;
import com.wzh.study.vo.respVO.user.UserLoginRespVO;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/user")
    public String user(){
        return "/user/user";
    }

    @PostMapping(value = "/user/userLogin",produces ={MediaType.APPLICATION_JSON_VALUE} )
    @ResponseBody
    public DataResult<UserLoginRespVO> userLogin(@RequestBody UserLoginReqVO userLoginReqVO){

        return userService.userLogin(userLoginReqVO);

    }

    @GetMapping("/user/token")
    @ResponseBody
    public DataResult refreshAccessToken(ServletRequest request){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String accessToken1 = httpServletRequest.getHeader("AccessToken");
        System.out.println(accessToken1);
        String accessToken = userService.refreshAccessToken(accessToken1);

        DataResult<String> dataResult = new DataResult<>(ResponseCode.SUCCESS);
        dataResult.setData(accessToken);

        return dataResult;

    }

    @GetMapping("/user/show")
    @ResponseBody
    public DataResult<Object> userShow() throws JsonProcessingException {

        DataResult<Object> dataResult = new DataResult<>(ResponseCode.SUCCESS);
        dataResult.setData(getMockData());
        return dataResult;

    }
    public Object getMockData() throws JsonProcessingException {
        String str = "{\n" +
                "  \"code\": 0\n" +
                "  ,\"msg\": \"\"\n" +
                "  ,\"count\": 1000\n" +
                "  ,\"totalRow\": {\n" +
                "    \"era\": {\n" +
                "      \"tang\": \"2\",\n" +
                "      \"song\": \"2\",\n" +
                "      \"xian\": \"20\"\n" +
                "    }\n" +
                "  }\n" +
                "  ,\"data\": [{\n" +
                "    \"id\": \"10001\"\n" +
                "    ,\"username\": \"李白\"\n" +
                "    ,\"email\": \"test1@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"君不见，黄河之水天上来，奔流到海不复回。 君不见，高堂明镜悲白发，朝如青丝暮成雪。 人生得意须尽欢，莫使金樽空对月。 天生我材必有用，千金散尽还复来。 烹羊宰牛且为乐，会须一饮三百杯。 岑夫子，丹丘生，将进酒，杯莫停。 与君歌一曲，请君为我倾耳听。(倾耳听 一作：侧耳听) 钟鼓馔玉不足贵，但愿长醉不复醒。(不足贵 一作：何足贵；不复醒 一作：不愿醒/不用醒) 古来圣贤皆寂寞，惟有饮者留其名。(古来 一作：自古；惟 通：唯) 陈王昔时宴平乐，斗酒十千恣欢谑。 主人何为言少钱，径须沽取对君酌。 五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。\"\n" +
                "    ,\"experience\": \"12\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10002\"\n" +
                "    ,\"username\": \"杜甫\"\n" +
                "    ,\"email\": \"test2@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"舍南舍北皆春水，但见群鸥日日来。花径不曾缘客扫，蓬门今始为君开。盘飧市远无兼味，樽酒家贫只旧醅。肯与邻翁相对饮，隔篱呼取尽余杯。\"\n" +
                "    ,\"experience\": \"116\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"108\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "    ,\"LAY_CHECKED\": true\n" +
                "  }, {\n" +
                "    \"id\": \"10003\"\n" +
                "    ,\"username\": \"苏轼\"\n" +
                "    ,\"email\": \"test3@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"大江东去，浪淘尽，千古风流人物。故垒西边，人道是，三国周郎赤壁。乱石穿空，惊涛拍岸，卷起千堆雪。江山如画，一时多少豪杰。遥想公瑾当年，小乔初嫁了，雄姿英发。羽扇纶巾，谈笑间，樯橹灰飞烟灭。故国神游，多情应笑我，早生华发。人生如梦，一尊还酹江月。\"\n" +
                "    ,\"experience\": \"65\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10004\"\n" +
                "    ,\"username\": \"李清照\"\n" +
                "    ,\"email\": \"test4@email.com\"\n" +
                "    ,\"sex\": \"女\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"昨夜雨疏风骤，浓睡不消残酒。试问卷帘人，却道海棠依旧。知否，知否？应是绿肥红瘦。\"\n" +
                "    ,\"experience\": \"777\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10005\"\n" +
                "    ,\"username\": \"冰心\"\n" +
                "    ,\"email\": \"test5@email.com\"\n" +
                "    ,\"sex\": \"女\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"86\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10006\"\n" +
                "    ,\"username\": \"张三\"\n" +
                "    ,\"email\": \"test6@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"12\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10007\"\n" +
                "    ,\"username\": \"张三7\"\n" +
                "    ,\"email\": \"test7@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"16\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10008\"\n" +
                "    ,\"username\": \"张三8\"\n" +
                "    ,\"email\": \"test8@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10009\"\n" +
                "    ,\"username\": \"张三9\"\n" +
                "    ,\"email\": \"test9@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10010\"\n" +
                "    ,\"username\": \"张三10\"\n" +
                "    ,\"email\": \"test10@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10011\"\n" +
                "    ,\"username\": \"张三11\"\n" +
                "    ,\"email\": \"test11@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10012\"\n" +
                "    ,\"username\": \"张三12\"\n" +
                "    ,\"email\": \"test12@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10013\"\n" +
                "    ,\"username\": \"张三13\"\n" +
                "    ,\"email\": \"test13@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10014\"\n" +
                "    ,\"username\": \"张三14\"\n" +
                "    ,\"email\": \"test14@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10015\"\n" +
                "    ,\"username\": \"张三15\"\n" +
                "    ,\"email\": \"test15@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10016\"\n" +
                "    ,\"username\": \"张三16\"\n" +
                "    ,\"email\": \"test16@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10017\"\n" +
                "    ,\"username\": \"张三17\"\n" +
                "    ,\"email\": \"test17@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10018\"\n" +
                "    ,\"username\": \"张三18\"\n" +
                "    ,\"email\": \"test18@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10019\"\n" +
                "    ,\"username\": \"张三19\"\n" +
                "    ,\"email\": \"test19@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10020\"\n" +
                "    ,\"username\": \"张三20\"\n" +
                "    ,\"email\": \"test20@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10021\"\n" +
                "    ,\"username\": \"张三21\"\n" +
                "    ,\"email\": \"test21@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10022\"\n" +
                "    ,\"username\": \"张三22\"\n" +
                "    ,\"email\": \"test22@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }, {\n" +
                "    \"id\": \"10023\"\n" +
                "    ,\"username\": \"张三23\"\n" +
                "    ,\"email\": \"test23@email.com\"\n" +
                "    ,\"sex\": \"男\"\n" +
                "    ,\"city\": \"浙江杭州\"\n" +
                "    ,\"sign\": \"保持真善美，温和待人\"\n" +
                "    ,\"experience\": \"106\"\n" +
                "    ,\"ip\": \"192.168.0.8\"\n" +
                "    ,\"checkin\": \"106\"\n" +
                "    ,\"joinTime\": \"2016-10-14\"\n" +
                "  }]\n" +
                "}  ";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(str);
        System.out.println(jsonNode);
        return jsonNode;
    }
}
