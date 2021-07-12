package io.simpolor.graphql.component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import io.simpolor.graphql.repository.entity.ReportCard;
import io.simpolor.graphql.service.ReportCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportCardQuery implements GraphQLQueryResolver {

    private final ReportCardService reportCardService;

    public long countReportCards(){
        return reportCardService.getTotalCount();
    }

    public Iterable<ReportCard> findAllReportCards(){
        return reportCardService.getAll();
    }

    public ReportCard getReportCard(Long seq){

        return reportCardService.get(seq);
    }

}
