package com.sqlupdate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public class ObjectJPA {
    
    @EmbeddedId
    private KeyJPA key;

    @Column(name = "TYPE", table = "OBJECT")
    private String type;
    
}
