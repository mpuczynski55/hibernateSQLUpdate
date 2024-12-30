package com.sqlupdate;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

@QuarkusTest
class ParentRepositoryTest {

    @Inject
    ParentRepository parentRepository;

    @Test
    void test1() {
        persistParent();
    }

    @Test
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    void test2() {
        System.out.println("----QUERY AND DETACH----");
        ChildJPA childJPA = parentRepository.queryFirst();

        System.out.println("----UPDATE----");

        update(childJPA);

    }

    private ChildJPA persistParent() {
        KeyJPA keyJPA = new KeyJPA();
        keyJPA.setKey1("key1_test");
        keyJPA.setKey2(2L);

        ChildJPA childJPA = new ChildJPA();
        childJPA.setType("type_test");
        childJPA.setKey(keyJPA);

        parentRepository.persist(childJPA);
        return childJPA;
    }

    private void update(ChildJPA childJPA) {
        childJPA.setLabel("t123");
        parentRepository.update(childJPA);
    }

}