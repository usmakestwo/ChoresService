
package com.usmakestwo.chores.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Chore {


    private Integer customerID;

    private String name;
    
    private Integer id;
    
    private Boolean completed;
    
    private String recurrent;
    

    public Chore () {
    }


    @JsonProperty("customerID")
    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }
    
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
    @JsonProperty("completed")
    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    
    
    
    @JsonProperty("recurrent")
    public String getRecurrent() {
        return recurrent;
    }

    public void setRecurrent    (String recurrent) {
        this.recurrent = recurrent;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chore Chore = (Chore) o;

        return Objects.equals(customerID, Chore.customerID) &&
         Objects.equals(name, Chore.name) &&
         Objects.equals(id, Chore.id) &&
         Objects.equals(completed, Chore.completed) &&
         Objects.equals(recurrent, Chore.recurrent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, name, id, completed,  recurrent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Chore {\n");

        sb.append("    customerID: ").append(toIndentedString(customerID)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    completed: ").append(toIndentedString(completed)).append("\n");
        sb.append("    recurrent: ").append(toIndentedString(recurrent)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
