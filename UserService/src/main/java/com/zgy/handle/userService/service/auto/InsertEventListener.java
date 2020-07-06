package com.zgy.handle.userService.service.auto;

import com.zgy.handle.common.zuul.context.UserContext;
import com.zgy.handle.userService.model.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.FlushMode;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class InsertEventListener implements PostInsertEventListener {
    @Autowired
    private HttpServletRequest request;

    public static final InsertEventListener INSTANCE =
            new InsertEventListener();
    @Override
    public void onPostInsert(PostInsertEvent event) {
        final Object entity = event.getEntity();

        /*if (entity instanceof BaseModel){
            BaseModel baseModel = (BaseModel)entity;
            baseModel.setBelongId("1");
            baseModel.setCreatedId("3");
            event.getSession().flush();
        }*/
    }



    @Override
    public boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }
}
