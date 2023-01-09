package io.simpolor.feign.httpclient;

import com.google.gson.Gson;
import feign.*;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.simpolor.feign.endpoint.model.ServiceResponse;
import io.simpolor.feign.httpclient.feign.RemoteFeign;
import io.simpolor.feign.httpclient.model.RemoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemoteClient {

    @Value("${application.remote.endpoint}")
    private String remoteEndpoint;
    private RemoteFeign remoteFeign;

    private final Gson gson;

    @PostConstruct
    public void init(){
        this.remoteFeign = Feign.builder()
                .contract(new Contract.Default())
                .retryer(new Retryer.Default())
                .options(new Request.Options(1000, 1000))
                // .encoder(new Encoder.Default())
                // .decoder(new Decoder.Default())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .decode404()
                .logLevel(Logger.Level.BASIC)
                .target(new Target.HardCodedTarget<>(RemoteFeign.class, "remoteFeign", remoteEndpoint));
    }

    public List<RemoteDto.RemoteResponse> getAll() {

        ServiceResponse response = remoteFeign.getAll();
        if(response.getCode().equals(0)){

            String result = gson.toJson(response.getResult());

            return gson.fromJson(result, new ListOfJson<>(RemoteDto.RemoteResponse.class));
        }

        return Collections.EMPTY_LIST;
    }

    public RemoteDto.RemoteResponse get(Long studentId) {

        ServiceResponse response = remoteFeign.get(studentId);
        if(!response.getCode().equals(0)){
            throw new IllegalArgumentException("Not found studentId: "+ studentId);
        }

        String result = gson.toJson(response.getResult());

        return gson.fromJson(result, RemoteDto.RemoteResponse.class);
    }

    public RemoteDto.RemoteResultResponse create(RemoteDto.RemoteRequest request) {

        ServiceResponse response = remoteFeign.post(request);

        if(!response.getCode().equals(0)){
            throw new IllegalArgumentException("Remote error: "+ request);
        }

        String result = gson.toJson(response.getResult());

        return gson.fromJson(result, RemoteDto.RemoteResultResponse.class);
    }

    public void update(Long studentId, RemoteDto.RemoteRequest request) {

        ServiceResponse response = remoteFeign.put(studentId, request);
        if(!response.getCode().equals(0)){
            throw new IllegalArgumentException("Not found studentId: "+ studentId);
        }
    }

    public void delete(Long studentId) {

        ServiceResponse response = remoteFeign.delete(studentId);
        if(!response.getCode().equals(0)){
            throw new IllegalArgumentException("Not found studentId: "+ studentId);
        }
    }

}
