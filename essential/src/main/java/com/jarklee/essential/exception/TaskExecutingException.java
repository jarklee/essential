/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/12/24
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.jarklee.essential.exception;

public class TaskExecutingException extends RuntimeException {
    public TaskExecutingException() {
    }

    public TaskExecutingException(String detailMessage) {
        super(detailMessage);
    }

    public TaskExecutingException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public TaskExecutingException(Throwable throwable) {
        super(throwable);
    }
}
