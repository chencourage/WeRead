package com.weread.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class BeanUtil {
	
	/**
     * 复制集合对象属性值，生成新类型集合
     * @param source 源集合
     * @param targetClass 目标集合类型
     * @return 新集合
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * */
    public static <T> List<T> copyList(List source,Class<T> targetClass) {
        List<T> target = new ArrayList<>(source.size());
        try{
	        for( int i = 0 ; i < source.size() ; i++){
	            Object sourceItem = source.get(i);
	            T targetItem = targetClass.newInstance();
	            BeanUtils.copyProperties(sourceItem,targetItem);
	            target.add(targetItem);
	        }
        }catch(Exception ex){
        	
        }
        return target;

    }

}
