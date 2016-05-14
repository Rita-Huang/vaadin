package demo.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

/**
 * StringToTimestamp轉換器
 * 原因：
 * com.vaadin.data.util.converter.Converter$ConversionException: 
 * Unable to convert value of type java.util.Date to model type class java.sql.Timestamp. 
 * No converter is set and the types are not compatible.
 */
public class StringToTimestampConverter implements Converter<Date, Timestamp> {

	private static final long serialVersionUID = 1L;
	public static final StringToTimestampConverter INSTANCE = new StringToTimestampConverter();

	@Override
	public Timestamp convertToModel(Date value, Class<? extends Timestamp> targetType, Locale locale)
			throws ConversionException {
		return value == null ? null : new Timestamp(value.getTime());
	}

	@Override
	public Date convertToPresentation(Timestamp value, Class<? extends Date> targetType, Locale locale)
			throws ConversionException {
		return value;
	}

	@Override
	public Class<Timestamp> getModelType() {
		return Timestamp.class;
	}

	@Override
	public Class<Date> getPresentationType() {
		return Date.class;
	}

	private Object readResolve() {
		return INSTANCE; // preserves singleton property
	}

}
