package pers.xiaomuma.auth.example.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pers.xiaomuma.auth.example.api.AuthServiceUrl;
import java.util.Map;


@FeignClient(AuthServiceUrl.SERVICE_NAME)
public interface IAuthServiceClient {

    @ResponseBody
    @RequestMapping(AuthServiceUrl.Auth.CHECK_TOKEN_URL)
    Map<String, Object> checkToken(@RequestParam("token") String value);

    @PostMapping(AuthServiceUrl.Auth.GET_TOKEN)
    ResponseEntity<Map<String,String>> postAccessToken(@RequestParam("client_id") String clientId, @RequestParam("client_secret") String clientSecret, @RequestBody Map<String, String> parameters);
}
