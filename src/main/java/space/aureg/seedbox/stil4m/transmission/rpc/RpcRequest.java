package space.aureg.seedbox.stil4m.transmission.rpc;

public class RpcRequest<T extends Object> {

    private String method;

    private T arguments;

    private Long tag;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public T getArguments() {
        return arguments;
    }

    public void setArguments(T arguments) {
        this.arguments = arguments;
    }

    public Long getTag() {
        return tag;
    }

    public void setTag(Long tag) {
        this.tag = tag;
    }
}
