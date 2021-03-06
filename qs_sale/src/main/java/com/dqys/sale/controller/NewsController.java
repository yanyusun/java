package com.dqys.sale.controller;

import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import com.dqys.sale.orm.query.NewsQuery;
import com.dqys.sale.service.dto.NewsDTO;
import com.dqys.sale.service.dto.news.NewsDtoY;
import com.dqys.sale.service.dto.news.SecondLevelDtoList;
import com.dqys.sale.service.facade.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 新闻管理
 * Created by mkfeng on 2016/12/24.
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * @api {post} news/noVerify/newsList 获取新闻列表
     * @apiName news/noVerify/newsList
     * @apiSampleRequest news/noVerify/newsList
     * @apiParam {int} [type] 类别:新闻资讯0，行业动态1，业务信息2
     * @apiParam {int} [isRefer] 是否推荐1是0否
     * @apiParam {int} [isHeadline] 是否头条1是0否
     * @apiParam {boolean} [isOrder] //默认true从大到小 false 是从小到大
     * @apiGroup　 SaleNews
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "newsList": [
     * {
     * "news": {
     * "id": 1,
     * "auther": 1,//作者
     * "province": 1,
     * "city": 1,
     * "area": 1,
     * "type": 1,
     * "isRefer": 1,//是否推荐
     * "isHeadline": 1,//是否头条A
     * "title": "1",//标题',
     * "content": "1",//内容
     * "cover": "1",//封面
     * "digest": "1",//摘要
     * "creatAt": "2016-12-24",//创建时间
     * "status": 1,
     * "openTime": "2016-12-24",//发布时间
     * "readNum": 1,//阅读数',
     * "mark": "1"//备注
     * },
     * "lables": [
     * {
     * "id": 1,
     * "type": 1,
     * "name": "测试"
     * },
     * {
     * "id": 2,
     * "type": 2,
     * "name": "再来一次"
     * }
     * ]
     * }
     * ],
     * "query": {
     * "page": 0,
     * "pageCount": 20,
     * "startPage": 0,
     * "totalCount": 1,
     * "ids": null,
     * "type": null,
     * "isRefer": null,
     * "isHeadline": null,
     * "status": null,
     * "order": false
     * }
     * }
     * }
     */
    @RequestMapping("/noVerify/newsList")
    public JsonResponse newsList(NewsQuery query) {
//        query.setStatus(1);
        return newsService.indexList(query);
    }

    /**
     * @api {post} news/noVerify/getDetail 获取新闻详情
     * @apiName news/noVerify/getDetail
     * @apiSampleRequest news/noVerify/getDetail
     * @apiGroup　 SaleNews
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "news": {
     * "id": 1,
     * "auther": 1,//作者
     * "province": 1,
     * "city": 1,
     * "area": 1,
     * "type": 1,
     * "isRefer": 1,//是否推荐
     * "isHeadline": 1,//是否头条A
     * "title": "1",//标题',
     * "content": "1",//内容
     * "cover": "1",//封面
     * "digest": "1",//摘要
     * "creatAt": "2016-12-24",//创建时间
     * "status": 1,
     * "openTime": "2016-12-24",//发布时间
     * "readNum": 1,//阅读数',
     * "mark": "1"//备注
     * },
     * "lables": [
     * {
     * "id": 1,
     * "type": 1,
     * "name": "测试"
     * },
     * {
     * "id": 2,
     * "type": 2,
     * "name": "再来一次"
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/noVerify/getDetail")
    public JsonResponse getDetail(Integer newsId) {
        return newsService.getDetail(newsId);
    }

    /**
     * @api {post} news/list 获取新闻列表
     * @apiName news/list
     * @apiSampleRequest news/list
     * @apiParam {int} [type] 类别:新闻资讯0，行业动态1，业务信息2
     * @apiParam {int} [isRefer] 是否推荐1是0否
     * @apiParam {int} [isHeadline] 是否头条1是0否
     * @apiParam {boolean} [isOrder] //默认true从大到小 false 是从小到大
     * @apiGroup　 SaleNews
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "newsList": [
     * {
     * "news": {
     * "id": 1,
     * "auther": 1,//作者
     * "province": 1,
     * "city": 1,
     * "area": 1,
     * "type": 1,
     * "isRefer": 1,//是否推荐
     * "isHeadline": 1,//是否头条A
     * "title": "1",//标题',
     * "content": "1",//内容
     * "cover": "1",//封面
     * "digest": "1",//摘要
     * "creatAt": "2016-12-24",//创建时间
     * "status": 1,
     * "openTime": "2016-12-24",//发布时间
     * "readNum": 1,//阅读数',
     * "mark": "1"//备注
     * },
     * "lables": [
     * {
     * "id": 1,
     * "type": 1,
     * "name": "测试"
     * },
     * {
     * "id": 2,
     * "type": 2,
     * "name": "再来一次"
     * }
     * ]
     * }
     * ],
     * "query": {
     * "page": 0,
     * "pageCount": 20,
     * "startPage": 0,
     * "totalCount": 1,
     * "ids": null,
     * "type": null,
     * "isRefer": null,
     * "isHeadline": null,
     * "status": null,
     * "order": false
     * }
     * }
     * }
     */
    @RequestMapping("/list")
    public JsonResponse list(NewsQuery query) {
        query.setUserId(UserSession.getCurrent().getUserId());
        return newsService.newsList(query);
    }


    /**
     * @api {post} news/addOrUpdateNews 添加或修改新闻
     * @apiName news/addOrUpdateNews
     * @apiSampleRequest news/addOrUpdateNews
     * @apiGroup　 SaleNews
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {}
     * }
     */
    @RequestMapping("/addOrUpdateNews")
    public JsonResponse addOrUpdateNews(@ModelAttribute NewsDTO newsDTO) {
        return newsService.addOrUpdateNews_tx(newsDTO);
    }

    /**
     * @api {post} news/delNews 删除新闻
     * @apiName news/delNews
     * @apiSampleRequest news/delNews
     * @apiGroup　 SaleNews
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {}
     * }
     */
    @RequestMapping("/delNews")
    public JsonResponse delNews(@RequestParam Integer newsId) {
        return newsService.delNews(newsId);
    }

    /**
     * 二级页面
     *
     * @return
     */
    @RequestMapping("/noVerify/secondLeveL")
    public JsonResponse getSecondLevelDto() {
        SecondLevelDtoList secondLevelDtoList = newsService.sencondLevelPage();
        return JsonResponseTool.success(secondLevelDtoList);
    }

    /**
     * 二级页面的列表
     *
     * @return
     */
    @RequestMapping("/noVerify/secondNewsList")
    public JsonResponse getSecondLevelDto(int type, int page) {
        List<NewsDtoY> newsDtoYList = newsService.sencondNewsList(type, page);
        return JsonResponseTool.success(newsDtoYList);
    }

}
