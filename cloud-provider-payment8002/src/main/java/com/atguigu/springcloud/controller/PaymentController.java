package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ClassName：PaymentController
 * Description：
 *
 * @author lihw
 * CreateTime: 2020/12/15 0:43
 * @version 1.0.0
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        System.out.println("Hello World");
        log.info("****插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据成功,serverPort：" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
//        System.out.println("Hello World");
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果：" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询数据成功，serverPort：" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询数据失败,查询id：" + id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
