/*
 * ******************************************************************************
 *  Copyright Ⓒ 2016. TrinhQuan. All right reserved
 *  Author: TrinhQuan. Created on 2016/12/24
 *  Contact: trinhquan.171093@gmail.com
 * ******************************************************************************
 */

package com.jarklee.essential.common.helper;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class StringHelper {

    public static String join(@NonNull final String joiner,
                              final Object firstObject,
                              final Object secondObject,
                              final Object... objects) {
        return Joiner.on(joiner).skipNull().join(firstObject, secondObject, objects);
    }

    public static String join(@NonNull final String joiner, @NonNull final List<Object> stringList) {
        int size = stringList.size();
        if (size == 1) {
            return stringList.get(0).toString();
        } else if (size == 2) {
            return join(joiner, stringList.get(0), stringList.get(1));
        } else if (size > 2) {
            Object firstObj = stringList.get(0);
            Object secondObj = stringList.get(1);
            Object[] objects = new Object[size - 2];
            for (int i = 2; i < size; i++) {
                objects[i - 2] = stringList.get(i);
            }
            return join(joiner, firstObj, secondObj, objects);
        }
        return "";
    }

    public static boolean equal(final String lhs, final String rhs) {
        if (lhs == null && rhs == null) {
            return true;
        }
        if (lhs == null || rhs == null) {
            return false;
        }
        return lhs.equals(rhs);
    }

    public static boolean equalIgnoreCase(final String lhs, final String rhs) {
        if (lhs == null && rhs == null) {
            return true;
        }
        if (lhs == null || rhs == null) {
            return false;
        }
        return lhs.equalsIgnoreCase(rhs);
    }

    public static boolean isEmpty(final String s) {
        return s == null || s.length() == 0;
    }

    public static String stringByTrimInSet(final String s, char... trimSet) {
        if (trimSet == null || trimSet.length == 0) {
            return s;
        }
        trimSet = Arrays.copyOf(trimSet, trimSet.length);
        Arrays.sort(trimSet);
        StringBuilder builder = new StringBuilder(s);
        int stringLength = builder.length();
        while (stringLength != 0
                && Arrays.binarySearch(trimSet, builder.charAt(stringLength - 1)) >= 0) {
            builder.deleteCharAt(stringLength - 1);
            stringLength = builder.length();
        }
        if (stringLength == 0) {
            return "";
        }
        while (stringLength != 0 && Arrays.binarySearch(trimSet, builder.charAt(0)) >= 0) {
            builder.deleteCharAt(0);
            stringLength = builder.length();
        }
        return builder.toString();
    }

    private static class Joiner {

        private final Object joiner;
        private boolean isSkipNull;

        Joiner(final @NonNull Object joiner) {
            this.joiner = joiner;
            this.isSkipNull = false;
        }

        public static Joiner on(final @NonNull Object joiner) {
            return new Joiner(joiner);
        }

        Joiner skipNull() {
            isSkipNull = true;
            return this;
        }

        String join(final Object firstObject, final Object secondObject, final Object... objects) {
            final boolean includeNull = !isSkipNull;
            final StringBuilder builder = new StringBuilder("");
            final String joiner = String.valueOf(this.joiner);
            appendText(builder, joiner, firstObject, includeNull);
            appendText(builder, joiner, secondObject, includeNull);
            if (objects != null && objects.length != 0) {
                for (Object object : objects) {
                    appendText(builder, joiner, object, includeNull);
                }
            }
            if (builder.length() > 0) {
                builder.delete(builder.length() - joiner.length(), builder.length());
            }
            return builder.toString();
        }

        private void appendText(final StringBuilder builder,
                                final String joiner,
                                final Object object,
                                final boolean includeNull) {
            if (object == null && !includeNull) {
                return;
            }
            if (object == null) {
                builder.append("null").append(joiner);
            } else {
                builder.append(object).append(joiner);
            }
        }
    }
}
