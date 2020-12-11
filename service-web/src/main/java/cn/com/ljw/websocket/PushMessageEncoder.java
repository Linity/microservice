package cn.com.ljw.websocket;

import net.sf.json.JSONArray;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.util.List;

/**
 * @author Steph_Lin
 * @date 2020/11/20
 */
public class PushMessageEncoder implements Encoder.Text<List<String>> {

    @Override
    public String encode(List<String> list) throws EncodeException {

        JSONArray jsonObject = JSONArray.fromObject(list);

        System.out.println(jsonObject.toString());

        return jsonObject.toString();
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

}
