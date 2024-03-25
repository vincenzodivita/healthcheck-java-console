package it.contrader.enums;

public enum PackageEnum {
    USER_PACKAGE("user."), REGISTRY_PACKAGE("registry.");
    private final String packageName;

    PackageEnum(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageName() {
        return this.packageName;
    }
}


