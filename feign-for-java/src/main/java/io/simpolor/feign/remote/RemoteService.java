package io.simpolor.feign.remote;

import com.google.gson.Gson;
import feign.*;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import io.simpolor.feign.endpoint.model.ServiceResponse;
import io.simpolor.feign.remote.client.RemoteClient;
import io.simpolor.feign.remote.model.RemoteDto;
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
public class RemoteService {

    @Value("${application.remote.endpoint}")
    private String remoteEndpoint;

    private RemoteClient remoteClient;

    @PostConstruct
    public void init(){
        this.remoteClient = Feign.builder()
                .contract(new Contract.Default())
                .retryer(new Retryer.Default())
                .options(new Request.Options(1000, 1000))
                // .encoder(new Encoder.Default())
                // .decoder(new Decoder.Default())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .decode404()
                .logLevel(Logger.Level.BASIC)
                .target(new Target.HardCodedTarget<>(RemoteClient.class, "remoteClient", remoteEndpoint));
    }

    public List<RemoteDto.RemoteResponse> getAll() {

        ServiceResponse response = remoteClient.getAll();
        if(response.getCode().equals(0)){

            String result = RemoteUtils.toString(response.getResult());

            return RemoteUtils.getList(result, RemoteDto.RemoteResponse.class);
        }

        return Collections.EMPTY_LIST;
    }

    public RemoteDto.RemoteResponse get(Long studentId) {

        ServiceResponse response = remoteClient.get(studentId);
        if(!response.getCode().equals(0)){
            throw new IllegalArgumentException("Not found studentId: "+ studentId);
        }

        String result = RemoteUtils.toString(response.getResult());

        return RemoteUtils.getObject(result, RemoteDto.RemoteResponse.class);
    }

    public RemoteDto.RemoteResultResponse create(RemoteDto.RemoteRequest request) {

        ServiceResponse response = remoteClient.post(request);

        if(!response.getCode().equals(0)){
            throw new IllegalArgumentException("Remote error: "+ request);
        }

        String result = RemoteUtils.toString(response.getResult());

        return RemoteUtils.getObject(result, RemoteDto.RemoteResultResponse.class);
    }

    public void update(Long studentId, RemoteDto.RemoteRequest request) {

        ServiceResponse response = remoteClient.put(studentId, request);
        if(!response.getCode().equals(0)){
            throw new IllegalArgumentException("Not found studentId: "+ studentId);
        }
    }

    public void delete(Long studentId) {

        ServiceResponse response = remoteClient.delete(studentId);
        if(!response.getCode().equals(0)){
            throw new IllegalArgumentException("Not found studentId: "+ studentId);
        }
    }

}
