package io.simpolor.validation.web.request;

import lombok.Data;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Data
public class BulkRequest<E> {

    @Valid
    private List<E> contents;

    public BulkRequest() {

        contents = new ArrayList<>();
    }

    public BulkRequest(List<E> contents) {

        this.contents = contents;
    }

    public boolean add(E e) {

        return contents.add(e);}

    public BulkRequest<E> push(E e) {

        contents.add(e);
        return this;
    }

    public void forEach(Consumer<E> action) {

        contents.forEach(action);
    }
}
