package com.gpmall.pay.services;

import com.gpmall.commons.lock.annotation.CustomerLock;
import com.gpmall.pay.biz.abs.BasePayment;
import com.gpmall.pay.utils.ExceptionProcessorUtils;
import com.gupaoedu.pay.PayCoreService;
import com.gupaoedu.pay.constants.PayReturnCodeEnum;
import com.gupaoedu.pay.dto.*;
import com.gupaoedu.pay.dto.PaymentNotifyRequest;
import com.gupaoedu.pay.dto.PaymentNotifyResponse;
import com.gupaoedu.pay.dto.PaymentRequest;
import com.gupaoedu.pay.dto.PaymentResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * @author 风骚的Mic 老师
 * create-date: 2019/7/30-13:54
 */
@Slf4j
@Service(cluster = "failfast")
public class PayCoreServiceImpl implements PayCoreService {


    @Override
    @CustomerLock(lockKey = "#request.tradeNo",lockType = "zookeeper", tryLock = true)
    public PaymentResponse execPay(PaymentRequest request) {

        PaymentResponse paymentResponse=new PaymentResponse();
        try {
            paymentResponse=(PaymentResponse) BasePayment.paymentMap.get(request.getPayChannel()).process(request);
        }catch (Exception e){
            log.error("PayCoreServiceImpl.execPay Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(paymentResponse,e);
        }
        return paymentResponse;
    }


    @Override
    public PaymentNotifyResponse paymentResultNotify(PaymentNotifyRequest request) {
        log.info("paymentResultNotify request:"+request);
        PaymentNotifyResponse response=new PaymentNotifyResponse();
        try{
            response=(PaymentNotifyResponse) BasePayment.paymentMap.get
                    (request.getPayChannel()).completePayment(request);

        }catch (Exception e){
            log.error("paymentResultNotify occur exception:"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }finally {
            log.info("paymentResultNotify return result:"+response);
        }
        return response;
    }

    /**
     * 执行退款
     * @param refundRequest
     * @return
     */
    @Override
    public RefundResponse execRefund(RefundRequest refundRequest) {
        RefundResponse refundResponse=new RefundResponse();
        try {
            refundResponse=(RefundResponse) BasePayment.paymentMap.get(refundRequest.getPayChannel()).process(refundRequest);
        }catch (Exception e){
            log.error("PayCoreServiceImpl.execRefund Occur Exception :{}",e);
            ExceptionProcessorUtils.wrapperHandlerException(refundResponse,e);
        }
        return refundResponse;
    }
}
