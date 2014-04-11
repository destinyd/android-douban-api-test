package com.realityandapp.service;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import com.realityandapp.constants.RequestUrls;
import com.realityandapp.model.v2.Celebrity;
import com.realityandapp.model.v2.Subject;
import com.realityandapp.model.v2.SubjectList;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.github.kevinsawicki.http.HttpRequest.get;

/**
 * Created by dd on 14-4-10.
 */
public class MovieService {
    public SubjectList search(String q) throws UnsupportedEncodingException {
        return search(q, null, 0, 10);
    }

    public SubjectList search(String q, String tag, Integer start, Integer count) throws UnsupportedEncodingException {
        String str_param = "?";
        if(q != null && q.length() > 0)
            str_param += "q=" + URLEncoder.encode(q, "UTF-8") ;
        if(tag != null && tag.length() > 0)
            str_param += "&tag=" + URLEncoder.encode(tag, "UTF-8");
        if(start != 0)
            str_param += "&start=" + start.toString();
        if(start != 20)
            str_param += "&count=" + count.toString();
        String url = RequestUrls.DOUBAN_MOVIE_V2_SEARCH_PREFIX + str_param;
        HttpRequest request = get(url);
        String body = request.body();
        return JSON.parseObject(body, SubjectList.class);
    }

    public Subject getMovieById(String id){
        String url = RequestUrls.DOUBAN_MOVIE_V2_SUBJECT_PREFIX + id;
        HttpRequest request = get(url);
        String body = request.body();
        return JSON.parseObject(body, Subject.class);
    }

    public Celebrity getCelebrityById(String id){
        String url = RequestUrls.DOUBAN_MOVIE_V2_CELEBRITY_PREFIX + id;
        HttpRequest request = get(url);
        String body = request.body();
        return JSON.parseObject(body, Celebrity.class);
    }
}
