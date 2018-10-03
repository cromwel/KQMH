package com.kqmh.app.kqmh.Utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A collection of utility abstractions
 */
public final class Utils {

    private Utils() {
        // no instances
    }

    /**
     * A Null-safe safeUnmodifiableList.
     *
     * @param list
     * @return
     */
    @Nullable
    public static <T> List<T> safeUnmodifiableList(@Nullable List<T> list) {
        if (list != null) {
            return Collections.unmodifiableList(list);
        }

        return null;
    }

    public static boolean isDeleted(@NonNull ObjectWithDeleteInterface object) {
        return object.deleted() != null && object.deleted();
    }

    public static <T> void isNull(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Object must not be null");
        }
    }

    @SafeVarargs
    public static <T> T[] appendInNewArray(T[] first, T... rest) {
        int totalLength = first.length + rest.length;

        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        System.arraycopy(rest, 0, result, offset, rest.length);
        return result;
    }

    public static String commaAndSpaceSeparatedArrayValues(String... values) {
        String withBrackets = Arrays.toString(values);
        return withBrackets.substring(1, withBrackets.length() - 1);
    }

    private static String commaSeparatedArrayValues(String... values) {
        return commaAndSpaceSeparatedArrayValues(values).replace(" ", "");
    }

    public static String commaSeparatedCollectionValues(Collection<String> values) {
        return commaSeparatedArrayValues(values.toArray(new String[values.size()]));
    }

    public static String joinCollectionWithSeparator(Collection<String> values, String separator) {
        return commaSeparatedCollectionValues(values).replace(",", separator);
    }

    @SuppressWarnings({"PMD.AvoidInstantiatingObjectsInLoops"})
    public static <T> List<Set<T>> setPartition(Set<T> originalSet, int size) {
        int setCount = (int) Math.ceil((double) originalSet.size() / size);
        List<Set<T>> sets = new ArrayList<>(setCount);
        for (int i = 0; i < setCount; i++) {
            Set<T> setI = new HashSet<>(size);
            sets.add(setI);
        }

        int index = 0;
        for (T object : originalSet) {
            sets.get(index++ % setCount).add(object);
        }

        return sets;
    }

    public interface ObjectWithDeleteInterface {
        Boolean deleted();
    }
}