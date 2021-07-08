package com.qiashe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiashe.dao.TableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/table")
public class TableController {
    @Autowired
    private TableDao dao;
    @RequestMapping("/findColName")
    @ResponseBody
    public String findColName(String tableName) throws IOException {
        List<String> colNames=dao.selectColName(tableName);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(colNames);
        return json;
    }
}
