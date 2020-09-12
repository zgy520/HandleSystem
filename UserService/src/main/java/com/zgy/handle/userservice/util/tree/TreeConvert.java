package com.zgy.handle.userservice.util.tree;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 树形转化的辅助类
 */
public class TreeConvert {
    private String keyLabel = "id";
    private String parentLabel = "parentId";
    private String childrenLabel = "children";
    private List dtoList;

    public TreeConvert(final List dtoList){
        this.dtoList = dtoList;
    }

    public TreeConvert(final String keyLabel,final String parentLabel,final String childrenLabel,final List dtoList){
        this.keyLabel = keyLabel;
        this.parentLabel = parentLabel;
        this.childrenLabel = childrenLabel;
        this.dtoList = dtoList;
    }


    /**
     * 将对象列表转化为树形结构
     * @param classz
     * @return
     * @throws Exception
     */
    public List toJsonArray(Class classz) throws Exception {
        if (dtoList == null){
            throw  new Exception("请传入数据列表!");
        }
        JSONArray jsonArray = new JSONArray(dtoList);
        // 获取所有的父级数组
        JSONArray parentArray = new JSONArray();
        JSONArray childArray = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.get(parentLabel) == null){
                parentArray.add(jsonObject);
            }else {
                childArray.add(jsonObject);
            }
        }
        int parentSize = parentArray.size();
        if (parentSize == 0){
            throw new Exception("至少要有一个父级元素");
        }
        for (int i = 0; i < parentSize; i++){
            JSONObject parentJson = parentArray.getJSONObject(i);
            parentJson.put(childrenLabel,getChildArray(parentJson,childArray,classz));
        }

        return parentArray.toJavaList(classz);
    }
    private JSONArray getChildArray(JSONObject parentJson,JSONArray childJsonArray,Class classz){
        JSONArray children = new JSONArray();
        JSONArray otherArray = new JSONArray(childJsonArray.toJavaList(classz));
        int childrenSize = childJsonArray.size();
        for (int i = 0; i < childrenSize; i++){
            JSONObject jsonObject = childJsonArray.getJSONObject(i);
            if (parentJson.getString(keyLabel).equals(jsonObject.getString(parentLabel))){
                children.add(jsonObject);
                otherArray.remove(jsonObject);
                if (otherArray.size() != 0){
                    jsonObject.put(childrenLabel,getChildArray(jsonObject,otherArray,classz));
                }
            }
        }
        return children;
    }

}
