package demo.util;
//com.vaadin.data.validator
//com.vaadin.data.util.converter
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.data.Validator;

public class DateValidator implements Validator {
    private static final long serialVersionUID = 6335800727861861261L;
    private String DATE_FORMAT= "yyyyMMdd";

    @Override
    public void validate(Object value) throws InvalidValueException {
        DateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        try {
            if(value!=null && value instanceof Date) {
                Date dateValue = (Date) value;
                String formatString = sdf.format(dateValue);

                Date now = new Date();
                String nowString = sdf.format(now);
                
                if(Integer.parseInt(formatString)<Integer.parseInt(nowString)) {
                    throw new InvalidValueException("日期格式需晚於系統日期！");
                }
            }
        } catch(Exception e) {
            throw new InvalidValueException("日期格式不正確！"+e.getMessage());
        }
    }

    public String getDATE_FORMAT() {
        return DATE_FORMAT;
    }

    public void setDATE_FORMAT(String dATE_FORMAT) {
        DATE_FORMAT = dATE_FORMAT;
    }
}

