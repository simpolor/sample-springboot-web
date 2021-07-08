package io.simpolor.graphql.component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.simpolor.graphql.repository.entity.ReportCard;
import io.simpolor.graphql.repository.entity.Student;
import io.simpolor.graphql.service.ReportCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportCardMutation implements GraphQLMutationResolver {

    private final ReportCardService reportCardService;

    public ReportCard createReportCard(Integer korean, Integer english, Integer math, Student student){

        ReportCard reportCard = new ReportCard();
        reportCard.setKorean(korean);
        reportCard.setEnglish(english);
        reportCard.setMath(math);
        reportCard.setStudent(student);

        reportCardService.create(reportCard);

        return reportCard;
    }

    public ReportCard updateReportCard(ReportCard reportCard){

        return reportCardService.update(reportCard);
    }

    public boolean deleteReportCard(Long seq){

        reportCardService.delete(seq);

        return true;
    }
}
