package com.zgy.handle.userservice.service.authority.role.excel;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zgy.handle.userservice.model.authority.role.Role;
import com.zgy.handle.userservice.repository.authority.role.RoleUpdateRepository;
import com.zgy.handle.userservice.service.excel.TemplateUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: a4423
 * @date: 2020/10/4 15:47
 */
@Service
@Slf4j
public class RoleImportService extends TemplateUploadService<Role> {
    @Autowired
    private RoleUpdateRepository roleUpdateRepository;

    @Override
    public JSONArray handleData(JSONArray jsonArray, String attachData) {
        JSONArray failArray = new JSONArray();
        List<Role> roleList = new ArrayList<>();
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String code = jsonObject.getString("code");
            String desc = jsonObject.getString("desc");
            Role role = new Role();
            role.setName(name);
            role.setCode(code);
            role.setNote(desc);
            role.setCreatedId(getPersonalId());
            role.setBelongId(getDepartId());
            roleList.add(role);
        }
        roleUpdateRepository.saveAll(roleList);
        return failArray;
    }
}
