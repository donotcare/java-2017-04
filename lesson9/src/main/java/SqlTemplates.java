
public enum SqlTemplates {
    INSERT("INSERT INTO %s (%s) VALUES (%s)"), UPDATE("UPDATE %s SET %s WHERE ID = ?"), SELECT("SELECT * FROM %s WHERE ID = ?");
    public final String template;

    SqlTemplates(String template) {
        this.template = template;
    }
}
