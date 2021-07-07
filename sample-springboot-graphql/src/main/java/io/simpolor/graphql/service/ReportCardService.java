package io.simpolor.graphql.service;

import io.simpolor.graphql.repository.ReportCardRepository;
import io.simpolor.graphql.repository.entity.ReportCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportCardService {

    private final ReportCardRepository reportCardRepository;

    public Long getTotalCount() {
        return reportCardRepository.count();
    }

    public List<ReportCard> getAll() {

        Iterable<ReportCard> reportCards = reportCardRepository.findAll();
        List<ReportCard> list = new ArrayList<>();
        for(ReportCard reportCard : reportCards){
            list.add(reportCard);
        }

        return list;
    }

    public ReportCard get(long seq) {

        Optional<ReportCard> optionalStudent = reportCardRepository.findById(seq);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("seq : "+seq);
        }

        return optionalStudent.get();
    }

    public ReportCard create(ReportCard reportCard) {

        return reportCardRepository.save(reportCard);
    }

    public ReportCard update(ReportCard reportCard) {

        Optional<ReportCard> optionalReportCard = reportCardRepository.findById(reportCard.getSeq());
        if(!optionalReportCard.isPresent()){
            throw new IllegalArgumentException("seq : "+reportCard.getSeq());
        }

        return reportCardRepository.save(reportCard);
    }

    public long delete(long seq) {

        reportCardRepository.deleteById(seq);

        return seq;
    }

}
