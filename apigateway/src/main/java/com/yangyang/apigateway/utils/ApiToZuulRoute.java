package com.yangyang.apigateway.utils;


import com.yangyang.apigateway.entity.ZuulRouteVO;
import com.yangyang.pojo.entity.Api;
import com.yangyang.pojo.entity.ApiCategory;

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
    zuulRouteVO.setRetryable(true);
    zuulRouteVO.setApiName(api.getApi_name());
    zuulRouteVO.setStripPrefix(true);
    zuulRouteVO.setServiceId(null);
    zuulRouteVOList.add(zuulRouteVO);
}
return zuulRouteVOList;
    }
    public  static List<ZuulRouteVO> apiCategoryToZuulRoute(List<ApiCategory> list){
List<ZuulRouteVO> zuulRouteVOList=new ArrayList<>();
        for(ApiCategory apiCategory:list){
    ZuulRouteVO zuulRouteVO =new ZuulRouteVO();
    zuulRouteVO.setId(apiCategory.getApi_category_id());
    zuulRouteVO.setPath(apiCategory.getApi_category_path());
    zuulRouteVO.setUrl("http://127.0.0.1:8090/error");
    zuulRouteVO.setEnabled(apiCategory.getApi_category_enabled()==1?true:false);
    zuulRouteVO.setRetryable(true);
    zuulRouteVO.setApiName(apiCategory.getApi_category_name());
    zuulRouteVO.setStripPrefix(true);
    zuulRouteVO.setServiceId(null);
    zuulRouteVOList.add(zuulRouteVO);
}
return zuulRouteVOList;
    }

}
