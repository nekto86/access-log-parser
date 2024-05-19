public class logLine {
    String ip;
    String date;
    String method;

    @Override
    public String toString() {
        return "logLine{" +
                "ip='" + ip + '\'' +
                ", date='" + date + '\'' +
                ", method='" + method + '\'' +
                ", answer='" + answer + '\'' +
                ", size='" + size + '\'' +
                ", url='" + url + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }

    String answer;
    String size;
    String url;
    String userAgent;

    public String getIp() {
        return ip;
    }

    private void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    private void setMethod(String method) {
        this.method = method;
    }

    public String getAnswer() {
        return answer;
    }

    private void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSize() {
        return size;
    }

    private void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public String getUserAgent() {
        return userAgent;
    }

    private void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public logLine(String ip, String date, String method, String answer, String size, String path, String userAgent) {
        setIp(ip);
        setDate(date);
        setMethod(method);
        setAnswer(answer);
        setSize(size);
        setUrl(path);
        setUserAgent(userAgent);
    }
}
