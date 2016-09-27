package com.dqys.business.controller;

import com.dqys.business.service.service.SynthLenderService;
import com.dqys.business.service.utils.message.MessageUtils;
import com.dqys.core.model.JsonResponse;
import com.dqys.core.model.UserSession;
import com.dqys.core.utils.JsonResponseTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 催收机构的借款人详情
 * Created by mkfeng on 2016/9/9.
 */
@Controller
@RequestMapping("/synLender")
public class SynLenderController {

    @Autowired
    private SynthLenderService synthLenderService;

    /**
     * @api {post} synLender/pawnList 借款人信息并关联下抵押物和借据信息
     * @apiParam {int} lenderId 借款人id
     * @apiSampleRequest synLender/pawnList
     * @apiGroup synLender
     * @apiName synLender/pawnList
     * @apiSuccessExample {json} Data-Response:
     * {
     * "code": 2000,
     * "msg": "成功",
     * "data": {
     * "result": "yes",
     * "iouList": [
     * {
     * "id": 528,
     * "version": 4,
     * "stateflag": 0,
     * "createAt": "2016-09-13",
     * "updateAt": "2016-09-21",
     * "remark": null,//标签说明
     * "iouNo": "IO1609139996",//借据编号
     * "name": "借据01",//借据自定义名称
     * "lenderId": 190,//借款人ID
     * "type": "1",//品种
     * "agency": "某某经办机构",//代理机构
     * "iouCode": "B4532S",//原始借据号
     * "loanTime": "2016-09-14",//放款时间
     * "loanAttime": "2016-09-28",//到款日期
     * "amount": 20100,//放款金额
     * "pactRate": 3,//合同利率
     * "outtimeMultiple": 12,//逾期利率倍数
     * "appropriationMultiple": "1",//挪用利率倍数
     * "accrualRepay": 4300,//已还利息金额
     * "loanRepay": 1200,//已还贷款金额
     * "levelType": "1",//5级分类'
     * "outDays": 120,//逾期天数
     * "lessCorpus": 1555,//剩余本金
     * "accrualArrears": 8500,//拖欠利息
     * "penalty": 0,//罚息
     * "arrears": 120000,//欠款合计
     * "endAt": "2016-09-21",//欠款截止日期
     * "worth": 20000,//抵押物银行估值
     * "advanceCorpus": 30000,//提前偿还本金
     * "evaluateExcellent": "great",//评优
     * "evaluateLevel": "A",//评级
     * "memo": "备注一下借据",//备注
     * "repayStatus": 0,//还款状态（0已经还完1未还完）
     * "onCollection": 1,//是否可以催收:0可以1不能
     * "onLawyer": 1,//是否可以进行司法处置:0可以1不能
     * "onAgent": 1 //是否可以中介处置:0可以1不能
     * }
     * ],
     * "lenderSynDTO": {
     * "avg": null,//头像地址
     * "name": "张三",// 姓名
     * "gender": "1",//性别(1-男;0-女)
     * "type": 1,//借款类型
     * "address": "张三在北京的住宅地址",//详细地址
     * "lenderNo": "JKR7758",//借款人编号
     * "tags": null,//标签
     * "accrual": "116500.0",//总利息
     * "loan": "12222.0",// 总贷款
     * "appraisal": "40000.0",//总评估
     * "out_days": null,//逾期天数（计算？）
     * "loanType": "2",//贷款类型
     * "loanMode": "1",//贷款方式
     * "loanName": "贷款机构名称哦哦哦",//贷款名称
     * "realUrgeTime": 6,//实地催收次数
     * "phoneUrgeTime": 23,//电话催收次数
     * "entrustUrgeTime": 2,//委托催收次数
     * "followUpDate": null,//最后跟进时间
     * "enteringDate": "2016-09-13",//录入时间
     * "belongId": null,//所属人ID
     * "belongName": null,// 所属人姓名
     * "startAt": "2016-09-14",//委托开始时间
     * "endAt": "2016-10-30",//委托结束时间
     * "surplusDay": "-17",//剩余委托催收天数
     * "source": null,//来源
     * "assetNo": "QS1609132853" //资产包编号
     * },
     * "pawnList": [
     * {
     * "id": 624,
     * "version": 0,
     * "stateflag": 0,
     * "createAt": "2016-09-13",
     * "updateAt": "2016-09-13",
     * "remark": null,//标签说明'
     * "lenderId": 190,//借款人ID
     * "pawnNo": "CO1609133220",//抵押物编号
     * "name": "抵押物A",//抵押物自定义名称
     * "amount": 52200,//贷款金额
     * "type": "1",//抵押物类型
     * "evaluateExcellent": "great",//评优
     * "evaluateLevel": "A",//评级
     * "size": "122",//规模大小
     * "province": 12,//省份
     * "city": 1202,//城市
     * "district": 120221,//区域
     * "address": "zhanljlgkggga顶顶顶",//地址
     * "pawnRate": 12,// 抵押率
     * "disposeStatus": "y",//处置状态
     * "worth": 40000,//价值
     * "memo": null,//备注
     * "onCollection": 0,//是否可以催收:0可以1不能
     * "onLawyer": 0,//是否可以司法处置:0可以1不能
     * "onAgent": 0 //是否可以中介处置:0可以,1不行
     * }
     * ]
     * }
     * }
     */
    @RequestMapping("/pawnList")
    @ResponseBody
    public JsonResponse pawnList(Integer lenderId) {
        if (UserSession.getCurrent() == null) {
            return JsonResponseTool.authFailure("账号没登入");
        }
        Map map = new HashMap<>();
        map = synthLenderService.pawnList(lenderId);
        if ("yes".equals(MessageUtils.transMapToString(map, "result"))) {
            return JsonResponseTool.success(map);
        } else {
            return JsonResponseTool.successNullList();
        }
    }

}
