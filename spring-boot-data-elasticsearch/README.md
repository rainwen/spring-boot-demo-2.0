http://{{elastic_domain}}/weibo/weibo_checkin/B2094652DA68A6FE4999/_termvectors?fields=placename
```json
{
    "_index": "weibo",
    "_type": "weibo_checkin",
    "_id": "B2094652DA68A6FE4999",
    "_version": 3,
    "found": true,
    "took": 2675,
    "term_vectors": {
        "placename": {
            "field_statistics": {
                "sum_doc_freq": 7135,
                "doc_count": 1090,
                "sum_ttf": 7276
            },
            "terms": {
                "乐": {
                    "term_freq": 1,
                    "tokens": [
                        {
                            "position": 1,
                            "start_offset": 1,
                            "end_offset": 2
                        }
                    ]
                },
                "大": {
                    "term_freq": 1,
                    "tokens": [
                        {
                            "position": 3,
                            "start_offset": 3,
                            "end_offset": 4
                        }
                    ]
                },
                "宫": {
                    "term_freq": 1,
                    "tokens": [
                        {
                            "position": 2,
                            "start_offset": 2,
                            "end_offset": 3
                        }
                    ]
                },
                "店": {
                    "term_freq": 1,
                    "tokens": [
                        {
                            "position": 5,
                            "start_offset": 5,
                            "end_offset": 6
                        }
                    ]
                },
                "百": {
                    "term_freq": 1,
                    "tokens": [
                        {
                            "position": 0,
                            "start_offset": 0,
                            "end_offset": 1
                        }
                    ]
                },
                "酒": {
                    "term_freq": 1,
                    "tokens": [
                        {
                            "position": 4,
                            "start_offset": 4,
                            "end_offset": 5
                        }
                    ]
                }
            }
        }
    }
}
```