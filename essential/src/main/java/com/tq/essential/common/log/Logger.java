/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/12/24
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.tq.essential.common.log;

public class Logger {
    private static Log logger;

    public static void setLogger(Log logger) {
        Logger.logger = logger;
    }

    public static void l(String msg) {
        if (logger == null) {
            logger = new JavaLog();
        }
        logger.l(msg);
    }

    public static void l(String msg, Throwable e) {
        if (logger == null) {
            logger = new JavaLog();
        }
        logger.l(msg, e);
    }
}
