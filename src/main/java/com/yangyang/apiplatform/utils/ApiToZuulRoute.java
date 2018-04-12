package com.yangyang.apiplatform.utils;

import com.yangyang.apiplatform.entity.Api;
import com.yangyang.apiplatform.entity.ZuulRouteVO;

import java.util.ArrayList;
import java.util.List;

public class ApiToZuulRoute {
    public  static List<ZuulRouteVO> apiToZuulRoute(List<Api> list){
List<ZuulRouteVO> zuulRouteVOList=new ArrayList<>();
        for(Api api:list){
    ZuulRouteVO zuulRouteVO =new ZuulRouteVO();
    zuulRouteVO.setId(api.getApi_id());
    zuulRouteVO.setPath(api.getApi_path());
    zuulRouteVO.setUrl(api.getApi_url());
    zuulRouteVO.setEnabled(api.getApi_enabled()==1?true:false);
    zuulRouteVO.setRetryable(api.getApi_retryable()==1?true:false);
    zuulRouteVO.setApiName(api.getApi_name());
    zuulRouteVO.setStripPrefix(api.getApi_strip_prefix()==1?true:false);
    zuulRouteVO.setServiceId(null);
    zuulRouteVOList.add(zuulRouteVO);
}
return zuulRouteVOList;
    }
}
