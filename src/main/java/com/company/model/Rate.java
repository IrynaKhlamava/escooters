package com.company.model;

import com.company.dao.DaoException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public enum Rate {

    SUBSCRIPTION,
    HOURLY;

    public static Rate getRateByStr(String rate) {
        try {
            for (Rate rateName : Rate.values()) {
                if (rate.equals(rateName.name())) {
                    return rateName;
                }
            }
        } catch (IllegalArgumentException e) {
            log.warn("No rate value found", e);
            throw new DaoException("No rate value found", e);
        }
        return null;
    }

}
