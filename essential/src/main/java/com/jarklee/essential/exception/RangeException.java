/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/12/24
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.jarklee.essential.exception;

public class RangeException extends RuntimeException {
    public RangeException() {
    }

    public RangeException(String detailMessage) {
        super(detailMessage);
    }

    public RangeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RangeException(Throwable throwable) {
        this("Invalid range", throwable);
    }
}
