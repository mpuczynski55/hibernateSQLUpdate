package com.sqlupdate;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.BasicType;
import org.hibernate.type.Type;
import org.hibernate.type.spi.TypeConfiguration;

import java.io.Serializable;
import java.util.Properties;

public class SequenceStringIdGenerator extends SequenceStyleGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return String.valueOf(super.generate(session, object));
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        BasicType<Long> longType = new TypeConfiguration()
                .getBasicTypeRegistry()
                .getRegisteredType(Long.class);
        super.configure(longType, params, serviceRegistry);
    }

}