package jp.hf.commons.dbutil;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import jp.hf.commons.annotation.AutoIncrement;
import jp.hf.commons.annotation.Col;
import jp.hf.commons.annotation.Pkey;

public class QueryBuilder {

    @SuppressWarnings("rawtypes")
    public static String queryInsert(Class arg) {
        Field[] fs = arg.getDeclaredFields();
        StringBuffer field = new StringBuffer();
        StringBuffer param = new StringBuffer();
        boolean first = true;
        for (Field f : fs) {
            if (f.isAnnotationPresent(Pkey.class) || f.isAnnotationPresent(Col.class)) {
                String value = null;
                if (f.isAnnotationPresent(Pkey.class)) {
                    Pkey ano = f.getAnnotation(Pkey.class);
                    value = ano.value();
                } else {
                    Col ano = f.getAnnotation(Col.class);
                    value = ano.value();
                }

                if (!first) {
                    field.append(", ");
                    param.append(", ");
                }
                // auto increment のフィールドはinsert文で生成しない
                if (!f.isAnnotationPresent(AutoIncrement.class)) {
                    field.append(fieldToColumn(f.getName(), value));
                    param.append(":").append(f.getName());
                    first = false;
                }
            }
        }

        StringBuffer ret = new StringBuffer();
        ret.append("insert into ").append(camelToSnake(arg.getSimpleName().replace("Entity", "")));
        if (!field.toString().isEmpty()) {
            ret.append(" (").append(field.toString()).append(") values (").append(param.toString())
                    .append(")");
        }

        // System.out.println(ret.toString());

        return ret.toString();
    }

    @SuppressWarnings("rawtypes")
    public static String queryUpdate(Class arg) {
        Field[] fs = arg.getDeclaredFields();
        StringBuffer key = new StringBuffer();
        StringBuffer param = new StringBuffer();
        boolean keyfirst = true;
        boolean paramfirst = true;
        for (Field f : fs) {
            if (f.isAnnotationPresent(Pkey.class)) {
                Pkey ano = f.getAnnotation(Pkey.class);
                if (!keyfirst) {
                    key.append(" and ");
                }
                key.append(fieldToColumn(f.getName(), ano.value())).append("=:")
                        .append(f.getName());
                keyfirst = false;
            } else if (f.isAnnotationPresent(Col.class)) {
                Col ano = f.getAnnotation(Col.class);
                if (!paramfirst) {
                    param.append(", ");
                }
                param.append(fieldToColumn(f.getName(), ano.value())).append("=:")
                        .append(f.getName());
                paramfirst = false;
            }
        }

        StringBuffer ret = new StringBuffer();
        ret.append("update ").append(camelToSnake(arg.getSimpleName().replace("Entity", "")));
        if (param.length() > 0) {
            ret.append(" set ").append(param.toString());
        }
        ret.append(" where ").append(key.toString());

        return ret.toString();
    }

    @SuppressWarnings("rawtypes")
    public static String queryDelete(Class arg) {
        Field[] fs = arg.getDeclaredFields();
        StringBuffer key = new StringBuffer();
        boolean keyfirst = true;
        for (Field f : fs) {
            if (f.isAnnotationPresent(Pkey.class)) {
                Pkey ano = f.getAnnotation(Pkey.class);
                if (!keyfirst) {
                    key.append(" and ");
                }
                key.append(fieldToColumn(f.getName(), ano.value())).append("=:")
                        .append(f.getName());
                keyfirst = false;
            }
        }

        StringBuffer ret = new StringBuffer();
        ret.append("delete from ").append(camelToSnake(arg.getSimpleName().replace("Entity", "")))
                .append(" where ").append(key.toString());
        return ret.toString();
    }

    public static String camelToSnake(String camelCase) {
        return StringUtils.join(camelCase.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2").split("_"), "_").toLowerCase();
    }

    private static String fieldToColumn(String field, String column) {
        if (!"".equals(column) && column != null) {
            return column;
        }
        return camelToSnake(field);
    }
}
