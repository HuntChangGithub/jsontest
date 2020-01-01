package com.hunt.jsontest.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hunt.jsontest.pojo.Person;
import com.hunt.jsontest.vo.ResultViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by asus on 2019/12/26.
 */
@Controller
public class TestController {


    @RequestMapping(value = "/testMethod" , method = RequestMethod.POST)
    @ResponseBody
    public ResultViewModel getInfo(@RequestBody String userList){
        ResultViewModel resultViewModel = new ResultViewModel();
        try {
            JSONObject jsonObject = JSONObject.parseObject(userList);
            JSONArray jsonArray = jsonObject.getJSONArray("persons");
            for (int i = 0; i <jsonArray.size(); i++) {
                JSONObject jsonArrayObjectItem = JSONObject.parseObject(jsonArray.get(i).toString());
                if(jsonArrayObjectItem!=null){
                    Person person = new Person();
                    person.setId(jsonArrayObjectItem.getString("id"));
                    person.setName(jsonArrayObjectItem.getString("name"));
                    person.setAddress(jsonArrayObjectItem.getString("address"));
                    System.out.println(person);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        resultViewModel.setStat(200);
        resultViewModel.setMsg("ok");
        return resultViewModel;

    }


}
