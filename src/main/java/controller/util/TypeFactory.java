package controller.util;

public class TypeFactory {
    private TypeFactory() {
    }

    public static final Type getTypeReq(DataType dataType) {
        switch (dataType) {

            case JSON:
                return new JSON();
            case XML:
                return new XML();
            case TEXT:
                return new TEXT();
            default:
                throw new IllegalArgumentException("This Type type is unsupported");
        }
    }
}
