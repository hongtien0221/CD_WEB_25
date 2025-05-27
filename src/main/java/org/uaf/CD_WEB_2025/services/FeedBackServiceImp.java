package org.uaf.CD_WEB_2025.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uaf.CD_WEB_2025.entity.FeedBack;
import org.uaf.CD_WEB_2025.reponsitory.FeedBackReponesitory;
import org.uaf.CD_WEB_2025.services.IServices.IFeedBackService;

import java.sql.Date;

@Service
public class FeedBackServiceImp implements IFeedBackService {
    private final FeedBackReponesitory feedBackReponesitory;

    @Autowired
    public FeedBackServiceImp(FeedBackReponesitory feedBackReponesitory) {
        this.feedBackReponesitory = feedBackReponesitory;
    }
    @Override
    public long getCountFeedBack() {
        return feedBackReponesitory.count();
    }


    @Override
    public void insertFeedBack(String idPr, String idUser, int scorestar, String text, Date date) {
        FeedBack f = new FeedBack();
        f.setId((int) (getCountFeedBack() + 1));
        f.setIdPr(idPr);
        f.setIdUser(idUser);
        f.setScorestar(scorestar);
        f.setText(text);
        f.setDate(date);
        feedBackReponesitory.save(f);
    }

}
