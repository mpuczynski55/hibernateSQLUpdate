package com.sqlupdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
@Entity
@Table(name = "OBJECT")
@SecondaryTable(name = "ELEMENT", pkJoinColumns = {
        @PrimaryKeyJoinColumn(name = "ID"),
        @PrimaryKeyJoinColumn(name = "KEY_1"),
        @PrimaryKeyJoinColumn(name = "KEY_2"),
})
@SecondaryRow(table = "ELEMENT", optional = false)
@SecondaryTable(name = "COMPONENT", pkJoinColumns = {
        @PrimaryKeyJoinColumn(name = "ID"),
        @PrimaryKeyJoinColumn(name = "KEY_1"),
        @PrimaryKeyJoinColumn(name = "KEY_2")
})
@SecondaryRow(table = "COMPONENT", optional = false)
@SQLUpdate(table = "COMPONENT", sql = AbstractObjectJPA.COMPONENT_SQL_UPDATE_QUERY)
public class ChildJPA extends AbstractObjectJPA {

    @Column(name = "LABEL", table = "ELEMENT")
    private String label;

}