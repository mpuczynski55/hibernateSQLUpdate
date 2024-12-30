package com.sqlupdate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
public class KeyJPA implements Serializable {

    private static final String ID_GENERATOR_STRATEGY_NAME = "ID_GENERATOR";

    @GenericGenerator(name = ID_GENERATOR_STRATEGY_NAME,
            type = SequenceStringIdGenerator.class,
            parameters = {
                    @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "MAIN_SEQUENCE"),
                    @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "50")
            })
    @GeneratedValue(generator = ID_GENERATOR_STRATEGY_NAME)
    @Column(name = "ID")
        private String xId;

    @Column(name = "KEY_1")
    private String key1;

    @Column(name = "KEY_2")
    private Long key2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyJPA keyJPA = (KeyJPA) o;
        return Objects.equals(xId, keyJPA.xId) &&
                Objects.equals(key1, keyJPA.key1) &&
                Objects.equals(key2, keyJPA.key2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xId, key1, key2);
    }
}

    
