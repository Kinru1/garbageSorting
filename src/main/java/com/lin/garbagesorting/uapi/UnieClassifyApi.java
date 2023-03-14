package com.lin.garbagesorting.uapi;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.aliyun.imagerecog20190930.models.ClassifyingRubbishResponse;
import com.aliyun.imagerecog20190930.models.ClassifyingRubbishResponseBody;
import com.aliyun.tea.TeaException;
import com.aliyun.tea.TeaModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
@Slf4j
@Api(tags = "Uni垃圾识别管理")
@RestController
@RequestMapping("/garbageClassify")
public class UnieClassifyApi {



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
    @PostMapping()
    @SaCheckPermission("garbageClassify.c")
    public ClassifyingRubbishResponseBody garbageClassify(@RequestParam(required = false,value = "image") MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        log.info(String.valueOf(inputStream));
        com.aliyun.imagerecog20190930.Client client = UnieClassifyApi.createClient("LTAI5tKrtbURcpho96FohLVX", "kXpOUorV8Wu6Ibl38smddMiteaegJj");
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
