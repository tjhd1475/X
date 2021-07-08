package com.qiashe.dao;

import java.util.List;

public interface TableDao {
    List<String> selectColName(String tableName);
}
