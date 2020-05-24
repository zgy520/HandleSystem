package com.zgy.handle.handleService.service.staticInfo;

import com.zgy.handle.handleService.model.staticInfo.HandleDailyCount;
import com.zgy.handle.handleService.model.staticInfo.HandleDailyCountDTO;
import com.zgy.handle.handleService.repository.SystemRepository;
import com.zgy.handle.handleService.repository.staticInfo.HandleDailyCountRepository;
import com.zgy.handle.handleService.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Request;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HandleDailyCountService extends SystemService<HandleDailyCount, HandleDailyCountDTO> {
    private static final String url = "";
    private HandleDailyCountRepository handleDailyCountRepository;
    public HandleDailyCountService(HandleDailyCountRepository handleDailyCountRepository) {
        super(handleDailyCountRepository);
        this.handleDailyCountRepository = handleDailyCountRepository;
    }

    public void updateDailyCount(){
        Request request = new Request.Builder()
                .url(url)
                .build();

    }
}
