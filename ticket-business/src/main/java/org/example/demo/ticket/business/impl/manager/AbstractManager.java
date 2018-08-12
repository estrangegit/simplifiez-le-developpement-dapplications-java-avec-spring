package org.example.demo.ticket.business.impl.manager;

import org.example.demo.ticket.consumer.contract.DaoFactory;

import javax.inject.Inject;

public abstract class AbstractManager {
    @Inject
    private DaoFactory daoFactory;

    protected DaoFactory getDaoFactory() {
        return daoFactory;
    }
    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
}
