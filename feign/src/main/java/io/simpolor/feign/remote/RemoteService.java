package io.simpolor.feign.remote;

import io.simpolor.feign.endpoint.model.ServiceResponse;
import io.simpolor.feign.remote.client.RemoteClient;
import io.simpolor.feign.remote.model.RemoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RemoteService {

    private final RemoteClient remoteClient;

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
