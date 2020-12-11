package cn.com.ljw.service;

import cn.com.ljw.model.ApplicationFormDTO;
import cn.com.ljw.result.ResultCodeEnum;
import cn.com.ljw.result.ResultObject;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Steph_Lin
 * @date 2020/10/22
 */
@Service
public class RemoteServiceImpl implements RemoteService {

    @Autowired
    protected HttpServletRequest httpServletRequest;

    @Override
    public void applyExamine(ApplicationFormDTO applicationFormDTO) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResultObject> responseEntity = restTemplate.postForEntity("http://localhost:9095/develop/applicant/applyExamine",
                constructBody(applicationFormDTO),
                ResultObject.class);
        checkOk(responseEntity);
    }

    /**
     * 校验请求
     *
     * @param responseEntity
     */
    private void checkOk(ResponseEntity<ResultObject> responseEntity) {
        HttpStatus statusCode = responseEntity.getStatusCode();
        if (!ObjectUtils.isEmpty(statusCode)) {
            if (HttpStatus.OK.compareTo(statusCode) == 0) {
                if (ResultCodeEnum.SUCCESSFUL.equals(responseEntity.getBody().getCode())) {
                    return;
                }
            }
        }
        throw new RuntimeException("请求失败");
    }


    /**
     * 构造请求体
     *
     * @param body
     * @return
     */
    private HttpEntity<Object> constructBody(Object body) {
        String TOKEN = "token";
        String userToken = "b850659b-6214-4bad-a7b3-1b7b991eed80";
        if (StringUtils.isEmpty(userToken)) {
            throw new RuntimeException("获取用户token信息异常");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(TOKEN, userToken);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(JSON.toJSON(body), headers);

    }
}
