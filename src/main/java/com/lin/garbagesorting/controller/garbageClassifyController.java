package com.lin.garbagesorting.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.aliyun.imagerecog20190930.models.ClassifyingRubbishResponse;
import com.aliyun.imagerecog20190930.models.ClassifyingRubbishResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaModel;
import com.lin.garbagesorting.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.Format;

@Api(tags = "垃圾识别管理")
@RestController
@RequestMapping("/garbageClassify")
public class garbageClassifyController {




    public static com.aliyun.imagerecog20190930.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()

                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "imagerecog.cn-shanghai.aliyuncs.com";
        return new com.aliyun.imagerecog20190930.Client(config);
    }


    @ApiOperation(value = "垃圾识别", notes = "垃圾识别")
    @PostMapping("/Classify")
    @SaCheckPermission("garbageClassify.c")
    public ClassifyingRubbishResponseBody garbageClassify(@RequestParam(required = false) MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        com.aliyun.imagerecog20190930.Client client = garbageClassifyController.createClient("LTAI5tKrtbURcpho96FohLVX", "kXpOUorV8Wu6Ibl38smddMiteaegJj");

        // 场景二，使用任意可访问的url
       // URL url = new URL("https://viapi-test-bj.oss-cn-beijing.aliyuncs.com/viapi-3.0domepic/imagerecog/ClassifyingRubbish/ClassifyingRubbish1.jpg");
       // InputStream inputStream2 = url.openConnection().getInputStream();
        com.aliyun.imagerecog20190930.models.ClassifyingRubbishAdvanceRequest classifyingRubbishAdvanceRequest = new com.aliyun.imagerecog20190930.models.ClassifyingRubbishAdvanceRequest()
                .setImageURLObject(inputStream);
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            ClassifyingRubbishResponse classifyingRubbishAdvanceResponse = client.classifyingRubbishAdvance(classifyingRubbishAdvanceRequest, runtime);
            // 获取整体结果。
            System.out.println(1);
            System.out.println(com.aliyun.teautil.Common.toJSONString(TeaModel.buildMap(classifyingRubbishAdvanceResponse)));
            // 获取单个字段。
            System.out.println(2);
            System.out.println(classifyingRubbishAdvanceResponse.getBody());
            ClassifyingRubbishResponseBody  body = classifyingRubbishAdvanceResponse.getBody();
            ClassifyingRubbishResponseBody.ClassifyingRubbishResponseBodyData data = body.data;
            System.out.println(3);

            return body;


        } catch (TeaException teaException) {
            // 获取整体报错信息。
            System.out.println(com.aliyun.teautil.Common.toJSONString(teaException));
            // 获取单个字段。
            System.out.println(teaException.getCode());
            throw new Exception(teaException.getCode());
        }


    }




}
