# DSL相关语法语句

### 创建索引库
```
PUT /heima
{
  "mappings": {
    "properties": {
      "info": {
        "type": "text",
        "analyzer": "ik_smart"
      },
      "email": {
        "type": "keyword",
        "index": false
      },
      "name": {
        "type": "object",
        "properties": {
          "firstName": {
            "type": "keyword"
          },
          "lastName": {
            "type": "keyword"
          }
        }
      }
    }
  }
}
```

### 查询索引库
```
GET /heima
```

### 修改索引库时，只能添加新字段，不能更改原字段
```
PUT /heima/_mapping
{
  "properties": {
    "age": {
      "type": "integer"
    }
  }
}
```

### 删除索引库
```
DELETE /heima
```

### 插入文档
```
POST /heima/_doc/1
{
  "info": "黑马程序员Jaba讲师",
  "email": "zy@itcast.cn",
  "name": {
    "firstName": "云",
    "lastName": "赵"
  }
}
```

### 查询文档
```
GET /heima/_doc/1
```

### 删除文档
```
DELETE /heima/_doc/1
```

### 全量修改文档 
``` 
PUT /heima/_doc/1
{
  "info": "黑马程序员Jaba讲师",
  "email": "ZhaoYun@itcast.cn",
  "name": {
    "firstName": "云",
    "lastName": "赵"
  }
}
```

### 局部修改文档字段
```
POST /heima/_update/1
{
  "doc": {
    "email": "ZYun@itcast.cn"
  }
}
```

### 酒店的mapping
```
PUT /hotel
{
  "mappings": {
    "properties": { 
      "id": {
        "type": "keyword"
      },
      "name": {
        "type": "text",
        "analyzer": "ik_max_word",
        "copy_to": "all"
      },
      "address": {
        "type": "keyword",
        "index": false
      },
      "price": {
        "type": "integer"
      },
      "score": {
        "type": "integer"
      },
      "brand": {
        "type": "keyword",
        "copy_to": "all"
      },
      "city": {
        "type": "keyword"
      },
      "starName": {
        "type": "keyword"
      },
      "business": {
        "type": "keyword",
        "copy_to": "all"
      },
      "location": {
        "type": "geo_point"
      },
      "pic": {
        "type": "keyword",
        "index": false
      },
      "all": {
        "type": "text",
        "analyzer": "ik_max_word"
      }
    }
  }
}
```

### 查询索引库
```
GET /hotel
```

### 查询文档
```
GET /hotel/_doc/61083
```

### 查询所有
```
GET /hotel/_search
{
  "query": {
    "match_all": {}
  }
}
```
### match查询
```
GET /hotel/_search
{
  "query": {
    "match": {
      "all": "外滩如家"
    }
  }
}
```

### multi_match查询
```
GET /hotel/_search
{
  "query": {
    "multi_match": {
      "query": "外滩如家",
      "fields": ["brand","name","business"]
    }
  }
}
```

### term查询
```
GET /hotel/_search
{
  "query": {
    "term": {
      "city": {
        "value": "上海"
      }
    }
  }
}
```

### range查询
```
GET /hotel/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 100,
        "lte": 300
      }
    }
  }
}
```

### distance查询
```
GET /hotel/_search
{
  "query": {
    "geo_distance": {
      "distance":  "5km",
      "location": "31.21,121.5"
    }
  }
}
```

### function score查询
```
GET /hotel/_search
{
  "query": {
    "function_score": {
      "query": {
        "match": {
          "all": "外滩"
        }
      },
      "functions": [
        {
          "filter": {
            "term": {
              "brand": "如家"
            }
          },
          "weight": 10
        }
      ],
      "boost_mode": "sum"
    }
  }
}
```

### bool查询
```
GET /hotel/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "name": "如家"
          }
        }
      ],
      "must_not": [
        {
          "range": {
            "price": {
              "gt": 400
            }
          }
        }
      ],
      "filter": [
        {
          "geo_distance": {
            "distance": "10km",
            "location": {
              "lat": 31.21,
              "lon": 121.5
            }
          }
        }
      ]
    }
  }
}
```

### sort排序
```
GET /hotel/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "score": "desc"
    },
    {
      "price": "asc"
    }
  ]
}
```

### 找到121.612282,31.034661周围的酒店，距离升序排序
```
GET /hotel/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "_geo_distance": {
        "location": {
          "lat": 31.034661,
          "lon": 121.612282
        }, 
        "order": "asc",
        "unit": "km"
      }
    }
  ]
}
```

### 分页查询
```
GET /hotel/_search
{
  "query": {
    "match_all": {}
  },
  "sort": [
    {
      "price": "asc"
    }
  ],
  "from": 10,
  "size": 10
}
```

### 高亮查询，默认情况下，ES搜索字段必须与高亮字段一致
```
GET /hotel/_search
{
  "query": {
    "match": {
      "all": "如家"
    }
  },
  "highlight": {
    "fields": {
      "name": {
        "require_field_match": "false"
      }
    }
  }
}
```

### 添加isAD字段
```
POST /hotel/_update/1902197537
{
  "doc": {
    "isAD": true
  }
}
POST /hotel/_update/2056126831
{
  "doc": {
    "isAD": true
  }
}
POST /hotel/_update/1989806195
{
  "doc": {
    "isAD": true
  }
}
POST /hotel/_update/2056105938
{
  "doc": {
    "isAD": true
  }
}
```

### 聚合功能
```
GET /hotel/_search
{
  "size": 0,
  "aggs": {
    "brandAgg": {
      "terms": {
        "field": "brand",
        "size": 10
      }
    }
  }
}
```

### 聚合功能,自定义排序规则
```
GET /hotel/_search
{
  "size": 0,
  "aggs": {
    "brandAgg": {
      "terms": {
        "field": "brand",
        "size": 10,
        "order": {
          "_count": "asc"
        }
      }
    }
  }
}
```

### 聚合功能,限定聚合范围
```
GET /hotel/_search
{
  "query": {
    "range": {
      "price": {
        "lte": 200
      }
    }
  }, 
  "size": 0,
  "aggs": {
    "brandAgg": {
      "terms": {
        "field": "brand",
        "size": 20
      }
    }
  }
}
```

### 嵌套聚合metric
```
GET /hotel/_search
{
  "size": 0,
  "aggs": {
    "brandAgg": {
      "terms": {
        "field": "brand",
        "size": 10,
        "order": {
          "scoreAgg.avg": "desc"
        }
      },
      "aggs": {
        "scoreAgg": {
          "stats": {
            "field": "score"
          }
        }
      }
    }
  }
}
```

### ik_smart分词器（粗粒度）
```
POST /_analyze
{
  "text": ["如家酒店还不错"],
  "analyzer": "ik_smart"
}
```

### ik_max_word分词器（细粒度）
```
POST /_analyze
{
  "text": ["如家酒店还不错"],
  "analyzer": "ik_max_word"
}
```

### py分词器（拼音）
```
POST /test/_analyze
{
  "text": ["如家酒店还不错"],
  "analyzer": "my_analyzer"
}
```

### 删除索引库
```
DELETE /test
```

### 自定义分词器
```
PUT /test
{
  "settings": {
    "analysis": {
      "analyzer": { 
        "my_analyzer": { 
          "tokenizer": "ik_max_word",
          "filter": "py"
        }
      },
      "filter": {
        "py": { 
          "type": "pinyin",
          "keep_full_pinyin": false,
          "keep_joined_full_pinyin": true,
          "keep_original": true,
          "limit_first_letter_length": 16,
          "remove_duplicated_term": true,
          "none_chinese_pinyin_tokenize": false
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "name": {
        "type": "text",
        "analyzer": "my_analyzer",
        "search_analyzer": "ik_smart"
      }
    }
  }
}
```

### 插入数据
```
POST /test/_doc/1
{
  "id": 1,
  "name": "狮子"
}
```
### 插入数据
```
POST /test/_doc/2
{
  "id": 2,
  "name": "虱子"
}
```

### 查询数据
```
GET /test/_search
{
  "query": {
    "match": {
      "name": "掉入狮子笼咋办"
    }
  }
}
```

### 自动补全的索引库
```
PUT test2
{
  "mappings": {
    "properties": {
      "title":{
        "type": "completion"
      }
    }
  }
}
```

### 示例数据
```
POST test2/_doc
{
  "title": ["Sony", "WH-1000XM3"]
}
POST test2/_doc
{
  "title": ["SK-II", "PITERA"]
}
POST test2/_doc
{
  "title": ["Nintendo", "switch"]
}
```

### 自动补全查询
```
GET /test2/_search
{
  "suggest": {
    "titleSuggest": {
      "text": "s",
      "completion": {
        "field": "title",
        "skip_duplicates": true,
        "size": 10
      }
    }
  }
}
```

### 查看酒店数据结构
```
GET /hotel/_mapping
```

### 删除索引库
```
DELETE /hotel
```

### 酒店数据索引库
```
PUT /hotel
{
  "settings": {
    "analysis": {
      "analyzer": {
        "text_anlyzer": {
          "tokenizer": "ik_max_word",
          "filter": "py"
        },
        "completion_analyzer": {
          "tokenizer": "keyword",
          "filter": "py"
        }
      },
      "filter": {
        "py": {
          "type": "pinyin",
          "keep_full_pinyin": false,
          "keep_joined_full_pinyin": true,
          "keep_original": true,
          "limit_first_letter_length": 16,
          "remove_duplicated_term": true,
          "none_chinese_pinyin_tokenize": false
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "id":{
        "type": "keyword"
      },
      "name":{
        "type": "text",
        "analyzer": "text_anlyzer",
        "search_analyzer": "ik_smart",
        "copy_to": "all"
      },
      "address":{
        "type": "keyword",
        "index": false
      },
      "price":{
        "type": "integer"
      },
      "score":{
        "type": "integer"
      },
      "brand":{
        "type": "keyword",
        "copy_to": "all"
      },
      "city":{
        "type": "keyword"
      },
      "starName":{
        "type": "keyword"
      },
      "business":{
        "type": "keyword",
        "copy_to": "all"
      },
      "location":{
        "type": "geo_point"
      },
      "pic":{
        "type": "keyword",
        "index": false
      },
      "all":{
        "type": "text",
        "analyzer": "text_anlyzer",
        "search_analyzer": "ik_smart"
      },
      "suggestion":{
          "type": "completion",
          "analyzer": "completion_analyzer"
      }
    }
  }
}
```

### 自动补全查询
```
GET /hotel/_search
{
  "suggest": {
    "suggestions": {
      "text": "hzasasdas",
      "completion": {
        "field": "suggestion",
        "skip_duplicates": true,
        "size": 10
      }
    }
  }
}
```











