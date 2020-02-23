package com.zgy.handle.userService.model.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class UUIDGenerator implements IdentifierGenerator, Configurable {
    private final String sql = "select uuid_short()";
    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {

    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        synchronized (this){
            Long result;
            try {
                Long id = null;
                Field[] declaredFields = o.getClass().getDeclaredFields();
                int size = declaredFields.length;
                for (int i = 0; i < size; i++){
                    Field field = declaredFields[i];
                    Id annotation = (Id)field.getAnnotation(Id.class);
                    if (annotation != null){
                        try {
                            Method method = o.getClass().getMethod("get" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1));
                            Object invoke = method.invoke(o);
                            id = (Long)invoke;
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                        break;
                    }
                }
                if (id == null){
                    PreparedStatement st = sharedSessionContractImplementor.connection().prepareStatement("select uuid_short()");
                    try{
                        ResultSet rs = st.executeQuery();

                        long resultx;
                        try{
                            rs.next();
                            resultx = rs.getLong(1);
                        }finally {
                            rs.close();
                        }
                        return resultx;
                    }finally {
                        st.close();
                    }
                }
                result = id;
            }catch (Exception ex){
                return null;
            }
            return result;
        }
    }
}
