package google_play.flightdataui.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by Hamza khan on 8/15/2015.
 */
@ParseClassName("Customer")
public class Customer extends ParseObject {

    public Customer(){

    }

    public String getFacebookUserID() {
        return getString("facebookUserID");
    }

    public void setFacebookUserID(String facebookUserID) {
        put("facebookUserID",facebookUserID);
    }

    public String getPassword() {
        return getString("password");
    }

    public void setPassword(String password) {
        put("password", password);
    }

    public String getUserName() {
        return getString("userName");
    }

    public void setUserName(String userName) {
        put("userName", userName);
    }

    public String getLastName() {
        return getString("lastName");
    }

    public void setLastName(String lastName) {
        put("lastName",lastName);
    }

    public String geteMail() {
        return getString("eMail");
    }

    public void seteMail(String eMail) {
        put("eMail",eMail);
    }

    public String getTelephone() {
        return getString("telephone");
    }

    public void setTelephone(String telephone) {
        put("telephone",telephone);
    }

    public String getHomeAddress() {
        return getString("homeAddress");
    }

    public void setHomeAddress(String homeAddress) {
        put("homeAddress",homeAddress);
    }

    public String getStreetAddress() {
        return getString("streetAddress");
    }

    public void setStreetAddress(String streetAddress) {
        put("streetAddress",streetAddress);
    }

    public String getProvince() {
        return getString("province");
    }

    public void setProvince(String province) {
        put("province",province);
    }

    public String getCity() {
        return getString("city");
    }

    public void setCity(String city) {
        put("city",city);
    }

    public String getCountry() {
        return getString("country");
    }

    public void setCountry(String country) {
        put("country",country);
    }

    public String getEmployeeDetail() {
        return getString("employeeDetail");
    }

    public void setEmployeeDetail(String employeeDetail) {
        put("employeeDetail",employeeDetail);
    }

    public String getHomeStatus() {
        return getString("homeStatus");
    }

    public void setHomeStatus(String homeStatus) {
        put("homeStatus",homeStatus);
    }

    public String getEmployeeName() {
        return getString("employeeName");
    }

    public void setEmployeeName(String employeeName) {
        put("employeeName", employeeName);
    }

    public String getCompanyName() {
        return getString("companyName");
    }

    public void setCompanyName(String companyName) {
        put("companyName",companyName);
    }

    public Date getEmploymentDate() {
        return getDate("employmentDate");
    }

    public void setEmploymentDate(Date employmentDate) {
        put("employmentDate", employmentDate);
    }

    public String getIncome() {
        return getString("income");
    }

    public void setIncome(String income) {
        put("income",income);
    }

    public static ParseQuery<Customer> getQuery() {
        return ParseQuery.getQuery(Customer.class);
    }
}
