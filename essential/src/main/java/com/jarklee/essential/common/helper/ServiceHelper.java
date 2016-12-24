/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/12/24
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.jarklee.essential.common.helper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import com.jarklee.essential.annotation.BindServiceFlag;
import com.jarklee.essential.common.Preconditions;
import com.jarklee.essential.exception.ParameterException;

public class ServiceHelper {

    public static void bindToService(Context context, Class<? extends Service> clazz,
                                     ServiceConnection connection) throws ParameterException {
        bindToService(context, clazz, connection, Context.BIND_AUTO_CREATE);
    }


    public static void bindToService(Context context, Class<? extends Service> clazz,
                                     ServiceConnection connection,
                                     @BindServiceFlag int flag) throws ParameterException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(clazz);
        Preconditions.checkNotNull(connection);
        Intent intent = new Intent(context, clazz);
        context.bindService(intent, connection, flag);
    }
}
