package com.eamtar.mccn.faces.components;

import java.io.Serializable;
import java.util.List;  
import javax.faces.model.ListDataModel;  
import org.primefaces.model.SelectableDataModel;  

import com.eamtar.mccn.model.User;
/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 29 AUG, 2014
 */  
public class UserDataModel extends ListDataModel<User> implements SelectableDataModel<User>, Serializable {    
  
    public UserDataModel() {  
    }  
  
    public UserDataModel(List<User> data) {  
        super(data);  
    }  
      
    public User getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<User> users = (List<User>) getWrappedData();  
          
        for(User user : users) {  
            if(user.getUserId().toString().equals(rowKey))  
                return user;  
        }  
          
        return null;  
    }  
  
    public Integer getRowKey(User user) {  
        return user.getUserId();  
    }  
}  