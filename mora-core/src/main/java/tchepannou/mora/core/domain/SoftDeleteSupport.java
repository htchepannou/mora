package tchepannou.mora.core.domain;

public interface SoftDeleteSupport {
    public boolean isDeleted();
    public void setDeleted(boolean deleted);
}
