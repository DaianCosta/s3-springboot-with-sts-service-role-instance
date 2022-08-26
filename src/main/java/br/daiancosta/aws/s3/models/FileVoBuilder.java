package br.daiancosta.aws.s3.models;

public class FileVoBuilder {

    private String key;
    private String value;

    public FileVoBuilder(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public FileVoBuilder setKey(final String key) {
        this.key = key;
        return this;
    }

    public FileVoBuilder setValue(final String value) {
        this.value = value;
        return this;
    }

    public FileVo build() {
        return new FileVo(key, value);
    }
}
