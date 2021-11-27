
package com.mt.test.utils;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {
    private volatile static ParserConfig parserConfig = new ParserConfig();

    static {
        parserConfig.setSafeMode(true);
    }

    /**
     * 将json字符串转换成json list
     *
     * @param <T>
     * @param str
     * @return
     */
    public static <T> List<T> toList(String str, Class<T> cls) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }
        List<T> list;
        DefaultJSONParser parser = new DefaultJSONParser(str, parserConfig);
        JSONLexer lexer = parser.lexer;
        int token = lexer.token();
        if (token == JSONToken.NULL) {
            lexer.nextToken();
            list = null;
        } else if (token == JSONToken.EOF && lexer.isBlankInput()) {
            list = null;
        } else {
            list = new ArrayList<T>();
            parser.parseArray(cls, list);

            parser.handleResovleTask(list);
        }
        parser.close();

        return list;
    }
}
