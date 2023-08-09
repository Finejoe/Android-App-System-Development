package UersAndPerson;
public class users {
    private String username;
    private String pass;
    private String tele;

    public users(String username, String pass, String tele) {
        this.username = username;
        this.pass = pass;
        this.tele = tele;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getTele() {
        return tele;
    }

    public String getInsertSql() {
        StringBuffer sql = (new StringBuffer()).append("insert into users(username,pass,tele) values(")
                .append("'" + this.username + "'")
                .append(",")
                .append("'" + this.pass + "'")
                .append(",")
                .append("'" + this.tele + "'")
                .append(")");
        return sql.toString();
    }

    public String getUpdateSql() {
        String sql = "update users set ";

        if (this.pass != null) {
            sql = sql + " pass='" + this.pass + "' ";
        }

        sql = sql + "where username='" + this.username + "'";
        return sql;
    }

}
